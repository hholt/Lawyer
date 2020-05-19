package com.wl.lawyer.mvp.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerConsultingOrderDetailComponent
import com.wl.lawyer.di.module.ConsultingOrderDetailModule
import com.wl.lawyer.mvp.contract.ConsultingOrderDetailContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.ChatBean
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import com.wl.lawyer.mvp.presenter.ConsultingOrderDetailPresenter
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.*
import kotlinx.android.synthetic.main.include.*

/**
 * 咨询订单详情
 */
@Route(path = RouterPath.CONSULT_ORDER_DETAIL)
class ConsultingOrderDetailActivity : BaseSupportActivity<ConsultingOrderDetailPresenter>(),
    ConsultingOrderDetailContract.View {

    @Autowired(name = RouterArgs.CONSULT_ORDER)
    @JvmField
    var order: MyConsultOrderBean? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerConsultingOrderDetailComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .consultingOrderDetailModule(ConsultingOrderDetailModule(this))
            .build()
            .inject(this)
    }

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf()
        ).apply { }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_consulting_order_detail
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        tv_title.text = "咨询订单详情"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initRv()
        mPresenter?.getOrderDetail(order!!.id)
    }

    private fun initRv() {
        rv_item.layoutManager = LinearLayoutManager(mContext)
        RVUtils.myDivider(mContext, rv_item)
    }

    private fun updateAdapter() {


    }

    override fun onOrderGet(orderBean: MyConsultOrderBean) {
        order = orderBean
        orderBean.apply {
            adapter.setNewData(arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "咨询律师：",
                    "$lawyerName",
                    Api.APP_DOMAIN + lawyerAvatar
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "咨询套餐：",
                    "${packageType.name}"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "开始时间：",
                    "$startTimeText"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE,
                    "结束时间：",
                    "$endTimeText"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_COLOR,
                    "付费金额：",
                    "${payAmount}元",
                    ContextCompat.getColor(mContext, R.color.app_font_red)
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_COLOR,
                    "当前状态：",
                    "$statusText",
                    ContextCompat.getColor(mContext, R.color.app_font_red)
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_BUTTON,
                    "立即前往沟通"
                )
            ))
            adapter.apply {

                setOnItemChildClickListener { adapter, view, position ->
                    if (view.id == R.id.btn_common) {
                        showMessage("前往沟通页")
                        order?.let {
                        mPresenter?.addUserChat(it.id.toString(), it.inviteLawyerId, 1)//正式使用lawyerId ，测试使用inviteawyerId

                        }
                    }
                }
                rv_item.adapter = this
            }
        }
    }

    override fun onChatAdded(chatBean: ChatBean) {
        order?.let {
            ARouter.getInstance()
                .build(RouterPath.CHAT_ACTIVITY)
                .withSerializable(RouterArgs.CONSULT_ORDER, it)
                .withSerializable(RouterArgs.CHAT, chatBean)
                .navigation()
        }
    }
}




















