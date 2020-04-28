package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.tencent.imsdk.TIMConversationType
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo
import com.tencent.qcloud.tim.uikit.modules.chat.layout.inputmore.InputMoreActionUnit
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.di.component.DaggerChatComponent
import com.wl.lawyer.di.module.ChatModule
import com.wl.lawyer.mvp.contract.ChatContract
import com.wl.lawyer.mvp.presenter.ChatPresenter
import com.wl.lawyer.mvp.ui.activity.call.ChatLayoutHelper
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : BaseSupportActivity<ChatPresenter>(), ChatContract.View {
    override fun post(runnable: Runnable?) {

    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerChatComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .chatModule(ChatModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_chat
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTencentIM()
    }

    private fun initTencentIM() {
        chat_layout.initDefault()
        var chatInfo = ChatInfo()
        chatInfo.type = TIMConversationType.C2C
        chatInfo.id = AppConstant.FRIEND_ID
        chatInfo.chatName = AppConstant.FRIEND_ID
        chat_layout.chatInfo = chatInfo

        var tabBarLayout = chat_layout.findViewById<TitleBarLayout>(R.id.chat_title_bar).also {
            it.setRightIcon(R.drawable.ic_contact)
            it.setLeftIcon(R.drawable.ic_back_black)
        }

        var inputActionVideo = InputMoreActionUnit()
        inputActionVideo.iconResId = R.drawable.default_user_icon
        inputActionVideo.titleId = R.string.video_call
        inputActionVideo.setOnClickListener {
            mlog("点击")
            mPresenter?.startVideoCall()
        }
        chat_layout.inputLayout.addAction(inputActionVideo)
    }

    override fun onResume() {
        super.onResume()
        ChatLayoutHelper(mContext).customizeChatLayout(chat_layout)
    }
}
