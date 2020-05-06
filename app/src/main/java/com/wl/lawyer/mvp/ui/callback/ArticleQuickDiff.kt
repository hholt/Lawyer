package com.wl.lawyer.mvp.ui.callback

import com.chad.library.adapter.base.diff.BaseQuickDiffCallback
import com.wl.lawyer.mvp.model.bean.LawPopularizationDataBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean

class ArticleQuickDiff(newItem: List<LawPopularizationDataBean>): BaseQuickDiffCallback<LawPopularizationDataBean>(newItem) {
    override fun areItemsTheSame(oldItem: LawPopularizationDataBean, newItem: LawPopularizationDataBean): Boolean {
        return oldItem.lawyerArticle?.id == newItem.lawyerArticle?.id
    }

    override fun areContentsTheSame(
        oldItem: LawPopularizationDataBean,
        newItem: LawPopularizationDataBean
    ): Boolean {
        return oldItem.lawyerArticle?.id == newItem.lawyerArticle?.id
    }
}