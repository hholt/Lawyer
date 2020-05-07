package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseListBean<T>(
    val list: List<T>,
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("total_page") val totalPage: Int) : Serializable
/*
*
* "total_count": 32,
* "total_page": 4
* */