package com.wl.lawyer.mvp.presenter

import android.Manifest
import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.PermissionUtil
import com.lxj.androidktx.core.getObject
import com.lxj.androidktx.core.sp
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tencent.qcloud.tim.uikit.TUIKit
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.isNotExpired
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.SplashContract
import com.wl.lawyer.mvp.model.bean.TencentUserSignatureBean
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@ActivityScope
class SplashPresenter
@Inject
constructor(model: SplashContract.Model, rootView: SplashContract.View) :
    BasePresenter<SplashContract.Model, SplashContract.View>(model, rootView) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    fun checkPermission() {
        //检查权限 需要的权限加载后面
        PermissionUtil.requestPermission(
            object : PermissionUtil.RequestPermission {
                override fun onRequestPermissionFailureWithAskNeverAgain(permissions: MutableList<String>?) {
                    mlog("onRequestPermissionFailureWithAskNeverAgain")
                    mlog(permissions.toString())
                    goNextActivity()
                }

                override fun onRequestPermissionSuccess() {
                    mlog("onRequestPermissionSuccess")
                    //申请权限成功
                    goNextActivity()
                }

                override fun onRequestPermissionFailure(permissions: MutableList<String>?) {
                    mlog("onRequestPermissionFailure")
                    goNextActivity()
                }

            },
            RxPermissions(mAppManager.topActivity as FragmentActivity),
            mErrorHandler,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SYSTEM_ALERT_WINDOW
        )
        /**
         * <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.RECORD_AUDIO" />
        <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
        <uses-permission android:name="android.permission.BLUETOOTH" />
        <uses-permission android:name="android.permission.CAMERA" />
        <uses-permission android:name="android.permission.READ_PHONE_STATE" />
        <uses-feature android:name="android.hardware.Camera"/>
        <uses-feature android:name="android.hardware.camera.autofocus" />
        <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
        <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
         */
    }

    private fun goNextActivity() {
        //1秒钟之后跳转登录界面
        Observable.timer(1, TimeUnit.SECONDS)
            .compose(RxCompose.transformer())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                    val userInfo = sp().getObject<UserBean>(AppConstant.SP_USER)?.userinfo
                    val userSig = sp().getObject<TencentUserSignatureBean>(AppConstant.SP_SIG)
                    if (userInfo != null && userSig != null && userInfo.expiretime.isNotExpired()) {
                        tuikitLogin(userSig)
                    } else {
                        ActivityUtils.goLoginActivity()
                    }
//                    mAppManager.startActivity()
//                    mAppManager.startActivity(LoginActivity::class.java)
                    /*var userSig = GenerateTestUserSig.genTestUserSig(AppConstant.USER_ID)
                    TUIKit.login(AppConstant.USER_ID, userSig, object : IUIKitCallBack {

                        override fun onError(module: String?, errCode: Int, errMsg: String?) {
                            mlog("module:$module err code:$errCode msg:$errMsg")
                        }

                        override fun onSuccess(data: Any?) {
//                            mAppManager.startActivity(LiveActivity::class.java)
//                            mAppManager.startActivity(ChatActivity::class.java)

                            if (sp().getObject<UserBean>(AppConstant.SP_USER)?.userinfo?.token != null) {
                                ActivityUtils.goMainActivity()
                            } else {
                                ActivityUtils.goLoginActivity()
                            }
                        }

                    })*/
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Long) {

                }

                override fun onError(e: Throwable) {

                }
            })
    }

    private fun tuikitLogin(userSig: TencentUserSignatureBean) {

        TUIKit.login(userSig.nickname, userSig.sig, object : IUIKitCallBack {

            override fun onError(module: String?, errCode: Int, errMsg: String?) {
                mlog("module:$module err code:$errCode msg:$errMsg")
                ActivityUtils.goMainActivity()
                RxView.showMsg(mRootView, "$errMsg")
            }

            override fun onSuccess(data: Any?) {
//                            mAppManager.startActivity(LiveActivity::class.java)
//                            mAppManager.startActivity(ChatActivity::class.java)

                ActivityUtils.goMainActivity()
            }

        })
    }

}