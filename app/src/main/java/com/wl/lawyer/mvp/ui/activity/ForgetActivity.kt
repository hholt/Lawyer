package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerForgetComponent
import com.wl.lawyer.di.module.ForgetModule
import com.wl.lawyer.mvp.contract.ForgetContract
import com.wl.lawyer.mvp.presenter.ForgetPresenter
import kotlinx.android.synthetic.main.activity_quick_register.*
import kotlinx.android.synthetic.main.include.*

/**
 * 忘记密码
 */
class ForgetActivity : BaseSupportActivity<ForgetPresenter>(), ForgetContract.View,
    View.OnClickListener {

    var currentPosition = 0

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerForgetComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .forgetModule(ForgetModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_forget
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "找回密码"
        btn_common.setOnClickListener(this)
        iv_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            iv_back.id -> {
                mPresenter?.mAppManager?.onBack()
            }
            btn_common.id -> {
                setWidgetVisibility(++currentPosition)
            }
        }
    }
    private fun setWidgetVisibility(position: Int) {
        when (position) {
            0 -> {
                //register_1.visibility = View.VISIBLE
                register_2.visibility = View.GONE
                register_3.visibility = View.GONE
                btn_common.text = "立即短信验证"
            }
            1 -> {
                //register_1.visibility = View.GONE
                register_2.visibility = View.VISIBLE
                register_3.visibility = View.GONE
                btn_common.text = "提交验证码"
            }
            2 -> {
                //register_1.visibility = View.GONE
                register_2.visibility = View.GONE
                register_3.visibility = View.VISIBLE
                btn_common.text = "确认提交"
            }
            else -> {
//                currentPosition = 0
            }
        }

        tv_status.next()
    }
}
