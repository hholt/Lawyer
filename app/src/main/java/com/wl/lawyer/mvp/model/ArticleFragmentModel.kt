package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.ArticleFragmentContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean
import io.reactivex.Observable
import javax.inject.Inject

@FragmentScope
class ArticleFragmentModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ArticleFragmentContract.Model {
    @Inject
    lateinit var mGson: Gson

    @Inject
    lateinit var mApplication: Application

    override fun getLawyerArticle(
        keyword: String,
        lawyerId: Int,
        typeId: Int
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java)
        .getLawyerArticle(keyword = keyword, lawyerId = lawyerId, typeId = typeId)

}