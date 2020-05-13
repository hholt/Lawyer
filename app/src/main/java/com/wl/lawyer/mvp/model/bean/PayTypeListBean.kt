package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PayTypeListBean(
    @SerializedName("pay_type_list") val payTypeList: List<PayTypeBean>
) : Serializable