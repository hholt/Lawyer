package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.tencent.imsdk.TIMCallBack
import com.tencent.imsdk.TIMManager
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerChatListComponent
import com.wl.lawyer.di.module.ChatListModule
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.model.bean.TencentUserSignatureBean
import com.wl.lawyer.mvp.presenter.ChatListPresenter
import kotlinx.android.synthetic.main.include.*

@Route(path = RouterPath.CHAT_LIST)
class ChatListActivity : BaseSupportActivity<ChatListPresenter>(), ChatListContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerChatListComponent.builder()
            .appComponent(appComponent)
            .chatListModule(ChatListModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_chat_list
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "我的消息"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        mPresenter?.getUserSignature()

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this)
            .titleBar(tab_bar)
            .init()
    }

    override fun onSignatureGet(userBean: TencentUserSignatureBean) {
        TIMManager.getInstance().login(userBean.nickname, userBean.sig, object : TIMCallBack {
            override fun onSuccess() {
                mlog("im login")
            }

            override fun onError(p0: Int, p1: String?) {
            }
        })
    }
}