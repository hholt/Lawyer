package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LawyerBean(
    val id: Int,
    @SerializedName("lawyer_id") val lawyerId: Int,
    @SerializedName("lawyer_old") val lawyerOld: Int,
    @SerializedName("lawyer_level") val lawyerLevel: Int,
    @SerializedName("province_id") val provinceId: Int,
    @SerializedName("city_id") val cityId: Int,
    @SerializedName("country_id") val countryId: Int,
    val introduce: String,
    val score: String,
    @SerializedName("legal_category_ids") val legalCategoryIds: String,
    @SerializedName("legal_service_ids") val legalServiceIds: String,
    @SerializedName("legal_service_num") val legalServiceNum: Int,
    @SerializedName("business_license") val businessLicense: Any,
    @SerializedName("createtime") val createTime: Any,
    @SerializedName("updatetime") val updateTime: Int,
    val avatar: String,
    val username: String,
    val nickname: String,
    @SerializedName("is_recommend") val isRecommend: Int,
    @SerializedName("lawyer_office") val lawyerOffice: String,
    @SerializedName("province_text") val provinceText: String,
    @SerializedName("city_text") val cityText: String,
    @SerializedName("country_text") val countryText: String,
    @SerializedName("category_list") val categoryList: List<CategoryBean>,
    @SerializedName("service_list") val serviceList: List<LawyerServiceBean>
) :
    Serializable

/**
 *  "id": 2,
 * "lawyer_id": 13,
 * "lawyer_old": 4,
 * "lawyer_level": 0,
 * "province_id": 801,
 * "city_id": 802,
 * "country_id": 808,
 * "introduce": "勤奋踏实，细致谨慎，能吃苦耐劳，上进心与学习能力较强。 法律功底扎实，实践经验丰富，兼顾诉讼与非诉业务且均较为熟练，口才好，擅长商业谈判，擅长销售、租赁、并购等房地产传统业务，透彻了解企业融资方案的实际、风险的把控、熟悉担保业务、工作认真负责。",
 * "score": "95.00%",
 * "legal_category_ids": "1,2",
 * "legal_service_ids": "1,2,3,4",
 * "legal_service_num": 22,
 * "business_license": null,
 * "createtime": null,
 * "updatetime": 1581733985,
 * "avatar": "/uploads/20200318/e794c4d1d49c6718585beb5efae2b685.jpg",
 * "username": "111",
 * "nickname": "于小伟",
 * "is_recommend": 1,
 * "lawyer_office": "",
 * "province_text": "上海",
 * "city_text": "上海市",
 * "country_text": "闸北区",
 * "category_list": [
 */