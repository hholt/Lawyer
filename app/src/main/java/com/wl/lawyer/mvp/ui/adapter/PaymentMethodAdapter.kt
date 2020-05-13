package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.image
import com.wl.lawyer.app.mlog
import com.wl.lawyer.mvp.model.bean.PayTypeBean
import com.wl.lawyer.mvp.model.bean.PaymentMethodBean

class PaymentMethodAdapter(data: List<PayTypeBean>?) :
    BaseQuickAdapter<PayTypeBean, BaseViewHolder>(R.layout.adapter_payment_method, data) {

    var currentPosition = -1

    init {
        defaultPayType(data)
    }

    private fun defaultPayType(data: List<PayTypeBean>?) {
        data?.let {
            for ((index, value) in it.withIndex()) {
                mlog("index $index value ${value.default}")
                if (value.default) {
                    currentPosition = index
                }
            }
        }
    }

    override fun convert(helper: BaseViewHolder, item: PayTypeBean) {
        item.apply {
            helper?.getView<AppCompatImageView>(R.id.iv_payment).image(image)
            helper?.getView<AppCompatTextView>(R.id.tv_payment).text = name
            helper?.getView<AppCompatImageView>(R.id.iv_selected).image(
                if (currentPosition == helper.layoutPosition) R.drawable.ic_circle_selected else R.drawable.ic_circle_un_selected
            )
        }
    }

    override fun setNewData(data: List<PayTypeBean>?) {
        defaultPayType(data)
        super.setNewData(data)
    }

    fun getSelectPayType() = mData[currentPosition]

}
