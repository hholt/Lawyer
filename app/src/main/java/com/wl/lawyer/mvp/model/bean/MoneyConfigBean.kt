package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName

/**
 * 和钱相关尽量写在一起
 */
class MoneyConfigBean {

    @SerializedName("money_list")
    var moneyList: List<MoneyListBean>? = null
    @SerializedName("pay_type_list")
    var payTypeList: List<PayTypeListBean>? = null

    class MoneyListBean {
        var value: String? = null
        var text: String? = null
        @SerializedName("default")
        var isDefaultX = false

        override fun toString(): String {
            return "MoneyListBean(value=$value, text=$text, isDefaultX=$isDefaultX)"
        }

    }

    class PayTypeListBean {
        var value: String? = null
        var image: String? = null
        @SerializedName("default")
        var isDefaultX = false

        override fun toString(): String {
            return "PayTypeListBean(value=$value, image=$image, isDefaultX=$isDefaultX)"
        }

    }

    override fun toString(): String {
        return "MoneyConfigBean(moneyList=$moneyList, payTypeList=$payTypeList)"
    }

}