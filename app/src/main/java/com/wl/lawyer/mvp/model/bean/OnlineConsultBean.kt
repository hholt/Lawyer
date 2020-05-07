package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OnlineConsultlationBean(
    @SerializedName("type_list") val typeList: List<ConsultlationSetBean>
) : Serializable