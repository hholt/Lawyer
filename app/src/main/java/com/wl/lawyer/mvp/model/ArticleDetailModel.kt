package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.PopularizationArticleDetailContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.ArticleDetailBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean
import io.reactivex.Observable
import javax.inject.Inject

@ActivityScope
class ArticleDetailModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    PopularizationArticleDetailContract.Model {
    @Inject
    lateinit var mGson: Gson

    @Inject
    lateinit var mApplication: Application

    override fun getArticleDetail(id: Int) = mRepositoryManager
        .obtainRetrofitService(CommonService::class.java).getArticleDetail(id)

}