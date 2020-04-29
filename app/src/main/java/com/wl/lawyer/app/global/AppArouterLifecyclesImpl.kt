package com.wl.lawyer.app.global

import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.base.delegate.AppLifecycles
import com.wl.lawyer.BuildConfig

class AppArouterLifecyclesImpl: AppLifecycles {
    override fun attachBaseContext(base: Context) {
    }

    override fun onCreate(application: Application) {
        // Arouter 初始化
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }

    override fun onTerminate(application: Application) {
    }
}