package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.image
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerOrderStatusComponent
import com.wl.lawyer.di.module.OrderStatusModule
import com.wl.lawyer.mvp.contract.OrderStatusContract
import com.wl.lawyer.mvp.presenter.OrderStatusPresenter
import kotlinx.android.synthetic.main.activity_order_status.*
import kotlinx.android.synthetic.main.include.*

/**
 * 订单状态
 */
class OrderStatusActivity : BaseSupportActivity<OrderStatusPresenter>(), OrderStatusContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerOrderStatusComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .orderStatusModule(OrderStatusModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_order_status
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "订单状态"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        tv_desc.text = "您的订单已经被承接，下方是承接律师信息，律师将会通过平台信息与您联系，请注意消息提醒。"
        tv_name.text = "古润玉"
        tv_time.text = "执业五年"
        tv_address.text = "所在地：广东省深圳市宝安区西乡"
        iv_avatar.image("http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg")

        btn_common.setOnClickListener { }
    }

}
