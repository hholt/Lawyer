package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.wl.lawyer.mvp.model.api.BaseResponse
import io.reactivex.Observable
import okhttp3.MultipartBody

/**
 * 一些通用
 */
interface CommonContract {
    interface View : IView {
    }

    interface Model : IModel {
        fun uploadFile(file: MultipartBody.Part): Observable<BaseResponse<String>>
    }
}