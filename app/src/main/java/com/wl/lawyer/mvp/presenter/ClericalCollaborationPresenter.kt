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
import com.wl.lawyer.mvp.contract.ClericalCollaborationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.ClericalOrderBean
import com.wl.lawyer.mvp.model.bean.SpecBean
import com.wl.lawyer.mvp.model.bean.SpecPriceBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.io.File
import javax.inject.Inject


@ActivityScope
class ClericalCollaborationPresenter
@Inject
constructor(
    model: ClericalCollaborationContract.Model,
    rootView: ClericalCollaborationContract.View
) :
    BasePresenter<ClericalCollaborationContract.Model, ClericalCollaborationContract.View>(
        model,
        rootView
    ) {
    fun getSpecList() {
        mModel.getSpecList()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<List<SpecBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<List<SpecBean>>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onSpecListGet(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun getSpecPrice(arr: List<Int>) {
        mModel.getSpecPrice(arr)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<SpecPriceBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<SpecPriceBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onSpecPriceGet(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun createClericalOrder(lawyerId: Int, specId: Int) {
        val memo = mRootView.getMemo()
        val filePath = mRootView.getFilePath()
        if (filePath.isEmpty()) {
            mRootView.showMessage("未选择文件")
            return
        } else {
            CommonPresenter(mApplication, null).uploadFile(
                File(filePath),
                object : CommonPresenter.OnListener<String> {
                    override fun onSuccess(msg: String?, data: String?) {
                        data?.apply {
                            mlog(data)
                            createRealClericalOrder(lawyerId, specId, memo, this)
                        }
                    }

                    override fun onFailed(msg: String?) {
                        RxView.showErrorMsg(mRootView, msg)
                    }
                }
            )
        }

    }

    fun createRealClericalOrder(lawyerId: Int, specId: Int, memo: String, url: String) {
        mModel.createRealClericalOrder(lawyerId, specId, memo, url)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<ClericalOrderBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<ClericalOrderBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView.onOrderCreate(it)
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
