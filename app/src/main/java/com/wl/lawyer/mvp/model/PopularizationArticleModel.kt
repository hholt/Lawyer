package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.PopularizationArticleContract
import com.wl.lawyer.mvp.model.api.service.CommonService
import javax.inject.Inject

@ActivityScope
class PopularizationArticleModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    PopularizationArticleContract.Model {
    @Inject
    lateinit var mGson: Gson

    @Inject
    lateinit var mApplication: Application

    override fun loadData() = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getArticles()

    override fun loadMore(page: Int) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getArticles(page)
}
