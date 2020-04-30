package com.wl.lawyer.mvp.ui.adapter

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter

class SetSpinnerAdapter<T>(val resId: Int, data: List<T>): BaseAdapter(), SpinnerAdapter {

    var mData: List<T>
    var mHandler: ((T)->String)? = null
    init {
        mData = data
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        convertView?.let{
            return convertView
        }
        return LayoutInflater.from(parent?.context).inflate(resId, parent, false)
    }

    override fun getItem(position: Int): Any {
        return mData.get(position) as Any
    }

    override fun getItemId(position: Int) = position as Long

    override fun getCount() = mData.size


    override fun isEmpty() = mData?.size != 0



}