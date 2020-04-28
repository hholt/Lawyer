package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.isPhone
import com.lxj.androidktx.core.putObject
import com.lxj.androidktx.core.sp
import com.vondear.rxtool.RxKeyboardTool
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerQuickRegisterComponent
import com.wl.lawyer.di.module.QuickRegisterModule
import com.wl.lawyer.mvp.contract.QuickRegisterContract
import com.wl.lawyer.mvp.model.api.Type
import com.wl.lawyer.mvp.model.bean.UserBean
import com.wl.lawyer.mvp.presenter.QuickRegisterPresenter
import kotlinx.android.synthetic.main.activity_quick_register.*
import kotlinx.android.synthetic.main.include.*
import kotlinx.android.synthetic.main.include_register_1.*
import kotlinx.android.synthetic.main.include_register_2.*
import kotlinx.android.synthetic.main.include_register_3.*

/**
 * 手机号码快速注册账号
 */
class QuickRegisterActivity : BaseSupportActivity<QuickRegisterPresenter>(),
    QuickRegisterContract.View, View.OnClickListener {

    // 当前手机号码
    lateinit var mobile: String
    lateinit var code: String
    private var isForget: Boolean = false

    var currentPosition = 0

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerQuickRegisterComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .quickRegisterModule(QuickRegisterModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_quick_register
    }

    override fun initData(savedInstanceState: Bundle?) {
        btn_common.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        intent?.getBooleanExtra(AppConstant.INTENT_FORGET_OR_QUICK_REGISTER, false)?.let {
            isForget = it
        }
        if (isForget) {
            tv_title.text = "找回密码"
        }
        setWidgetVisibility(-1)
    }

    override fun onClick(v: View?) {
        RxKeyboardTool.hideSoftInput(mContext)
        when (v?.id) {
            iv_back.id -> {
                mPresenter?.mAppManager?.onBack()
            }
            btn_common.id -> {
                setWidgetVisibility(currentPosition)
            }
        }
    }

    private fun setWidgetVisibility(position: Int) {
        when (position) {
            0 -> {// 输入手机号 获取验证码
                et_phone?.text?.trim()?.let {
                    if (it.isNullOrBlank()) {
                        showMessage("手机号码不能为空")
                        return
                    }
                    if (it.toString().isPhone()) {
                        // 发送验证码
                        if (isForget) {
                            // 重置密码
                            mPresenter?.sendSms(it.toString(), Type.RESET_PWD)
                        } else {
                            // 注册
                            mPresenter?.sendSms(it.toString(), Type.REGISTER)
                        }
                        mlog("手机号:$it")
                        mobile = it.toString()
                    } else {
                        showMessage("请输入11位手机号码")
                    }
                }
            }
            1 -> {// 验证验证码
                et_auto_code?.text?.trim()?.let {
                    if (it.isNullOrBlank()) {
                        showMessage("验证码不能为空")
                        return
                    }
                    if (it.toString().length != 4) {
                        showMessage("验证码格式不正确")
                        return
                    } else {
                        code = it.toString()
                        if (isForget) {
                            mPresenter?.checkSms(mobile, code, Type.RESET_PWD)
                        } else {
                            mPresenter?.checkSms(mobile, code, Type.REGISTER)
                        }
                    }
                }
            }
            2 -> {// 设置密码
                var pwd: String? = null
                var pwdConfirm: String? = null
                RxKeyboardTool.hideSoftInput(mContext)
                et_psw?.text?.trim()?.let {
                    if (it.isNullOrBlank()) {
                        showMessage("密码不能为空")
                        return
                    }
                    if (it.toString().length >= 6 || it.toString().length <= 16) {
                        pwd = it.toString()
                    } else {
                        showMessage("密码长度为[6-16]")
                        return
                    }
                }
                et_psw_confirm?.text?.trim()?.let {
                    if (it.isNullOrBlank()) {
                        showMessage("密码不能为空")
                        return
                    }
                    if (it.toString().length >= 6 || it.toString().length <= 16) {
                        pwdConfirm = it.toString()
                    } else {
                        showMessage("密码长度为[6-16]")
                        return
                    }
                }
                pwd?.let {
                    pwdConfirm?.let { p ->
                        if (it == p) {
                            if (isForget) {
                                mPresenter?.resetPwd(mobile, it, code, Type.MOBILE)
                            } else {
                                mPresenter?.registerUser(mobile, code, it)
                            }
                        } else {
                            showMessage("两次输入密码不一致")
                        }
                    }
                }
            }
            else -> {
                // 默认显示第一个界面
                register_1.visibility = VISIBLE
                register_2.visibility = GONE
                register_3.visibility = GONE
                btn_common.text = "获取验证码"
            }
        }
    }

    // 验证验证码成功
    override fun checkSmsSuccess(msg: String?) {
        // 马上显示第三个设置密码界面
        register_1.visibility = GONE
        register_2.visibility = GONE
        register_3.visibility = VISIBLE
        btn_common.text = "确认提交"
        tv_status.next()
        currentPosition++
    }

    // 发送验证码成功
    override fun sendSmsSuccess(msg: String?) {
        // 马上显示第二输入验证码界面
        register_1.visibility = GONE
        register_2.visibility = VISIBLE
        register_3.visibility = GONE
        tv_auto_code.text = "验证短信已发送到$mobile"
        btn_common.text = "提交验证码"
        tv_status.next()
        currentPosition++
    }

    override fun registerUserSuccess(msg: String?, userBean: UserBean?) {
        msg?.let {
            showMessage(it)
        }
        userBean?.let {
            sp().putObject(AppConstant.SP_USER, userBean)
        }
    }
}
