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
import com.wl.lawyer.di.component.DaggerPayComponent
import com.wl.lawyer.di.module.PayModule
import com.wl.lawyer.mvp.contract.PayContract
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.model.bean.PaymentMethodBean
import com.wl.lawyer.mvp.presenter.PayPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import com.wl.lawyer.mvp.ui.adapter.PaymentMethodAdapter
import com.wl.lawyer.mvp.ui.dialog.PaySuccessDialog
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.android.synthetic.main.include.*

/**
 * 支付费用
 */
class PayActivity : BaseSupportActivity<PayPresenter>(), PayContract.View {

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "目前你选择的是：",
                    "古润玉律师",
                    "http://jessehuan.fun:38080/apps/files_sharing/publicpreview/a6CPDjJDYTLTaw8?x=2880&y=732&a=true&file=img_video.png&scalingup=0"
                ),
                CommonBean(CommonBean.TYPE_SIMPLE, "目前你选择的套餐是：", "音视频咨询套餐"),
                CommonBean(CommonBean.TYPE_SIMPLE_COLOR, "价格：", "¥69/次", Color.RED)
            )
        ).apply {

        }
    }


    private val paymentAmountAdapter by lazy {
        PaymentMethodAdapter(
            arrayListOf(
                PaymentMethodBean(R.drawable.ic_payment_wechat, "微信支付", true),
                PaymentMethodBean(R.drawable.ic_payment_alipay, "支付宝支付", false)
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                currentPosition = position
                notifyDataSetChanged()
            }
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPayComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .payModule(PayModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pay
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "支付费用"
        btn_common.text = "支付费用"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initRv1()
        initRv2()
        btn_common.setOnClickListener {
            // 显示支付成功页面
            PaySuccessDialog.show(this)
        }
    }

    private fun initRv1() {
        rv_item_1.layoutManager = LinearLayoutManager(mContext)
        rv_item_1.adapter = adapter

        RVUtils.myDivider(mContext, rv_item_1)
    }

    private fun initRv2() {
        rv_item_2.layoutManager = LinearLayoutManager(mContext)
        rv_item_2.adapter = paymentAmountAdapter
    }

}
