package com.wl.lawyer.im.bean

import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo
import com.wl.lawyer.mvp.model.bean.ChatBean
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import java.io.Serializable

data class CustomChatBean(
    val chat: ChatBean,
    val profile: LawyerDetailBean,
    val conversation: ConversationInfo? = null
) : Serializable