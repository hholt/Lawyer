package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.onStartActivityWithSingleTop
import com.wl.lawyer.di.component.DaggerAccountSecurityComponent
import com.wl.lawyer.di.module.AccountSecurityModule
import com.wl.lawyer.mvp.contract.AccountSecurityContract
import com.wl.lawyer.mvp.model.bean.SettingDataBean
import com.wl.lawyer.mvp.presenter.AccountSecurityPresenter
import com.wl.lawyer.mvp.ui.adapter.SettingAdapter
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.include.*


class AccountSecurityActivity : BaseSupportActivity<AccountSecurityPresenter>(),
    AccountSecurityContract.View {

    private val settingAdapter by lazy {
        SettingAdapter(
            arrayListOf(
                SettingDataBean(null, null, "修改密码", SettingDataBean.SETTING_TYPE_1, null),
                SettingDataBean(null, null, "修改手机", SettingDataBean.SETTING_TYPE_1, null)
            )
        ).apply {
            onItemClickListener =
                BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                    when (position) {
                        0 -> { // 忘记密码
                            mPresenter?.mAppManager?.onStartActivityWithSingleTop(ForgetActivity::class.java)
                        }
                        1 -> { // 修改手机号码
                            mPresenter?.mAppManager?.onStartActivityWithSingleTop(ModifyPhoneActivity::class.java)
                        }
                    }
                }
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAccountSecurityComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .accountSecurityModule(AccountSecurityModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_account_security
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "账号安全"
        iv_back.setOnClickListener {
            mPresenter?.mAppManager?.onBack()
        }

        rv_item.layoutManager = LinearLayoutManager(this)
        rv_item.adapter = settingAdapter
    }

}
