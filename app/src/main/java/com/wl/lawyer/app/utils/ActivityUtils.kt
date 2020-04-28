package com.wl.lawyer.app.utils

import android.content.Intent
import com.jess.arms.integration.AppManager
import com.lxj.androidktx.core.getObject
import com.lxj.androidktx.core.putObject
import com.lxj.androidktx.core.sp
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.onStartActivityWithSingleTop
import com.wl.lawyer.app.startActivityForResult
import com.wl.lawyer.mvp.model.bean.UserBean
import com.wl.lawyer.mvp.ui.activity.*

/**
 * Activity工具类
 */
class ActivityUtils {

    companion object {

        private fun onStartActivityWithSingleTop(activityClass: Class<*>) {
            AppManager.getAppManager()?.onStartActivityWithSingleTop(activityClass)
        }

        private fun onStartActivityWithSingleTop(intent: Intent) {
            AppManager.getAppManager()?.onStartActivityWithSingleTop(intent)
        }

        fun finishTopActivity() {
            AppManager.getAppManager()?.topActivity?.let {
                it.finish()
            }
        }

        // 快速注册界面
        fun goQuickRegisterActivity() {
            onStartActivityWithSingleTop(QuickRegisterActivity::class.java)
        }

        // 忘记密码界面
        fun goForgetRegisterActivity() {
            AppManager.getAppManager()?.topActivity?.let {
                val intent = Intent(it, QuickRegisterActivity::class.java)
                intent.putExtra(AppConstant.INTENT_FORGET_OR_QUICK_REGISTER, true)
                onStartActivityWithSingleTop(intent)
            }
        }

        // 跳转主页面
        fun goMainActivity() {
            AppManager.getAppManager()?.onStartActivityWithSingleTop(MainActivity::class.java)
            finishTopActivity()
        }

        // 个人资料
        fun goMyProfileActivity() {
            AppManager.getAppManager()
                ?.onStartActivityWithSingleTop(PersonalInformationActivity::class.java)
        }

        // 登录
        fun goLoginActivity() {
            // 清空用户信息中的token
            sp().apply {
                val userBean = getObject<UserBean>(AppConstant.SP_USER)
                userBean?.let {
                    it.userinfo?.token = null
                    putObject(AppConstant.SP_USER, it)
                }
            }
            AppManager.getAppManager()?.onStartActivityWithSingleTop(LoginActivity::class.java)
            AppManager.getAppManager().killAll()
        }

        // 设置
        fun goSettingActivity() {
            AppManager.getAppManager()?.onStartActivityWithSingleTop(SettingActivity::class.java)
        }

        // 意见反馈
        fun goFeedbackActivity() {
            AppManager.getAppManager()?.onStartActivityWithSingleTop(FeedbackActivity::class.java)
        }

        // 钱包
        fun goWalletActivity() {
            AppManager.getAppManager()?.onStartActivityWithSingleTop(WalletActivity::class.java)
        }

        // 编辑昵称
        fun goEditNicknameActivity() {
            AppManager.getAppManager()?.apply {
                topActivity?.let {
                    val intent = Intent(it, EditActivity::class.java)
                    intent.putExtra(AppConstant.INTENT_EDIT, AppConstant.KEY_NICKNAME)
                    startActivityForResult(intent, AppConstant.REQUEST_CODE_NICKNAME)
                }
            }
        }

        // 编辑个人信息
        fun goEditProfileActivity() {
            AppManager.getAppManager()?.apply {
                topActivity?.let {
                    val intent = Intent(it, EditActivity::class.java)
                    intent.putExtra(AppConstant.INTENT_EDIT, AppConstant.KEY_PERSONAL_PROFILE)
                    startActivityForResult(intent, AppConstant.REQUEST_CODE_PERSONAL_PROFILE)
                }
            }
        }

        // 输入地址信息
        fun goEditAddressActivity() {
            AppManager.getAppManager()?.apply {
                topActivity?.let {
                    val intent = Intent(it, EditActivity::class.java)
                    intent.putExtra(AppConstant.INTENT_EDIT, AppConstant.KEY_ADDRESS)
                    startActivityForResult(intent, AppConstant.REQUEST_CODE_ADDRESS)
                }
            }
        }

        // 余额充值
        fun goRechargeActivity() {
            AppManager.getAppManager()?.onStartActivityWithSingleTop(RechargeActivity::class.java)
        }
    }
}