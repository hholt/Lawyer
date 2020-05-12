package com.wl.lawyer.mvp.ui.adapter

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.skydoves.powerspinner.PowerSpinnerView
import com.wl.lawyer.R
import com.wl.lawyer.mvp.model.bean.SpecBean

class ClericalServiceAdapter(data: List<SpecBean>?) :
    BaseQuickAdapter<SpecBean, BaseViewHolder>(
        R.layout.adapter_clerical_service,
        data
    ) {
    var arr = arrayListOf<Int>()

    var selectListener: ((arr: List<Int>)->Unit)? = null

    override fun convert(helper: BaseViewHolder, item: SpecBean?) {
        item?.apply {
            helper.getView<AppCompatTextView>(R.id.tv_type).text = "$name："
            helper.getView<PowerSpinnerView>(R.id.psv_type).apply {
                if (arr.size == helper.adapterPosition) {
                    arr.add(item.children[0].id)
                    text = item.children[0].name
                    if (arr.size == data.size) selectListener?.invoke(arr)
                    setSpinnerAdapter(
                        ServiceSpinnerAdapter(this, children)
                            .apply {
                                setOnItemClickListener { adpter, view, pos ->

                                    arr[helper.adapterPosition] = children[pos].id
                                    selectListener?.invoke(arr)
                                    this.notifyItemSelected(pos)
                                }
                            }
                    )
                }
                setOnSpinnerOutsideTouchListener { _, _ ->
                    dismiss()
                }
            }
        }
    }

}



