package com.wl.lawyer.mvp.ui.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.wl.lawyer.app.image
import com.wl.lawyer.mvp.model.bean.BannerDataBean
import com.youth.banner.adapter.BannerAdapter


class BannerImageAdapter(datas: MutableList<BannerDataBean>?) :
    BannerAdapter<BannerDataBean, BannerViewHolder>(datas) {
    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        var imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder?,
        data: BannerDataBean?,
        position: Int,
        size: Int
    ) {
        data?.path?.let {
            holder?.imageView?.image(data?.path!!)
        }
        data?.res?.let {
            holder?.imageView?.image(data?.res!!)
        }
    }

}

class BannerViewHolder(@param:NonNull var imageView: ImageView) :
    RecyclerView.ViewHolder(imageView)