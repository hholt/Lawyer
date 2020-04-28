package com.wl.lawyer.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.wl.lawyer.R;

public class LabelWidget extends LinearLayout {

    private CharSequence[] textArray;

    public LabelWidget(Context context) {
        super(context);
    }

    public LabelWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LabelWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LabelWidget);
        textArray = a.getTextArray(R.styleable.LabelWidget_labels);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);

        Drawable drawable = context.getResources().getDrawable(R.drawable.shape_label_bg);
        for (int i = 0; i < textArray.length; i++) {
            AppCompatTextView textView = new AppCompatTextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            if (i != 0)
                layoutParams.setMargins(20, 0, 0, 0);
            textView.setLayoutParams(layoutParams);
            textView.setText(textArray[i]);
            textView.setTextSize(12);
            textView.setTextColor(context.getResources().getColor(R.color.app_font_gray));
            textView.setBackgroundDrawable(drawable);
            addView(textView);
        }
    }

}
