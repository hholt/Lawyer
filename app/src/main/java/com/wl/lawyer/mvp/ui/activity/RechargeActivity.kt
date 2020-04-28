package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerRechargeComponent
import com.wl.lawyer.di.module.RechargeModule
import com.wl.lawyer.mvp.contract.RechargeContract
import com.wl.lawyer.mvp.model.bean.MoneyConfigBean
import com.wl.lawyer.mvp.model.bean.PaymentMethodBean
import com.wl.lawyer.mvp.presenter.RechargePresenter
import com.wl.lawyer.mvp.ui.adapter.PaymentMethodAdapter
import com.wl.lawyer.mvp.ui.adapter.RechargeAmountAdapter
import kotlinx.android.synthetic.main.activity_recharge.*
import kotlinx.android.synthetic.main.include.*

/**
 * 充值页面
 */
class RechargeActivity : BaseSupportActivity<RechargePresenter>(), RechargeContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerRechargeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .rechargeModule(RechargeModule(this))
            .build()
            .inject(this)
    }

    private val adapter by lazy {
        RechargeAmountAdapter(null).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                currentPosition = position
                notifyDataSetChanged()
            }
        }
    }

    private val paymentAmountAdapter by lazy {
        PaymentMethodAdapter(null).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                currentPosition = position
                notifyDataSetChanged()
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_recharge
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "充值"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_item.layoutManager = GridLayoutManager(mContext, 3)
        rv_item.adapter = adapter

        rv_payment_method.layoutManager = LinearLayoutManager(mContext)
        rv_payment_method.adapter = paymentAmountAdapter

        btn_common.setOnClickListener {
            mPresenter?.recharge()
        }

        mPresenter?.getMoneyConfig()
    }

    override fun moneyConfig(msg: String?, moneyConfigBean: MoneyConfigBean?) {
        updateRechargeMoneyConfigAdp(moneyConfigBean)
        updateRechargeTypeAdp(moneyConfigBean)
    }

    private fun updateRechargeTypeAdp(moneyConfigBean: MoneyConfigBean?) {
        val payTypes = ArrayList<PaymentMethodBean>()
        moneyConfigBean?.payTypeList?.let {
            for (payType in it) {
                payTypes.add(
                    when (payType?.value) {
                        AppConstant.PAY_WECHAT -> {
                            PaymentMethodBean(
                                R.drawable.ic_payment_wechat,
                                "微信支付",
                                payType.isDefaultX
                            )
                        }
                        AppConstant.PAY_ALIPAY -> {
                            PaymentMethodBean(
                                R.drawable.ic_payment_alipay,
                                "支付宝支付",
                                payType.isDefaultX
                            )
                        }
                        else -> {
                            PaymentMethodBean(
                                R.drawable.ic_payment_wechat,
                                "微信支付",
                                payType.isDefaultX
                            )
                        }
                    }
                )
            }
        }
        paymentAmountAdapter.setNewData(payTypes)
    }

    private fun updateRechargeMoneyConfigAdp(moneyConfigBean: MoneyConfigBean?) {
        val moneys = ArrayList<Int>()
        mlog("${moneyConfigBean.toString()}")
        moneyConfigBean?.moneyList?.let {
            for (money in it) {
                moneys.add(money?.value?.toInt() ?: 0)
            }
        }
        adapter.setNewData(moneys)
    }

    override fun getRechargeCount(): Double {
        return 0.1
    }

    override fun getRechargeType(): String {
        return "alipay"
    }

}