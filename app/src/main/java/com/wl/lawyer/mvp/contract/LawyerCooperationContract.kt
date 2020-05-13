package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.CooperateOrderBean
import com.wl.lawyer.mvp.model.bean.CooperateServiceBean
import io.reactivex.Observable


interface LawyerCooperationContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onServiceTypeGet(serviceList: List<CooperateServiceBean>)
        fun getAddress(): String
        fun getDesc(): String
        fun onCooperateOrderCreate(orderBean: CooperateOrderBean)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getServiceType(): Observable<BaseResponse<List<CooperateServiceBean>>>
        fun createCooperateOrder(lawyerId: Int, serviceId: Int, desc: String): Observable<BaseResponse<CooperateOrderBean>>
    }

}
