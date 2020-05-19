package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.im.bean.CustomChatBean
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.ChatBean
import com.wl.lawyer.mvp.model.bean.HomeDataBean
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import com.wl.lawyer.mvp.model.bean.TencentUserSignatureBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class ChatListPresenter
@Inject
constructor(model: ChatListContract.Model, rootView: ChatListContract.View) :
    BasePresenter<ChatListContract.Model, ChatListContract.View>(model, rootView) {
    fun getUserChatList() {
        mModel.getUserChatList()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<List<ChatBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<List<ChatBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            getUserProfile(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun getUserProfile(chatList: List<ChatBean>) {
        val chatlist = Observable.fromIterable(chatList)
        val profilelist = Observable.fromIterable(chatList)
            .concatMap {
                mModel.getUserProfile(it.lawyerId)
                    .compose(RxCompose.transformer(mRootView))
                    .map {
                        if (!it.isSuccess) {
                            RxView.showErrorMsg(mRootView, it.msg)
                        }
                        it.data
                    }
            }
        Observable.zip(
            chatlist,
            profilelist,
            BiFunction<ChatBean, LawyerDetailBean?, CustomChatBean> { t1, t2 -> CustomChatBean(t1, t2) }

        ).toList()
            .subscribe(object : SingleObserver<List<CustomChatBean>> {
                override fun onSuccess(t: List<CustomChatBean>) {
                    mRootView.onChatListGet(t)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                }


            }

            )
        /*mModel.getUserProfile(id)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<LawyerDetailBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<LawyerDetailBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })*/

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