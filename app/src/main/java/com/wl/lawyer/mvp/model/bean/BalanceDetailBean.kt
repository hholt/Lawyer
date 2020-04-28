package com.wl.lawyer.mvp.model.bean

class BalanceDetailBean {
    var type: String? = null
    var time: String? = null
    var balance: String? = null

    constructor() {

    }

    constructor(type: String, time: String, balance: String) {
        this.type = type
        this.time = time
        this.balance = balance
    }
}
