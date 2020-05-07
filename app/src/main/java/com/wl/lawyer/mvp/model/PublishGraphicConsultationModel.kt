package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.PublishGraphicConsultationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.CategoryBean
import com.wl.lawyer.mvp.model.bean.PublishResultBean
import io.reactivex.Observable


@ActivityScope
class PublishGraphicConsultationModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    PublishGraphicConsultationContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getCetagories() = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getPTCCategories()

    override fun createConsult(
        title: String,
        content: String,
        imgs: String,
        cId: Int
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).create(title, content, imgs, cId)
}
