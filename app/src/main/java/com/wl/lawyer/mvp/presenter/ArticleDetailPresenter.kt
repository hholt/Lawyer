package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.PopularizationArticleDetailContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.ArticleDetailBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@ActivityScope
class ArticleDetailPresenter
@Inject
constructor(
    model: PopularizationArticleDetailContract.Model,
    rootView: PopularizationArticleDetailContract.View
) : BasePresenter<PopularizationArticleDetailContract.Model, PopularizationArticleDetailContract.View>(
    model,
    rootView
) {
    fun getArticleDetail(id: Int) {
        mModel.getArticleDetail(id)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<ArticleDetailBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<ArticleDetailBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.initArticle(it)
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