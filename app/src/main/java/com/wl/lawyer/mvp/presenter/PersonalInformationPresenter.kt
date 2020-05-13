package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.app.utils.common.CommonPresenter
import com.wl.lawyer.mvp.contract.PersonalInformationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.UploadBean
import com.wl.lawyer.mvp.model.bean.UserBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.io.File
import javax.inject.Inject


@ActivityScope
class PersonalInformationPresenter
@Inject
constructor(model: PersonalInformationContract.Model, rootView: PersonalInformationContract.View) :
    BasePresenter<PersonalInformationContract.Model, PersonalInformationContract.View>(
        model,
        rootView
    ) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    var mUserInfoBean: UserBean.UserinfoBean? = null

    // 修改用户资料
    fun updateProfileUser() {
        val profileUserBeanInfo = mRootView?.getProfileUserBean(mUserInfoBean)
        // 如果修改头像了需要先上传图像
        if (mRootView.isChangeAvatar()) {
            CommonPresenter(mApplication, null).uploadPic(
                File(profileUserBeanInfo?.avatar),
                mRootView,
                object : CommonPresenter.OnListener<UploadBean> {
                    override fun onSuccess(msg: String?, data: UploadBean?) {
                        profileUserBeanInfo?.avatar = data?.url
                        updateProfileUser(profileUserBeanInfo)
                    }

                    override fun onFailed(msg: String?) {
                        RxView.showErrorMsg(mRootView, msg)
                    }
                }
            )
        } else {
            updateProfileUser(profileUserBeanInfo)
        }

    }

    // 修改用户资料
    private fun updateProfileUser(userBean: UserBean.UserinfoBean?) {
        if (userBean != null) {
            mModel.updateProfileUser(userBean)
                .compose(RxCompose.transformer(mRootView))
                .subscribe(object : ErrorHandleSubscriber<BaseResponse<Any>>(mErrorHandler) {
                    override fun onNext(t: BaseResponse<Any>) {
                        if (t.isSuccess) {
                            RxView.showMsg(mRootView, t.msg)
                        } else {
                            RxView.showErrorMsg(mRootView, t.msg)
                        }
                    }
                })
        } else {
            RxView.showErrorMsg(mRootView, "用户信息不能为空！")
        }
    }

    fun profileUser() {
        mModel.profileUser()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<UserBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<UserBean>) {
                    if (t.isSuccess) {
//                        mUserInfoBean = t?.data?.userinfo
                        mlog("获取用户信息 $mUserInfoBean")
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }
}
