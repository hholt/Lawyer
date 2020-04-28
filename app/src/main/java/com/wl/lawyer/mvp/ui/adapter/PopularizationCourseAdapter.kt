package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.mvp.model.bean.PopularizationCourseBean

/**
 * 普法课程列表
 */
class PopularizationCourseAdapter(data: List<PopularizationCourseBean>) :
    BaseQuickAdapter<PopularizationCourseBean, BaseViewHolder>(
        R.layout.adapter_popularization_course,
        data
    ) {
    override fun convert(helper: BaseViewHolder, item: PopularizationCourseBean?) {
        item?.img?.let {
            helper?.getView<AppCompatImageView>(R.id.iv_img).roundedImage(item?.img!!, 10)
        }
        item?.title?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_title).text = item?.title
        }
        item?.desc?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_desc).text = item?.desc
        }
    }

}