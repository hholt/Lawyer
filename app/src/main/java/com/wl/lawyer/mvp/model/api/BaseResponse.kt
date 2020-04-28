package com.wl.lawyer.mvp.model.api

import java.io.Serializable

class BaseResponse<T> : Serializable {
    var code: Int? = null
    var msg: String? = null
    var time: String? = null
    var data: T? = null

    /**
     * 请求是否成功
     *
     * @return
     */
    val isSuccess: Boolean
        get() = code === Api.RequestSuccess
}
