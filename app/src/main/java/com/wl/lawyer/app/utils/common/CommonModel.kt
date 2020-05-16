package com.wl.lawyer.app.utils.common

import android.app.Application
import com.wl.lawyer.mvp.contract.CommonContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import com.wl.lawyer.mvp.model.bean.UploadBean
import io.reactivex.Observable
import okhttp3.MultipartBody

/**
 * 一些通用Model
 */
class CommonModel(applicationContext: Application, view: CommonContract.View) :
    BaseCommonModel(applicationContext),
    CommonContract.Model {

    override fun uploadPic(file: MultipartBody.Part): Observable<BaseResponse<UploadBean>> {
        return mIRepositoryManager.obtainRetrofitService(CommonService::class.java).uploadPic(file)
    }

    override fun uploadFile(file: MultipartBody.Part): Observable<BaseResponse<UploadBean>> {
        return mIRepositoryManager.obtainRetrofitService(CommonService::class.java).uploadFile(file)
    }

    override fun getLawyerInfo(lawyerId: Int) = mIRepositoryManager.obtainRetrofitService(CommonService::class.java).lawyerData(lawyerId)

    override fun onDestroy() {

    }

}