package com.wl.lawyer.app.utils.common

import android.app.Application
import com.wl.lawyer.mvp.contract.CommonContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import io.reactivex.Observable
import okhttp3.MultipartBody

/**
 * 一些通用Model
 */
class CommonModel(applicationContext: Application, view: CommonContract.View) :
    BaseCommonModel(applicationContext),
    CommonContract.Model {

    override fun uploadFile(file: MultipartBody.Part): Observable<BaseResponse<String>> {
        return mIRepositoryManager.obtainRetrofitService(CommonService::class.java).uploadFile(file)
    }

    override fun onDestroy() {

    }

}