package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.wl.lawyer.R
import com.wl.lawyer.app.roundedImage
import com.wl.lawyer.app.toTime
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.CommentAdapterBean

class CommentAdapter:
    BaseQuickAdapter<CommentAdapterBean, BaseViewHolder> {
    constructor(data: MutableList<CommentAdapterBean>?) : super(data) {

        multiTypeDelegate = object : MultiTypeDelegate<CommentAdapterBean>() {
            override fun getItemType(t: CommentAdapterBean?): Int {
                return if (t == null) 0 else t?.type!!
            }
        }

        multiTypeDelegate
            .registerItemType(CommentAdapterBean.TYPE_LAWYER, R.layout.adapter_comment_lawyer)
            .registerItemType(CommentAdapterBean.TYPE_REPLY, R.layout.adapter_comment_reply)
    }

    override fun convert(helper: BaseViewHolder, item: CommentAdapterBean?) {
        when (item?.type) {
            CommentAdapterBean.TYPE_LAWYER -> {
                item.commentBean.apply {
                    helper.getView<AppCompatImageView>(R.id.iv_lawyer_avatar).roundedImage(Api.APP_DOMAIN + user.avatar, 18)
                    helper.getView<AppCompatTextView>(R.id.tv_lawyer_name).text = user.nickname
                    helper.getView<AppCompatTextView>(R.id.tv_lawyer_reply_count).text = "回复次数：${commentCount}次"
                    helper.getView<AppCompatTextView>(R.id.tv_lawyer_reply_time).text = createtime.toTime()
                    helper.getView<AppCompatTextView>(R.id.tv_lawyer_reply).text = content
                }
            }
            CommentAdapterBean.TYPE_REPLY -> {
                item.commentBean.apply {
                    helper.getView<AppCompatImageView>(R.id.iv_user_avatar).roundedImage(Api.APP_DOMAIN + user.avatar, 13)
                    helper.getView<AppCompatTextView>(R.id.tv_user_name).text = user.nickname
                    helper.getView<AppCompatTextView>(R.id.tv_reply_name).text = toUser?.nickname
                    helper.getView<AppCompatTextView>(R.id.tv_user_reply_time).text = createtime.toTime()
                    helper.getView<AppCompatTextView>(R.id.tv_user_reply).text = content
                }

            }
            else -> {}
        }
    }
}