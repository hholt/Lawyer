package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LiveListBean(
    val total: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("current_page") val pageCount: Int,
    @SerializedName("last_page") val lastPage: Int,
    @SerializedName("data") val liveList: List<LiveBean>
) : Serializable {
}
/*
*
* "total": 1,
    "per_page": 10,
    "current_page": 1,
    "last_page": 1,
    "data": [
      {
        "id": 10009,
        "image": "/uploads/20200227/725e66cdc8c7b590529b530ea8bd1140.png",
        "name": "关于我们",
        "desc": "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300...",
        "im_group_id": "",
        "push_address": "rtmp://push.gouxingyun.cn/live/10009?txSecret=fffc8809b75bef8f1edcdf7868e261fe&txTime=5ECE1CD8",
        "start_time": 1588751704,
        "end_time": 1590566104,
        "live_status": 0,
        "start_time_text": "2020-05-06 15:55:04",
        "end_time_text": "2020-05-27 15:55:04",
        "live_status_text": "未开播"
      }
*
* */