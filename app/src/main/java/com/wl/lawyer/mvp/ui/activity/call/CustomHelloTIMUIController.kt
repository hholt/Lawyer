package com.wl.lawyer.mvp.ui.activity.call

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup
import com.tencent.qcloud.tim.uikit.utils.ToastUtil
import com.wl.lawyer.R
import com.wl.lawyer.app.global.MyApplication
import com.wl.lawyer.app.mlog


object CustomHelloTIMUIController {

    fun onDraw(parent: ICustomMessageViewGroup, data: CustomMessage?) {

        // 把自定义消息view添加到TUIKit内部的父容器里
        val view = LayoutInflater.from(MyApplication.application)
            .inflate(R.layout.test_custom_message_layout1, null, false)
        parent.addMessageContentView(view)

        // 自定义消息view的实现，这里仅仅展示文本信息，并且实现超链接跳转
        val textView = view.findViewById<TextView>(R.id.test_custom_message_tv)
        val text = "不支持的自定义消息"
        if (data == null) {
            textView.setText(text)
        } else {
            textView.setText(data.text)
        }
        view.setClickable(true)
        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (data == null) {
                    mlog("Do what?")
                    ToastUtil.toastShortMessage(text)
                    return
                }
                val intent = Intent()
                intent.action = "android.intent.action.VIEW"
                val content_url = Uri.parse(data.link)
                intent.data = content_url
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                MyApplication.application.startActivity(intent)
            }
        })
    }
}
