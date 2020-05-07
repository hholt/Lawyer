package com.wl.lawyer.mvp.model.api.service

import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.TencentUserSignatureBean
import io.reactivex.Observable
import retrofit2.http.GET

interface TencentCloudService {
    /**
     * ******************************* TencentIM *******************************
     */

    /**
     * 获取重置配置信息
     */
    @GET("/api/user/generalTencentUserSig")
    fun getSignature(): Observable<BaseResponse<TencentUserSignatureBean>>

}