package com.wl.lawyer.mvp.ui.adapter

import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.wl.lawyer.R
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.app.image
import com.wl.lawyer.mvp.model.bean.SettingDataBean
import com.wl.lawyer.mvp.ui.widget.LawyerCheckView

class SettingAdapter :
    BaseQuickAdapter<SettingDataBean, BaseViewHolder> {

    constructor(data: List<SettingDataBean>?) : super(data) {
        multiTypeDelegate = object : MultiTypeDelegate<SettingDataBean>() {
            override fun getItemType(t: SettingDataBean?): Int {
                return if (t == null) 0 else t?.type!!
            }
        }
        multiTypeDelegate
            .registerItemType(SettingDataBean.SETTING_TYPE_0, R.layout.adapter_setting)//左边图片 右边文字
            .registerItemType(SettingDataBean.SETTING_TYPE_1, R.layout.adapter_setting_1)//两边是文字
            .registerItemType(SettingDataBean.SETTING_TYPE_2, R.layout.adapter_setting_2)//左边文字 右边图片
            .registerItemType(SettingDataBean.SETTING_TYPE_3, R.layout.adapter_setting_3)//上面文字下面输入框
            .registerItemType(SettingDataBean.SETTING_TYPE_4, R.layout.adapter_setting_4)//按钮
    }

    override fun convert(helper: BaseViewHolder, item: SettingDataBean) {
        when (helper.itemViewType) {
            SettingDataBean.SETTING_TYPE_0 -> {
                item?.drawableRes?.let {
                    helper.getView<AppCompatImageView>(R.id.iv_setting).image(item?.drawableRes)
                }
                item?.path?.let {
                    helper.getView<AppCompatImageView>(R.id.iv_setting).image(item?.path!!)
                }
                item?.text?.let {
                    helper.getView<AppCompatTextView>(R.id.tv_setting_text).text = item?.text
                }

                //最后一条隐藏分割线
                if (helper.layoutPosition == data.size - 1) {
                    helper.getView<View>(R.id.dividing_line).visibility = View.GONE
                }
            }
            SettingDataBean.SETTING_TYPE_1 -> {
                item?.text?.let {
                    helper.getView<AppCompatTextView>(R.id.tv_setting_text).text = item?.text
                }
                item?.subText?.let {
                    helper.getView<AppCompatTextView>(R.id.tv_setting_sub_text).text = item?.subText
                }
            }
            SettingDataBean.SETTING_TYPE_2 -> {
                item?.text?.let {
                    helper.getView<AppCompatTextView>(R.id.tv_setting_text).text = item?.text
                }
//                item?.drawableRes?.let {
//                    helper.getView<AppCompatImageView>(R.id.iv_setting).circleImage(item?.drawableRes)
//                }
                item?.path?.let {
                    helper.getView<AppCompatImageView>(R.id.iv_setting).circleImage(item?.path!!)
                }
            }
            SettingDataBean.SETTING_TYPE_3 -> {// Feedback 类型
                item?.text?.let {
                    helper.getView<AppCompatTextView>(R.id.tv_title).text = item?.text
                }
                item?.hintText?.let {
                    var et = helper.getView<AppCompatEditText>(R.id.et_input)
                    et.hint = item?.hintText
                    et.gravity = Gravity.TOP
                    et.height = item?.inputHeight!!
                }
            }
            SettingDataBean.SETTING_TYPE_4 -> {// 按钮
                item?.text?.let {
                    helper.getView<LawyerCheckView>(R.id.btn_common).text = item?.text
                    // 按钮点击事件如果不传递给onItemClickListener则无效
                    helper.getView<LawyerCheckView>(R.id.btn_common).setOnClickListener {
                        onItemClickListener.onItemClick(this, it, helper.layoutPosition)
                    }
                }
            }

        }
    }

    fun setAvatar(index: Int, path: String) {
        var i = 0
        for (dataBean in data) {
            if (dataBean.type == SettingDataBean.SETTING_TYPE_2) {
                i++
                if (index == i) {
                    dataBean.path = path
                }
            }
        }

        notifyDataSetChanged()
    }

    fun updateSubTextWithPosition(index: Int, newValue: String) {
        if (index >= 0 && index < data.size) {
            val oldDataBean = data[index]
            when (oldDataBean.type) {
                SettingDataBean.SETTING_TYPE_2 -> {
                    oldDataBean.path = newValue
                }
                SettingDataBean.SETTING_TYPE_1 -> {
                    oldDataBean.subText = newValue
                }
            }
            data[index] = oldDataBean
            notifyItemChanged(index)
        }
    }
}
