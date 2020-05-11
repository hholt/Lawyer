package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FindLawyerBean(
    val total: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("last_page") val lastPage: Int,
    @SerializedName("data") val lawyerList: List<LawyerBean>
) : Serializable {

    /*
    * "total": 12,
    "per_page": 15,
    "current_page": 1,
    "last_page": 1,
    "data": [
    * */
}