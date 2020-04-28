package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.mvp.contract.FindLawyerContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class FindLawyerPresenter
@Inject
constructor(model: FindLawyerContract.Model, rootView: FindLawyerContract.View) :
    BasePresenter<FindLawyerContract.Model, FindLawyerContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

}
