package com.wl.lawyer.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.di.component.DaggerEditComponent
import com.wl.lawyer.di.module.EditModule
import com.wl.lawyer.mvp.contract.EditContract
import com.wl.lawyer.mvp.presenter.EditPresenter
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.adapter_setting_4.*
import kotlinx.android.synthetic.main.include.*

/**
 * 输入编辑信息页面
 */
class EditActivity : BaseSupportActivity<EditPresenter>(), EditContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEditComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .editModule(EditModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_edit
    }

    override fun initData(savedInstanceState: Bundle?) {
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }
        mlog("Intent key ${AppConstant.INTENT_EDIT} value ${intent?.getStringExtra(AppConstant.INTENT_EDIT)}")
        when (intent?.getStringExtra(AppConstant.INTENT_EDIT)) {
            AppConstant.KEY_PERSONAL_PROFILE -> {
                tv_title?.text = "编辑个人简介"
                et_input?.hint = "介绍一下自己，150个字符以内"
                et_input?.filters = arrayOf(InputFilter.LengthFilter(150))
                btn_common?.text = "完成"
                btn_common.setOnClickListener {
                    // 设置编辑个人介绍结果
                    val intent = Intent()
                    val v = et_input?.text.toString()?.trim()
                    mlog("个人介绍:${v}")
                    intent.putExtra(AppConstant.INTENT_EDIT_VALUE, v)
                    setResult(Activity.RESULT_OK, intent)
                    ActivityUtils.finishTopActivity()
                }
            }
            AppConstant.KEY_NICKNAME -> {
                tv_title?.text = "编辑昵称"
                et_input?.hint = "请输入昵称"
                et_input?.filters = arrayOf(InputFilter.LengthFilter(20))
                btn_common?.text = "保存"
                btn_common.setOnClickListener {
                    // 设置编辑昵称结果
                    val intent = Intent()
                    val v = et_input?.text?.toString()?.trim()
                    mlog("昵称：${v}")
                    intent.putExtra(AppConstant.INTENT_EDIT_VALUE, v)
                    setResult(Activity.RESULT_OK, intent)
                    ActivityUtils.finishTopActivity()
                }
            }
            AppConstant.KEY_ADDRESS -> {
                tv_title?.text = "编辑地址"
                et_input?.hint = "请输入地址"
                et_input?.filters = arrayOf(InputFilter.LengthFilter(50))
                btn_common?.text = "保存"
                btn_common.setOnClickListener {
                    // 设置编辑昵称结果
                    val intent = Intent()
                    val v = et_input?.text?.toString()?.trim()
                    mlog("地址：${v}")
                    intent.putExtra(AppConstant.INTENT_EDIT_VALUE, v)
                    setResult(Activity.RESULT_OK, intent)
                    ActivityUtils.finishTopActivity()
                }
            }
        }
    }

}
