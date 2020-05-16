package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.LawyerArticleDetailContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@ActivityScope
class LawyerArticleDetailPresenter
@Inject
constructor(
    model: LawyerArticleDetailContract.Model,
    rootView: LawyerArticleDetailContract.View
) : BasePresenter<LawyerArticleDetailContract.Model, LawyerArticleDetailContract.View>(
    model,
    rootView
) {
    fun getLawyerArticleDetail(id: Int) {
        mModel.getLawyerArticleDetail(id)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<LawyerArticleDetailBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<LawyerArticleDetailBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.initLawyerArticle(it)
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