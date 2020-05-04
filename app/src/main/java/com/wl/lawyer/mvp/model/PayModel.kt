package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.PayContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.CreateOrderBean
import io.reactivex.Observable


@ActivityScope
class PayModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    PayContract.Model {
    override fun createOrder(
        serviceId: Int,
        lawyerId: Int,
        payMethod: String
    ): Observable<BaseResponse<CreateOrderBean>> =
        mRepositoryManager.obtainRetrofitService(CommonService::class.java).createOrder(serviceId, lawyerId, payMethod)


    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

}
