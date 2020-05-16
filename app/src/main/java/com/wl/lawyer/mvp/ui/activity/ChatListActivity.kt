package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.click
import com.tencent.imsdk.*
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerChatListComponent
import com.wl.lawyer.di.module.ChatListModule
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.presenter.ChatListPresenter
import kotlinx.android.synthetic.main.activity_chat_list.*
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

        mPresenter
        btn_send_one.click {
            sendMessage()
        }
        initChatList()
    }

    private fun initChatList() {
        layout_conversation.titleBar.visibility = View.GONE
        layout_conversation.initDefault()
        layout_conversation.conversationList.adapter!!.setOnItemClickListener { view, position, messageInfo ->
            mlog("messinifo: $messageInfo")
            ARouter.getInstance()
                .build(RouterPath.CHAT_ACTIVITY)
                .withInt(RouterArgs.LAWYER_ID, messageInfo.id.toInt())
                .navigation()
        }

    }

    fun sendMessage() {
        val conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, "27")
        val size = TIMManager.getInstance().conversationList.size
        mlog("$size")

        val elem = TIMTextElem()
        elem.text = "11111111"

        val message = TIMMessage()
        if (message.addElement(elem) != 0) {
            mlog("add fail")
        }

        conversation.sendMessage(message, object : TIMValueCallBack<TIMMessage> {
            override fun onSuccess(p0: TIMMessage?) {
                mlog("send ok")
            }

            override fun onError(p0: Int, p1: String?) {
                mlog("send fail $p1")
            }

        })
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this)
            .titleBar(tab_bar)
            .init()
    }

}