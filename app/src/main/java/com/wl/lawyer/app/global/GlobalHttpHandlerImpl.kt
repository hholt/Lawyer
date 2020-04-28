package com.wl.lawyer.app.global

import com.jess.arms.http.GlobalHttpHandler
import com.lxj.androidktx.core.getObject
import com.lxj.androidktx.core.sp
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.mvp.model.bean.UserBean
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class GlobalHttpHandlerImpl : GlobalHttpHandler {
    override fun onHttpRequestBefore(chain: Interceptor.Chain, request: Request): Request {
        sp().getObject<UserBean>(AppConstant.SP_USER)?.let {
            it?.userinfo?.token?.let { token ->
                return request.newBuilder().addHeader("token", token).build()
            }
        }
        return request
    }

    override fun onHttpResultResponse(
        httpResult: String?,
        chain: Interceptor.Chain,
        response: Response
    ): Response = response

}