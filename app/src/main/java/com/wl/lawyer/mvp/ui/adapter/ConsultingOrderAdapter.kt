package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lxj.androidktx.core.color
import com.wl.lawyer.R
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.ConsultingOrderBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean


class ConsultingOrderAdapter(data: List<MyConsultOrderBean>?) :
    BaseQuickAdapter<MyConsultOrderBean, BaseViewHolder>(R.layout.adapter_consulting_order, data) {

    override fun convert(helper: BaseViewHolder, item: MyConsultOrderBean?) {
        item?.apply {
            helper.getView<AppCompatImageView>(R.id.iv_avatar).circleImage(Api.APP_DOMAIN + lawyerAvatar)
            helper.getView<AppCompatTextView>(R.id.tv_law_name).text = "咨询律师：${lawyerName}律师"
            helper.getView<AppCompatTextView>(R.id.tv_status).text = statusText
            helper.getView<AppCompatTextView>(R.id.tv_consulting_package).text = "咨询套餐：$packageName"
            helper.getView<AppCompatTextView>(R.id.tv_start_time).text = "开始时间：$startTimeText"
            helper.getView<AppCompatTextView>(R.id.tv_end_time).text = "有效时间：$endTimeText"
            if (status == "expired") {
                helper.getView<AppCompatTextView>(R.id.tv_status).background = mContext.getDrawable(R.drawable.shape_state_unavailable)
                helper.getView<AppCompatTextView>(R.id.tv_status).color(R.color.app_order_unavailable)
            } else {
                helper.getView<AppCompatTextView>(R.id.tv_status).background = mContext.getDrawable(R.drawable.shape_state_available)
                helper.getView<AppCompatTextView>(R.id.tv_status).color(R.color.app_order_available)
            }
        }
        /*item?.avatar?.let {
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
        }*/
    }

}