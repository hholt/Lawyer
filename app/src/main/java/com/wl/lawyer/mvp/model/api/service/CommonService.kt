package com.wl.lawyer.mvp.model.api.service

import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface CommonService {
    /**
     * ******************************* 文件 *******************************
     */

    /**
     * 上传文件
     */
    @Multipart
    @POST("/api/common/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<BaseResponse<String>>

    /**
     * ******************************* 首页 *******************************
     */
    @POST("/api/index/getIndexData")
    fun indexData(): Observable<BaseResponse<HomeDataBean>>

    /**
     * ******************************* 详情 *******************************
     */
    @GET("/api/lawyer/getLawyerDetail")
    fun lawyerData(@Query("id") id: Int): Observable<BaseResponse<LawyerDetailBean>>

    /**
     * ******************************* 在线咨询 *******************************
     */
    @GET("/api/consultation/getServiceType")
    fun serviceType(): Observable<BaseResponse<OnlineConsultlationBean>>

    /**
     * ******************************* 创建订单 *******************************
     */
    @GET("/api/consultation/createOrder")
    fun createOrder(
        @Query("package_id") serviceId: Int,
        @Query("invite_lawyer_id") lawyerId: Int,
        @Query("pay_type") payMethod: String
    ): Observable<BaseResponse<CreateOrderBean>>

    /**
     * ******************************* 普法文章 *******************************
     */
    @GET("/api/popularize_law/getArticles")
    fun getArticles(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 10
    ): Observable<BaseResponse<BaseListBean<LawyerArticleBean>>>

    @GET("/api/popularize_law/getArticleDetail")
    fun getArticleDetail(@Query("id") id: Int): Observable<BaseResponse<ArticleDetailBean>>

    /**
     * ******************************* 图文咨询分类 *******************************
     */
    @GET("/api/picture_text_consultation/getPTCCategories")
    fun getPTCCategories(): Observable<BaseResponse<List<PtcCategoryBean>>>


    /**
     * ******************************* 我的图文咨询列表 *******************************
     */
    @GET("/api/picture_text_consultation/getPTCList")
    fun getPTCList(
        @Query("page") page: Int=1,
        @Query("page_size") pageSize: Int=10
    ): Observable<BaseResponse<BaseListBean<GraphicConsultationBean>>>

    /**
     * ******************************* 图文咨询详情 *******************************
     */
    @GET("/api/picture_text_consultation/getDetail")
    fun getConsulationDetail(
        @Query("consultation_id") cid: Int
    ): Observable<BaseResponse<GraphicConsultationBean>>

    /**
     * ******************************* 获取图文咨询评论 *******************************
     */
    @GET("/api/picture_text_consultation/getComments")
    fun getComments(
        @Query("consultation_id") cid: Int
    ): Observable<BaseResponse<CommentRefreshBean>>

    /**
     * ******************************* 创建图文咨询 *******************************
     */
    @GET("/api/picture_text_consultation/create")
    fun create(@Query("title") title: String,
               @Query("content") content: String,
               @Query("images") imgs: String,
               @Query("p_t_category_id") cId: Int): Observable<BaseResponse<PublishResultBean>>

    /**
     * ******************************* 获取我的订单列表 *******************************
     */
    @GET("/api/consultation/getMyOrderList")
    fun getMyOrderList(): Observable<BaseResponse<BaseListBean<OrderInfoBean>>>

    /**
     * ******************************* 直播列表 *******************************
     */
    @GET("/api/live/getLiveRoomList")
    fun getLiveRoomList(): Observable<BaseResponse<LiveListBean>>
}