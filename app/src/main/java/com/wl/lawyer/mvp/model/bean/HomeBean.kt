package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName

class HomeBean {

    var bannerList: List<BannerListBean>? = null
    var lawLectureList: List<LawLectureListBean>? = null
    var lawyerList: List<LawyerListBean>? = null

    class BannerListBean {
        /**
         * id : 3
         * image : /uploads/20200203/aeea365209d13b508ba0dcc642320514.jpg
         * title : 广告测试
         * desc : 广告测试的描述
         * content_link : https://www.wanlang.cn
         */
        var id = 0
        var image: String? = null
        var title: String? = null
        var desc: String? = null
        @SerializedName("content_link")
        var contentLink: String? = null

        override fun toString(): String {
            return "BannerListBean(id=$id, image=$image, title=$title, desc=$desc, contentLink=$contentLink)"
        }

    }

    class LawLectureListBean {
        /**
         * id : 4
         * title : 如果你被虚假广告欺骗了，该怎么办？
         * text : 你要及时向工商部门反映情况。你的举报有时会很好地帮助他们，同时也会好地保护你自己。
         */
        var id = 0
        var title: String? = null
        var text: String? = null
        override fun toString(): String {
            return "LawLectureListBean(id=$id, title=$title, text=$text)"
        }

    }

    class LawyerListBean {
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
        var id = 0
        @SerializedName("lawyer_id")
        var lawyerId = 0
        @SerializedName("lawyer_old")
        var lawyerOld = 0
        @SerializedName("lawyer_level")
        var lawyerLevel = 0
        @SerializedName("province_id")
        var provinceId = 0
        @SerializedName("city_id")
        var cityId = 0
        @SerializedName("country_id")
        var countryId = 0
        var introduce: String? = null
        var score: String? = null
        @SerializedName("legal_category_ids")
        var legalCategoryIds: String? = null
        @SerializedName("legal_service_ids")
        var legalServiceIds: String? = null
        @SerializedName("legal_service_num")
        var legalServiceNum = 0
        @SerializedName("createtime")
        var createTime: Any? = null
        @SerializedName("updatetime")
        var updateTime = 0
        @SerializedName("is_recommend")
        var isRecommend = 0
        var avatar: String? = null
        var username: String? = null
        @SerializedName("province_text")
        var provinceText: String? = null
        @SerializedName("city_text")
        var cityText: String? = null
        @SerializedName("country_text")
        var countryText: String? = null
        @SerializedName("category_list")
        var categoryList: List<CategoryListBean>? = null
        @SerializedName("service_list")
        var serviceList: List<ServiceListBean>? = null

        class CategoryListBean {
            /**
             * id : 1
             * name : 婚姻家事
             * status_text :
             */
            var id = 0
            var name: String? = null
            @SerializedName("status_text")
            var statusText: String? = null

            override fun toString(): String {
                return "CategoryListBean(id=$id, name=$name, statusText=$statusText)"
            }


        }

        class ServiceListBean {
            /**
             * id : 1
             * name : 在线咨询
             * desc : 多种咨询方式满足您的所有需求
             * icon_image : /uploads/20200213/22ab77fca36fca265e6cd6998ba1f862.png
             * price : 29.99
             * status_text :
             */
            var id = 0
            var name: String? = null
            var desc: String? = null
            @SerializedName("icon_image")
            var iconImage: String? = null
            var price = 0.0
            @SerializedName("status_text")
            var statusText: String? = null

            override fun toString(): String {
                return "ServiceListBean(id=$id, name=$name, desc=$desc, iconImage=$iconImage, price=$price, statusText=$statusText)"
            }

        }

        override fun toString(): String {
            return "LawyerListBean(id=$id, lawyerId=$lawyerId, lawyerOld=$lawyerOld, lawyerLevel=$lawyerLevel, provinceId=$provinceId, cityId=$cityId, countryId=$countryId, introduce=$introduce, score=$score, legalCategoryIds=$legalCategoryIds, legalServiceIds=$legalServiceIds, legalServiceNum=$legalServiceNum, createTime=$createTime, updateTime=$updateTime, isRecommend=$isRecommend, avatar=$avatar, username=$username, provinceText=$provinceText, cityText=$cityText, countryText=$countryText, categoryList=$categoryList, serviceList=$serviceList)"
        }

    }

    override fun toString(): String {
        return "HomeBean(bannerList=$bannerList, lawLectureList=$lawLectureList, lawyerList=$lawyerList)"
    }


}