package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.HomeContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.HomeBean
import com.wl.lawyer.mvp.model.bean.HomeDataBean
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class HomeModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    HomeContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun indexData(): Observable<BaseResponse<HomeDataBean>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java).indexData()
    }

}
