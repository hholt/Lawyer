package com.wl.lawyer.mvp.model.bean

class GraphicConsultationBean {
    var avatar: String? = null
    var title: String? = null
    var desc: String? = null
    var reply: String? = null
    var time: String? = null

    constructor()

    constructor(avatar: String?, title: String?, desc: String?, reply: String?, time: String?) {
        this.avatar = avatar
        this.title = title
        this.desc = desc
        this.reply = reply
        this.time = time
    }


}