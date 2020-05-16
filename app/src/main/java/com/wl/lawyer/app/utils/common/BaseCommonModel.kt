package com.wl.lawyer.app.utils.common

import android.app.Application
import com.jess.arms.base.BaseApplication
import com.jess.arms.di.component.AppComponent
import com.jess.arms.integration.IRepositoryManager
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import io.reactivex.Observable
import me.jessyan.rxerrorhandler.core.RxErrorHandler

abstract class BaseCommonModel {
    var mApplicationContext: Application
    var mBaseApplication: BaseApplication
    var mAppComponent: AppComponent
    var mIRepositoryManager: IRepositoryManager
    var mRxErrorHandler: RxErrorHandler

    constructor(applicationContext: Application) {
        mApplicationContext = applicationContext
        mBaseApplication = mApplicationContext as BaseApplication
        mAppComponent = mBaseApplication.appComponent
        mIRepositoryManager = mAppComponent.repositoryManager()
        mRxErrorHandler = mAppComponent.rxErrorHandler()
    }

}