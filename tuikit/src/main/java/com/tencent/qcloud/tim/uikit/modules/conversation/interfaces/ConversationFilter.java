package com.tencent.qcloud.tim.uikit.modules.conversation.interfaces;

import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;

import java.util.ArrayList;
import java.util.List;

public interface ConversationFilter {

    List<ConversationInfo> filter(List<ConversationInfo> conversations);

}
