package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.tencent.imsdk.TIMManager
import com.tencent.trtc.TRTCCloud
import com.tencent.trtc.TRTCCloudDef
import com.tencent.trtc.TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL
import com.tencent.trtc.TRTCCloudListener
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.im.GenerateTestUserSig
import com.wl.lawyer.app.mlog
import com.wl.lawyer.di.component.DaggerVideoCallComponent
import com.wl.lawyer.di.module.VideoCallModule
import com.wl.lawyer.mvp.contract.VideoCallContract
import com.wl.lawyer.mvp.presenter.VideoCallPresenter
import com.wl.lawyer.mvp.ui.activity.call.CustomAVCallUIController
import com.wl.lawyer.mvp.ui.activity.call.TRTCListener
import kotlinx.android.synthetic.main.activity_video_call.*
import java.util.*

@Route(path = RouterPath.SERVICE_CASE)
class VideoCallActivity : BaseSupportActivity<VideoCallPresenter>(), VideoCallContract.View {

    val KEY_ROOM_ID = "room_id"
    var mTRTCCloud: TRTCCloud? = null
    var mTRTCParams: TRTCCloudDef.TRTCParams? = null
    var mTRTCCloudListener: TRTCCloudListener? = object : TRTCCloudListener() {
        override fun onError(errCode: Int, errMsg: String, extraInfo: Bundle) {
            mlog("onError $errCode $errMsg")
            finish()
        }

        override fun onExitRoom(reason: Int) {
            super.onExitRoom(reason)
            mlog("onExitRoom $reason")
            finish()
        }

        override fun onRemoteUserEnterRoom(userId: String) {
            super.onRemoteUserEnterRoom(userId)
            mlog("onRemoteUserEnterRoom $userId")
        }

        override fun onRemoteUserLeaveRoom(userId: String, reason: Int) {
            super.onRemoteUserLeaveRoom(userId, reason)
            mlog("onRemoteUserLeaveRoom $userId $reason")
            // 对方超时掉线
            if (reason == 1) {
                finishVideoCall()
                finish()
            }
        }

        override fun onFirstVideoFrame(userId: String?, steamType: Int, width: Int, height: Int) {
            mlog("onFirstVideoFrame $userId $steamType $width $height")
            super.onFirstVideoFrame(userId!!, steamType, width, height)
            if (!TextUtils.equals(userId!!, TIMManager.getInstance().loginUser)) {
                val params = sub_video_layout.layoutParams
                val FIXED = 480
                params.width = FIXED
                params.height = FIXED * height / width
                sub_video_layout.layoutParams = params
                mlog("init frame  params")
            }
        }

        override fun onUserVideoAvailable(userId: String, available: Boolean) {
            super.onUserVideoAvailable(userId, available)
            mlog("onUserVideoAvailable $userId $available")
            if (available) {
                mTRTCCloud?.setRemoteViewFillMode(userId, TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FIT)
                mTRTCCloud?.startRemoteView(userId, sub_video_layout)
            }
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerVideoCallComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .videoCallModule(VideoCallModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_video_call
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun initData(savedInstanceState: Bundle?) {
        initVideoView()
    }

    private fun initVideoView() {
        iv_handup.setOnClickListener {
            mlog("Hangup click")
            finish()
            finishVideoCall()
        }
        var roomId = intent.getIntExtra(KEY_ROOM_ID, Random().nextInt())
        var sdkAppId = GenerateTestUserSig.SDKAPPID
        var userId = AppConstant.USER_ID
        var userSig = GenerateTestUserSig.genTestUserSig(userId)
        mlog("rootId:$roomId sdkAppId:$sdkAppId userId:$userId userSig:$userSig")
        mTRTCParams = TRTCCloudDef.TRTCParams(sdkAppId, userId, userSig, roomId, "", "")
        mTRTCCloud = TRTCCloud.sharedInstance(mContext)
//        mTRTCCloud?.muteLocalVideo(false)
//        mTRTCCloud?.muteLocalAudio(false)
//        mTRTCCloud?.muteAllRemoteVideoStreams(false)
        // 开始进房
        TRTCListener.instance.addTRTCCloudListener(mTRTCCloudListener)
        mTRTCCloud?.setListener(TRTCListener.instance)
        mTRTCCloud?.startLocalAudio()
        mTRTCCloud?.startLocalPreview(true, local_video_layout)
        mTRTCCloud?.enterRoom(mTRTCParams, TRTC_APP_SCENE_VIDEOCALL)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mlog("${this::class.java.simpleName} onBackPressed")
        finishVideoCall()
    }

    fun finishVideoCall() {
        CustomAVCallUIController.instance.hangup()
        mTRTCCloud?.exitRoom()
    }

    override fun onDestroy() {
        super.onDestroy()
        TRTCListener.instance.removeTRTCCloudListener(mTRTCCloudListener)
    }
}
