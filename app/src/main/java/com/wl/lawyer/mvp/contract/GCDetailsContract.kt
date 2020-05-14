package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.CommentRefreshBean
import com.wl.lawyer.mvp.model.bean.CommentResultBean
import com.wl.lawyer.mvp.model.bean.ConsulationCommentBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import io.reactivex.Observable


interface GCDetailsContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onDetailGet(consultationBean: GraphicConsultationBean)
        fun onCommentsGet(commentRefreshBean: CommentRefreshBean)
        fun onGraphicGet(data: List<String>)
        fun getGCId(): Int
        fun getCommentId(): String
        fun getComment(): String
        fun onCommentAdded(it: CommentResultBean)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getDetail(cid: Int): Observable<BaseResponse<GraphicConsultationBean>>
        fun getComments(cid: Int): Observable<BaseResponse<CommentRefreshBean>>
        fun addComment(comment: String, id: Int, toId: String): Observable<BaseResponse<CommentResultBean>>
    }

}
