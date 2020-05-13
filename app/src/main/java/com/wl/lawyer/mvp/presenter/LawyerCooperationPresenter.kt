package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.LawyerCooperationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.CooperateOrderBean
import com.wl.lawyer.mvp.model.bean.CooperateServiceBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class LawyerCooperationPresenter
@Inject
constructor(model: LawyerCooperationContract.Model, rootView: LawyerCooperationContract.View) :
    BasePresenter<LawyerCooperationContract.Model, LawyerCooperationContract.View>(
        model,
        rootView
    ) {
    fun getServiceType() {
        mModel.getServiceType()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<List<CooperateServiceBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<List<CooperateServiceBean>>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.onServiceTypeGet(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun createCooperateOrder(lawyerId: Int, serviceId: Int) {
//        val address = mRootView.getAddress()
        val desc = mRootView.getDesc()
        /*address.isEmpty().let {
            mRootView.showMessage("地址为空")
            return
        }*/
        mModel.createCooperateOrder(lawyerId, serviceId, desc)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<CooperateOrderBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<CooperateOrderBean>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.onCooperateOrderCreate(it)
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
