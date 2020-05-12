package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.ClericalCollaborationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.ClericalOrderBean
import com.wl.lawyer.mvp.model.bean.SpecBean
import io.reactivex.Observable


@ActivityScope
class ClericalCollaborationModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ClericalCollaborationContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getSpecList() = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getSpecList()

    override fun getSpecPrice(arr: List<Int>) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getSpecPrice(arr)

    override fun createRealClericalOrder(
        lawyerId: Int,
        specId: Int,
        memo: String,
        url: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).createClericalOrder(lawyerId, specId, memo, url)
}
