package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.toTime
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.di.component.DaggerWalletComponent
import com.wl.lawyer.di.module.WalletModule
import com.wl.lawyer.mvp.contract.WalletContract
import com.wl.lawyer.mvp.model.bean.BalanceDetailBean
import com.wl.lawyer.mvp.model.bean.RechargeBean
import com.wl.lawyer.mvp.presenter.WalletPresenter
import com.wl.lawyer.mvp.ui.adapter.BalanceAdapter
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.include.*

/**
 * 我的钱包
 */
class WalletActivity : BaseSupportActivity<WalletPresenter>(), WalletContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerWalletComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .walletModule(WalletModule(this))
            .build()
            .inject(this)
    }

    private val adapter: BalanceAdapter by lazy {
        BalanceAdapter(
            arrayListOf(
                BalanceDetailBean("微信充值余额", "2019-10-19 14:50", "+500"),
                BalanceDetailBean("微信充值余额", "2019-10-19 14:50", "+500"),
                BalanceDetailBean("微信充值余额", "2019-10-19 14:50", "+500"),
                BalanceDetailBean("微信充值余额", "2019-10-19 14:50", "+500")
            )
        ).apply {

        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_wallet
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "我的钱包"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter

        tv_recharge.setOnClickListener {
            ActivityUtils.goRechargeActivity()
        }

        mPresenter?.rechargeList(null, null)
    }

    override fun rechargeList(msg: String?, rechargeBean: RechargeBean?) {
        rechargeBean?.amount?.let { tv_balance.text = it }
        rechargeBean?.logList?.let {
            val datas = ArrayList<BalanceDetailBean>()
            for (bean in it) {
                // 计算充值金额还是消费金额
                val difference =
                    ((bean.after?.toDouble() ?: 0.0) - (bean.before?.toDouble() ?: 0.0))
                val balance = if (difference > 0) {
                    "+${difference}"
                } else {
                    "-${difference}"
                }
                datas.add(BalanceDetailBean(bean?.memo ?: "", bean.createtime.toTime(), balance))
            }
            adapter.setNewData(datas)
        }
    }

}