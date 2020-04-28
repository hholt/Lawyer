package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.vondear.rxtool.RxAppTool
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.app.utils.GlideUtils
import com.wl.lawyer.di.component.DaggerSettingComponent
import com.wl.lawyer.di.module.SettingModule
import com.wl.lawyer.mvp.contract.SettingContract
import com.wl.lawyer.mvp.model.bean.SettingDataBean
import com.wl.lawyer.mvp.presenter.SettingPresenter
import com.wl.lawyer.mvp.ui.adapter.SettingAdapter
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.include.*

/**
 * 设置界面
 */
class SettingActivity : BaseSupportActivity<SettingPresenter>(), SettingContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSettingComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .settingModule(SettingModule(this))
            .build()
            .inject(this)
    }


    private val settingAdapter by lazy {
        SettingAdapter(
            arrayListOf(
                SettingDataBean(null, null, "账号安全", SettingDataBean.SETTING_TYPE_1, null),
                SettingDataBean(
                    null,
                    null,
                    "检查更新",
                    SettingDataBean.SETTING_TYPE_1,
                    "当前版本${RxAppTool.getAppVersionCode(mContext)}"
                ),
                SettingDataBean(
                    null,
                    null,
                    "清除缓存",
                    SettingDataBean.SETTING_TYPE_1,
                    "${GlideUtils.getCacheSize(mContext)}"
                ),
                SettingDataBean("退出登录", SettingDataBean.SETTING_TYPE_4)
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                mlog("Item Count ${data.size} Click position $position")
                when (position) {
                    0 -> {
                        mPresenter?.mAppManager?.startActivity(AccountSecurityActivity::class.java)
                    }
                    3 -> {// 退出登录
                        ActivityUtils.goLoginActivity()
                    }
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_setting
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "设置"
        iv_back.setOnClickListener {
            mPresenter?.mAppManager?.onBack()
        }
        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = settingAdapter
    }

}