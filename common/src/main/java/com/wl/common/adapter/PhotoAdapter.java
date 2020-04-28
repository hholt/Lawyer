package com.wl.common.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.image.ImageLoader;
import com.wl.common.R;
import com.wl.common.common.MyRecyclerViewAdapter;

import java.util.List;


/**
 *    desc   : 图片选择适配器
 */
public final class PhotoAdapter extends MyRecyclerViewAdapter<String> {

    private final List<String> mSelectPhoto;

    public PhotoAdapter(Context context, List<String> data) {
        super(context);
        mSelectPhoto = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    final class ViewHolder extends MyRecyclerViewAdapter.ViewHolder {

        ImageView mImageView;
        CheckBox mCheckBox;

        ViewHolder() {
            super(R.layout.item_photo);
        }

        @Override
        public void onBindView(int position) {
            mImageView = itemView.findViewById(R.id.iv_photo_image);
            mCheckBox = itemView.findViewById(R.id.iv_photo_check);
            ImageLoader.with(getContext())
                    .load(getItem(position))
                    .into(mImageView);

            mCheckBox.setChecked(mSelectPhoto.contains(getItem(position)));
        }
    }

    @Override
    protected RecyclerView.LayoutManager getDefaultLayoutManager(Context context) {
        return new GridLayoutManager(context, 3);
    }
}