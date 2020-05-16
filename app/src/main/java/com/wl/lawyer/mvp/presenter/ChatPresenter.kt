package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.app.utils.common.CommonPresenter
import com.wl.lawyer.mvp.contract.ChatContract
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import com.wl.lawyer.mvp.ui.activity.VideoCallActivity
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@ActivityScope
class ChatPresenter
@Inject
constructor(model: ChatContract.Model, rootView: ChatContract.View) :
    BasePresenter<ChatContract.Model, ChatContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    fun startVideoCall(){
        mAppManager.startActivity(VideoCallActivity::class.java)
    }

    fun getLawyerInfo(lawyerId: Int) {
        CommonPresenter(mApplication, null).getLawyerInfo(
            lawyerId,
            mRootView,
            object : CommonPresenter.OnListener<LawyerDetailBean> {
                override fun onSuccess(msg: String?, data: LawyerDetailBean?) {
                    data?.apply {
                        mRootView.onLawyerInfoGet(this)
                    }
                }

                override fun onFailed(msg: String?) {
                    RxView.showErrorMsg(mRootView, msg)
                }
            }
        )
    }

}
