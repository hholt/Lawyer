package com.wl.lawyer.app.global

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.hjq.image.ImageLoader
import com.hjq.toast.ToastUtils
import com.jess.arms.base.delegate.AppLifecycles
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.imsdk.TIMMessage
import com.tencent.imsdk.TIMSdkConfig
import com.tencent.qcloud.tim.uikit.TUIKit
import com.tencent.qcloud.tim.uikit.base.IMEventListener
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig
import com.tencent.qcloud.tim.uikit.config.GeneralConfig
import com.tencent.rtmp.TXLiveBase
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.mlog
import com.wl.lawyer.im.call.CustomAVCallUIController


class AppLifecyclesImpl : AppLifecycles {

    override fun attachBaseContext(base: Context) {
        MultiDex.install(base)
    }

    override fun onCreate(application: Application) {
        Thread.setDefaultUncaughtExceptionHandler(DefaultCrashHandler.instance)
        TXLiveBase.setConsoleEnabled(true)
        MyApplication.application = application
        //bugly
        var strategy: CrashReport.UserStrategy = CrashReport.UserStrategy(application)
        strategy.setAppVersion(TXLiveBase.getSDKVersionStr())
        CrashReport.initCrashReport(application, strategy)

        ToastUtils.init(application)
        //腾讯IM
        var configs = TUIKit.getConfigs()
        configs.sdkConfig = TIMSdkConfig(AppConstant.TENCENT_IM_SDK_ID)
        configs.customFaceConfig = CustomFaceConfig()
        configs.generalConfig = GeneralConfig()
        TUIKit.init(application, AppConstant.TENCENT_IM_SDK_ID, configs)
        // 腾讯直播
        TXLiveBase.getInstance().setLicence(
            application,
            AppConstant.TENCENT_LIVE_LICENCE_URL,
            AppConstant.TENCENT_LIVE_LICENCE_KEY
        )
        //
        CustomAVCallUIController.instance.onCreate()
        val imEventListener = object : IMEventListener() {
            override fun onNewMessages(msgs: MutableList<TIMMessage>?) {
                mlog(msgs.toString())
                CustomAVCallUIController.instance.onNewMessage(msgs!!)
            }
        }
        TUIKit.addIMEventListener(imEventListener)
        ImageLoader.init(application)
    }

    override fun onTerminate(application: Application) {

    }

}