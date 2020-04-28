package com.wl.lawyer.mvp.model.bean

class PopularizationCourseBean {
    var img: String? = null
    var title: String? = null
    var desc: String? = null

    constructor()

    constructor(img: String?, title: String?, desc: String?) {
        this.img = img
        this.title = title
        this.desc = desc
    }

}