package com.wl.lawyer.mvp.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.image
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerLawyerCooperationComponent
import com.wl.lawyer.di.module.LawyerCooperationModule
import com.wl.lawyer.mvp.contract.LawyerCooperationContract
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.model.bean.LawyerBean
import com.wl.lawyer.mvp.presenter.LawyerCooperationPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.*
import kotlinx.android.synthetic.main.include.*

/**
 * 律师合作
 */
@Route(path = RouterPath.SERVICE_COOPER)
class LawyerCooperationActivity : BaseSupportActivity<LawyerCooperationPresenter>(),
    LawyerCooperationContract.View {

    @Autowired(name = RouterArgs.LAWYER)
    @JvmField
    var lawyer: LawyerBean? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLawyerCooperationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .lawyerCooperationModule(LawyerCooperationModule(this))
            .build()
            .inject(this)
    }

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "目前你选择的是：",
                    "古润玉律师",
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT,
                    "请选择您的地址和联系方式",
                    "王某某        18152041076\n" +
                            "广东省深圳市宝安区中熙ECO国际",
                    false, 70
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "服务类型：", "律师陪同谈判、纠纷处理"),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT,
                    "服务解释：",
                    "您可以在输入框简单描述服务",
                    true,
                    80
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_COLOR, "服务费用：", "￥1000/次", Color.RED),
                CommonBean(CommonBean.TYPE_SIMPLE_BUTTON, "立即预约")
            )
        ).apply {

        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_lawyer_cooperation
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)

        tv_title.text = "律师合作"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initParamsRv()
        initRightShare()
    }

    // 设置head分享按钮
    private fun initRightShare() {
        iv_right.visibility = View.VISIBLE
        iv_right.image(R.drawable.ic_share_it)
        iv_right.setOnClickListener { }
    }

    private fun initParamsRv() {
        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter
        RVUtils.myDivider(mContext, rv_item)
    }

}
