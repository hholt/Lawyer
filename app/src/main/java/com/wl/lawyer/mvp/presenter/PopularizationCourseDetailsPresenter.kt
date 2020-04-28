package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.mvp.contract.PopularizationCourseDetailsContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@ActivityScope
class PopularizationCourseDetailsPresenter
@Inject
constructor(
    model: PopularizationCourseDetailsContract.Model,
    rootView: PopularizationCourseDetailsContract.View
) :
    BasePresenter<PopularizationCourseDetailsContract.Model, PopularizationCourseDetailsContract.View>(
        model,
        rootView
    ) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

}
