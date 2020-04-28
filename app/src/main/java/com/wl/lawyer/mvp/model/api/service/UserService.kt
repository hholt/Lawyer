package com.wl.lawyer.mvp.model.api.service

import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.Type
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    /**
     * ******************************* 验证码 *******************************
     */

    /**
     * 发送验证码
     * @param mobile 手机号
     * @param event 类型
     */
    @FormUrlEncoded
    @POST("/api/sms/send")
    fun sendSms(
        @Field("mobile") mobile: String?,
        @Type.SendSmsEvent @Field("event") event: String?
    ): Observable<BaseResponse<Any>>

    /**
     * 验证验证码
     * @param mobile 手机号
     * @param event 类型
     * @param code 验证码
     */
    @FormUrlEncoded
    @POST("/api/sms/check")
    fun checkSms(
        @Field("mobile") mobile: String?,
        @Type.SendSmsEvent @Field("event") event: String?,
        @Field("captcha") code: String?
    ): Observable<BaseResponse<Any>>

    /**
     * ******************************* 用户 *******************************
     */

    /**
     * 用户注册
     */
    @FormUrlEncoded
    @POST("/api/user/register")
    fun registerUser(
        @Field("mobile") mobile: String?,
        @Field("code") code: String?,
        @Field("password") password: String?
    ): Observable<BaseResponse<UserBean>>

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("/api/user/login")
    fun loginUser(
        @Field("account") mobile: String?,
        @Field("password") password: String?
    ): Observable<BaseResponse<UserBean>>

    /**
     * 重置密码
     * @param captcha 验证码
     * @param type 账户类型
     */
    @FormUrlEncoded
    @POST("/api/user/resetpwd")
    fun resetPwd(
        @Field("mobile") mobile: String,
        @Field("newpassword") newPassword: String,
        @Field("captcha") code: String,
        @Type.AccountType @Field("type") type: String
    ): Observable<BaseResponse<Any>>

    /**
     * 获取用户的基本个人资料（修改资料页面）
     */
    @POST("/api/user/getUserProfile")
    fun profileUser(): Observable<BaseResponse<UserBean>>

    /**
     * 设置用户资料
     * @param avatar 头像
     * @param nickname 昵称
     * @param bio 个人介绍
     * @param gender 性别
     * @param address 所在区域（文字）
     */
    @FormUrlEncoded
    @POST("/api/user/profile")
    fun updateProfileUser(@Body userBean: UserBean.UserinfoBean): Observable<BaseResponse<Any>>
}