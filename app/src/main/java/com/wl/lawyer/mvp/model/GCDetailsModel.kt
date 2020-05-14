package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.GCDetailsContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.CommentResultBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import io.reactivex.Observable


@ActivityScope
class GCDetailsModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    GCDetailsContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getDetail(cid: Int) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getConsulationDetail(cid)
    override fun getComments(cid: Int) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getComments(cid)
    override fun addComment(
        comment: String,
        id: Int,
        toId: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).addComment(comment, id, toId)
}
