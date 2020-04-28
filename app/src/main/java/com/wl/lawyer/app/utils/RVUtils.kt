package com.wl.lawyer.app.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.wl.lawyer.R

class RVUtils {
    companion object {
        /**
         * 设置RecyclerView Footer View
         */
        fun myFooterView(context: Context?, viewGroup: ViewGroup?): View {
            return LayoutInflater.from(context)
                .inflate(R.layout.include_law_footer, viewGroup, false)
        }

        /**
         * 设置RecyclerView 分割线
         */
        fun myDivider(context: Context, rv: RecyclerView?) {
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_divider)!!)
            rv?.addItemDecoration(divider)
        }

        /**
         * 设置RecyclerView 分割虚线
         */
        fun myDottedDivider(context: Context, rv: RecyclerView?) {
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_dotted_line)!!)
            rv?.addItemDecoration(divider)
        }

    }
}
