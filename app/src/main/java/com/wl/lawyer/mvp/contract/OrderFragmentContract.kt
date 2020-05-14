package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import io.reactivex.Observable

class OrderFragmentContract {

    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onOrderListGet(data: BaseListBean<MyConsultOrderBean>)
        fun moreOrderGet(data: BaseListBean<MyConsultOrderBean>)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getOrderList(type: String) : Observable<BaseResponse<BaseListBean<MyConsultOrderBean>>>
        fun getMoreOrder(page: Int, status: String) : Observable<BaseResponse<BaseListBean<MyConsultOrderBean>>>
    }

}