package com.wl.lawyer.mvp.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.mvp.model.bean.PopularizationCourseReviewsBean

/**
 *普法课程回复
 */
class PopularizationCourseReviewsAdapter :
    BaseSectionQuickAdapter<PopularizationCourseReviewsBean, BaseViewHolder> {

    override fun convertHead(helper: BaseViewHolder?, item: PopularizationCourseReviewsBean?) {
        item?.header?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_recommended)?.text = it
        }
        helper?.getView<AppCompatTextView>(R.id.tv_more)?.visibility = View.GONE
    }

    constructor(data: List<PopularizationCourseReviewsBean>) : super(
        R.layout.adapter_popularization_course_reviews,
        R.layout.adapter_recommended_lawyer_header,
        data
    )

    override fun convert(helper: BaseViewHolder, item: PopularizationCourseReviewsBean?) {
        item?.avatar?.let {
            helper.getView<AppCompatImageView>(R.id.iv_avatar)
                .circleImage(it)
        }
        item?.title?.let {
            helper.getView<AppCompatTextView>(R.id.tv_title).text = it
        }
        item?.time?.let {
            helper.getView<AppCompatTextView>(R.id.tv_time).text = it
        }
        item?.content?.let {
            helper.getView<AppCompatTextView>(R.id.tv_reviews).text = it
        }
    }

    /*override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, TYPE_HEADER)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        FullSpanUtil.onViewAttachedToWindow(holder, this, TYPE_HEADER)
    }*/


}