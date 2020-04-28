package com.wl.lawyer.mvp.model.bean

import androidx.annotation.DrawableRes

data class BannerDataBean(
    var path: String? = null, @DrawableRes var res: Int? = null,
    var data: HomeBean.BannerListBean?
)