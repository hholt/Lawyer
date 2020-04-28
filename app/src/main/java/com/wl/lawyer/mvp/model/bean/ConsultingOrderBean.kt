package com.wl.lawyer.mvp.model.bean

class ConsultingOrderBean {
    var avatar: String? = null
    var lawName: String? = null
    var status: String? = null
    var consultingPackage: String? = null
    var startTime: String? = null
    var endTime: String? = null

    constructor()
    constructor(
        avatar: String?,
        lawName: String?,
        status: String?,
        consultingPackage: String?,
        startTime: String?,
        endTime: String?
    ) {
        this.avatar = avatar
        this.lawName = lawName
        this.status = status
        this.consultingPackage = consultingPackage
        this.startTime = startTime
        this.endTime = endTime
    }


}