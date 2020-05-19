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
import com.wl.lawyer.mvp.model.bean.*
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class PayPresenter
@Inject
constructor(model: PayContract.Model, rootView: PayContract.View) :
    BasePresenter<PayContract.Model, PayContract.View>(model, rootView) {
    fun getPayType() {
        mModel.getPayType()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<PayTypeListBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<PayTypeListBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onPayTypeGet(it.payTypeList)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun goPay() {

    }

    fun payConsultOrder(orderId: Int) {
        val payWay = mRootView.getSelectPayType().value
        mModel.payConsultOrder(orderId, payWay)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<PayOrderBean<RealConsultOrderBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<PayOrderBean<RealConsultOrderBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onConsultOrderPay(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun payClericalOrder(orderId: Int) {
        val payWay = mRootView.getSelectPayType().value
        mModel.payClericalOrder(orderId, payWay)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<PayOrderBean<RealClericalOrderBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<PayOrderBean<RealClericalOrderBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onClericalOrderPay(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun payCooperateOrder(orderId: Int) {
        val payWay = mRootView.getSelectPayType().value
        mModel.payCooperateOrder(orderId, payWay)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<PayOrderBean<RealCooperateOrderBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<PayOrderBean<RealCooperateOrderBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onCooperateOrderPay(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun addUserChat(orderId: String, lawyerId: Int, type: Int) {
        mModel.addUserCHat(orderId, lawyerId, type)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<ChatBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<ChatBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onChatAdded(it)
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
