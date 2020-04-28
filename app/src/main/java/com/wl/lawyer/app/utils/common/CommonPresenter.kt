package com.wl.lawyer.app.utils.common

import android.app.Application
import com.hjq.toast.ToastUtils
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.CommonContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CommonPresenter(application: Application, view: CommonContract.View?) :
    CommonContract.View {

    private var mCommonModel: CommonModel
    private var mCommonView: CommonContract.View

    init {
        if (view == null) {
            this.mCommonView = this
        } else {
            this.mCommonView = view
        }
        mCommonModel = CommonModel(application, mCommonView)
    }

    override fun showMessage(message: String) {
        ToastUtils.show(message)
    }

    // 上传文件
    fun uploadFile(file: File, onListener: OnListener<String>?) {
        // 创建 RequestBody，用于封装 请求RequestBody
        val requestFile: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        // MultipartBody.Part is used to send also the actual file name
        val body =
            MultipartBody.Part.createFormData("file", file.name, requestFile)
        mCommonModel.uploadFile(body)
            .compose(RxCompose.transformer(mCommonView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<String>>(mCommonModel.mRxErrorHandler) {
                override fun onNext(t: BaseResponse<String>) {
                    if (t.isSuccess) {
                        onListener?.onSuccess(t?.msg, t?.data)
                        mlog("Abs path(${file.absolutePath}) net url(${t.data})")
                    } else {
                        RxView.showErrorMsg(mCommonView, t.msg)
                        onListener?.onFailed(t?.msg)
                    }
                }
            })
    }

    interface OnListener<T> {
        fun onSuccess(msg: String?, data: T?)
        fun onFailed(msg: String?)
    }
}