package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.OrderFragmentContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import io.reactivex.Observable
import javax.inject.Inject

@FragmentScope
class OrderFragmentModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    OrderFragmentContract.Model {
    @Inject
    lateinit var mGson: Gson

    @Inject
    lateinit var mApplication: Application

    override fun getOrderList(status: String) =
        mRepositoryManager.obtainRetrofitService(CommonService::class.java)
            .getMyOrderList(status = status)

    override fun getMoreOrder(
        page: Int,
        status: String
    ) = mRepositoryManager.obtainRetrofitService(CommonService::class.java)
        .getMyOrderList(page = page, status = status)
}