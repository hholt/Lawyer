package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.mvp.model.bean.ConsultingOrderBean


class ConsultingOrderAdapter(data: List<ConsultingOrderBean>?) :
    BaseQuickAdapter<ConsultingOrderBean, BaseViewHolder>(R.layout.adapter_consulting_order, data) {

    override fun convert(helper: BaseViewHolder, item: ConsultingOrderBean?) {
        item?.avatar?.let {
            helper.getView<AppCompatImageView>(R.id.iv_avatar).circleImage(item?.avatar!!)
        }
        item?.lawName?.let {
            helper.getView<AppCompatTextView>(R.id.tv_law_name).text = item?.lawName
        }
        item?.status?.let {
            helper.getView<AppCompatTextView>(R.id.tv_status).text = item?.status
        }
        item?.consultingPackage?.let {
            helper.getView<AppCompatTextView>(R.id.tv_consulting_package).text =
                item?.consultingPackage
        }
        item?.startTime?.let {
            helper.getView<AppCompatTextView>(R.id.tv_start_time).text = item?.startTime
        }
        item?.endTime?.let {
            helper.getView<AppCompatTextView>(R.id.tv_end_time).text = item?.endTime
        }
    }

}