package com.wl.lawyer.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

class PopularizationCourseReviewsBean : SectionEntity<PopularizationCourseReviewsBean> {
    var avatar: String? = null
    var title: String? = null
    var time: String? = null
    var content: String? = null


    constructor(
        avatar: String?,
        title: String?,
        time: String?,
        content: String?) : super(false, "") {
        this.avatar = avatar
        this.title = title
        this.time = time
        this.content = content
    }

    constructor(isHeader: Boolean, header: String?) : super(isHeader, header)

}