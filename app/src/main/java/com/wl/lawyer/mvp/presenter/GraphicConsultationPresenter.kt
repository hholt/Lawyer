package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.GraphicConsultationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import com.wl.lawyer.mvp.model.bean.HomeDataBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class GraphicConsultationPresenter
@Inject
constructor(model: GraphicConsultationContract.Model, rootView: GraphicConsultationContract.View) :
    BasePresenter<GraphicConsultationContract.Model, GraphicConsultationContract.View>(
        model,
        rootView
    ) {
    var isLoadingMore = false
    fun getPTCList() {
        mModel.getPTCList()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<BaseListBean<GraphicConsultationBean>>>(
                    mErrorHandler
                ) {
                override fun onNext(t: BaseResponse<BaseListBean<GraphicConsultationBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onPTCListGet(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun loadMore(page: Int) {
        if (!isLoadingMore) {
            isLoadingMore = true
            mModel.getPTCListMore(page)
                .compose(RxCompose.transformer(mRootView))
                .subscribe(object :
                    ErrorHandleSubscriber<BaseResponse<BaseListBean<GraphicConsultationBean>>>(
                        mErrorHandler
                    ) {
                    override fun onNext(t: BaseResponse<BaseListBean<GraphicConsultationBean>>) {
                        if (t.isSuccess) {
                            t.data?.let {
                                mRootView?.onPTCListMore(it)
                            }
                        } else {
                            RxView.showErrorMsg(mRootView, t.msg)
                        }
                        isLoadingMore = false
                    }
                })
        }
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
