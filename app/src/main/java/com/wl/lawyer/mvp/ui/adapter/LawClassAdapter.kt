package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.mvp.model.bean.LawClassDataBean

/**
 * 法律小课堂
 */
class LawClassAdapter(data: List<LawClassDataBean>) :
    BaseSectionQuickAdapter<LawClassDataBean, BaseViewHolder>(
        R.layout.adapter_law_class,
        R.layout.adapter_law_class_header,
        data
    ) {

    override fun convertHead(helper: BaseViewHolder, item: LawClassDataBean) {
        //header
    }

    override fun convert(helper: BaseViewHolder, item: LawClassDataBean) {
        //item
        item?.title?.let {
            helper.getView<AppCompatTextView>(R.id.tv_law_class_title).text = item?.title
        }
        item?.content?.let {
            helper.getView<AppCompatTextView>(R.id.tv_law_class_content).text = item?.content
        }

    }
}
