package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.ConsultingOrderDetailContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.FindLawyerBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class ConsultingOrderDetailPresenter
@Inject
constructor(
    model: ConsultingOrderDetailContract.Model,
    rootView: ConsultingOrderDetailContract.View
) :
    BasePresenter<ConsultingOrderDetailContract.Model, ConsultingOrderDetailContract.View>(
        model,
        rootView
    ) {
    fun getOrderDetail(id: Int) {
        mModel.getOrderDetail(id)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<MyConsultOrderBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<MyConsultOrderBean>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.onOrderGet(it)
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
