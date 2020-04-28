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
import com.wl.lawyer.di.component.DaggerClericalCollaborationComponent
import com.wl.lawyer.di.module.ClericalCollaborationModule
import com.wl.lawyer.mvp.contract.ClericalCollaborationContract
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.presenter.ClericalCollaborationPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.*
import kotlinx.android.synthetic.main.include.*

/**
 * 文书协作
 */
class ClericalCollaborationActivity : BaseSupportActivity<ClericalCollaborationPresenter>(),
    ClericalCollaborationContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerClericalCollaborationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .clericalCollaborationModule(ClericalCollaborationModule(this))
            .build()
            .inject(this)
    }

    private val desc = "1、支付定金后，系统将g即将你的订单派发至指定律，优推荐，如平小时丙无律师接革，将会自动追款至您的余额中。\n\n" +
            "2、县体价格造与律师商议，参考价格仅为建议，实际价格请以律师报价为准。\n\n" +
            "3、如订单达成，定金在支付订单费用时自动抵扣，订单未达成，则定金作为前期服务费，不予退款。\n\n" +
            "4、律师有权不回答与代写文书无关的法律问题。\n\n" +
            "5、文书付款半小时内申请退款的，可退款，超过半小时的，不子追款.\n\n" +
            "6、文书出现三次或以上明显法律错误的，可退款。\n\n" +
            "7、文书首次交付延时的，可退款：若因为用户未能即时交付。"

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
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "舒服类型：", "文书处理"),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "文书类型：", "文书"),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "协助方式：", "审查"),
                CommonBean(CommonBean.TYPE_SIMPLE, "参考价格：", "¥299/次"),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT,
                    "备注：",
                    "您可以在输入框简单描述文书服务需求",
                    true,
                    80
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_COLOR, "价格：", "¥30/次", Color.RED),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_DESC,
                    "为更方便快捷的让律师浏览审查文书，请上传文书源文件，目前仅支持doc、docx两种形式。"
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_FILE, "选择文件", "未选择任何文件"),
                CommonBean(CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT, "说明：", desc, false, 350),
                CommonBean(CommonBean.TYPE_SIMPLE_BUTTON, "支付下单")
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    5 -> {// 支付费用
                        mPresenter?.mAppManager?.startActivity(PayActivity::class.java)
                    }
                    11 -> {//
                        mPresenter?.mAppManager?.startActivity(OrderStatusActivity::class.java)
                    }
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_clerical_collaboration
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "文书协作"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter

        RVUtils.myDivider(mContext, rv_item)

    }

}