package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.LawyerCooperationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.CooperateOrderBean
import com.wl.lawyer.mvp.model.bean.CooperateServiceBean
import io.reactivex.Observable


@ActivityScope
class LawyerCooperationModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    LawyerCooperationContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getServiceType() = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getCooperateService()

    override fun createCooperateOrder(
        lawyerId: Int,
        serviceId: Int,
        desc: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).createCooperateOrder(lawyerId, serviceId, desc)
}
