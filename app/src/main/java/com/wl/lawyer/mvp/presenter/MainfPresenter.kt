package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.mvp.contract.MainfContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class MainfPresenter
@Inject
constructor(model: MainfContract.Model, rootView: MainfContract.View) :
    BasePresenter<MainfContract.Model, MainfContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

}
