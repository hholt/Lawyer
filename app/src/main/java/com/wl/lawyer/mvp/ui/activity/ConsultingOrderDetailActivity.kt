package com.wl.lawyer.mvp.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerConsultingOrderDetailComponent
import com.wl.lawyer.di.module.ConsultingOrderDetailModule
import com.wl.lawyer.mvp.contract.ConsultingOrderDetailContract
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.presenter.ConsultingOrderDetailPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.*
import kotlinx.android.synthetic.main.include.*

/**
 * 咨询订单详情
 */
class ConsultingOrderDetailActivity : BaseSupportActivity<ConsultingOrderDetailPresenter>(),
    ConsultingOrderDetailContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerConsultingOrderDetailComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .consultingOrderDetailModule(ConsultingOrderDetailModule(this))
            .build()
            .inject(this)
    }

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "咨询订单：",
                    "古润玉",
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "咨询套餐：",
                    "音视频咨询套餐"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "开始时间：",
                    "2019.12.18  10:20"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "结束时间：",
                    "2019.12.18  10:20"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_COLOR,
                    "付费金额：",
                    "50元",
                    Color.RED
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_COLOR,
                    "当前状态：",
                    "咨询中",
                    Color.RED
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_BUTTON,
                    "立即前往沟通"
                )
            )
        ).apply { }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_consulting_order_detail
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "咨询订单详情"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initRv()
    }

    private fun initRv() {
        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter
        RVUtils.myDivider(mContext, rv_item)
    }
}




















