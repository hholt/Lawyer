package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.mvp.model.bean.CategoryBean
import com.wl.lawyer.mvp.model.bean.SearchBean

class CategoryAdapter(data: List<CategoryBean>?) :
    BaseQuickAdapter<CategoryBean, BaseViewHolder>(
        R.layout.adapter_province,
        data
    ) {
    var mSelectItemPosition = -1

    override fun convert(helper: BaseViewHolder, item: CategoryBean?) {
        item?.apply {
            helper.getView<AppCompatTextView>(R.id.tv_province).text = name
            if (helper.adapterPosition == mSelectItemPosition)
                helper.getView<AppCompatTextView>(R.id.tv_province).background=mContext.getDrawable(R.drawable.bg_area_selected)
            else
                helper.getView<AppCompatTextView>(R.id.tv_province).setBackground(null)
        }
    }

    fun reset() {
        mSelectItemPosition = -1
    }

    fun getSelectItem() = getItem(mSelectItemPosition)
}