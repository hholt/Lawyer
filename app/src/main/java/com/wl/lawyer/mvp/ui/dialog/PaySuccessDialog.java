package com.wl.lawyer.mvp.ui.dialog;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wl.base.BaseDialog;
import com.wl.base.BaseDialogFragment;
import com.wl.base.BaseRecyclerViewAdapter;
import com.wl.common.common.MyDialogFragment;
import com.wl.lawyer.R;

public class PaySuccessDialog {

    /*public static void show(AppCompatActivity activity){
        new BaseDialogFragment.Builder(activity)
                .setContentView(R.layout.dialog_pay_success)
                .
                .show();
    }*/

    public static final class Builder
            extends MyDialogFragment.Builder<Builder>
            implements View.OnClickListener {

        private OnListener mListener;
        private boolean mAutoDismiss = true;

        private final TextView mCancelView;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_pay_success);
            mCancelView = findViewById(R.id.tv_go);
            mCancelView.setOnClickListener(this);
        }

        public Builder setCancel(@StringRes int id) {
            return setCancel(getString(id));
        }

        public Builder setCancel(CharSequence text) {
            mCancelView.setText(text);
            return this;
        }

        public Builder setAutoDismiss(boolean dismiss) {
            mAutoDismiss = dismiss;
            return this;
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        @Override
        public void onClick(View v) {
            if (mAutoDismiss) dismiss();
            if (v == mCancelView) {
                if (mListener != null) {
                    mListener.onCancel(getDialog());
                }
            }
        }
    }

    public interface OnListener {

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);
    }

}
