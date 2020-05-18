package com.wl.lawyer.im.holder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.wl.lawyer.im.adapter.CustomConversationListAdapter;

public abstract class ConversationBaseHolder extends RecyclerView.ViewHolder {

    protected View rootView;
    protected CustomConversationListAdapter mAdapter;

    public ConversationBaseHolder(View itemView) {
        super(itemView);
        rootView = itemView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = (CustomConversationListAdapter) adapter;
    }

    public abstract void layoutViews(ConversationInfo conversationInfo, int position);

}
