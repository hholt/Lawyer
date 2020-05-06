package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.PopularizationCourseContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.LiveListBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class PopularizationCoursePresenter
@Inject
constructor(
    model: PopularizationCourseContract.Model,
    rootView: PopularizationCourseContract.View
) :
    BasePresenter<PopularizationCourseContract.Model, PopularizationCourseContract.View>(
        model,
        rootView
    ) {
    fun loadData() {
        mModel.loadLiveData()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<LiveListBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<LiveListBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onDataLoad(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

}
