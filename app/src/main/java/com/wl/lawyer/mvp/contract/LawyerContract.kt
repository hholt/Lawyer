package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import com.wl.lawyer.mvp.model.bean.LawyerServiceBean
import io.reactivex.Observable


interface LawyerContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView{
        fun initLawyerInfo(lawyerInfo: LawyerDetailBean)
        fun initArticle(articles: List<LawyerArticleBean>)
        fun initService(services: List<LawyerServiceBean>)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun lawyerData(id: Int): Observable<BaseResponse<LawyerDetailBean>>
    }

}
