package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import io.reactivex.Observable


interface GraphicConsultationContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onPTCListGet(listBean: BaseListBean<GraphicConsultationBean>)
        fun onPTCListMore(listBean: BaseListBean<GraphicConsultationBean>)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getPTCList(): Observable<BaseResponse<BaseListBean<GraphicConsultationBean>>>
        fun getPTCListMore(page: Int): Observable<BaseResponse<BaseListBean<GraphicConsultationBean>>>
    }

}
