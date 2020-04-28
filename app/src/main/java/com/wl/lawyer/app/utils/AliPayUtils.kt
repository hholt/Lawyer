package com.wl.lawyer.app.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import com.alipay.sdk.app.PayTask
import com.vondear.rxfeature.module.alipay.PayResult
import com.vondear.rxtool.interfaces.OnSuccessAndErrorListener
import com.wl.lawyer.app.mlog

class AliPayUtils {
    companion object {
        private const val SDK_PAY_FLAG = 1

        fun aliPay(
            activity: Activity,
            orderInfo: String,
            onSuccessAndErrorListener: OnSuccessAndErrorListener
        ) {
            this.sOnSuccessAndErrorListener = onSuccessAndErrorListener
            val payRunnable = Runnable {
                val alipay = PayTask(activity)
                val result = alipay.payV2(orderInfo, true)
                mlog("msp ${result.toString()}")
                val msg = Message()
                msg.what = SDK_PAY_FLAG
                msg.obj = result
                handler.sendMessage(msg)
            }

            val payThread = Thread(payRunnable)
            payThread.start()
        }

        private var sOnSuccessAndErrorListener: OnSuccessAndErrorListener? = null

        private val handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                when (msg?.what) {
                    SDK_PAY_FLAG -> {
                        val payResult = PayResult(msg.obj as Map<String?, String?>)

                        //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                        val resultInfo = payResult.result // 同步返回需要验证的信息

                        val resultStatus = payResult.resultStatus
                        // 判断resultStatus 为9000则代表支付成功
                        if (TextUtils.equals(resultStatus, "9000")) {
                            sOnSuccessAndErrorListener?.onSuccess(resultStatus)
                        } else { // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            sOnSuccessAndErrorListener?.onError(resultStatus)
                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }
}