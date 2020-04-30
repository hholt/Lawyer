package com.wl.lawyer.app

class AppConstant {
    companion object {
        const val TENCENT_LIVE_LICENCE_KEY: String = "9a3719044e68b75b59089a2f88b610e0"
        const val TENCENT_LIVE_LICENCE_URL: String =
            "http://license.vod2.myqcloud.com/license/v1/0dc38e01207e313a66523e3d0a0ad123/TXLiveSDK.licence"
        const val TENCENT_IM_SDK_ID: Int = 1400307065
        //应用包名
        const val PACKAGE_NAME_QQ: String = "com.tencent.mobileqq"
        const val PACKAGE_NAME_WEIBO: String = "com.sina.weibo"
        const val PACKAGE_NAME_WECHAT: String = "com.tencent.mm"

        const val USER_ID: String = "9"
        const val FRIEND_ID: String = "4"
        const val PAY_ALIPAY = "alipay"
        const val PAY_WECHAT = "wechat"

        /**
         * /////////////////////////////////////intent/////////////////////////////////////
         */
        // 直播普法 普法课程
        const val INTENT_POPULARIZATION = "PopularizationCourse"
        const val KEY_POPULARIZATION = "popularization"
        const val KEY_LIVE = "live"
        // 编辑页面
        const val INTENT_EDIT = "intent_edit"
        const val KEY_PERSONAL_PROFILE = "personal_profile"
        const val REQUEST_CODE_PERSONAL_PROFILE = 1000
        const val KEY_NICKNAME = "nickname"
        const val REQUEST_CODE_NICKNAME = 1001
        // 跳转注册界面还是忘记密码界面（同一个界面）true 忘记密码 不传默认为注册界面
        const val INTENT_FORGET_OR_QUICK_REGISTER: String = "intent_forget_or_quick_register"
        // 编辑页面返回设置的result
        const val INTENT_EDIT_VALUE: String = "edit_result_value"
        // 跳转输入地址页面
        const val KEY_ADDRESS: String = "key_address"
        const val REQUEST_CODE_ADDRESS: Int = 2001

        /**
         * /////////////////////////////////////intent/////////////////////////////////////
         */
        // 律师服务类型
        const val SERVICE_ID_CONSULTATION = 1
        const val SERVICE_ID_COLLABORATION = 2
        const val SERVICE_ID_COOPERATION = 3
        const val SERVICE_ID_CASE = 4

        /**
         * /////////////////////////////////////SP key/////////////////////////////////////
         */
        const val SP_USER: String = "sp_user"

        /**
         * /////////////////////////////////////Exception key/////////////////////////////////////
         */
        // token 过期
        const val E_TOKEN_EXPRIED = 3
        // 请求失败
        const val E_FAILED = 2
    }
}
