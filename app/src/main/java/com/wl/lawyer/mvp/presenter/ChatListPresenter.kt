package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.HomeDataBean
import com.wl.lawyer.mvp.model.bean.TencentUserSignatureBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class ChatListPresenter
@Inject
constructor(model: ChatListContract.Model, rootView: ChatListContract.View) :
    BasePresenter<ChatListContract.Model, ChatListContract.View>(model, rootView) {
    fun getUserSignature() {
        mModel.getUserSignature()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<TencentUserSignatureBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<TencentUserSignatureBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onSignatureGet(it)
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