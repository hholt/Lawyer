package com.wl.lawyer.im.call

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.tencent.imsdk.TIMConversationType
import com.tencent.imsdk.TIMManager
import com.tencent.imsdk.TIMMessage
import com.tencent.imsdk.TIMValueCallBack
import com.tencent.liteav.demo.trtc.debug.GenerateTestUserSig
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil
import com.tencent.qcloud.tim.uikit.utils.DateTimeUtil
import com.tencent.qcloud.tim.uikit.utils.TUIKitLog
import com.tencent.trtc.TRTCCloud
import com.tencent.trtc.TRTCCloudListener
import com.wl.lawyer.R
import com.wl.lawyer.app.global.MyApplication
import com.wl.lawyer.app.mlog
import com.wl.lawyer.mvp.ui.activity.ChatActivity
import com.wl.lawyer.mvp.ui.activity.VideoCallActivity
import com.wl.lawyer.im.call.CustomMessage.Companion.JSON_VERSION_3_ANDROID_IOS_TRTC
import com.wl.lawyer.im.call.CustomMessage.Companion.VIDEO_CALL_ACTION_ACCEPTED
import com.wl.lawyer.im.call.CustomMessage.Companion.VIDEO_CALL_ACTION_DIALING
import com.wl.lawyer.im.call.CustomMessage.Companion.VIDEO_CALL_ACTION_HANGUP
import com.wl.lawyer.im.call.CustomMessage.Companion.VIDEO_CALL_ACTION_LINE_BUSY
import com.wl.lawyer.im.call.CustomMessage.Companion.VIDEO_CALL_ACTION_REJECT
import com.wl.lawyer.im.call.CustomMessage.Companion.VIDEO_CALL_ACTION_SPONSOR_CANCEL
import com.wl.lawyer.im.call.CustomMessage.Companion.VIDEO_CALL_ACTION_SPONSOR_TIMEOUT
import java.util.*

class CustomAVCallUIController private constructor() : TRTCCloudListener() {
    private var mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE

    val KEY_ROOM_ID = "room_id"
    private val VIDEO_CALL_OUT_GOING_TIME_OUT = 60 * 1000L
    private val VIDEO_CALL_OUT_INCOMING_TIME_OUT = 60 * 1000L
    private var mEnterRoomTime: Long = 0
    private var mOnlineCall: CustomMessage? = null
    private var mUISender: ChatLayout? = null
    private var mDialog: TRTCDialog? = null
    private var mTRTCCloud: TRTCCloud? = null
    private val mHandler = Handler()
    private val mVideoCallOutgoingTimeOut = Runnable {
        mlog("time out, dismiss outgoing dialog")
        mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
        sendVideoCallAction(VIDEO_CALL_ACTION_SPONSOR_CANCEL, mOnlineCall!!)
        dismissDialog()
    }

    private val mVideoCallIncomingTimeOut = Runnable {
        mlog("time out, dismiss incoming dialog")
        mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
        sendVideoCallAction(VIDEO_CALL_ACTION_SPONSOR_TIMEOUT, mOnlineCall!!)
        dismissDialog()
    }

    init {
        mTRTCCloud = TRTCCloud.sharedInstance(MyApplication.application)
        TRTCListener.instance.addTRTCCloudListener(this)
        mTRTCCloud!!.setListener(TRTCListener.instance)
    }

    fun onCreate() {
        mTRTCCloud = TRTCCloud.sharedInstance(MyApplication.application)
        mTRTCCloud!!.setListener(this)
    }

    override fun onError(errCode: Int, errMsg: String?, extraInfo: Bundle?) {
        mlog("trtc onError")
        mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
        sendVideoCallAction(VIDEO_CALL_ACTION_HANGUP, mOnlineCall!!)
        Toast.makeText(mUISender!!.context, "通话异常: $errMsg[$errCode]", Toast.LENGTH_LONG).show()
        if (mTRTCCloud != null) {
            mTRTCCloud!!.exitRoom()
        }
    }

    override fun onEnterRoom(elapsed: Long) {
        mlog("onEnterRoom $elapsed")
        Toast.makeText(mUISender!!.context, "开始通话", Toast.LENGTH_SHORT).show()
        mEnterRoomTime = System.currentTimeMillis()
    }

