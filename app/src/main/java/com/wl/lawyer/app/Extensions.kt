package com.wl.lawyer.app

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.jess.arms.integration.AppManager
import com.wl.common.widget.MenuDialog
import com.wl.lawyer.BuildConfig
import com.wl.lawyer.R
import com.wl.lawyer.app.utils.GlideRoundTransform
import com.wl.lawyer.mvp.ui.activity.PayActivity
import com.wl.lawyer.mvp.ui.dialog.PaySuccessDialog
import java.text.SimpleDateFormat
import java.util.*

var tag = "lawyer-log"

fun Rect.string(): String {
    return "left:${this.left} top:${this.top} right:${this.right} bottom:${this.bottom} "
}

fun Any.mlog(any: Any) {
    if (BuildConfig.DEBUG) {
        var classname = ""
        if (this.javaClass.simpleName != null) {
            classname = this.javaClass.simpleName
        } else if (this.javaClass.superclass.simpleName != null) {
            classname = "Supper class(${this.javaClass.superclass.simpleName})"
        }
        if (classname.isNullOrBlank()) {
            Log.i(tag, "Null -> ${any as String}")
        } else {
            Log.i(tag, "$classname -> ${any as String}")
        }
    }
}

fun Any.mloge(any: Any) {
    if (BuildConfig.DEBUG) {
        var classname = ""
        if (this.javaClass.simpleName != null) {
            classname = this.javaClass.simpleName
        } else if (this.javaClass.superclass.simpleName != null) {
            classname = "Supper class(${this.javaClass.superclass.simpleName})"
        }
        if (classname.isNullOrBlank()) {
            Log.e(tag, "Null -> ${any as String}")
        } else {
            Log.e(tag, "$classname -> ${any as String}")
        }
    }
}

fun ImageView.image(path: String) {
    val options = RequestOptions()
//        .placeholder(R.drawable.icon_avatar)
//        //异常占位图(当加载异常的时候出现的图片)
//        .error(R.drawable.icon_avatar)
//        .transform(CircleCrop())
        //禁止Glide硬盘缓存缓存
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    Glide.with(context)
        .load(path)
        .apply(options)
        .into(this)
}

fun ImageView.circleImage(path: String) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_my_avatar)
        .error(R.drawable.ic_mine)
//        .placeholder(R.drawable.icon_avatar)
//        //异常占位图(当加载异常的时候出现的图片)
//        .error(R.drawable.icon_avatar)
        .transform(CircleCrop())
        //禁止Glide硬盘缓存缓存
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    Glide.with(context)
        .load(path)
        .apply(options)
        .into(this)
}

fun ImageView.circleImage(base: String, path: String) {
    val options = RequestOptions()
//        .placeholder(R.drawable.icon_avatar)
//        //异常占位图(当加载异常的时候出现的图片)
//        .error(R.drawable.icon_avatar)
        .transform(CircleCrop())
        //禁止Glide硬盘缓存缓存
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    Glide.with(context)
        .load(base + path)
        .apply(options)
        .into(this)
}

fun ImageView.circleImage(path: Int) {
    val options = RequestOptions()
//        .placeholder(R.drawable.icon_avatar)
//        //异常占位图(当加载异常的时候出现的图片)
//        .error(R.drawable.icon_avatar)
        .transform(CircleCrop())
        //禁止Glide硬盘缓存缓存
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    Glide.with(context)
        .load(path)
        .apply(options)
        .into(this)
}

/**
 * @param rounder : dp
 */
fun ImageView.roundedImage(path: String, rounder: Int) {
//    val options = RequestOptions()
//        .placeholder(R.drawable.icon_avatar)
//        异常占位图(当加载异常的时候出现的图片)
//        .error(R.drawable.icon_avatar)
//        禁止Glide硬盘缓存缓存
//        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//        .transform(RoundedCorners(rounder))
//    val options = RequestOptions.bitmapTransform(RoundedCorners(rounder))
//        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    val options = RequestOptions()
        .placeholder(R.drawable.ic_my_avatar)
        .error(R.drawable.ic_mine)
        .transform(GlideRoundTransform(rounder))
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    Glide.with(context)
        .load(path)
        .apply(options)
        .into(this)
}

fun ImageView.image(path: Int?) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_my_avatar)
//        //异常占位图(当加载异常的时候出现的图片)
        .error(R.drawable.ic_mine)
//        .transform(CircleCrop())
        //禁止Glide硬盘缓存缓存
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    Glide.with(context)
        .load(path!!)
        .apply(options)
        .into(this)

}

fun FragmentActivity.showSexDialog(onListener: MenuDialog.OnListener<String>) {
    val data = ArrayList<String>()
    data.add("男")
    data.add("女")
    // 底部选择框
    MenuDialog.Builder(this)
        // 设置 null 表示不显示取消按钮
        //.setCancel(getString(R.string.common_cancel))
        // 设置点击按钮后不关闭对话框
        //.setAutoDismiss(false)
        .setList(data)
        .setListener(onListener)
        .show()
}

fun AppManager.onBack() {
    topActivity?.finish()
}

// 只能启动一次 多次启动无效
fun AppManager.onStartActivityWithSingleTop(activityClass: Class<*>) {
    if (!activityClassIsLive(activityClass)) {
        startActivity(activityClass)
    } else {
        mlog("不能重复启动Activity")
    }
}

fun AppManager.startActivityForResult(intent: Intent, requestCode: Int) {
    if (!activityClassIsLive(Class.forName(intent.component.className))) {
        topActivity?.startActivityForResult(intent, requestCode)
    } else {

    }
}

fun AppManager.onStartActivityWithSingleTop(intent: Intent) {
    if (!activityClassIsLive(Class.forName(intent.component.className))) {
        startActivity(intent)
    } else {
        mlog("不能重复启动Activity with Intent")
    }
}

fun String.toAge(): Int {
    return when (this) {
        "男" -> 0
        "女" -> 1
        else -> 0
    }
}

fun Int.toAge(): String {
    return when (this) {
        0 -> "男"
        1 -> "女"
        else -> "男"
    }
}

fun Long.toTime(): String {
    return toTime("yyyy-MM-dd HH:mm")
}


fun Long.toTime(sdf: String): String {
    return SimpleDateFormat(sdf).format(Date(this * 1000))
}

fun Long.isNotExpired(): Boolean {
    return Date().time < (this * 1000)
}

fun PayActivity.showDialag(title: String, listen: () -> Unit) {
    PaySuccessDialog.Builder(this)
        .setCancel(title)
        .setListener{
            listen.invoke()
        }
        .show()
}

fun AppCompatActivity.hideSoftInput() {
    val view: View = this.getWindow().getDecorView()
    if (view.context == null) return
    val imm = view.context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
