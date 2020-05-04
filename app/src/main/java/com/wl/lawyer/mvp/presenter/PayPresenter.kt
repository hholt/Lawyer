package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.PayContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.CreateOrderBean
import com.wl.lawyer.mvp.model.bean.OnlineConsultlationBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class PayPresenter
@Inject
constructor(model: PayContract.Model, rootView: PayContract.View) :
    BasePresenter<PayContract.Model, PayContract.View>(model, rootView) {
    fun createOrder(serviceId: Int, lawyerId: Int, payMethod: String) {
        mModel.createOrder(serviceId, lawyerId, payMethod)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
            ErrorHandleSubscriber<BaseResponse<CreateOrderBean>>(mErrorHandler) {
            override fun onNext(t: BaseResponse<CreateOrderBean>) {
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

    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

}
