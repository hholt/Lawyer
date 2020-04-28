package com.wl.lawyer.mvp.contract

import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.Type
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable


interface QuickRegisterContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun sendSmsSuccess(msg: String?)
        fun checkSmsSuccess(msg: String?)
        fun registerUserSuccess(msg: String?, userBean: UserBean?)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {

        fun sendSms(mobile: String?, @Type.SendSmsEvent event: String?): Observable<BaseResponse<Any>>

        fun checkSms(
            mobile: String?, @Type.SendSmsEvent event: String?,
            code: String?
        ): Observable<BaseResponse<Any>>

        fun registerUser(
            mobile: String?,
            code: String?,
            password: String?
        ): Observable<BaseResponse<UserBean>>

        fun resetPwd(
            mobile: String,
            newPassword: String,
            code: String, @Type.AccountType type: String
        ): Observable<BaseResponse<Any>>
    }


}
