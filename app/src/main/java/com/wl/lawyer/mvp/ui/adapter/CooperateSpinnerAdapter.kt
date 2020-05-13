package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import com.skydoves.powerspinner.PowerSpinnerInterface
import com.skydoves.powerspinner.PowerSpinnerView
import com.wl.lawyer.R
import com.wl.lawyer.mvp.model.bean.ConsultlationSetBean
import com.wl.lawyer.mvp.model.bean.CooperateServiceBean

class CooperateSpinnerAdapter(powerSpinnerView: PowerSpinnerView, data: List<CooperateServiceBean>) :
    BaseQuickAdapter<CooperateServiceBean, BaseViewHolder>(
        R.layout.adapter_popview_text,
        data
    ),
    PowerSpinnerInterface<CooperateServiceBean> {

    override var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener<CooperateServiceBean>? =  null
    override val spinnerView: PowerSpinnerView = powerSpinnerView

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    override fun convert(helper: BaseViewHolder, item: CooperateServiceBean?) {
        item?.apply {
            helper.getView<AppCompatTextView>(R.id.tv_simple_desc).text = name
        }
    }

    override fun notifyItemSelected(index: Int) {
        spinnerView.notifyItemSelected(index, mData.get(index).name)
        onSpinnerItemSelectedListener?.onItemSelected(index, mData.get(index))
    }

    override fun setItems(itemList: List<CooperateServiceBean>) = setNewData(itemList)

}