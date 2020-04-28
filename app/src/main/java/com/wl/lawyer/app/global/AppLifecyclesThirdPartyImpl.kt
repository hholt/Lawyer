package com.wl.lawyer.app.global

import android.app.Application
import android.content.Context
import com.jess.arms.base.delegate.AppLifecycles
import com.lxj.androidktx.AndroidKtxConfig
import com.wl.lawyer.BuildConfig

/**
 * 第三方库初始化
 */
class AppLifecyclesThirdPartyImpl : AppLifecycles {

    override fun attachBaseContext(base: Context) {

    }

    override fun onCreate(application: Application) {
        AndroidKtxConfig.init(
            application,
            BuildConfig.DEBUG,
            "androidktx-lawyer",
            "androidktx-lawyer"
        )
    }

    override fun onTerminate(application: Application) {
    }

}