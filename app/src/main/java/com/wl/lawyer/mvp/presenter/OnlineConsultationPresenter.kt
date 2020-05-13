package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.OnlineConsultationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.ConsultOrderBean
import com.wl.lawyer.mvp.model.bean.OnlineConsultlationBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class OnlineConsultationPresenter
@Inject
constructor(model: OnlineConsultationContract.Model, rootView: OnlineConsultationContract.View) :
    BasePresenter<OnlineConsultationContract.Model, OnlineConsultationContract.View>(
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

    fun serviceType() {
        mModel.getServiceType()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<OnlineConsultlationBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<OnlineConsultlationBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.initType(it.typeList)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun createOrder(serviceId: Int, lawyerId: Int) {
        mModel.createOrder(serviceId, lawyerId)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<ConsultOrderBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<ConsultOrderBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onOrderCreated(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

}
