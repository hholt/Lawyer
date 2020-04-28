package com.wl.lawyer.mvp.ui.dialog;

import androidx.appcompat.app.AppCompatActivity;

import com.wl.base.BaseDialogFragment;
import com.wl.lawyer.R;

public class PaySuccessDialog {

    public static void show(AppCompatActivity activity){
        new BaseDialogFragment.Builder(activity)
                .setContentView(R.layout.dialog_pay_success)
                .show();
    }
}
