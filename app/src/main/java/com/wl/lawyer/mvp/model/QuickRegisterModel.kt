package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.QuickRegisterContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.UserService
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class QuickRegisterModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    QuickRegisterContract.Model {

    override fun registerUser(
        mobile: String?,
        code: String?,
        password: String?
    ): Observable<BaseResponse<UserBean>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .registerUser(mobile, code, password)
    }

    override fun resetPwd(
        mobile: String,
        newPassword: String,
        code: String,
        type: String
    ): Observable<BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .resetPwd(mobile, newPassword, code, type)
    }

    override fun checkSms(
        mobile: String?,
        event: String?,
        code: String?
    ): Observable<BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .checkSms(mobile, event, code)
    }

    override fun sendSms(mobile: String?, event: String?): Observable<BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .sendSms(mobile, event)
    }

    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

}