    override fun onExitRoom(reason: Int) {
        mlog("onExitRoom $reason")
        Toast.makeText(mUISender!!.context, "结束通话", Toast.LENGTH_SHORT).show()
        mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
    }

    fun setUISender(layout: ChatLayout) {
        mlog("setUISender: $layout")
        mUISender = layout
        if (mCurrentVideoCallStatus == VIDEO_CALL_STATUS_WAITING) {
            val success = showIncomingDialingDialog()
            if (success) {
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_BUSY
            } else {
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
                sendVideoCallAction(VIDEO_CALL_ACTION_REJECT, mOnlineCall!!)
                Toast.makeText(mUISender!!.context, "发起通话失败，没有弹出对话框权限", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onDraw(parent: ICustomMessageViewGroup, data: CustomMessage?) {
        // 把自定义消息view添加到TUIKit内部的父容器里
        val view = LayoutInflater.from(MyApplication.application)
            .inflate(R.layout.test_custom_message_av_layout1, null, false)
        parent.addMessageContentView(view)

        if (data == null) {
            mlog("onCalling null data")
            return
        }
        val textView = view.findViewById<TextView>(R.id.test_custom_message_tv)

        var callingAction = ""
        when (data!!.action) {
            // 新接一个电话
            VIDEO_CALL_ACTION_DIALING -> callingAction = "[请求通话]"
            VIDEO_CALL_ACTION_SPONSOR_CANCEL -> callingAction = "[取消通话]"
            VIDEO_CALL_ACTION_REJECT -> callingAction = "[拒绝通话]"
            VIDEO_CALL_ACTION_SPONSOR_TIMEOUT -> callingAction = "[无应答]"
            VIDEO_CALL_ACTION_ACCEPTED -> callingAction = "[开始通话]"
            VIDEO_CALL_ACTION_HANGUP -> callingAction =
                "[结束通话，通话时长：" + DateTimeUtil.formatSeconds(data!!.duration) + "]"
            VIDEO_CALL_ACTION_LINE_BUSY -> callingAction = "[正在通话中]"
            else -> {
                mlog("unknown data.action: " + data!!.action)
                callingAction = "[不能识别的通话指令]"
            }
        }
        textView.setText(callingAction)
    }

    fun createVideoCallRequest() {
        // 显示通话UI
        val success = showOutgoingDialingDialog()
        if (success) {
            mCurrentVideoCallStatus = VIDEO_CALL_STATUS_BUSY
            assembleOnlineCall(null)
            sendVideoCallAction(VIDEO_CALL_ACTION_DIALING, mOnlineCall!!)
            mHandler.removeCallbacksAndMessages(null)
            mHandler.postDelayed(mVideoCallOutgoingTimeOut, VIDEO_CALL_OUT_GOING_TIME_OUT)
        } else {
            Toast.makeText(mUISender!!.context, "发起通话失败，没有弹出对话框权限", Toast.LENGTH_SHORT).show()
        }
    }

    fun hangup() {
        mlog("hangup")
        mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
        sendVideoCallAction(VIDEO_CALL_ACTION_HANGUP, mOnlineCall!!)
    }

    private fun enterRoom() {
        val intent = Intent(mUISender!!.context, VideoCallActivity::class.java)
        intent.putExtra(KEY_ROOM_ID, mOnlineCall!!.room_id)
        mUISender!!.context.startActivity(intent)
    }

    fun sendVideoCallAction(action: Int, roomInfo: CustomMessage) {
        mlog(
            "sendVideoCallAction action: " + action
                    + " call_id: " + roomInfo.call_id
                    + " room_id: " + roomInfo.room_id
                    + " partner: " + roomInfo.partner
        )
        val gson = Gson()
        val message = CustomMessage()
        message.version = JSON_VERSION_3_ANDROID_IOS_TRTC
        message.call_id = roomInfo.call_id
        message.room_id = roomInfo.room_id
        message.action = action

        message.requestUser = roomInfo.requestUser
        message.roomID = roomInfo.room_id
        message.videoState = roomInfo.action
        message.invited_list = roomInfo.invited_list
        if (action == VIDEO_CALL_ACTION_HANGUP) {
            message.duration = (System.currentTimeMillis() - mEnterRoomTime + 500).toInt() / 1000L
        }
        val data = gson.toJson(message)
        val info = MessageInfoUtil.buildCustomMessage(data)
        if (TextUtils.equals(mOnlineCall!!.partner, roomInfo.partner)) {
            mlog("Send data:$info")
            mUISender!!.sendMessage(info, false)
        } else {
            val con = TIMManager.getInstance()
                .getConversation(TIMConversationType.C2C, roomInfo.partner)
            con.sendMessage(info.timMessage, object : TIMValueCallBack<TIMMessage> {

                override fun onError(code: Int, desc: String) {
                    mlog("sendMessage fail:$code=$desc")
                }

                override fun onSuccess(timMessage: TIMMessage) {
                    TUIKitLog.i(TAG, "sendMessage onSuccess")
                }
            })
        }
    }

    private fun createCallID(): String {
        val CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789"
        val random = Random()
        val sb = StringBuilder()
        sb.append(GenerateTestUserSig.SDKAPPID).append("-")
            .append(TIMManager.getInstance().loginUser).append("-")
        for (i in 0..31) {
            val index = random.nextInt(CHARS.length)
            sb.append(CHARS[index])
        }
        return sb.toString()
    }

    private fun assembleOnlineCall(roomInfo: CustomMessage?) {
        mOnlineCall = CustomMessage()
        if (roomInfo == null) {
            mOnlineCall!!.call_id = createCallID()
            mOnlineCall!!.room_id = Random().nextInt()
            mOnlineCall!!.invited_list = arrayOf(mUISender!!.chatInfo.id)
            mOnlineCall!!.partner = mUISender!!.chatInfo.id
        } else {
            mOnlineCall!!.call_id = roomInfo!!.call_id
            mOnlineCall!!.room_id = roomInfo!!.room_id
            mOnlineCall!!.invited_list = roomInfo!!.invited_list
            mOnlineCall!!.partner = roomInfo!!.partner
        }
    }

    fun onNewMessage(msgs: List<TIMMessage>) {
        val data = CustomMessage.convert2VideoCallData(msgs)
        if (data != null) {
            onNewComingCall(data!!)
        }
    }

    private fun onNewComingCall(message: CustomMessage) {
        mlog(
            "onNewComingCall current state: " + mCurrentVideoCallStatus
                    + " call_id action: " + message.action
                    + " coming call_id: " + message.call_id
                    + " coming room_id: " + message.room_id
                    + " current room_id: " + if (mOnlineCall == null) null else mOnlineCall!!.room_id
        )
        when (message.action) {
            VIDEO_CALL_ACTION_DIALING -> if (mCurrentVideoCallStatus == VIDEO_CALL_STATUS_FREE) {
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_WAITING
                startC2CConversation(message)
                assembleOnlineCall(message)
            } else {
                sendVideoCallAction(VIDEO_CALL_ACTION_LINE_BUSY, message)
            }
            VIDEO_CALL_ACTION_SPONSOR_CANCEL -> if (mCurrentVideoCallStatus != VIDEO_CALL_STATUS_FREE && TextUtils.equals(
                    message.call_id,
                    mOnlineCall!!.call_id
                )
            ) {
                dismissDialog()
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
            }
            VIDEO_CALL_ACTION_REJECT -> if (mCurrentVideoCallStatus != VIDEO_CALL_STATUS_FREE && TextUtils.equals(
                    message.call_id,
                    mOnlineCall!!.call_id
                )
            ) {
                dismissDialog()
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
            }
            VIDEO_CALL_ACTION_SPONSOR_TIMEOUT -> if (mCurrentVideoCallStatus != VIDEO_CALL_STATUS_FREE && TextUtils.equals(
                    message.call_id,
                    mOnlineCall!!.call_id
                )
            ) {
                dismissDialog()
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
            }
            VIDEO_CALL_ACTION_ACCEPTED -> {
                if (mCurrentVideoCallStatus != VIDEO_CALL_STATUS_FREE && TextUtils.equals(
                        message.call_id,
                        mOnlineCall!!.call_id
                    )
                ) {
                    dismissDialog()
                }
                assembleOnlineCall(message)
                enterRoom()
            }
            VIDEO_CALL_ACTION_HANGUP -> {
                dismissDialog()
                mTRTCCloud!!.exitRoom()
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
            }
            VIDEO_CALL_ACTION_LINE_BUSY -> if (mCurrentVideoCallStatus == VIDEO_CALL_STATUS_BUSY && TextUtils.equals(
                    message.call_id,
                    mOnlineCall!!.call_id
                )
            ) {
                dismissDialog()
            }
            else -> mlog("unknown data.action: " + message.action)
        }
    }

    private fun startC2CConversation(message: CustomMessage) {
        // 小米手机需要在安全中心里面把demo的"后台弹出权限"打开，才能当应用退到后台时弹出通话请求对话框。
        mlog("startC2CConversation " + message.partner)
        val chatInfo = ChatInfo()
        chatInfo.type = TIMConversationType.C2C
        chatInfo.id = message.partner
        chatInfo.chatName = message.partner
        val intent = Intent(MyApplication.application, ChatActivity::class.java)
        //intent.putExtra(Constants.CHAT_INFO, chatInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        MyApplication.application.startActivity(intent)
    }

    private fun showIncomingDialingDialog(): Boolean {
        dismissDialog()
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed(mVideoCallIncomingTimeOut, VIDEO_CALL_OUT_INCOMING_TIME_OUT)
        mDialog = TRTCDialog(mUISender!!.context)
        mDialog!!.setTitle("来电话了")
        mDialog!!.setPositiveButton("接听", object : View.OnClickListener {
            override fun onClick(v: View) {
                mlog("VIDEO_CALL_ACTION_ACCEPTED")
                mHandler.removeCallbacksAndMessages(null)
                sendVideoCallAction(VIDEO_CALL_ACTION_ACCEPTED, mOnlineCall!!)
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_BUSY
                enterRoom()
            }
        })
        mDialog!!.setNegativeButton("拒绝", object : View.OnClickListener {
            override fun onClick(v: View) {
                mlog("VIDEO_CALL_ACTION_REJECT")
                mHandler.removeCallbacksAndMessages(null)
                mCurrentVideoCallStatus = VIDEO_CALL_STATUS_FREE
                sendVideoCallAction(VIDEO_CALL_ACTION_REJECT, mOnlineCall!!)
            }
        })
        return mDialog!!.showSystemDialog()
    }

    private fun showOutgoingDialingDialog(): Boolean {
        dismissDialog()
        mDialog = TRTCDialog(mUISender!!.context)
        mDialog!!.setTitle("等待对方接听")
        mDialog!!.setPositiveButton("取消", object : View.OnClickListener {
            override fun onClick(v: View) {
                mlog("VIDEO_CALL_ACTION_SPONSOR_CANCEL")
                mHandler.removeCallbacksAndMessages(null)
                sendVideoCallAction(VIDEO_CALL_ACTION_SPONSOR_CANCEL, mOnlineCall!!)
            }
        })
        return mDialog!!.showSystemDialog()
    }

    private fun dismissDialog() {
        mHandler.removeCallbacksAndMessages(null)
        if (mDialog != null) {
            mDialog!!.dismiss()
        }
    }

    companion object {

        private val TAG = CustomAVCallUIController::class.java.simpleName

        private val VIDEO_CALL_STATUS_FREE = 1
        private val VIDEO_CALL_STATUS_BUSY = 2
        private val VIDEO_CALL_STATUS_WAITING = 3

        private var mController: CustomAVCallUIController? = null

        private val VIDEO_CALL_OUT_GOING_TIME_OUT = 60 * 1000
        private val VIDEO_CALL_OUT_INCOMING_TIME_OUT = 60 * 1000

        val instance: CustomAVCallUIController
            get() {
                if (mController == null) {
                    mController = CustomAVCallUIController()
                }
                return mController as CustomAVCallUIController
            }
    }

}