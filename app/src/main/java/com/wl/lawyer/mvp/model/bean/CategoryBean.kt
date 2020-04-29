package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryBean(val id: Int, val name: String,
                        @SerializedName("status_text") val statusText: String,
                        val isHeader: Boolean,
                        val header: String): Serializable
/**
 * id : 1
 * name : 婚姻家事
 * status_text :
 */