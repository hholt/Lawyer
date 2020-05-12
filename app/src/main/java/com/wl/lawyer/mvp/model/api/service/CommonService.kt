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
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 10
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
    fun create(
        @Query("title") title: String,
        @Query("content") content: String,
        @Query("images") imgs: String,
        @Query("p_t_category_id") cId: Int
    ): Observable<BaseResponse<PublishResultBean>>

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

    /**
     * ******************************* 获取律师列表 *******************************
     */
    @GET("/api/lawyer/getLawyerList")
    fun getLawyerList(
        @Query("page") page: Int = 1,
        @Query("keyword") keyword: String = "",
        @Query("province_id") pId: Int = 0,
        @Query("city_id") cid: Int = 0,
        @Query("country_id") bId: Int = 0,
        @Query("legal_category_id") categoryId: String = "",
        @Query("legal_service_id") serviceId: String = "",
        @Query("sort_order") sortBy: String = ""
    ): Observable<BaseResponse<FindLawyerBean>>

    /**
     * ******************************* 获取律师搜索字段 *******************************
     */
    @GET("/api/lawyer/getLawyerSearchFiled")
    fun getSearchField(): Observable<BaseResponse<SearchBean>>

    /**
     * ******************************* 获取地区列表 *******************************
     */
    @GET("/api/common/getAreaList")
    fun getAreaList(
        @Query("id") id: Int
    ): Observable<BaseResponse<List<SearchBean.AreaBean>>>

    /**
     * ******************************* 获取规格列表 *******************************
     */
    @GET("/api/essay_writing/getSpecList")
    fun getSpecList(): Observable<BaseResponse<List<SpecBean>>>

    /**
     * ******************************* 获取规格列表 *******************************
     */
    @GET("/api/essay_writing/getSpecPrice")
    fun getSpecPrice(@Query("key[]") list: List<Int>): Observable<BaseResponse<SpecPriceBean>>

    /**
     * ******************************* 创建文书协作订单 *******************************
     */
    @GET("/api/essay_writing/addEssayWritingOrder")
    fun createClericalOrder(
        @Query("lawyer_id") lawyerId: Int,
        @Query("spec_id") specId: Int,
        @Query("user_memo") memo: String,
        @Query("file_path") filePath: String
    ): Observable<BaseResponse<ClericalOrderBean>>


}