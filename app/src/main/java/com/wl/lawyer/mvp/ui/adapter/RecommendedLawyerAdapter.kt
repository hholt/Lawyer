package com.wl.lawyer.mvp.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.mvp.model.bean.RecommendedLawyerDataBean


class RecommendedLawyerAdapter(
    data: MutableList<RecommendedLawyerDataBean>?
) :
    BaseSectionQuickAdapter<RecommendedLawyerDataBean, BaseViewHolder>(
        R.layout.adapter_recommended_lawyer,
        R.layout.adapter_recommended_lawyer_header,
        data
    ) {
    override fun convertHead(helper: BaseViewHolder?, item: RecommendedLawyerDataBean?) {
        item?.header?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_recommended)?.text = item?.header
        }
    }

    override fun convert(helper: BaseViewHolder, item: RecommendedLawyerDataBean?) {
        item?.avator?.let {
            helper.getView<AppCompatImageView>(R.id.iv_recommended_avatar).circleImage(item?.avator!!)
        }
        item?.name?.let {
            helper.getView<AppCompatTextView>(R.id.tv_name).text = item?.name
        }
        item?.price?.let {
            helper.getView<AppCompatTextView>(R.id.tv_price).text = item?.price
        }
        item?.time?.let {
            helper.getView<AppCompatTextView>(R.id.tv_time).text =
                "执业${item?.time}年  |  ${item?.address}"
        }
        //最后一条隐藏分割线
        if (helper.layoutPosition == data.size - 1) {
            helper.getView<View>(R.id.dividing_line).visibility = View.GONE
        }
    }

}