package com.wl.lawyer.mvp.model.api.service

import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.HomeBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {
    /**
     * ******************************* 文件 *******************************
     */

    /**
     * 上传文件
     */
    @Multipart
    @POST("/api/common/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<BaseResponse<String>>

    /**
     * ******************************* 首页 *******************************
     */
    @POST("/api/index/getIndexData")
    fun indexData(): Observable<BaseResponse<HomeBean>>
}