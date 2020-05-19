package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.gone
import com.tencent.imsdk.TIMConversationType
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo
import com.tencent.qcloud.tim.uikit.modules.chat.layout.inputmore.InputMoreActionUnit
import com.wl.lawyer.R
import com.wl.lawyer.app.*
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerChatComponent
import com.wl.lawyer.di.module.ChatModule
import com.wl.lawyer.mvp.contract.ChatContract
import com.wl.lawyer.mvp.model.bean.ChatBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import com.wl.lawyer.mvp.presenter.ChatPresenter
import com.wl.lawyer.im.ChatLayoutHelper
import com.wl.lawyer.im.call.CustomFeeTIMUIController
import com.wl.lawyer.im.call.IFeeCallback
import com.wl.lawyer.im.message.FeeBean
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.include.*

@Route(path = RouterPath.CHAT_ACTIVITY)
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

    @Autowired(name = RouterArgs.CONSULT_ORDER)
    @JvmField
    var consultOrder: MyConsultOrderBean? = null

    @Autowired(name = RouterArgs.CHAT)
    @JvmField
    var chatBean: ChatBean? = null

    @Autowired(name = RouterArgs.LAWYER_ID)
    @JvmField
    var lawyerId: Int? = -1

    @Autowired(name = RouterArgs.LAWYER_NAME)
    @JvmField
    var lawyerName: String = ""

    lateinit var mLawyerDetail: LawyerDetailBean

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_chat
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)

        iv_right.gone()
        dividing_line.gone()
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        chatBean?.let {
            lawyerId = it.lawyerId

        }

        lawyerId?.let {
            tv_title.text = lawyerName
            mPresenter?.getLawyerInfo(it)
        }

    }

    private fun initTencentIM() {
        chat_layout.initDefault()
        var chatInfo = ChatInfo()
        chatInfo.type = TIMConversationType.C2C
        chatInfo.id = mLawyerDetail.id.toString()
        chatInfo.chatName = mLawyerDetail.userName
        chat_layout.chatInfo = chatInfo


        var inputActionVideo = InputMoreActionUnit()
        inputActionVideo.iconResId = R.drawable.default_user_icon
        inputActionVideo.titleId = R.string.video_call
        inputActionVideo.setOnClickListener {
            mlog("点击")
            mPresenter?.startVideoCall()
        }
        chat_layout.inputLayout.addAction(inputActionVideo)
        ChatLayoutHelper(mContext, chatInfo).customizeChatLayout(chat_layout)
        CustomFeeTIMUIController.callback = object : IFeeCallback {
            override fun onPositiveClick(data: FeeBean?) {
                mlog("打开费用单")
            }

            override fun onNegativeClivk(data: FeeBean?) {
                mlog("拒绝费用单")
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onLawyerInfoGet(lawyerDetailBean: LawyerDetailBean) {
        mLawyerDetail = lawyerDetailBean

        tv_title.text = lawyerDetailBean.userName
        initTencentIM()
    }
}
