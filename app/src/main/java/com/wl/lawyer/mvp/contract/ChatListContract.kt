package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.wl.lawyer.im.bean.CustomChatBean
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.ChatBean
import com.wl.lawyer.mvp.model.bean.LawyerDetailBean
import com.wl.lawyer.mvp.model.bean.TencentUserSignatureBean
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable

class ChatListContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onChatListGet(data: List<CustomChatBean>)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getUserChatList() : Observable<BaseResponse<List<ChatBean>>>
        fun getUserProfile(id: Int) : Observable<BaseResponse<LawyerDetailBean>>
    }

}