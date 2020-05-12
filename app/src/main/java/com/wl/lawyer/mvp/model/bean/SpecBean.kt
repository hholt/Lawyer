package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SpecBean(
    val id: Int,
    val pid: Int,
    @SerializedName("spec_name") val name: String,
    val children: List<SpecBean>
) : Serializable {
    /*
    "id": 1,
    "pid": 0,
    "spec_name": "服务类型",
    "children": [
    */
}