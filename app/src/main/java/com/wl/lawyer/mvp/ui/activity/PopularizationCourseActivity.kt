package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerPopularizationCourseComponent
import com.wl.lawyer.di.module.PopularizationCourseModule
import com.wl.lawyer.mvp.contract.PopularizationCourseContract
import com.wl.lawyer.mvp.model.bean.PopularizationCourseBean
import com.wl.lawyer.mvp.presenter.PopularizationCoursePresenter
import com.wl.lawyer.mvp.ui.adapter.PopularizationCourseAdapter
import kotlinx.android.synthetic.main.activity_graphic_consultation.*
import kotlinx.android.synthetic.main.include.*

/**
 * 普法课程
 */
class PopularizationCourseActivity : BaseSupportActivity<PopularizationCoursePresenter>(),
    PopularizationCourseContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPopularizationCourseComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .popularizationCourseModule(PopularizationCourseModule(this))
            .build()
            .inject(this)
    }

    private val adapter by lazy {
        PopularizationCourseAdapter(
            arrayListOf(
                PopularizationCourseBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "李立刚委员讲解《劳动法》",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                PopularizationCourseBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "李立刚委员讲解《劳动法》",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                PopularizationCourseBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "李立刚委员讲解《劳动法》",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                PopularizationCourseBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "李立刚委员讲解《劳动法》",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                PopularizationCourseBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "李立刚委员讲解《劳动法》",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                )
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                mPresenter?.mAppManager?.startActivity(PopularizationCourseDetailsActivity::class.java)
            }// 跳转详情
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_popularization_course
    }

    override fun initData(savedInstanceState: Bundle?) {

        tv_title.text = when (intent?.getStringExtra(AppConstant.INTENT_POPULARIZATION)) {
            AppConstant.KEY_POPULARIZATION -> {
                "法律课程"
            }
            AppConstant.KEY_LIVE -> {
                "直播普法"
            }
            else -> {
                "法律课程"
            }
        }

        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter
    }

}