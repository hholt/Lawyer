package com.wl.lawyer.mvp.ui.callback

import com.chad.library.adapter.base.diff.BaseQuickDiffCallback
import com.wl.lawyer.mvp.model.bean.LawPopularizationDataBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean
import com.wl.lawyer.mvp.model.bean.LawyerBean

class LawyerQuickDiff(newItem: List<LawyerBean>): BaseQuickDiffCallback<LawyerBean>(newItem) {
    override fun areItemsTheSame(oldItem: LawyerBean, newItem: LawyerBean): Boolean {
        return oldItem?.lawyerId == newItem?.lawyerId
    }

    override fun areContentsTheSame(
        oldItem: LawyerBean,
        newItem: LawyerBean
    ): Boolean {
        return oldItem?.lawyerId == newItem?.lawyerId
    }
}