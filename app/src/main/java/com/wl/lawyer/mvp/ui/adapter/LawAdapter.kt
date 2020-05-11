package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.CategoryBean
import com.wl.lawyer.mvp.model.bean.LawyerBean
import com.wl.lawyer.mvp.model.bean.RecommendedLawyerDataBean
import com.wl.lawyer.mvp.ui.widget.LabelWidget

class LawAdapter(data: List<LawyerBean>?) :
    BaseQuickAdapter<LawyerBean, BaseViewHolder>(
        R.layout.adapter_recommended_lawyer,
        data
    ) {

    override fun convert(helper: BaseViewHolder, item: LawyerBean) {
        item?.let {
            helper.getView<AppCompatImageView>(R.id.iv_recommended_avatar)
                .circleImage(Api.APP_DOMAIN + it.avatar)
            helper.getView<AppCompatTextView>(R.id.tv_name).text = it.nickname
            helper.getView<AppCompatTextView>(R.id.tv_time).text =
                "执业${it.lawyerOld}年  |  ${it.cityText + it.countryText}"
            when (it.isRecommend) {
                1 -> helper.getView<AppCompatImageView>(R.id.iv_recommended_approve).setImageResource(
                    R.drawable.ic_online
                )
                else -> helper.getView<AppCompatImageView>(R.id.iv_recommended_approve).setImageResource(
                    R.drawable.ic_offline
                )
            }
            it.categoryList?.let {
                var categories = arrayListOf<String?>()
                for (category: CategoryBean in it) {
                    categories.add(category.name)
                }
                helper.getView<LabelWidget>(R.id.lw_type).setData(categories)
            }
        }

        /*item?.avator?.let {
            helper.getView<AppCompatImageView>(R.id.iv_recommended_avatar)
                .circleImage(item?.avator!!)
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
        }*/
    }
}
