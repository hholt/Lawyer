package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.app.toTime
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import kotlin.time.days

class GraphicConsultationAdapter(data: List<GraphicConsultationBean>) :
    BaseQuickAdapter<GraphicConsultationBean, BaseViewHolder>(
        R.layout.adapter_graphic_consultation,
        data
    ) {

    override fun convert(helper: BaseViewHolder, item: GraphicConsultationBean?) {
        item?.apply {
            helper?.getView<AppCompatImageView>(R.id.iv_avatar).roundedImage(Api.APP_DOMAIN + cover, 10)
            helper?.getView<AppCompatTextView>(R.id.tv_title).text = title
            helper?.getView<AppCompatTextView>(R.id.tv_desc).text = content
            helper?.getView<AppCompatTextView>(R.id.tv_reply).text = "${lawyerCommentCount}条专业回复"
            helper?.getView<AppCompatTextView>(R.id.tv_time).text = createTime.toTime("yyyy.MM.dd")
        }
        /*item?.avatar?.let {
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
        }*/
    }

}