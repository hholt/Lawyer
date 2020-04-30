package com.wl.lawyer.mvp.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerOnlineConsultationComponent
import com.wl.lawyer.di.module.OnlineConsultationModule
import com.wl.lawyer.mvp.contract.OnlineConsultationContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.model.bean.HomeDataBean
import com.wl.lawyer.mvp.model.bean.OnlineConsultlationBean
import com.wl.lawyer.mvp.presenter.OnlineConsultationPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_online_consultation.*
import kotlinx.android.synthetic.main.include.*

/**
 * 在线咨询
 */
@Route(path = RouterPath.SERVICE_CONSULTATION)
class OnlineConsultationActivity : BaseSupportActivity<OnlineConsultationPresenter>(),
    OnlineConsultationContract.View {

    @Autowired(name = RouterArgs.LAWYER)
    @JvmField
    var lawyer: HomeDataBean.LawyerBean? = null

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "目前你选择的是：",
                    "古润玉律师",
                    "http://jessehuan.fun:38080/apps/files_sharing/publicpreview/a6CPDjJDYTLTaw8?x=2880&y=732&a=true&file=img_video.png&scalingup=0"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_DESC,
                    "我们针对不同的情况，定制了不同的咨询套餐，提供了不同的咨询方式和收费标准，请按照您的需要进行选择。"
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "咨询套餐：", "音视频咨询套餐"),
                CommonBean(CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT, "套餐介绍：", "请选择套餐", true, 80),
                CommonBean(CommonBean.TYPE_SIMPLE_COLOR, "价格：", "¥69/次", Color.RED),
                CommonBean(CommonBean.TYPE_SIMPLE_BUTTON, "确认选择")
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    5 -> {// 支付费用
                        mPresenter?.mAppManager?.startActivity(PayActivity::class.java)
                    }
                }
            }
        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerOnlineConsultationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .onlineConsultationModule(OnlineConsultationModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_online_consultation
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)

        tv_title.text = "在线咨询"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        mPresenter?.serviceType()
    }

    override fun initType(typeList: List<OnlineConsultlationBean.ConsultlationSetBean>) {
        lawyer?.let {
            iv_lawyer_avatar.circleImage(Api.APP_DOMAIN + it.avatar)
            et_lawyer_name.hint = "${it.username}律师"
            tv_desc.text = "我们针对不同的情况，定制了不同的咨询套餐，提供了不同的咨询方式和收费标准，请按照您的需要进行选择。"
            ArrayAdapter.createFromResource(
                this,
                R.array.planets_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spn_set.adapter = adapter
            }
        }
    }
}
