package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.LegalUnderstandingContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@FragmentScope
class LegalUnderstandingPresenter
@Inject
constructor(model: LegalUnderstandingContract.Model, rootView: LegalUnderstandingContract.View) :
    BasePresenter<LegalUnderstandingContract.Model, LegalUnderstandingContract.View>(
        model,
        rootView
    ) {
    fun getArticles() {
        mModel.getArticles()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<BaseListBean<LawyerArticleBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<BaseListBean<LawyerArticleBean>>) {
                    mlog("articles: ${t}")
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onGetArticles(it.list)
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
