package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.mvp.model.api.Api

class GraphicAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_graphic, data){

    override fun convert(helper: BaseViewHolder, item: String?) {
        item?.let {
            helper.getView<AppCompatImageView>(R.id.iv_graphic).roundedImage(Api.APP_DOMAIN + it, 10)
        }
    }
}