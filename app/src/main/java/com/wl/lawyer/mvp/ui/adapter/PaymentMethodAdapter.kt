package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.app.image
import com.wl.lawyer.app.mlog
import com.wl.lawyer.mvp.model.bean.PaymentMethodBean

class PaymentMethodAdapter(data: List<PaymentMethodBean>?) :
    BaseQuickAdapter<PaymentMethodBean, BaseViewHolder>(R.layout.adapter_payment_method, data) {

    var currentPosition = -1

    init {
        defaultPayType(data)
    }

    private fun defaultPayType(data: List<PaymentMethodBean>?) {
        data?.let {
            for ((index, value) in it.withIndex()) {
                mlog("index $index value ${value.default}")
                if (value?.default != false) {
                    currentPosition = index
                }
            }
        }
    }

    override fun convert(helper: BaseViewHolder, item: PaymentMethodBean) {
        item?.paymentRes?.let {
            helper?.getView<AppCompatImageView>(R.id.iv_payment).image(item?.paymentRes)
        }
        item?.let {
            var imageView = helper?.getView<AppCompatImageView>(R.id.iv_selected)
            if (currentPosition == helper.layoutPosition) {
                imageView.image(R.drawable.ic_circle_selected)
            } else {
                imageView.image(R.drawable.ic_circle_un_selected)
            }
        }
        item?.paymentName?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_payment).text = item?.paymentName
        }
    }

    override fun setNewData(data: MutableList<PaymentMethodBean>?) {
        defaultPayType(data)
        super.setNewData(data)
    }

}
