package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerSplashComponent
import com.wl.lawyer.di.module.SplashModule
import com.wl.lawyer.mvp.contract.SplashContract
import com.wl.lawyer.mvp.presenter.SplashPresenter


class SplashActivity : BaseSupportActivity<SplashPresenter>(), SplashContract.View {


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .splashModule(SplashModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.checkPermission()
    }

}
