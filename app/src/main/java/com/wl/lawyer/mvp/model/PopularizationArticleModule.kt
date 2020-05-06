package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.PopularizationArticleContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.LatestArticlesBean
import io.reactivex.Observable
import javax.inject.Inject

@ActivityScope
class PopularizationArticleModule
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
