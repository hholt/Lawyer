package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.lxj.androidktx.core.putObject
import com.lxj.androidktx.core.sp
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.OrderFragmentContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import com.wl.lawyer.mvp.model.bean.UserBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@FragmentScope
class OrderFragmentPresenter
@Inject
constructor(model: OrderFragmentContract.Model, rootView: OrderFragmentContract.View) :
    BasePresenter<OrderFragmentContract.Model, OrderFragmentContract.View>(model, rootView) {

    var isLoadingMore = false

    fun getOrderList(type: String) {
        mModel.getOrderList(type)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<BaseListBean<MyConsultOrderBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<BaseListBean<MyConsultOrderBean>>) {
                    if (t.isSuccess) {
                        t?.data?.let {
                            mRootView.onOrderListGet(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun loadMore(page: Int, status: String) {
        mModel.getMoreOrder(page, status)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<BaseListBean<MyConsultOrderBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<BaseListBean<MyConsultOrderBean>>) {
                    if (t.isSuccess) {
                        t?.data?.let {
                            mRootView.moreOrderGet(it)
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