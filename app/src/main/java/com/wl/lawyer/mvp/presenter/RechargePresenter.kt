package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.vondear.rxtool.interfaces.OnSuccessAndErrorListener
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.utils.AliPayUtils
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.RechargeContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.MoneyConfigBean
import com.wl.lawyer.mvp.model.bean.OrderInfoBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class RechargePresenter
@Inject
constructor(model: RechargeContract.Model, rootView: RechargeContract.View) :
    BasePresenter<RechargeContract.Model, RechargeContract.View>(model, rootView) {

    fun getMoneyConfig() {
        mModel.rechargeMoneyConfig()
            .compose(RxCompose.transformer())
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<MoneyConfigBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<MoneyConfigBean>) {
                    if (t.isSuccess) {
                        mRootView?.moneyConfig(t.msg, t.data)
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun recharge() {
        val rechargeType = mRootView?.getRechargeType()
        val rechargeCount = mRootView?.getRechargeCount()
        if (rechargeType != null && rechargeCount != null) {
            // 需要先添加订单到后台，根据后台返回的数据拉起支付软件，完成支付
            mModel.rechargeOrder("$rechargeCount", "$rechargeType")
                .compose(RxCompose.transformer(mRootView))
                .subscribe(object :
                    ErrorHandleSubscriber<BaseResponse<OrderInfoBean>>(mErrorHandler) {
                    override fun onNext(t: BaseResponse<OrderInfoBean>) {
                        if (t.isSuccess) {
                            mlog("${t.data?.toString()}")
                            t.data?.order?.let {
                                when (it?.paytype) {
                                    "wechat" -> {

                                    }
                                    "alipay" -> {
                                        callAliPay(t)
                                    }
                                }
                            }
                        } else {
                            RxView.showErrorMsg(mRootView, t.msg)
                        }
                    }
                })

        }
    }

    private fun callAliPay(t: BaseResponse<OrderInfoBean>) {
        mlog("call alipay")
        mAppManager?.topActivity?.let { top ->
            t.data?.sign?.let { sign ->

                AliPayUtils.aliPay(top, sign, object :
                    OnSuccessAndErrorListener {
                    override fun onSuccess(s: String?) {
                        mlog("Ailpay on success $s")
                        RxView.showMsg(mRootView, "支付成功 $s")
                    }

                    override fun onError(s: String?) {
                        mlog("Ailpay on error $s")
                        RxView.showErrorMsg(mRootView, "支付失败 $s")
                    }
                })
            }
        }
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
