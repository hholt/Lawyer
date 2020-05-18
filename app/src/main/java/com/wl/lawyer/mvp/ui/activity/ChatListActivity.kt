package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.tencent.imsdk.TIMFriendshipManager
import com.tencent.imsdk.TIMUserProfile
import com.tencent.imsdk.TIMValueCallBack
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack
import com.tencent.qcloud.tim.uikit.utils.ToastUtil
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerChatListComponent
import com.wl.lawyer.di.module.ChatListModule
import com.wl.lawyer.im.CustomConversationManagerKit
import com.wl.lawyer.im.CustomConversationProvider
import com.wl.lawyer.im.adapter.CustomConversationListAdapter
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.model.bean.ChatBean
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

    val adapter by lazy {
        CustomConversationListAdapter().apply {
            layout_rv_conversation.adapter = this
            setOnItemClickListener{ view, position, messageInfo ->
                mlog("messinifo: $messageInfo")
                ARouter.getInstance()
                    .build(RouterPath.CHAT_ACTIVITY)
                    .withInt(RouterArgs.LAWYER_ID, messageInfo.id.toInt())
                    .navigation()
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_chat_list
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "我的消息"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        mPresenter?.getUserChatList()
        initConversation()
        /*btn_send_one.click {
            sendMessage()
        }*/
        initChatList()
    }

    fun initConversation() {
        layout_rv_conversation.layoutManager = LinearLayoutManager(mContext)
    }

    private fun initChatList() {
        /*layout_conversation.titleBar.visibility = View.GONE
        layout_conversation.initDefault()
        layout_conversation.conversationList.adapter!!.setOnItemClickListener { view, position, messageInfo ->
            mlog("messinifo: $messageInfo")
            ARouter.getInstance()
                .build(RouterPath.CHAT_ACTIVITY)
                .withInt(RouterArgs.LAWYER_ID, messageInfo.id.toInt())
                .navigation()
        }*/

    }


    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this)
            .titleBar(tab_bar)
            .init()
    }

    override fun onChatListGet(data: List<ChatBean>) {
        val chatSet = data.map { it.lawyerId.toString() }.toMutableSet()
        chatSet.add("29")//测试
        CustomConversationManagerKit.getInstance().loadConversation(object : IUIKitCallBack {
            override fun onSuccess(data: Any) {
                val provider = data as CustomConversationProvider
                provider.setFilter {
                    it.filter {
                        mlog("info id: ${it.id}")
                        chatSet.contains(it.id)
                    }
                }
                adapter.setDataProvider(provider)

            }

            override fun onError(
                module: String,
                errCode: Int,
                errMsg: String
            ) {
                ToastUtil.toastLongMessage("加载消息失败")
            }
        })

    }

}