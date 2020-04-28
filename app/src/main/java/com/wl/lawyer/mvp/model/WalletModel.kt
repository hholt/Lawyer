package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.WalletContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.WalletService
import com.wl.lawyer.mvp.model.bean.RechargeBean
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class WalletModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    WalletContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun rechargeList(
        page: String?,
        pageSize: String?
    ): Observable<BaseResponse<RechargeBean>> {
        return mRepositoryManager.obtainRetrofitService(WalletService::class.java)
            .rechargeList(page, pageSize)
    }

}
