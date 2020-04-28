package com.wl.lawyer.mvp.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.vondear.rxtool.RxBarTool
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.image
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerLawyerDetailsComponent
import com.wl.lawyer.di.module.LawyerDetailsModule
import com.wl.lawyer.mvp.contract.LawyerDetailsContract
import com.wl.lawyer.mvp.presenter.LawyerDetailsPresenter
import kotlinx.android.synthetic.main.activity_lawyer_details.*
import kotlinx.android.synthetic.main.include.*
import kotlinx.android.synthetic.main.include_status_bar.*

/**
 * 律师详情页
 */
class LawyerDetailsActivity : BaseSupportActivity<LawyerDetailsPresenter>(),
    LawyerDetailsContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLawyerDetailsComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .lawyerDetailsModule(LawyerDetailsModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_lawyer_details
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "律师详情"
        tv_title.setTextColor(Color.WHITE)
        iv_right.visibility = View.VISIBLE
        dividing_line.visibility = View.GONE
        status_bar.layoutParams.height = RxBarTool.getStatusBarHeight(mContext)
        iv_right.image(R.drawable.ic_share_it)
        iv_back.image(R.drawable.ic_back)
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }
        iv_top.image("http://jessehuan.fun:38080/s/a6CPDjJDYTLTaw8/preview")

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this)
            .statusBarDarkFont(false, 0f)
            .init()
    }

}
