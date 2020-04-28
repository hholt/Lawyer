package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.PersonalInformationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.UserService
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class PersonalInformationModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    PersonalInformationContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun profileUser(): Observable<BaseResponse<UserBean>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java).profileUser()
    }
    override fun updateProfileUser(userBean: UserBean.UserinfoBean): Observable<BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .updateProfileUser(userBean)
    }

}
