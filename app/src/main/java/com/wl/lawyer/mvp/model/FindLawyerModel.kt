package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.FindLawyerContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.FindLawyerBean
import com.wl.lawyer.mvp.model.bean.SearchBean
import io.reactivex.Observable


@FragmentScope
class FindLawyerModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    FindLawyerContract.Model {
    @Inject
    lateinit var mGson: Gson

    @Inject
    lateinit var mApplication: Application

    override fun loadData() =
        mRepositoryManager.obtainRetrofitService(CommonService::class.java).getLawyerList()

    override fun getSearchField() =
        mRepositoryManager.obtainRetrofitService(CommonService::class.java).getSearchField()

    override fun getAreaData(id: Int) =
        mRepositoryManager.obtainRetrofitService(CommonService::class.java).getAreaList(id)

    override fun search(
        page: Int,
        keyWord: String,
        pId: Int,
        cId: Int,
        bId: Int,
        categoryId: String,
        serviceId: String,
        sortBy: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java)
        .getLawyerList(
            page = page,
            keyword = keyWord,
            pId = pId,
            cid = cId,
            bId = bId,
            categoryId = categoryId,
            serviceId = serviceId,
            sortBy = sortBy
        )

}
