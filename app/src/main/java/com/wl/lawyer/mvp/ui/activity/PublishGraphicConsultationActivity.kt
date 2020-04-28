package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerPublishGraphicConsultationComponent
import com.wl.lawyer.di.module.PublishGraphicConsultationModule
import com.wl.lawyer.mvp.contract.PublishGraphicConsultationContract
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.presenter.PublishGraphicConsultationPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.*
import kotlinx.android.synthetic.main.include.*

/**
 * 发布图文咨询
 */
class PublishGraphicConsultationActivity :
    BaseSupportActivity<PublishGraphicConsultationPresenter>(),
    PublishGraphicConsultationContract.View {

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "标题：",
                    "请输入咨询标题",
                    true
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT, "相关咨询：", "请输入详细情况", true, 100),
                CommonBean(CommonBean.TYPE_SIMPLE_RECORD, "长按说话语音转文字更方便"),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "分类：", "民事婚姻"),
                CommonBean(CommonBean.TYPE_SIMPLE_FILE_IMAGE, "补充照片文件"),
                CommonBean(CommonBean.TYPE_SIMPLE_DESC, "我们会将您的问题通知给擅长本专业的律师"),
                CommonBean(CommonBean.TYPE_SIMPLE_BUTTON, "支付下单")
            )
        ).apply {

        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPublishGraphicConsultationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .publishGraphicConsultationModule(PublishGraphicConsultationModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_publish_graphic_consultation
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "发布图文咨询"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter

        RVUtils.myDivider(mContext, rv_item)

    }

}
