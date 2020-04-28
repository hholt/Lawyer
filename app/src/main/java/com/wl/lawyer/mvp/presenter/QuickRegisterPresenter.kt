package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.QuickRegisterContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.Type
import com.wl.lawyer.mvp.model.bean.UserBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class QuickRegisterPresenter
@Inject
constructor(model: QuickRegisterContract.Model, rootView: QuickRegisterContract.View) :
    BasePresenter<QuickRegisterContract.Model, QuickRegisterContract.View>(model, rootView) {

    // 发送验证码
    fun sendSms(mobile: String, @Type.SendSmsEvent event: String?) {
        // mobile 手机号
        // Type.REGISTER 为注册账号
        mModel.sendSms(mobile, event)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<Any>) {
                    if (t.isSuccess) {
                        mRootView.sendSmsSuccess(t.msg)
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    // 验证验证码
    fun checkSms(mobile: String, code: String, @Type.SendSmsEvent event: String?) {
        // mobile 手机号
        // Type.REGISTER 为注册账号
        // code 验证码
        mModel.checkSms(mobile, event, code)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<Any>) {
                    if (t.isSuccess) {
                        mRootView.checkSmsSuccess(t.msg)
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    // 注册账户
    fun registerUser(mobile: String, code: String, pwd: String) {
        // code 验证码
        mModel.registerUser(mobile, code, pwd)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<UserBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<UserBean>) {
                    if (t.isSuccess) {
                        mRootView.registerUserSuccess(t.msg, t.data)
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun resetPwd(mobile: String, pwd: String, code1: String, type: String) {
        mModel.resetPwd(mobile, pwd, code1, type)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<Any>) {
                    if (t.isSuccess) {
                        ActivityUtils.finishTopActivity()
                    }
                    RxView.showErrorMsg(mRootView, t.msg)
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
