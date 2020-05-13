package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerLawyerArticleComponent
import com.wl.lawyer.di.module.LawyerArticleModule
import com.wl.lawyer.mvp.contract.LawyerArticleContract
import com.wl.lawyer.mvp.presenter.LawyerArticlePresenter

class LawyerArticleActivity: BaseSupportActivity<LawyerArticlePresenter>(),
    LawyerArticleContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLawyerArticleComponent
            .builder()
            .appComponent(appComponent)
            .lawyerArticleModule(LawyerArticleModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_lawyer_article
    }

    override fun initData(savedInstanceState: Bundle?) {
    }


}