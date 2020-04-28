package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean

class GraphicConsultationAdapter(data: List<GraphicConsultationBean>) :
    BaseQuickAdapter<GraphicConsultationBean, BaseViewHolder>(
        R.layout.adapter_graphic_consultation,
        data
    ) {

    override fun convert(helper: BaseViewHolder, item: GraphicConsultationBean?) {
        item?.avatar?.let {
            helper?.getView<AppCompatImageView>(R.id.iv_avatar).roundedImage(item?.avatar!!, 10)
        }
        item?.title?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_title).text = item?.title
        }
        item?.desc?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_desc).text = item?.desc
        }
        item?.reply?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_reply).text = item?.reply
        }
        item?.time?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_time).text = item?.time
        }
    }

}