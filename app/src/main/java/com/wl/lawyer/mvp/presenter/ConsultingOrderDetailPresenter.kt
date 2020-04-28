package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.mvp.contract.ConsultingOrderDetailContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@ActivityScope
class ConsultingOrderDetailPresenter
@Inject
constructor(
    model: ConsultingOrderDetailContract.Model,
    rootView: ConsultingOrderDetailContract.View
) :
    BasePresenter<ConsultingOrderDetailContract.Model, ConsultingOrderDetailContract.View>(
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
