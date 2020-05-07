package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerPowerAttorneyComponent
import com.wl.lawyer.di.module.PowerAttorneyModule
import com.wl.lawyer.mvp.contract.PowerAttorneyContract
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.presenter.PowerAttorneyPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.rv_item
import kotlinx.android.synthetic.main.activity_power_attorney.*
import kotlinx.android.synthetic.main.include.*

/**
 * 委托书报价单
 */
@Route(path = RouterPath.SERVICE_CASE)
class PowerAttorneyActivity : BaseSupportActivity<PowerAttorneyPresenter>(),
    PowerAttorneyContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPowerAttorneyComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .powerAttorneyModule(PowerAttorneyModule(this))
            .build()
            .inject(this)
    }


    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "订单状态：",
                    "等待用户支付"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "承接律师：",
                    "古润玉"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "服务内容：",
                    "修订企业制度"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "服务开始时间：",
                    "2019.12.18  10:20"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "预计交付时间：",
                    "2019.12.19  10:20"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "文书页数：",
                    "30页"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "平台建议价格：",
                    "￥1500"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "律师报价：",
                    "￥1000"
                )
            )
        ).apply { }
    }

    private val btnAdapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_BUTTON,
                    "同意并前往支付"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_BUTTON_HOLLOW,
                    "回绝此报价单"
                )
            )
        ).apply { }
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_power_attorney
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "委托书报价单"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initRv()

        initBtnRv()
    }

    // 初始化委托书报价单详情
    private fun initRv() {
        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter
        RVUtils.myDivider(mContext, rv_item)
    }

    // 初始化按钮
    private fun initBtnRv() {
        rv_btn.layoutManager = LinearLayoutManager(mContext)
        rv_btn.adapter = btnAdapter

    }
}






















