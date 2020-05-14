package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.ArticleFragmentContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean
import com.wl.lawyer.mvp.model.bean.TencentUserSignatureBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@FragmentScope
class ArticleFragmentPresenter
@Inject
constructor(model: ArticleFragmentContract.Model, rootView: ArticleFragmentContract.View) :
    BasePresenter<ArticleFragmentContract.Model, ArticleFragmentContract.View>(model, rootView) {
    fun loadMore(i: Int, status: Any) {
        TODO("Not yet implemented")
    }

    fun getLawyerArticle(type: Int, lawyerId: Int) {
        val keyWord = mRootView.getKeyWord()
        mModel.getLawyerArticle(keyWord, lawyerId, type)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<BaseListBean<LawyerArticleDetailBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<BaseListBean<LawyerArticleDetailBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onLawyerArticleGet(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }


    var isLoadingMore = false

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

}