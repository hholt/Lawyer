package com.wl.lawyer.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

class ServiceBean : SectionEntity<ServiceBean> {
    var img: Int? = null
    var title: String? = null
    var desc: String? = null
    var price: String? = null
    var serviceBean: LawyerServiceBean? = null



    constructor(img: Int?, title: String?, desc: String?, price: String?) : super(false, "") {
        this.img = img
        this.title = title
        this.desc = desc
        this.price = price
    }

    constructor(isHeader: Boolean, header: String?) : super(isHeader, header)

    constructor(serviceBean: LawyerServiceBean) : super(false, "") {
        this.serviceBean = serviceBean
    }

}