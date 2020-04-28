package com.wl.lawyer.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.wl.lawyer.R;

public class DownDropWidget extends LinearLayout {

    public DownDropWidget(Context context) {
        super(context);
    }

    public DownDropWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DownDropWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DownDropWidget);
        String string = a.getString(R.styleable.DownDropWidget_dp_text);
        a.recycle();

        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);

//        AppCompatTextView textView = new AppCompatTextView(context);
//        textView.setLayoutParams(new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        ));
//        textView.setTextSize(ArmsUtils.px2sp(context, ArmsUtils.dip2px(context, 18)));
//        textView.setText(string);
//
//        AppCompatImageView imageView = new AppCompatImageView(context);
//        LinearLayout.LayoutParams layoutParams = new LayoutParams(
//                ArmsUtils.dip2px(context, 14),
//                ArmsUtils.dip2px(context, 14)
//        );
//
//        layoutParams.setMargins(ArmsUtils.dip2px(context, 4), 0, 0, 0);
//        imageView.setLayoutParams(layoutParams);
//        imageView.setImageResource(R.drawable.ic_down_drop);
//
//        addView(textView);
//        addView(imageView);

        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_down_drop, null);
        AppCompatTextView textView = rootView.findViewById(R.id.tv_text);
        textView.setText(string);

        addView(rootView);
    }

}
