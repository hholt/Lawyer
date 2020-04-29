package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HomeDataBean(val bannerList: List<BannerBean>, val lawLectureList: List<LawLectureBean>, val lawyerList: List<LawyerBean>): Serializable {
    data class BannerBean(val id: Int, val image: String, val title: String, val desc: String, @SerializedName("content_link")
    val contentLink: String): Serializable
    /**
     * id : 3
     * image : /uploads/20200203/aeea365209d13b508ba0dcc642320514.jpg
     * title : 广告测试
     * desc : 广告测试的描述
     * content_link : https://www.wanlang.cn
     */
    data class LawLectureBean(val id: Int, val title: String, val text: String): Serializable
    /**
     * id : 4
     * title : 如果你被虚假广告欺骗了，该怎么办？
     * text : 你要及时向工商部门反映情况。你的举报有时会很好地帮助他们，同时也会好地保护你自己。
     */
    data class LawyerBean(val id: Int, @SerializedName("lawyer_id") val lawyerId: Int,
                          @SerializedName("lawyer_old") val lawyerOld: Int,
                          @SerializedName("lawyer_level") val lawyerLevel: Int,
                          @SerializedName("province_id") val provinceId: Int,
                          @SerializedName("city_id") val cityId: Int,
                          @SerializedName("country_id") val countryId: Int,
                          val introduce: String, val score: String,
                          @SerializedName("legal_category_ids") val legalCategoryIds: String,
                          @SerializedName("legal_service_ids") val legalServiceIds: String,
                          @SerializedName("legal_service_num") val legalServiceNum: Int,
                          @SerializedName("createtime") val createTime: Any,
                          @SerializedName("updatetime") val updateTime: Int,
                          @SerializedName("is_recommend") val isRecommend: Int,
                          val avatar: String, val username: String,
                          @SerializedName("province_text") val provinceText: String,
                          @SerializedName("city_text") val cityText: String,
                          @SerializedName("country_text") val countryText: String,
                          @SerializedName("category_list") val categoryList: List<CategoryBean>,
                          @SerializedName("service_list") val serviceList: List<LawyerServiceBean>): Serializable
        /**
         * id : 4
         * lawyer_id : 15
         * lawyer_old : 4
         * lawyer_level : 0
         * province_id : 801
         * city_id : 802
         * country_id : 808
         * introduce : 测试的律师信息，嘀嘀嘀嘀
         * score : 1.00%
         * legal_category_ids : 1,2,3
         * legal_service_ids : 1,2,3,4
         * legal_service_num : 34
         * createtime : null
         * updatetime : 1581733985
         * is_recommend : 1
         * avatar : /assets/img/default_avatar.png
         * username : lawyer3
         * province_text : 上海
         * city_text : 上海市
         * country_text : 闸北区
         * category_list : [{"id":1,"name":"婚姻家事","status_text":""},{"id":2,"name":"交通事故","status_text":""},{"id":3,"name":"财产纠纷","status_text":""}]
         * service_list : [{"id":1,"name":"在线咨询","desc":"多种咨询方式满足您的所有需求","icon_image":"/uploads/20200213/22ab77fca36fca265e6cd6998ba1f862.png","price":29.99,"status_text":""},{"id":2,"name":"文书协作","desc":"撰写、审核、修订一站式服务","icon_image":"/uploads/20200213/de02fd78e920000d48ce49c5e3aa6b54.png","price":30,"status_text":""},{"id":3,"name":"律师合作","desc":"见证、委托手续","icon_image":"/uploads/20200213/c92132e4117f691e1f0b07fd2ada04ac.png","price":500,"status_text":""},{"id":4,"name":"案件委托定金","desc":"打官司、约见律师","icon_image":"/uploads/20200213/1d3578ed97976a409fef2203287a798e.png","price":500,"status_text":""}]
         */


}