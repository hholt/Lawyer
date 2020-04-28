package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.image
import com.wl.lawyer.mvp.model.bean.FuncDataBean

class HomeFuncAdapter( data: MutableList<FuncDataBean>?) :
    BaseQuickAdapter<FuncDataBean, BaseViewHolder>(R.layout.adapter_func, data) {

    override fun convert(helper: BaseViewHolder, item: FuncDataBean?) {
        helper.getView<AppCompatImageView>(R.id.iv_avatar).image(item?.res)
        item?.title?.let {
            helper.getView<AppCompatTextView>(R.id.tv_item_title).text = item?.title
        }
    }

}