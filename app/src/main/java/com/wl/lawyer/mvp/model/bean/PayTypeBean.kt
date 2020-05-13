package com.wl.lawyer.mvp.model.bean

import java.io.Serializable

data class PayTypeBean(
    val name: String,
    val value: String,
    val image: String,
    val default: Boolean
) : Serializable
/*
*
* "name": "微信支付",
  "value": "wechat",
  "image": "http://www.lawyer.test/assets/addons/recharge/imgs/wechat.png",
  "default": true
*
* */