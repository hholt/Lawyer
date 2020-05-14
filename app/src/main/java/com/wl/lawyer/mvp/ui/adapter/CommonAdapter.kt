package com.wl.lawyer.mvp.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.jess.arms.utils.ArmsUtils
import com.wl.lawyer.R
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.mvp.model.bean.CommonBean
import com.wl.lawyer.mvp.ui.widget.LawyerCheckView

class CommonAdapter :
    BaseQuickAdapter<CommonBean, BaseViewHolder> {

    constructor(data: MutableList<CommonBean>?) : super(data) {

        multiTypeDelegate = object : MultiTypeDelegate<CommonBean>() {
            override fun getItemType(t: CommonBean?): Int {
                return if (t == null) 0 else t?.type!!
            }
        }

        multiTypeDelegate
            .registerItemType(CommonBean.TYPE_SIMPLE, R.layout.adapter_simple)
            .registerItemType(CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT, R.layout.adapter_setting_3)
            .registerItemType(CommonBean.TYPE_SIMPLE_COLOR, R.layout.adapter_simple)
            .registerItemType(CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE, R.layout.adapter_simple)
            .registerItemType(CommonBean.TYPE_SIMPLE_DOWN_DROP, R.layout.adapter_simple)
            .registerItemType(CommonBean.TYPE_SIMPLE_DESC, R.layout.adapter_simple_desc)
            .registerItemType(CommonBean.TYPE_SIMPLE_RECORD, R.layout.adapter_simple_record)
            .registerItemType(CommonBean.TYPE_SIMPLE_FILE_IMAGE, R.layout.adapter_simple_file_image)
            .registerItemType(CommonBean.TYPE_SIMPLE_BUTTON, R.layout.adapter_setting_4)
            .registerItemType(CommonBean.TYPE_SIMPLE_BUTTON_HOLLOW, R.layout.adapter_simple_botton_hollow)
            .registerItemType(CommonBean.TYPE_SIMPLE_FILE, R.layout.adapter_simple_file)

    }

    override fun convert(helper: BaseViewHolder, item: CommonBean?) {
        when (item?.type) {
            CommonBean.TYPE_SIMPLE,
            CommonBean.TYPE_SIMPLE_COLOR,
            CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
            CommonBean.TYPE_SIMPLE_DOWN_DROP -> {
                item?.title?.let {
                    helper?.getView<AppCompatTextView>(R.id.tv_simple_text).text = it
                }
                item?.subTitle?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_simple_subtext)?.hint = it
                }
                item?.enable?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_simple_subtext)?.isEnabled = it
                }
                item?.textColor?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_simple_subtext)?.setHintTextColor(it)
                }
                item?.subTitle?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_simple_subtext)?.hint = it
                }
                item?.avatar?.let {
                    helper?.getView<AppCompatImageView>(R.id.iv_simple_avatar)?.visibility =
                        View.VISIBLE
                    helper?.getView<AppCompatImageView>(R.id.iv_simple_avatar)?.circleImage(it)
                }
                if (item?.type == CommonBean.TYPE_SIMPLE_DOWN_DROP) {
                    helper?.getView<AppCompatImageView>(R.id.iv_simple_more)?.visibility =
                        View.VISIBLE
                }
            }
            CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT -> {
                item?.title?.let {
                    helper?.getView<AppCompatTextView>(R.id.tv_title).text = it
                }
                item?.subTitle?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_input)?.hint = it
                }
                item?.height?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_input)?.height =
                        ArmsUtils.dip2px(mContext, it * 1f)
                }
                item?.enable?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_input)?.isEnabled = it
                }
            }
            CommonBean.TYPE_SIMPLE_DESC -> {
                item?.title?.let {
                    helper?.getView<AppCompatTextView>(R.id.tv_simple_desc).text = it
                }
            }
            CommonBean.TYPE_SIMPLE_RECORD -> {
                item?.title?.let {
                    helper?.getView<AppCompatTextView>(R.id.tv_simple_record)?.text = it
                }
            }
            CommonBean.TYPE_SIMPLE_FILE_IMAGE -> {
                item?.title?.let {
                    helper?.getView<AppCompatTextView>(R.id.tv_simple_text).text = it
                }
            }
            CommonBean.TYPE_SIMPLE_BUTTON -> {
                item?.title?.let {
                    helper?.getView<LawyerCheckView>(R.id.btn_common).text = it
                }
                helper.addOnClickListener(R.id.btn_common)
            }
            CommonBean.TYPE_SIMPLE_BUTTON_HOLLOW -> {
                item?.title?.let {
                    helper?.getView<AppCompatButton>(R.id.btn_common).text = it
                }
            }
            CommonBean.TYPE_SIMPLE_FILE -> {
                item?.title?.let {
                    helper?.getView<AppCompatTextView>(R.id.tv_choice_file).text = it
                }
                item?.subTitle?.let {
                    helper?.getView<AppCompatEditText>(R.id.et_simple_subtext)?.hint = it
                }
            }

        }
    }

}