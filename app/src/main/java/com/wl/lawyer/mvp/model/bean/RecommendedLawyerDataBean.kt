package com.wl.lawyer.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity
import java.io.Serializable

class RecommendedLawyerDataBean : SectionEntity<RecommendedLawyerDataBean> {
    var avator: String? = null
    var name: String? = null
    var time: String? = null
    var price: String? = null
    var address: String? = null
    var type: String? = null
    var lawyer: HomeDataBean.LawyerBean? = null

    constructor(isHeader: Boolean, header: String) : super(isHeader, header) {}

    constructor(recommendedLawyerDataBean: RecommendedLawyerDataBean) : super(
        recommendedLawyerDataBean
    ) {

    }

    constructor(
        isHeader: Boolean,
        header: String?,
        avator: String?,
        name: String?,
        time: String?,
        price: String?,
        address: String?,
        type: String?
    ) : super(isHeader, header) {
        this.avator = avator
        this.name = name
        this.time = time
        this.price = price
        this.address = address
        this.type = type
    }

    constructor(
        lawyer: HomeDataBean.LawyerBean
    ) : this(false, "", lawyer.avatar, lawyer.username,
        lawyer.lawyerOld.toString(), lawyer.score, lawyer.cityText + lawyer.countryText, lawyer.categoryList?.get(0)?.name) {
        this.lawyer = lawyer
    }

}
