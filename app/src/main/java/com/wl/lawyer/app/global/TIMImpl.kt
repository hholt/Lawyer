package com.wl.lawyer.app.global

import android.app.Application
import android.content.Context
import com.jess.arms.base.delegate.AppLifecycles
import com.tencent.qcloud.tim.uikit.TUIKit

class TIMImpl : AppLifecycles {
    override fun attachBaseContext(base: Context) {
    }

    override fun onCreate(application: Application) {
        // tuikit 初始化
//        TUIKit.init(application, 1, )
    }

    override fun onTerminate(application: Application) {
    }
}