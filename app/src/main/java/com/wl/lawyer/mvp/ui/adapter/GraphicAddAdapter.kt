package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.GraphicAddBean

class GraphicAddAdapter: BaseQuickAdapter<GraphicAddBean, BaseViewHolder> {


    constructor(data: List<GraphicAddBean>) : super(data) {

        multiTypeDelegate = object : MultiTypeDelegate<GraphicAddBean>() {
            override fun getItemType(t: GraphicAddBean?): Int {
                return if (t == null) 0 else t?.type!!
            }
        }

        multiTypeDelegate
            .registerItemType(GraphicAddBean.TYPE_ADD, R.layout.adapter_graphic_add)
            .registerItemType(GraphicAddBean.TYPE_PIC, R.layout.adapter_graphic_new)
    }

    override fun convert(helper: BaseViewHolder, item: GraphicAddBean?) {
        when (item?.type) {
            GraphicAddBean.TYPE_ADD -> {

            }
            GraphicAddBean.TYPE_PIC -> {

            }
        }
    }
}