package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.click
import com.wl.lawyer.R
import com.wl.lawyer.app.*
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerPayComponent
import com.wl.lawyer.di.module.PayModule
import com.wl.lawyer.mvp.contract.PayContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.*
import com.wl.lawyer.mvp.presenter.PayPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import com.wl.lawyer.mvp.ui.adapter.PaymentMethodAdapter
import com.wl.lawyer.mvp.ui.dialog.PaySuccessDialog
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.android.synthetic.main.include.*

/**
 * 支付费用
 */
@Route(path = RouterPath.ORDER_COMFIRM)
class PayActivity : BaseSupportActivity<PayPresenter>(), PayContract.View {

    @Autowired(name = RouterArgs.SERVICE_TYPE)
    @JvmField
    var type: Int = 0

    @Autowired(name = RouterArgs.LAWYER)
    @JvmField
    var lawyer: LawyerBean? = null

    @Autowired(name = RouterArgs.CONSULT_ORDER)
    @JvmField
    var consultOrder: ConsultOrderBean? = null

    @Autowired(name = RouterArgs.CLERICAL_ORDER)
    @JvmField
    var clericalOrder: ClericalOrderBean? = null

    @Autowired(name = RouterArgs.COOPERATE_ORDER)
    @JvmField
    var cooperateOrder: CooperateOrderBean? = null

    var payMethod: String = AppConstant.PAY_WECHAT

    private val adapter by lazy {

        CommonAdapter(
            arrayListOf(
                /*CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "目前你选择的是：",
                    "古润玉律师",
                    "http://jessehuan.fun:38080/apps/files_sharing/publicpreview/a6CPDjJDYTLTaw8?x=2880&y=732&a=true&file=img_video.png&scalingup=0"
                ),
                CommonBean(CommonBean.TYPE_SIMPLE, "目前你选择的套餐是：", "音视频咨询套餐"),
                CommonBean(CommonBean.TYPE_SIMPLE_COLOR, "价格：", "¥69/次", ContextCompat.getColor(this, R.color.app_font_red))*/
            )
        ).apply {

        }
    }


    private val paymentAmountAdapter by lazy {
        PaymentMethodAdapter(
            arrayListOf(

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
        ARouter.getInstance().inject(this)

        tv_title.text = "支付费用"
        btn_common.text = "支付费用"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initRv1()
        initRv2()

        /*rbn_wechat_selected.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                rbn_alipay_selected.isChecked = false
                payMethod = AppConstant.PAY_WECHAT
            }
        }
        rbn_alipay_selected.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                rbn_wechat_selected.isChecked = false
                payMethod = AppConstant.PAY_ALIPAY
            }
        }
        rbn_wechat_selected.isChecked = true*/

        mPresenter?.getPayType()
    }

    private fun initRv1() {
        rv_item_1.layoutManager = LinearLayoutManager(mContext)
        rv_item_1.adapter = adapter
        rv_item_1.isNestedScrollingEnabled = false
        rv_item_1.isFocusable = false
        var data = arrayListOf<CommonBean>()
        lawyer?.let {
            data.add(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "目前你选择的是：",
                    "${it.nickname}律师",
                    Api.APP_DOMAIN + it.avatar
                )
            )
        }
        when (type) {
            AppConstant.SERVICE_ID_CONSULTATION -> {
                consultOrder?.let {
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE,
                            "目前你选择的套餐是：",
                            "${it.name}"
                        )
                    )
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE_COLOR,
                            "价格：",
                            "¥${it.payAmount}/次",
                            ContextCompat.getColor(this, R.color.app_font_red)
                        )
                    )
                }
            }
            AppConstant.SERVICE_ID_COOPERATION -> {
                cooperateOrder?.let {
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE,
                            "目前你选择的套餐是：",
                            "${it.serviceName}"
                        )
                    )
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE_COLOR,
                            "服务费用（不含差旅费）：",
                            "¥$it.servicePrice/次",
                            ContextCompat.getColor(this, R.color.app_font_red)
                        )
                    )
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE_COLOR,
                            "律师申报差旅费用：",
                            "¥${it.otherPrice}/次",
                            ContextCompat.getColor(this, R.color.app_font_red)
                        )
                    )
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE_COLOR,
                            "价格：",
                            "¥${it.totalPrice}/次",
                            ContextCompat.getColor(this, R.color.app_font_red)
                        )
                    )
                }
            }
            AppConstant.SERVICE_ID_COLLABORATION -> {
                clericalOrder?.let {
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE,
                            "目前你选择的套餐是：",
                            "${it.specs}"
                        )
                    )
                    data.add(
                        CommonBean(
                            CommonBean.TYPE_SIMPLE_COLOR,
                            "价格：",
                            "¥${it.totalPrice}/次",
                            ContextCompat.getColor(this, R.color.app_font_red)
                        )
                    )
                }
            }
        }
        adapter.setNewData(data)

        RVUtils.myDivider(mContext, rv_item_1)
    }


    private fun initRv2() {
        rv_item_2.layoutManager = LinearLayoutManager(mContext)
        rv_item_2.adapter = paymentAmountAdapter
        rv_item_2.isNestedScrollingEnabled = false
        rv_item_2.isFocusable = false
    }

    override fun onPayTypeGet(payTypeList: List<PayTypeBean>) {

        paymentAmountAdapter.setNewData(payTypeList)
        btn_common.click {
            when (type) {
                AppConstant.SERVICE_ID_CONSULTATION -> {
                    consultOrder?.apply {
                        mPresenter?.payConsultOrder(id)
                    }
                }
                AppConstant.SERVICE_ID_COLLABORATION -> {
                    clericalOrder?.apply {
                        mPresenter?.payClericalOrder(id)
                    }
                }
                AppConstant.SERVICE_ID_COOPERATION -> {
                    cooperateOrder?.apply {
                        mPresenter?.payCooperateOrder(id)
                    }
                }
            }
        }
    }

    override fun getSelectPayType() = paymentAmountAdapter.getSelectPayType()

    override fun onConsultOrderPay(payOrderBean: PayOrderBean<RealConsultOrderBean>) {
        if (payOrderBean.order.status == AppConstant.ORDER_STATUS_PAID) {
            showDialag("现在前往咨询页面"){
                mlog("跳转聊天")
            }
        }
    }

    override fun onClericalOrderPay(payOrderBean: PayOrderBean<RealClericalOrderBean>) {
        showDialag("查看状态"){
            mlog("跳转查看状态")
        }
    }

    override fun onCooperateOrderPay(payOrderBean: PayOrderBean<RealCooperateOrderBean>) {
        showDialag("查看状态"){
            mlog("跳转查看状态")
        }
    }

}
