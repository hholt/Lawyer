package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerFeedbackComponent
import com.wl.lawyer.di.module.FeedbackModule
import com.wl.lawyer.mvp.contract.FeedbackContract
import com.wl.lawyer.mvp.model.bean.SettingDataBean
import com.wl.lawyer.mvp.presenter.FeedbackPresenter
import com.wl.lawyer.mvp.ui.adapter.SettingAdapter
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.include.*


class FeedbackActivity : BaseSupportActivity<FeedbackPresenter>(), FeedbackContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerFeedbackComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .feedbackModule(FeedbackModule(this))
            .build()
            .inject(this)
    }

    private val adapter:SettingAdapter by lazy {
        SettingAdapter(
            arrayListOf(
                SettingDataBean("反馈内容",SettingDataBean.SETTING_TYPE_3,"在此输入反馈内容",ArmsUtils.dip2px(mContext,200f)),
                SettingDataBean("提交反馈",SettingDataBean.SETTING_TYPE_4)
            )
        ).apply {

        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_feedback
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "建议反馈"
        iv_back.setOnClickListener {
            mPresenter?.mAppManager?.onBack()
        }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter
    }

}
