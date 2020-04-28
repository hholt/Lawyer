package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.mvp.contract.ClericalCollaborationContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@ActivityScope
class ClericalCollaborationPresenter
@Inject
constructor(
    model: ClericalCollaborationContract.Model,
    rootView: ClericalCollaborationContract.View
) :
    BasePresenter<ClericalCollaborationContract.Model, ClericalCollaborationContract.View>(
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
