package com.wl.lawyer.mvp.model.bean

import androidx.annotation.DrawableRes

class SettingDataBean {
    @DrawableRes
    var drawableRes: Int? = null
    var path: String? = null
    var text: String? = null
    var type: Int? = null
    var subText: String? = null

    var hintText:String? = null
    var inputHeight:Int? = null

    constructor(drawableRes: Int?, path: String?, text: String?, type: Int?, subText: String?) {
        this.drawableRes = drawableRes
        this.path = path
        this.text = text
        this.type = type
        this.subText = subText
    }

    constructor(text: String?, type: Int?, hintText: String?, inputHeight: Int?) {
        this.text = text
        this.type = type
        this.hintText = hintText
        this.inputHeight = inputHeight
    }

    constructor(text: String?, type: Int?) {
        this.text = text
        this.type = type
    }

    companion object {
        const val SETTING_TYPE_0 = 0 // 左边图片 右边文字
        const val SETTING_TYPE_1 = 1 // 两边是文字
        const val SETTING_TYPE_2 = 2 // 左边文字 右边图片
        const val SETTING_TYPE_3 = 3 // 上边文字 下边输入框 需要指定输入框高度inputHeight hintText
        const val SETTING_TYPE_4 = 4 // 确认按钮 需要指定按钮文字text
    }

}