package com.wl.lawyer.mvp.ui.callback

import com.chad.library.adapter.base.diff.BaseQuickDiffCallback
import com.wl.lawyer.mvp.model.bean.CommentAdapterBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import com.wl.lawyer.mvp.model.bean.LawPopularizationDataBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean

class ConsulationQuickDiff(newItem: List<GraphicConsultationBean>): BaseQuickDiffCallback<GraphicConsultationBean>(newItem) {
    override fun areItemsTheSame(oldItem: GraphicConsultationBean, newItem: GraphicConsultationBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GraphicConsultationBean,
        newItem: GraphicConsultationBean
    ): Boolean {
        return oldItem.id == newItem.id
    }
}