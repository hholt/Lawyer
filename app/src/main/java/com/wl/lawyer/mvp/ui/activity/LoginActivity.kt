package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.getObject
import com.lxj.androidktx.core.sp
import com.vondear.rxtool.RxAppTool
import com.vondear.rxtool.RxKeyboardTool
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.di.component.DaggerLoginComponent
import com.wl.lawyer.di.module.LoginModule
import com.wl.lawyer.mvp.contract.LoginContract
import com.wl.lawyer.mvp.model.bean.UserBean
import com.wl.lawyer.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录界面
 */
@Route(path = RouterPath.LOGIN_ACTIVITY)
class LoginActivity : BaseSupportActivity<LoginPresenter>(), LoginContract.View,
    View.OnClickListener {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .loginModule(LoginModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initData(savedInstanceState: Bundle?) {
        iv_qq.setOnClickListener(this)
        iv_wechat.setOnClickListener(this)
        iv_weibo.setOnClickListener(this)
        tv_quick_register.setOnClickListener(this)
        tv_forget.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            iv_qq.id -> if (RxAppTool.isInstallApp(this, AppConstant.PACKAGE_NAME_QQ)) {

            } else {
                showMessage("未安装QQ")
            }
            iv_wechat.id -> if (RxAppTool.isInstallApp(this, AppConstant.PACKAGE_NAME_WECHAT)) {

            } else {
                showMessage("未安装微信")
            }
            iv_weibo.id -> if (RxAppTool.isInstallApp(this, AppConstant.PACKAGE_NAME_WEIBO)) {

            } else {
                showMessage("未安装微博")
            }
            tv_quick_register.id -> {// 快速注册
                ActivityUtils.goQuickRegisterActivity()
            }
            tv_forget.id -> {// 忘记密码
                ActivityUtils.goForgetRegisterActivity()
            }
            btn_login.id -> {// 登录
                mPresenter?.login()
            }
        }
    }

    override fun accountUser(): String? {
        RxKeyboardTool.hideSoftInput(mContext)
        et_account?.text?.toString()?.trim()?.let {
            return if (it.length >= 6) {
                it
            } else {
                showMessage("用户名长度最少为6位")
                null
            }
        }
        return null
    }

    override fun passwordUser(): String? {
        RxKeyboardTool.hideSoftInput(mContext)
        et_password?.text?.toString()?.trim()?.let {
            return if (it.length >= 6) {
                it
            } else {
                showMessage("密码长度最少为6位")
                null
            }
        }
        return null
    }

    override fun onResume() {
        super.onResume()
        val userBean = sp().getObject<UserBean>(AppConstant.SP_USER)
        userBean?.userinfo?.username?.let {
            et_account.setText(it.toString())
        }
    }
}
