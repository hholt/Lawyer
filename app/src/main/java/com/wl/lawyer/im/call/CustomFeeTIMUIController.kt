package com.wl.lawyer.im.call

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.lxj.androidktx.core.click
import com.lxj.androidktx.core.color
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo
import com.tencent.qcloud.tim.uikit.utils.ScreenUtil
import com.wl.lawyer.R
import com.wl.lawyer.app.global.MyApplication
import com.wl.lawyer.im.message.FeeBean
import me.jessyan.autosize.utils.ScreenUtils


object CustomFeeTIMUIController {

    var callback: IFeeCallback? = null


    fun onDraw(context: Context, parent: ICustomMessageViewGroup, data: FeeBean?, info: MessageInfo) {

        // 把自定义消息view添加到TUIKit内部的父容器里
        val view = LayoutInflater.from(MyApplication.application)
            .inflate(R.layout.message_fee_layout, null, false)
        parent.addMessageContentView(view)
        var btn_positive = view.findViewById<AppCompatButton>(R.id.btn_positive)
        var btn_negative = view.findViewById<AppCompatButton>(R.id.btn_negative)
        val tv_desc = view.findViewById<AppCompatTextView>(R.id.tv_desc)
        val tv_price = view.findViewById<AppCompatTextView>(R.id.tv_price)
        val tv_amount = view.findViewById<AppCompatTextView>(R.id.tv_amount)
        val tv_day = view.findViewById<AppCompatTextView>(R.id.tv_day)
        val tv_time = view.findViewById<AppCompatTextView>(R.id.tv_time)
        tv_desc.text = data?.text
        tv_price.text = "价格为："
        tv_amount.text = "总价：${data?.amount}"
        tv_day.text = "时长：${data?.day}"
        tv_time.text = "交付日期：${data?.day}"

        val color =
            ContextCompat.getColor(context,
                if (info.isSelf) R.color.text_chat_right else R.color.text_chat_left)

        tv_desc.setTextColor(color)
        tv_price.setTextColor(color)
        tv_amount.setTextColor(color)
        tv_day.setTextColor(color)
        tv_time.setTextColor(color)


        btn_positive.click {
            callback?.onPositiveClick(data)
        }
        btn_negative.click {
            callback?.onNegativeClivk(data)
        }

        /*// 自定义消息view的实现，这里仅仅展示文本信息，并且实现超链接跳转
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
        })*/
    }
}
