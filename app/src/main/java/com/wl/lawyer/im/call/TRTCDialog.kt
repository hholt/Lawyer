package com.wl.lawyer.im.call

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.view.WindowManager
import com.tencent.qcloud.tim.uikit.component.dialog.TUIKitDialog
import com.tencent.qcloud.tim.uikit.utils.ToastUtil


class TRTCDialog(private val mContext: Context) : TUIKitDialog(mContext) {

    init {

        builder()
        val lp = dialog.window!!.attributes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        }
        dialog.window!!.attributes = lp

        setCancelable(false)
        setCancelOutside(false)
        setDialogWidth(0.75f)
    }

    fun showSystemDialog(): Boolean {

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(mContext)) {
                ToastUtil.toastLongMessage("请打开设置“允许显示在其他应用的上层”选项")
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(intent)
                return false
            } else {
                // Android6.0以上
                if (!dialog.isShowing) {
                    super.show()
                    return true
                }
            }
        } else {
            // Android6.0以下，不用动态声明权限
            if (!dialog.isShowing) {
                super.show()
                return true
            }
        }
        return false
    }
}
