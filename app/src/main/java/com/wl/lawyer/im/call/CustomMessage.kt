package com.wl.lawyer.im.call

import com.google.gson.Gson
import com.tencent.imsdk.TIMConversationType
import com.tencent.imsdk.TIMCustomElem
import com.tencent.imsdk.TIMMessage
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.mlog


/**
 * 自定义消息的bean实体，用来与json的相互转化
 */
class CustomMessage {

    var partner = ""

    internal var text = "欢迎加入云通信IM大家庭！"
    internal var link = "https://cloud.tencent.com/document/product/269/3794"

    /**
     * 1: 仅仅是一个带链接的文本消息
     * 2: iOS支持的视频通话版本，后续已经不兼容
     * 3: Android/iOS/Web互通的视频通话版本
     */
    internal var version = 1
    /**
     * 表示一次通话的唯一ID
     */
    internal var call_id: String? = null
    internal var room_id = 0
    internal var action = VIDEO_CALL_ACTION_UNKNOWN
    internal var videoState = VIDEO_CALL_ACTION_UNKNOWN
    internal var duration = 0L

    internal var requestUser = AppConstant.USER_ID
    internal var roomID = 0
    /**
     * 群组时需要添加邀请人，接受者判断自己是否在邀请队列来决定是否加入通话
     */
    internal var invited_list: Array<String>? = null

    companion object {

        private val TAG = CustomMessage::class.java.simpleName

        val VIDEO_CALL_ACTION_UNKNOWN = -1
        /** 正在呼叫 */
        val VIDEO_CALL_ACTION_DIALING = 0
        /** 发起人取消   */
        val VIDEO_CALL_ACTION_SPONSOR_CANCEL = 1
        /** 拒接电话  */
        val VIDEO_CALL_ACTION_REJECT = 2
        /** 无人接听   */
        val VIDEO_CALL_ACTION_SPONSOR_TIMEOUT = 3
        /** 连接进入通话  */
        val VIDEO_CALL_ACTION_ACCEPTED = 4
        /** 挂断  */
        val VIDEO_CALL_ACTION_HANGUP = 5
        /** 电话占线  */
        val VIDEO_CALL_ACTION_LINE_BUSY = 6

        val JSON_VERSION_1_HELLOTIM = 1
        val JSON_VERSION_2_ONLY_IOS_TRTC = 2
        val JSON_VERSION_3_ANDROID_IOS_TRTC = 3
        val JSON_VERSION_FEE = 11

        // 一个欢迎提示富文本
        val HELLO_TXT = 1
        // 视频通话
        val VIDEO_CALL = 2

        fun convert2VideoCallData(msgs: List<TIMMessage>?): CustomMessage? {
            if (null == msgs || msgs.size == 0) {
                return null
            }
            for (msg in msgs) {
                val conversation = msg.conversation
                val type = conversation.type
                if (type != TIMConversationType.C2C) {
                    continue
                }
                val list = MessageInfoUtil.TIMMessage2MessageInfo(msg, false)
                for (info in list) {
                    if (info.msgType !== MessageInfo.MSG_TYPE_CUSTOM) {
                        continue
                    }
                    // 获取到自定义消息的json数据
                    if (info.element !is TIMCustomElem) {
                        continue
                    }
                    val elem = info.element as TIMCustomElem
                    // 自定义的json数据，需要解析成bean实例
                    var data: CustomMessage? = null
                    try {
                        data = Gson().fromJson(String(elem.data), CustomMessage::class.java)
                    } catch (e: Exception) {
                        mlog("invalid json: " + String(elem.data) + " " + e.message)
                    }

                    if (data == null) {
                        mlog("No Custom Data: " + String(elem.data))
                        continue
                    }
                    if (data.version == JSON_VERSION_3_ANDROID_IOS_TRTC ||data.version == JSON_VERSION_2_ONLY_IOS_TRTC ){
                        data.partner = info.fromUser
                    }else
                    {
                        mlog("unsupport ${data.version}")
                        continue
                    }
                    return data
                }
            }
            return null
        }
    }
}
