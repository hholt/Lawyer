package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.RechargeContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.WalletService
import com.wl.lawyer.mvp.model.bean.MoneyConfigBean
import com.wl.lawyer.mvp.model.bean.OrderInfoBean
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class RechargeModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    RechargeContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun rechargeOrder(
        money: String,
        payType: String
    ): Observable<BaseResponse<OrderInfoBean>> {
        return mRepositoryManager.obtainRetrofitService(WalletService::class.java)
            .rechargeOrder(money, payType)
    }

    override fun rechargeMoneyConfig(): Observable<BaseResponse<MoneyConfigBean>> {
        return mRepositoryManager.obtainRetrofitService(WalletService::class.java)
            .rechargeMoneyConfig()
    }


}
