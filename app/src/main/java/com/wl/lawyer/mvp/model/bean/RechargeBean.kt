package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName

class RechargeBean {

    var amount: String? = null
    @SerializedName("total_log_count")
    var totalLogCount = 0
    @SerializedName("total_page")
    var totalPage = 0
    @SerializedName("log_list")
    var logList: List<LogListBean>? = null

    class LogListBean {
        var id = 0
        @SerializedName("user_id")
        var userId = 0
        var money: String? = null
        var before: String? = null
        var after: String? = null
        var memo: String? = null
        var createtime: Long = 0
        override fun toString(): String {
            return "LogListBean(id=$id, userId=$userId, money=$money, before=$before, after=$after, " +
                    "memo=$memo, createtime=$createtime)"
        }

    }

    override fun toString(): String {
        return "RechargeBean(amount=$amount, totalLogCount=$totalLogCount, " +
                "totalPage=$totalPage, logList=$logList)"
    }


}