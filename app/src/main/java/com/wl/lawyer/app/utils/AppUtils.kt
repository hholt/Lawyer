package com.wl.lawyer.app.utils

class AppUtils {
    companion object {
        fun getSexByNum(sex: Int?) = when (sex) {
            0 -> "男"
            1 -> "女"
            else -> "男"
        }

        fun getSexByString(sex: String?) = when (sex) {
            "男" -> 0
            "女" -> 1
            else -> "男"
        }
    }
}