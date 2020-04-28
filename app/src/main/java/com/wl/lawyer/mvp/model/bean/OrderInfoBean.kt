package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName

class OrderInfoBean {

    var order: OrderBean? = null
    var sign: String? = null

    class OrderBean {

        var orderid: String? = null
        @SerializedName("user_id")
        var userId = 0
        var amount: String? = null
        var payamount = 0
        var paytype: String? = null
        var ip: String? = null
        var useragent: String? = null
        var status: String? = null
        var createtime = 0
        var id: String? = null

        override fun toString(): String {
            return "OrderBean(orderid=$orderid, userId=$userId, amount=$amount, payamount=$payamount, " +
                    "paytype=$paytype, ip=$ip, useragent=$useragent, status=$status, createtime=$createtime, id=$id)"
        }

    }

    override fun toString(): String {
        return "OrderInfoBean(order=$order, sign=$sign)"
    }

}