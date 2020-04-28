package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerModifyPhoneComponent
import com.wl.lawyer.di.module.ModifyPhoneModule
import com.wl.lawyer.mvp.contract.ModifyPhoneContract
import com.wl.lawyer.mvp.presenter.ModifyPhonePresenter
import kotlinx.android.synthetic.main.adapter_setting_4.*
import kotlinx.android.synthetic.main.include.*

/**
 * 修改手机号
 */
class ModifyPhoneActivity : BaseSupportActivity<ModifyPhonePresenter>(), ModifyPhoneContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerModifyPhoneComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .modifyPhoneModule(ModifyPhoneModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_modify_phone
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "修改手机"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }
        btn_common.text = "确定修改"

    }

}
