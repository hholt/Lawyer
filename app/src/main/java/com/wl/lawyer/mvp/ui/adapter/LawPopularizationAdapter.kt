package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.mvp.model.bean.LawPopularizationDataBean

class LawPopularizationAdapter(data: List<LawPopularizationDataBean>) :
    BaseSectionQuickAdapter<LawPopularizationDataBean, BaseViewHolder>(
        R.layout.adapter_law_popularization,
        R.layout.adapter_recommended_lawyer_header,
        data
    ) {

    override fun convertHead(helper: BaseViewHolder, item: LawPopularizationDataBean) {
        item?.header?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_recommended)?.text = item?.header
        }
    }

    override fun convert(helper: BaseViewHolder, item: LawPopularizationDataBean) {
        item?.avatar?.let {
            //10dp内部会进行自动转换
            helper?.getView<AppCompatImageView>(R.id.iv_popularization_avatar)
                ?.roundedImage(item?.avatar!!, 10)
        }
        item?.header?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_popularization_title)?.text = item?.header
        }
        item?.content?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_popularization_content)?.text = item?.content
        }
    }

}
