package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.ClericalOrderBean
import com.wl.lawyer.mvp.model.bean.SpecBean
import com.wl.lawyer.mvp.model.bean.SpecPriceBean
import io.reactivex.Observable


interface ClericalCollaborationContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onSpecListGet(data: List<SpecBean>)
        fun onSpecPriceGet(priceBean: SpecPriceBean)
        fun getMemo(): String
        fun getFilePath(): String
        fun onOrderCreate(orderBean: ClericalOrderBean)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getSpecList() : Observable<BaseResponse<List<SpecBean>>>
        fun getSpecPrice(arr: List<Int>) : Observable<BaseResponse<SpecPriceBean>>
        fun createRealClericalOrder(lawyerId: Int, specId: Int, memo: String, url: String) : Observable<BaseResponse<ClericalOrderBean>>
    }

}
