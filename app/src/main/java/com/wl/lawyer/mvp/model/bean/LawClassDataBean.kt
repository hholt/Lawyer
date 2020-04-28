package com.wl.lawyer.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

data class LawClassDataBean(
    var isHeader: Boolean,
    var header: String?,
    var title: String?,
    var content: String?
) : SectionEntity<LawClassDataBean>(isHeader, header)

