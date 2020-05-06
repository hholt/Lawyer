package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.PublishGraphicConsultationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import com.wl.lawyer.mvp.model.bean.PtcCategoryBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class PublishGraphicConsultationPresenter
@Inject
constructor(
    model: PublishGraphicConsultationContract.Model,
    rootView: PublishGraphicConsultationContract.View
) :
    BasePresenter<PublishGraphicConsultationContract.Model, PublishGraphicConsultationContract.View>(
        model,
        rootView
    ) {
    fun getCatagories() {
        mModel.getCetagories()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<List<PtcCategoryBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<List<PtcCategoryBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.initCategories(it)
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
