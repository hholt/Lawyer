package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.GCDetailsContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.CommentRefreshBean
import com.wl.lawyer.mvp.model.bean.CommentResultBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class GCDetailsPresenter
@Inject
constructor(model: GCDetailsContract.Model, rootView: GCDetailsContract.View) :
    BasePresenter<GCDetailsContract.Model, GCDetailsContract.View>(model, rootView) {
    fun getDetail(cid: Int) {
        mModel.getDetail(cid)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<GraphicConsultationBean>>(
                    mErrorHandler
                ) {
                override fun onNext(t: BaseResponse<GraphicConsultationBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onDetailGet(it)
                            if (it.images.isNotEmpty()) {
                                mRootView.onGraphicGet(it.images.split(";"))
                            }
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun getComment(cid: Int) {
        mModel.getComments(cid)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<CommentRefreshBean>>(
                    mErrorHandler
                ) {
                override fun onNext(t: BaseResponse<CommentRefreshBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onCommentsGet(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })

    }

    fun addComment() {
        val id = mRootView.getGCId()
        val toId = mRootView.getCommentId()
        val comment = mRootView.getComment()
        if (comment.isEmpty()) return
        mModel.addComment(comment, id, toId)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<CommentResultBean>>(
                    mErrorHandler
                ) {
                override fun onNext(t: BaseResponse<CommentResultBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onCommentAdded(it)
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
