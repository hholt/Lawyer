package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.CategoryBean
import com.wl.lawyer.mvp.model.bean.PtcCategoryBean
import com.wl.lawyer.mvp.model.bean.PublishResultBean
import io.reactivex.Observable


interface PublishGraphicConsultationContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun initCategories(list: List<PtcCategoryBean>)
        fun onPublishResult(result: PublishResultBean)
        fun getTitleText(): String
        fun getContent(): String
        fun getImages(): List<String>
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getCetagories(): Observable<BaseResponse<List<PtcCategoryBean>>>
        fun createConsult(title: String, content: String, imgs: String, cId: Int): Observable<BaseResponse<PublishResultBean>>
    }

}
