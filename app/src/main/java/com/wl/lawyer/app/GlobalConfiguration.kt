package com.wl.lawyer.app

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.base.delegate.AppLifecycles
import com.jess.arms.di.module.GlobalConfigModule
import com.jess.arms.integration.ConfigModule
import com.wl.lawyer.app.global.*
import com.wl.lawyer.mvp.model.api.Api

class GlobalConfiguration : ConfigModule {
    override fun injectFragmentLifecycle(
        context: Context,
        lifecycles: MutableList<FragmentManager.FragmentLifecycleCallbacks>
    ) {

    }

    override fun applyOptions(context: Context, builder: GlobalConfigModule.Builder) {
        // 后台API地址
        builder.baseurl(Api.APP_DOMAIN)
        // 全局网络处理配置
        builder.globalHttpHandler(GlobalHttpHandlerImpl())
        // Response响应错误处理
        builder.responseErrorListener(ResponseErrorListenerImpl())
    }

    override fun injectAppLifecycle(context: Context, lifecycles: MutableList<AppLifecycles>) {
        lifecycles.add(AppLifecyclesImpl())
        // 第三方库组件初始化（建议每一个库新建一个单独的AppLifecycles）
        lifecycles.add(AppLifecyclesThirdPartyImpl())
        lifecycles.add(AppArouterLifecyclesImpl())
    }

    override fun injectActivityLifecycle(
        context: Context,
        lifecycles: MutableList<Application.ActivityLifecycleCallbacks>
    ) {

    }

}