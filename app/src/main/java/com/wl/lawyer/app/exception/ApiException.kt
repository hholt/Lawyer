package com.wl.lawyer.app.exception

import com.wl.lawyer.app.AppConstant

class ApiException(private val mErrorCode: Int, errorMessage: String?) :
    RuntimeException(errorMessage) {
    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    val isTokenExpried: Boolean
        get() = mErrorCode == AppConstant.E_TOKEN_EXPRIED

    val isFailed: Boolean
        get() = mErrorCode == AppConstant.E_FAILED

}
