package com.wl.lawyer.mvp.ui.activity.call

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.lxj.androidktx.core.gone
import com.tencent.imsdk.TIMCustomElem
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout
import com.tencent.qcloud.tim.uikit.modules.chat.base.BaseInputFragment
import com.tencent.qcloud.tim.uikit.modules.chat.layout.inputmore.InputMoreActionUnit
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ICustomMessageViewGroup
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.IOnCustomMessageDrawListener
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil
import com.tencent.qcloud.tim.uikit.utils.ToastUtil
import com.wl.lawyer.R
import com.wl.lawyer.app.mlog
import com.wl.lawyer.mvp.ui.activity.call.CustomMessage.Companion.JSON_VERSION_1_HELLOTIM
import com.wl.lawyer.mvp.ui.activity.call.CustomMessage.Companion.JSON_VERSION_2_ONLY_IOS_TRTC
import com.wl.lawyer.mvp.ui.activity.call.CustomMessage.Companion.JSON_VERSION_3_ANDROID_IOS_TRTC
import com.wl.lawyer.mvp.ui.widget.OrderWight
import kotlinx.android.synthetic.main.activity_chat.*


class ChatLayoutHelper(private val mContext: Context) {
    var layoutAdded = false

    fun customizeChatLayout(layout: ChatLayout) {
        layout.titleBar.gone()

        if (!layoutAdded) {
            (layout.inputLayout.getChildAt(0) as ViewGroup).addView(
                OrderWight(mContext), 0, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
            layoutAdded = true
        }

        CustomAVCallUIController.instance.setUISender(layout)

        //        //====== NoticeLayout使用范例 ======//
        //        NoticeLayout noticeLayout = layout.getNoticeLayout();
        //        noticeLayout.alwaysShow(true);
        //        noticeLayout.getContent().setText("现在插播一条广告");
        //        noticeLayout.getContentExtra().setText("参看有奖");
        //        noticeLayout.setOnNoticeClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                ToastUtil.toastShortMessage("赏白银五千两");
        //            }
        //        });
        //
        //====== MessageLayout使用范例 ======//
        val messageLayout = layout.messageLayout

        messageLayout.rightBubble = mContext.getDrawable(R.drawable.chat_bubble_myself)
        messageLayout.leftBubble = mContext.getDrawable(R.drawable.chat_other_bg)
        messageLayout.rightChatContentFontColor =
            ContextCompat.getColor(mContext, R.color.app_chat_right)
        messageLayout.leftChatContentFontColor =
            ContextCompat.getColor(mContext, R.color.app_chat_left)
        messageLayout.leftNameVisibility = View.GONE
        messageLayout.avatarSize = intArrayOf(36, 36)
        messageLayout.avatarRadius = 18
        messageLayout.avatar = R.drawable.ic_my_avatar


        //        ////// 设置聊天背景 //////
        //        messageLayout.setBackground(new ColorDrawable(0xFFEFE5D4));
        //        ////// 设置头像 //////
        //        // 设置默认头像，默认与朋友与自己的头像相同
        //        messageLayout.setAvatar(R.drawable.ic_more_file);
        //        // 设置头像圆角
        //        messageLayout.setAvatarRadius(50);
        //        // 设置头像大小
        //        messageLayout.setAvatarSize(new int[]{48, 48});
        //
        //        ////// 设置昵称样式（对方与自己的样式保持一致）//////
        //        messageLayout.setNameFontSize(12);
        //        messageLayout.setNameFontColor(0xFF8B5A2B);
        //
        //        ////// 设置气泡 ///////
        //        // 设置自己聊天气泡的背景
        //        messageLayout.setRightBubble(new ColorDrawable(0xFFCCE4FC));
        //        // 设置朋友聊天气泡的背景
        //        messageLayout.setLeftBubble(new ColorDrawable(0xFFE4E7EB));
        //
        //        ////// 设置聊天内容 //////
        //        // 设置聊天内容字体字体大小，朋友和自己用一种字体大小
        //        messageLayout.setChatContextFontSize(15);
        //        // 设置自己聊天内容字体颜色
        //        messageLayout.setRightChatContentFontColor(0xFFA9A9A9);
        //        // 设置朋友聊天内容字体颜色
        //        messageLayout.setLeftChatContentFontColor(0xFFA020F0);
        //
        //        ////// 设置聊天时间 //////
        //        // 设置聊天时间线的背景
        //        messageLayout.setChatTimeBubble(new ColorDrawable(0xFFE4E7EB));
        //        // 设置聊天时间的字体大小
        //        messageLayout.setChatTimeFontSize(12);
        //        // 设置聊天时间的字体颜色
        //        messageLayout.setChatTimeFontColor(0xFF7E848C);
        //
        //        ////// 设置聊天的提示信息 //////
        //        // 设置提示的背景
        //        messageLayout.setTipsMessageBubble(new ColorDrawable(0xFFE4E7EB));
        //        // 设置提示的字体大小
        //        messageLayout.setTipsMessageFontSize(12);
        //        // 设置提示的字体颜色
        //        messageLayout.setTipsMessageFontColor(0xFF7E848C);
        //
        // 设置自定义的消息渲染时的回调
        messageLayout.setOnCustomMessageDrawListener(CustomMessageDraw())
        //
        //        // 新增一个PopMenuAction
        //        PopMenuAction action = new PopMenuAction();
        //        action.setActionName("test");
        //        action.setActionClickListener(new PopActionClickListener() {
        //            @Override
        //            public void onActionClick(int position, Object data) {
        //                ToastUtil.toastShortMessage("新增一个pop action");
        //            }
        //        });
        //        messageLayout.addPopAction(action);
        //
        //        final MessageLayout.OnItemClickListener l = messageLayout.getOnItemClickListener();
        //        messageLayout.setOnItemClickListener(new MessageLayout.OnItemClickListener() {
        //            @Override
        //            public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
        //                l.onMessageLongClick(view, position, messageInfo);
        //                ToastUtil.toastShortMessage("demo中自定义长按item");
        //            }
        //
        //            @Override
        //            public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
        //                l.onUserIconClick(view, position, messageInfo);
        //                ToastUtil.toastShortMessage("demo中自定义点击头像");
        //            }
        //        });


        //====== InputLayout使用范例 ======//
        val inputLayout = layout.inputLayout

        //        // TODO 隐藏音频输入的入口，可以打开下面代码测试
        //        inputLayout.disableAudioInput(true);
        //        // TODO 隐藏表情输入的入口，可以打开下面代码测试
        //        inputLayout.disableEmojiInput(true);
        //        // TODO 隐藏更多功能的入口，可以打开下面代码测试
        //        inputLayout.disableMoreInput(true);
        //        // TODO 可以用自定义的事件来替换更多功能的入口，可以打开下面代码测试
        //        inputLayout.replaceMoreInput(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                ToastUtil.toastShortMessage("自定义的更多功能按钮事件");
        //                MessageInfo info = MessageInfoUtil.buildTextMessage("自定义的消息");
        //                layout.sendMessage(info, false);
        //            }
        //        });
        //        // TODO 可以用自定义的fragment来替换更多功能，可以打开下面代码测试
        //        inputLayout.replaceMoreInput(new CustomInputFragment().setChatLayout(layout));
        //
        //        // TODO 可以disable更多面板上的各个功能，可以打开下面代码测试
        //        inputLayout.disableCaptureAction(true);
        //        inputLayout.disableSendFileAction(true);
        //        inputLayout.disableSendPhotoAction(true);
        //        inputLayout.disableVideoRecordAction(true);
        // TODO 可以自己增加一些功能，可以打开下面代码测试
        // 这里增加一个视频通话
//        InputMoreActionUnit videoCall = new InputMoreActionUnit();
//        videoCall.setIconResId(com.tencent.qcloud.tim.uikit.R.drawable.ic_more_video);
//        videoCall.setTitleId(R.string.video_call);
//        videoCall.setOnClickListener(new View . OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                CustomAVCallUIController.getInstance().createVideoCallRequest();
//            }
//        });
//        inputLayout.addAction(videoCall);

        var videoCall = InputMoreActionUnit()
        videoCall.iconResId = R.drawable.ic_more_video
        videoCall.titleId = R.string.video_call
        videoCall.setOnClickListener {
            CustomAVCallUIController.instance.createVideoCallRequest()
        }
        inputLayout.addAction(videoCall)

        // 增加一个欢迎提示富文本
        val unit = InputMoreActionUnit()
        unit.iconResId = R.drawable.ic_custom
        unit.titleId = R.string.test_custom_action
        unit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val gson = Gson()
                val customMessage = CustomMessage()
                val data = gson.toJson(customMessage)
                val info = MessageInfoUtil.buildCustomMessage(data)
                layout.sendMessage(info, false)
            }
        })
        inputLayout.addAction(unit)
        val orderBill = InputMoreActionUnit()
        orderBill.iconResId = R.drawable.ic_more_video
        orderBill.titleId = R.string.order_bill
        orderBill.setOnClickListener {
            val gson = Gson()
            val customMessage = CustomMessage()
            val data = gson.toJson(customMessage)
            val info = MessageInfoUtil.buildCustomMessage(data)
            layout.sendMessage(info, false)
        }

    }

    class CustomInputFragment : BaseInputFragment() {
        @Nullable
        override fun onCreateView(
            inflater: LayoutInflater, @Nullable container: ViewGroup?,
            savedInstanceState: Bundle
        ): View? {
            val baseView =
                inflater.inflate(R.layout.test_chat_input_custom_fragment, container, false)
            val btn1 = baseView.findViewById<Button>(R.id.test_send_message_btn1)
            btn1.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    ToastUtil.toastShortMessage("自定义的按钮1")
                    if (chatLayout != null) {
                        val gson = Gson()
                        val customMessage = CustomMessage()
                        val data = gson.toJson(customMessage)
                        val info = MessageInfoUtil.buildCustomMessage(data)
                        chatLayout.sendMessage(info, false)
                    }
                }
            })
            val btn2 = baseView.findViewById<Button>(R.id.test_send_message_btn2)
            btn2.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    ToastUtil.toastShortMessage("自定义的按钮2")
                    if (chatLayout != null) {
                        val gson = Gson()
                        val customMessage = CustomMessage()
                        val data = gson.toJson(customMessage)
                        val info = MessageInfoUtil.buildCustomMessage(data)
                        chatLayout.sendMessage(info, false)
                    }
                }
            })
            return baseView
        }

    }

    inner class CustomMessageDraw : IOnCustomMessageDrawListener {

        /**
         * 自定义消息渲染时，会调用该方法，本方法实现了自定义消息的创建，以及交互逻辑
         *
         * @param parent 自定义消息显示的父View，需要把创建的自定义消息view添加到parent里
         * @param info   消息的具体信息
         */
        override fun onDraw(parent: ICustomMessageViewGroup, info: MessageInfo) {
            // 获取到自定义消息的json数据
            if (info.getElement() !is TIMCustomElem) {
                return
            }
            val elem = info.getElement() as TIMCustomElem
            // 自定义的json数据，需要解析成bean实例
            var data: CustomMessage? = null
            try {
                data = Gson().fromJson(String(elem.data), CustomMessage::class.java)
            } catch (e: Exception) {
                mlog("invalid json: " + String(elem.data) + " " + e.message)
            }

            if (data == null) {
                mlog("No Custom Data: " + String(elem.data))
            } else if (data.version == JSON_VERSION_1_HELLOTIM) {
                CustomHelloTIMUIController.onDraw(parent, data)
            } else if (data.version == JSON_VERSION_3_ANDROID_IOS_TRTC || data.version == JSON_VERSION_2_ONLY_IOS_TRTC) {
                CustomAVCallUIController.instance.onDraw(parent, data)
            } else {
                mlog("unsupported version: " + data.version)
            }
        }
    }

}
