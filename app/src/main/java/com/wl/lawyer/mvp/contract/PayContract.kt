package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.*
import io.reactivex.Observable


interface PayContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onPayTypeGet(payTypeList: List<PayTypeBean>)
        fun getSelectPayType(): PayTypeBean
        fun onConsultOrderPay(payOrderBean: PayOrderBean<RealConsultOrderBean>)
        fun onClericalOrderPay(payOrderBean: PayOrderBean<RealClericalOrderBean>)
        fun onCooperateOrderPay(payOrderBean: PayOrderBean<RealCooperateOrderBean>)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getPayType(): Observable<BaseResponse<PayTypeListBean>>
        fun payConsultOrder(orderId: Int, payWay: String): Observable<BaseResponse<PayOrderBean<RealConsultOrderBean>>>
        fun payClericalOrder(orderId: Int, payWay: String): Observable<BaseResponse<PayOrderBean<RealClericalOrderBean>>>
        fun payCooperateOrder(orderId: Int, payWay: String): Observable<BaseResponse<PayOrderBean<RealCooperateOrderBean>>>

    }

}
