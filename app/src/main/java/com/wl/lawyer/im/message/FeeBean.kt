package com.wl.lawyer.im.message

import java.io.Serializable

class FeeBean(val text: String,
              val version: Int,
              val price: String,
              val amount: String,
              val day: String,
              val time: String,
              val orderId: String,
              val orderType: String,
              val avatarUserUrl: String,
              val feeId: String

) : Serializable

/*
*
*{@"text":text,
* @"version":@(11),
* @"price":cellData.price,
* @"amount":cellData.amount,
* @"day":cellData.day,
* @"time":cellData.time,
* @"orderId":cellData.orderId,
* @"orderType":@(cellData.orderType),
* @"avatarUserUrl":cellData.avatarUserUrl,
* @"feeId":cellData.feeId}]
*
* */