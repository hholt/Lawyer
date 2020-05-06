package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.PopularizationArticleContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.LatestArticlesBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@ActivityScope
class PopularizationArticlePresenter
@Inject
constructor(
    model: PopularizationArticleContract.Model,
    rootView: PopularizationArticleContract.View
) : BasePresenter<PopularizationArticleContract.Model, PopularizationArticleContract.View>(
    model,
    rootView
) {

    var isLoadingMore = false
    fun loadData() {
        mModel.loadData()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<LatestArticlesBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<LatestArticlesBean>) {
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

    fun loadMore(page: Int) {
        if (!isLoadingMore) {
            isLoadingMore = true
            mModel.loadMore(page)
                .compose(RxCompose.transformer(mRootView))
                .subscribe(object :
                    ErrorHandleSubscriber<BaseResponse<LatestArticlesBean>>(mErrorHandler) {
                    override fun onNext(t: BaseResponse<LatestArticlesBean>) {
                        if (t.isSuccess) {
                            t.data?.let {
                                mRootView?.onDataMore(it)
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