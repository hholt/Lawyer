package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerPopularizationArticleComponent
import com.wl.lawyer.di.module.PopularizationArticleModule
import com.wl.lawyer.mvp.contract.PopularizationArticleContract
import com.wl.lawyer.mvp.presenter.PopularizationArticlePresenter

class PopularizationArticleActivity: BaseSupportActivity<PopularizationArticlePresenter>(),
PopularizationArticleContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPopularizationArticleComponent
            .builder()
            .appComponent(appComponent)
            .popularizationArticleModule(PopularizationArticleModule(this))
            .build()
            .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_popularization_article
    }

    override fun initData(savedInstanceState: Bundle?) {
    }
}