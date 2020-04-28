package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerGCDetailsComponent
import com.wl.lawyer.di.module.GCDetailsModule
import com.wl.lawyer.mvp.contract.GCDetailsContract
import com.wl.lawyer.mvp.presenter.GCDetailsPresenter
import kotlinx.android.synthetic.main.activity_gcdetails.*
import kotlinx.android.synthetic.main.include.*

/**
 * 图文咨询详情 Graphic Consultation Details
 */
class GCDetailsActivity : BaseSupportActivity<GCDetailsPresenter>(), GCDetailsContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerGCDetailsComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .gCDetailsModule(GCDetailsModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_gcdetails
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "图文咨询详情"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        tv_gc_title.text = "张某与李某1、李某2婚约财产纠纷案"
        tv_gc_desc.text =
            "刑事辩护律师就被告人涉嫌诈骗罪，面临10年以上有期或无期徒刑，成功辩护，最终终以合同诈骗罪判处5年。案情简介：" +
                    "被告人吴某因涉嫌诈骗80余万元被检察机关提起公诉。公诉机关指控其犯罪行为构成诈骗罪。刑法第二百六十六" +
                    "条规定，诈骗公私财物，数额特别巨大或者有其他特别严重情节的，处十年以上有期徒刑或者无期徒刑，并处罚金或者没收财产。"
    }

}