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
import com.wl.lawyer.mvp.model.bean.*
import io.reactivex.Observable


@ActivityScope
class PayModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    PayContract.Model {

    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getPayType() = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getPayType()

    override fun payConsultOrder(
        orderId: Int,
        payWay: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).payConsultOrder(orderId, payWay)

    override fun payClericalOrder(
        orderId: Int,
        payWay: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).payClericalOrder(orderId, payWay)

    override fun payCooperateOrder(
        orderId: Int,
        payWay: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).payCooperateOrder(orderId, payWay)
}
