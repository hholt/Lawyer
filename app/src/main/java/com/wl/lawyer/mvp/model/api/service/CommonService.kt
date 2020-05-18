package com.wl.lawyer.mvp.model.api.service

import com.wl.lawyer.app.AppConstant
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
    fun uploadPic(@Part file: MultipartBody.Part): Observable<BaseResponse<UploadBean>>
    /**
     * 上传文件
     */
    @Multipart
    @POST("/api/common/uploadFile")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<BaseResponse<UploadBean>>

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
     * ******************************* 创建咨询订单 *******************************
     */
    @GET("/api/consultation/createOrder")
    fun createOrder(
        @Query("package_id") serviceId: Int,
        @Query("invite_lawyer_id") lawyerId: Int
    ): Observable<BaseResponse<ConsultOrderBean>>

    /**
     * ******************************* 普法文章 *******************************
     */
    @GET("/api/popularize_law/getArticles")
    fun getArticles(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = AppConstant.PAGE_SIZE
    ): Observable<BaseResponse<BaseListBean<LawyerArticleBean>>>

    @GET("/api/popularize_law/getArticleDetail")
    fun getArticleDetail(@Query("id") id: Int): Observable<BaseResponse<ArticleDetailBean>>

    /**
     * ******************************* 律师文章 *******************************
     */
    @GET("/api/lawyer/getLawyerArticle")
    fun getLawyerArticle(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = AppConstant.PAGE_SIZE,
        @Query("keywords") keyword: String = "",
        @Query("lawyer_id") lawyerId: Int,
        @Query("type_id") typeId: Int
    ): Observable<BaseResponse<BaseListBean<LawyerArticleDetailBean>>>

    /**
     * ******************************* 律师文章详情 *******************************
     */
    @GET("/api/lawyer/getArticleDetail")
    fun getLawyerArticleDetail(
        @Query("article_id") id: Int
    ): Observable<BaseResponse<LawyerArticleDetailBean>>

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
        @Query("page_size") pageSize: Int = AppConstant.PAGE_SIZE
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

    /**
     * ******************************* 获取支付方式 *******************************
     */
    @GET("/api/common/getPayWays")
    fun getPayType(): Observable<BaseResponse<PayTypeListBean>>

    /**
     * ******************************* 获取律师合作服务类型 *******************************
     */
    @GET("/api/lawyer_cooperate/getCooperateService")
    fun getCooperateService(): Observable<BaseResponse<List<CooperateServiceBean>>>

    /**
     * *******************************  创建律师合作订单 *******************************
     */
    @GET("/api/lawyer_cooperate/addLawyerCooperateOrder")
    fun createCooperateOrder(
        @Query("lawyer_id") lawyerId: Int,
        @Query("service_id") serciceId: Int,
        @Query("service_content") serviceContent: String
    ): Observable<BaseResponse<CooperateOrderBean>>

    /**
     * *******************************  支付咨询套餐的订单 *******************************
     */
    @GET("/api/consultation/payConsultationOrder")
    fun payConsultOrder(
        @Query("id") orderId: Int,
        @Query("pay_way") payWay: String
    ): Observable<BaseResponse<PayOrderBean<RealConsultOrderBean>>>

    /**
     * *******************************  支付文书协作订单 *******************************
     */
    @GET("/api/essay_writing/payEssayWritingOrder")
    fun payClericalOrder(
        @Query("id") orderId: Int,
        @Query("pay_way") payWay: String
    ): Observable<BaseResponse<PayOrderBean<RealClericalOrderBean>>>

    /**
     * *******************************  支付律师合作订单 *******************************
     */
    @GET("/api/lawyer_cooperate/payLawyerCooperateOrder")
    fun payCooperateOrder(
        @Query("id") orderId: Int,
        @Query("pay_way") payWay: String
    ): Observable<BaseResponse<PayOrderBean<RealCooperateOrderBean>>>

    /**
     * *******************************  获取我的咨询订单 *******************************
     */
    @GET("/api/consultation/getMyOrderList")
    fun getMyOrderList(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = AppConstant.PAGE_SIZE,
        @Query("status") status: String = "paid"
    ): Observable<BaseResponse<BaseListBean<MyConsultOrderBean>>>

    /**
     * *******************************  添加评论 *******************************
     */
    @GET("/api/picture_text_consultation/addComment")
    fun addComment(
        @Query("content") content: String,
        @Query("p_t_consultation_id") gcId: Int,
        @Query("to_comment_id") toId: String = ""
    ): Observable<BaseResponse<CommentResultBean>>

    /**
     * *******************************  订单详情 *******************************
     */
    @GET("/api/consultation/getOrderDetail")
    fun getConsultOrderDetail(
        @Query("id") id: Int
    ): Observable<BaseResponse<MyConsultOrderBean>>


    /**
     * *******************************  获取进行中的聊天 *******************************
     */
    @GET("/api/user_chat/getUserChatList")
    fun getChatList() : Observable<BaseResponse<List<ChatBean>>>
}