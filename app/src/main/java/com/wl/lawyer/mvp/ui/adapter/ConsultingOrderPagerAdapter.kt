package com.wl.lawyer.mvp.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.viewpager.widget.PagerAdapter
import com.chad.library.adapter.base.BaseQuickAdapter

import com.wl.lawyer.R
import com.wl.lawyer.mvp.model.bean.ConsultingOrderBean

/**
 * 咨询订单ViewPager
 */
class ConsultingOrderPagerAdapter(private val mDataList: List<String>?) : PagerAdapter() {

    var onClickListener: OnClickListener? = null

    override fun getCount(): Int {
        return mDataList?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    private val adapter by lazy {
        ConsultingOrderAdapter(
            arrayListOf(
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    else -> {
                        // 订单详情
                        onClickListener?.let {
                            it.onClick(null, position)
                        }
                    }
                }
            }
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // 每个咨询页内容可不一样
        val rootView =
            LayoutInflater.from(container.context)
                .inflate(R.layout.adapter_consulting_order_page, null)
        val rvItem = rootView.findViewById<RecyclerView>(R.id.rv_item)
        rvItem.layoutManager = LinearLayoutManager(container.context)
        rvItem.adapter = adapter
        container.addView(rootView)
        return rootView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        val textView = `object` as TextView
        val text = textView.text.toString()
        val index = mDataList!!.indexOf(text)
        return if (index >= 0) {
            index
        } else PagerAdapter.POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mDataList!![position]
    }

    interface OnClickListener {
        fun onClick(type: Int?, position: Int?)
    }
}
