package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.WalletContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.RechargeBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class WalletPresenter
@Inject
constructor(model: WalletContract.Model, rootView: WalletContract.View) :
    BasePresenter<WalletContract.Model, WalletContract.View>(model, rootView) {

    fun rechargeList(page: String?, size: String?) {
        mModel.rechargeList(page, size)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<RechargeBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<RechargeBean>) {
                    if (t.isSuccess) {
                        mRootView?.rechargeList(t.msg, t.data)
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
