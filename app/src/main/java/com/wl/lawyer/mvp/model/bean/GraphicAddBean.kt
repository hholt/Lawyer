package com.wl.lawyer.mvp.model.bean

import java.io.Serializable

data class GraphicAddBean(val type: Int=100, val filePath: String="") : Serializable {
    companion object {
        const val TYPE_PIC = 100
        const val TYPE_ADD = 101
    }
}