package com.wl.lawyer.mvp.model.bean

class CommonBean {
    var type: Int? = null // 布局类型
    var avatar: String? = null // 头像路径
    var title: String? = null // 标题
    var subTitle: String? = null // 自标题
    var enable: Boolean? = null // 是否可以输入
    var textColor: Int? = null
    var height: Int? = null

    constructor()

    constructor(type: Int?, title: String?) {
        this.type = type
        this.title = title
    }

    constructor(type: Int?, title: String?, subTitle: String?) {
        this.type = type
        this.title = title
        this.subTitle = subTitle
    }

    constructor(type: Int?, title: String?, subTitle: String?, enable: Boolean?) {
        this.type = type
        this.title = title
        this.subTitle = subTitle
        this.enable = enable
    }

    constructor(type: Int?, title: String?, subTitle: String?, textColor: Int?) {
        this.type = type
        this.title = title
        this.subTitle = subTitle
        this.textColor = textColor
    }

    constructor(type: Int?, title: String?, subTitle: String?, avatar: String?) {
        this.type = type
        this.avatar = avatar
        this.title = title
        this.subTitle = subTitle
    }

    constructor(type: Int?, title: String?, subTitle: String?, enable: Boolean?, height: Int?) {
        this.type = type
        this.title = title
        this.subTitle = subTitle
        this.enable = enable
        this.height = height
    }

    companion object {
        // adapter_simple
        const val TYPE_SIMPLE = 100 // type + text + subtext + enable
        // adapter_setting_3
        const val TYPE_SIMPLE_MULTIPLE_TEXT =
            101 // type + text + subtext + enable + height
        // adapter_simple
        const val TYPE_SIMPLE_COLOR = 102 // type + text + subtext + color
        // adapter_simple
        const val TYPE_SIMPLE_CIRCLE_IMAGE = 103 // type + text + avatar + subtext
        // adapter_simple
        const val TYPE_SIMPLE_DOWN_DROP = 104 // type + text
        // adapter_simple_desc
        const val TYPE_SIMPLE_DESC = 105 // type + text
        // adapter_simple_record
        const val TYPE_SIMPLE_RECORD = 106 // type + text
        // adapter_simple_file_image
        const val TYPE_SIMPLE_FILE_IMAGE = 107 // type + text
        // adapter_setting_4
        const val TYPE_SIMPLE_BUTTON = 108
        // adapter_simple_file
        const val TYPE_SIMPLE_FILE = 109
        // adapter_simple_button_hollow
        const val TYPE_SIMPLE_BUTTON_HOLLOW = 110

    }


}