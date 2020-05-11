package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchBean(
    val province: List<AreaBean>,
    @SerializedName("legal_category") val legalCategory: List<CategoryBean>,
    @SerializedName("sort_order") val sortOrder: List<SortBean>
) : Serializable {
    data class AreaBean(
        val id: Int,
        val name: String
    ) : Serializable

    data class SortBean(
        val field: String,
        val name: String
    ) : Serializable
    /*
    *
    * "province": [
      {
        "id": 1,
        "name": "北京"
      }]

      "legal_category": [
      {
        "id": 1,
        "name": "婚姻家事"
      }]
      *
      *
       "sort_order": [
      {
        "field": "all",
        "name": "默认"
      }]

    *
    * */
}