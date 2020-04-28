package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.lxj.androidktx.core.putObject
import com.lxj.androidktx.core.sp
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.LoginContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.UserBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class LoginPresenter
@Inject
constructor(model: LoginContract.Model, rootView: LoginContract.View) :
    BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    private var accountUser: String? = null
    private var passwordUser: String? = null


    fun login() {
        mRootView?.accountUser()?.let {
            accountUser = it
        }
        if (accountUser == null) {
            return
        }
        mRootView?.passwordUser()?.let {
            passwordUser = it
        }
        if (passwordUser == null) {
            return
        }
        mModel.loginUser(accountUser, passwordUser)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<UserBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<UserBean>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            sp().putObject(AppConstant.SP_USER, it)
                        }
                        ActivityUtils.goMainActivity()
                        ActivityUtils.finishTopActivity()
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }
}
