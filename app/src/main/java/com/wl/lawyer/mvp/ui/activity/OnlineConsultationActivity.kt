package com.wl.lawyer.mvp.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerOnlineConsultationComponent
import com.wl.lawyer.di.module.OnlineConsultationModule
import com.wl.lawyer.mvp.contract.OnlineConsultationContract
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.presenter.OnlineConsultationPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.*
import kotlinx.android.synthetic.main.include.*

/**
 * 在线咨询
 */
class OnlineConsultationActivity : BaseSupportActivity<OnlineConsultationPresenter>(),
    OnlineConsultationContract.View {

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "目前你选择的是：",
                    "古润玉律师",
                    "http://jessehuan.fun:38080/apps/files_sharing/publicpreview/a6CPDjJDYTLTaw8?x=2880&y=732&a=true&file=img_video.png&scalingup=0"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_DESC,
                    "我们针对不同的情况，定制了不同的咨询套餐，提供了不同的咨询方式和收费标准，请按照您的需要进行选择。"
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "咨询套餐：", "音视频咨询套餐"),
                CommonBean(CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT, "套餐介绍：", "请选择套餐", true, 80),
                CommonBean(CommonBean.TYPE_SIMPLE_COLOR, "价格：", "¥69/次", Color.RED),
                CommonBean(CommonBean.TYPE_SIMPLE_BUTTON, "确认选择")
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    5 -> {// 支付费用
                        mPresenter?.mAppManager?.startActivity(PayActivity::class.java)
                    }
                }
            }
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerOnlineConsultationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .onlineConsultationModule(OnlineConsultationModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_online_consultation
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "在线咨询"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter

        RVUtils.myDivider(mContext, rv_item)

    }

}
