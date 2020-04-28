package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.LoginContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.UserService
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class LoginModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    LoginContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun loginUser(mobile: String?, password: String?): Observable<BaseResponse<UserBean>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .loginUser(mobile, password)
    }

}
