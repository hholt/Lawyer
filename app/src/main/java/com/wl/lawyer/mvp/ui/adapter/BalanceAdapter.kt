package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wl.lawyer.R
import com.wl.lawyer.mvp.model.bean.BalanceDetailBean

class BalanceAdapter(data: List<BalanceDetailBean>?) :
    BaseQuickAdapter<BalanceDetailBean, BaseViewHolder>(R.layout.adapter_balance, data) {

    override fun convert(helper: BaseViewHolder, item: BalanceDetailBean) {
        item?.type?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_balance_type).text = item?.type
        }
        item?.time?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_balance_time).text = item?.time
        }
        item?.balance?.let {
            helper?.getView<AppCompatTextView>(R.id.tv_balance).text = item?.balance
        }
    }
}
