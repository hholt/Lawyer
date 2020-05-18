package com.wl.lawyer.im

import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationAdapter
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationProvider

class ConversationProviderProxy : IConversationProvider {

    lateinit var mProxy: IConversationProvider
    lateinit var mAdapter: IConversationAdapter

    fun proxy(provider: IConversationProvider) {
        mProxy = provider
    }

    override fun deleteConversations(conversations: MutableList<ConversationInfo>?) =
        mProxy.deleteConversations(conversations)

    override fun attachAdapter(adapter: IConversationAdapter?) {
        adapter?.let {
            mAdapter = adapter
        }
        mProxy.attachAdapter(adapter)
    }

    override fun getDataSource(): MutableList<ConversationInfo> =
        mProxy.dataSource

    override fun addConversations(conversations: MutableList<ConversationInfo>?) =
        mProxy.addConversations(conversations)

    override fun updateConversations(conversations: MutableList<ConversationInfo>?) =
        mProxy.updateConversations(conversations)
}