package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lxj.androidktx.core.toDateString
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.app.toTime
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean

class LawyerArticleAdapter(data: List<LawyerArticleDetailBean>) :
    BaseQuickAdapter<LawyerArticleDetailBean, BaseViewHolder>(
        R.layout.adapter_lawyer_article,
        data
    ) {


    override fun convert(helper: BaseViewHolder, item: LawyerArticleDetailBean) {
        item?.apply {
            //10dp内部会进行自动转换
            helper?.getView<AppCompatImageView>(R.id.iv_article_avatar)
                ?.roundedImage(Api.APP_DOMAIN + coverImage, 10)
            helper?.getView<AppCompatTextView>(R.id.tv_article_title)?.text = title
            helper?.getView<AppCompatTextView>(R.id.tv_article_content)?.text = desc
            helper?.getView<AppCompatTextView>(R.id.tv_article_date)?.text = createTime.toTime("yyyy.MM.dd")
            helper?.getView<AppCompatTextView>(R.id.tv_article_view_count)?.text = "浏览次数：${viewCount}"
        }
    }

}
