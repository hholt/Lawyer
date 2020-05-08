package com.wl.lawyer.mvp.ui.callback

import com.chad.library.adapter.base.diff.BaseQuickDiffCallback
import com.wl.lawyer.mvp.model.bean.CommentAdapterBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import com.wl.lawyer.mvp.model.bean.LawPopularizationDataBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean

class CommentQuickDiff(newItem: List<CommentAdapterBean>): BaseQuickDiffCallback<CommentAdapterBean>(newItem) {
    override fun areItemsTheSame(oldItem: CommentAdapterBean, newItem: CommentAdapterBean): Boolean {
        return oldItem.commentBean.id == newItem.commentBean.id
    }

    override fun areContentsTheSame(
        oldItem: CommentAdapterBean,
        newItem: CommentAdapterBean
    ): Boolean {
        return oldItem.commentBean.id == newItem.commentBean.id
    }
}