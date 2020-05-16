package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.app.utils.common.CommonPresenter
import com.wl.lawyer.mvp.contract.PublishGraphicConsultationContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import com.wl.lawyer.mvp.model.bean.PtcCategoryBean
import com.wl.lawyer.mvp.model.bean.PublishResultBean
import com.wl.lawyer.mvp.model.bean.UploadBean
import io.reactivex.Observable
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.io.File
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

    fun createConsult(cId: Int) {
        val title = mRootView.getTitleText()
        val content = mRootView.getContent()
        val imgs = mRootView.getImages()
        if (title.isEmpty()) {
            mRootView.showMessage("标题不能为空")
            return
        }
        if (content.isEmpty()) {
            mRootView.showMessage("内容不能为空")
            return
        }
        if (imgs.isEmpty()) {
            mRootView.showMessage("选择至少一张图片")
            return
        }
        var imgParam = ""
        var count = 0
        var errorCount = 0
        imgs.forEach {
            CommonPresenter(mApplication, null).uploadPic(
                File(it),
                mRootView,
                object : CommonPresenter.OnListener<UploadBean> {
                    override fun onSuccess(msg: String?, data: UploadBean?) {
                        count++
                        imgParam = imgParam + data?.url + ","
                        if (count == imgs.size) {
                            if (errorCount == 0) {
                                realCreateConsult(title, content, imgParam, cId)
                            } else {
                                mRootView.showMessage("图片上传失败")
                            }
                        }
                    }

                    override fun onFailed(msg: String?) {
                        count++
                        errorCount++
                        if (count == imgs.size) mRootView.showMessage("图片上传失败")
                        RxView.showErrorMsg(mRootView, msg)
                    }
                }
            )

        }
    }

    fun realCreateConsult(title: String, content: String, imgParam: String, cId: Int) {
        mModel.createConsult(title, content, imgParam, cId)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<PublishResultBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<PublishResultBean>) {
                    if (t.isSuccess) {
                        t.data?.let {
                            mRootView?.onPublishResult(it)
                            RxView.showMsg(mRootView, "发布完成")
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
