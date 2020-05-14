package com.wl.lawyer.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.click
import com.lxj.androidktx.core.getObject
import com.lxj.androidktx.core.sp
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportFragment
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.di.component.DaggerMyComponent
import com.wl.lawyer.di.module.MyModule
import com.wl.lawyer.mvp.contract.MyContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.SettingDataBean
import com.wl.lawyer.mvp.model.bean.UserBean
import com.wl.lawyer.mvp.presenter.MyPresenter
import com.wl.lawyer.mvp.ui.activity.ConsultingOrderActivity
import com.wl.lawyer.mvp.ui.activity.GraphicConsultationActivity
import com.wl.lawyer.mvp.ui.adapter.SettingAdapter
import kotlinx.android.synthetic.main.fragment_my.*


class MyFragment : BaseSupportFragment<MyPresenter>(), MyContract.View {

    override fun setData(data: Any?) {

    }

    companion object {
        fun newInstance(): MyFragment {
            val fragment = MyFragment()
            return fragment
        }
    }

    private val setting1Adapter by lazy {
        SettingAdapter(
            arrayListOf(
                SettingDataBean(R.drawable.ic_graphic_consultation, null, "我的图文详情", 0, null),
                SettingDataBean(R.drawable.ic_consulting_order, null, "我的咨询订单", 0, null)
            )
        ).apply {
            onItemClickListener =
                BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                    when (position) {
                        0 -> {//图文咨询详情
//                            mPresenter?.mAppManager?.startActivity(GraphicConsultationActivity::class.java)
                            ARouter.getInstance().build(RouterPath.GRAPHIC_CONSULE).navigation()
                        }
                        1 -> {//咨询订单
                            mPresenter?.mAppManager?.startActivity(ConsultingOrderActivity::class.java)
                        }
                    }
                }
        }
    }

    private val setting2Adapter by lazy {
        SettingAdapter(
            arrayListOf(
                SettingDataBean(R.drawable.ic_wallet, null, "我的钱包", 0, null),
                SettingDataBean(R.drawable.ic_feedback, null, "建议反馈", 0, null),
                SettingDataBean(R.drawable.ic_setting, null, "设置", 0, null)
            )
        ).apply {
            onItemClickListener =
                BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                    when (position) {
                        0 -> {// 我的钱包
                            ActivityUtils.goWalletActivity()
                        }
                        1 -> {// 建议反馈
                            ActivityUtils.goFeedbackActivity()
                        }
                        2 -> {// 启动设置
                            ActivityUtils.goSettingActivity()
                        }
                    }
                }
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMyComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myModule(MyModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        iv_my_avatar.circleImage("http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg")
        tv_name.text = "象律师事务所"
        tv_introduction.text = "法律明白人。。"
        constraint_avatar.setOnClickListener {
            ActivityUtils.goMyProfileActivity()
        }
        iv_new.click{
            ARouter.getInstance().build(RouterPath.CHAT_LIST).navigation()
        }
        initSetting1Adapter()
        initSetting2Adapter()
    }

    private fun initSetting1Adapter() {
        rv_my_1.layoutManager = LinearLayoutManager(mContext)
        rv_my_1.adapter = setting1Adapter
    }

    private fun initSetting2Adapter() {
        rv_my_2.layoutManager = LinearLayoutManager(mContext)
        rv_my_2.adapter = setting2Adapter
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
            .keyboardEnable(false)
            .statusBarDarkFont(false, 1f)
            .navigationBarColor(statusColor())
            .init()
    }

    override fun onResume() {
        super.onResume()
        sp().getObject<UserBean>(AppConstant.SP_USER)?.let {
            iv_my_avatar.circleImage("${Api.APP_DOMAIN}${it?.userinfo?.avatar}")
            tv_name.text = it?.userinfo?.nickname
            tv_introduction.text = it?.userinfo?.bio
        }
    }
}
