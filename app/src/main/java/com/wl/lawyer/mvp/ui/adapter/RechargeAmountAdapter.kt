package com.wl.lawyer.mvp.ui.adapter

import android.graphics.Color
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.mlog

class RechargeAmountAdapter(data: List<Int>?) :
    BaseQuickAdapter<Int, BaseViewHolder>(R.layout.adapter_recharge_amount, data) {

    var currentPosition: Int = -1

    private val selectColor by lazy {
        Color.parseColor("#1B76FB")
    }

    private val unSelectColor by lazy {
        Color.parseColor("#ECEDF4")
    }

    override fun convert(helper: BaseViewHolder, item: Int?) {
        item?.let {
            mlog("update data value $item")
            helper.getView<AppCompatTextView>(R.id.tv_amount).text = "$item"
            if (currentPosition == helper.layoutPosition) {
                helper.getView<LinearLayoutCompat>(R.id.ll_amount).background =
                    mContext.getDrawable(R.drawable.shape_amount_selected)
                helper.getView<AppCompatTextView>(R.id.tv_amount).setTextColor(selectColor)
                helper.getView<AppCompatTextView>(R.id.tv_amount_unit).setTextColor(selectColor)
            } else {
                helper.getView<LinearLayoutCompat>(R.id.ll_amount).background =
                    mContext.getDrawable(R.drawable.shape_amount_un_selected)
                helper.getView<AppCompatTextView>(R.id.tv_amount).setTextColor(unSelectColor)
                helper.getView<AppCompatTextView>(R.id.tv_amount_unit).setTextColor(unSelectColor)
            }
        }
    }

}