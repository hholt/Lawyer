package com.wl.lawyer.mvp.model.api.service

import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.MoneyConfigBean
import com.wl.lawyer.mvp.model.bean.OrderInfoBean
import com.wl.lawyer.mvp.model.bean.RechargeBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WalletService {
    /**
     * ******************************* 钱包 *******************************
     */

    /**
     * 获取重置配置信息
     */
    @POST("/api/recharge/getRechargeMoneyConfig")
    fun rechargeMoneyConfig(): Observable<BaseResponse<MoneyConfigBean>>

    /**
     * 调用微信或者支付宝支付之前需要先添加订单到后台
     * 返回过来一些支付需要的参数
     *
     * 提交充值订单
     */
    @FormUrlEncoded
    @POST("/api/recharge/submitRechargeOrder")
    fun rechargeOrder(
        @Field("money") money: String,
        @Field("paytype") payType: String
    ): Observable<BaseResponse<OrderInfoBean>>

    /**
     * 获取充值变动记录
     */
    @FormUrlEncoded
    @POST("/api/recharge/getMoneyLogList")
    fun rechargeList(
        @Field("page") page: String?,
        @Field("page_size") pageSize: String?
    ): Observable<BaseResponse<RechargeBean>>
}