package com.wl.lawyer.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

class LawPopularizationDataBean : SectionEntity<LawPopularizationDataBean> {

    var avatar: String? = null
    var content: String? = null
    var lawyerArticle: LawyerArticleBean? = null

    constructor(isHeader: Boolean, header: String) : super(isHeader, header) {}

    constructor(lawPopularizationDataBean: LawPopularizationDataBean) : super(
        lawPopularizationDataBean
    ) {
    }

    constructor(
        isHeader: Boolean,
        header: String,
        avatar: String,
        content: String
    ) : super(isHeader, header) {
        this.avatar = avatar
        this.content = content
    }

    constructor(
        lawPopularizationDataBean: LawPopularizationDataBean,
        avatar: String,
        content: String
    ) : super(lawPopularizationDataBean) {
        this.avatar = avatar
        this.content = content
    }

    constructor(lawyerArticle: LawyerArticleBean) : super(false, "") {
        this.lawyerArticle = lawyerArticle
    }
}
