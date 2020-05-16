package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LawyerArticleDetailBean(
    val id: Int,
    val title: String,
    val desc: String,
    val content: String,
    @SerializedName("createtime") val createTime: Long,
    @SerializedName("updatetime") val updateTime: Long,
    @SerializedName("lawyer_id") val lawyerId: Int,
    @SerializedName("view_count") val viewCount: Int,
    @SerializedName("cover_image") val coverImage: String,
    val type: String,
    val status: String,
    val author: Author,
    @SerializedName("type_text") val typeText: String,
    @SerializedName("status_text") val statusText: String
) : Serializable {
    data class Author(
        val id: Int,
        val nickname: String,
        val url: String
    ) : Serializable
}


/*
*
*
*   "id": 19,
    "title": "女方离婚注意事项",
    "desc": "离婚时女方应注意什么",
    "content": "<p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">1.<a href=\"https://www.66law.cn/special/lhxys/\" title=\"离婚协议\" target=\"_blank\" style=\"text-decoration-line:none;transition:all 0.2s ease 0s;outline:none;\">离婚协议</a>内容过于简单，不具有可操作性。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-align:center;\"><img src=\"https://imgf.66law.cn/upload/f/201902/18/181748465.jpg\" style=\"height:235.999px;\" /></p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">2.<a href=\"https://www.66law.cn/question/20535979.aspx\" title=\"贷款\" target=\"_blank\" style=\"text-decoration-line:none;transition:all 0.2s ease 0s;outline:none;\">贷款</a>房屋的约定和处理。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">3.银行存款的约定和处理。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">4.股票的约定和处理。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">5.<a href=\"https://v.66law.cn/shuofa/gsf/gsfgd/\" title=\"公司\" target=\"_blank\" style=\"text-decoration-line:none;transition:all 0.2s ease 0s;outline:none;\">公司</a>股权的约定和处理。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">6.给付金钱义务约定和处理。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">7.孩子抚养费的约定和处理。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">8.<a href=\"https://www.66law.cn/laws/hunyinjiating/zinvfuyang/tanshiquan/\" title=\"探视权\" target=\"_blank\" style=\"text-decoration-line:none;transition:all 0.2s ease 0s;outline:none;\">探视权</a>的约定和处理。</p><p style=\"margin-top:0px;margin-bottom:30px;padding:0px;line-height:30px;white-space:normal;text-indent:2em;\">9.关于<a href=\"https://www.66law.cn/special/hkszd/\" title=\"户口\" target=\"_blank\" style=\"text-decoration-line:none;transition:all 0.2s ease 0s;outline:none;\">户口</a>的约定处理</p>",
    "createtime": 1588926784,
    "updatetime": 1588926974,
    "lawyer_id": 1,
    "view_count": 0,
    "cover_image": "/uploads/20200508/2abbed37667310e686a4cc3aef0aaa02.jpg",
    "type": "1",
    "status": "1",
    "author": {
      "id": 1,
      "nickname": "欧阳照例",
      "url": "/u/1"
    },
    "type_text": "经典案例",
    "status_text": "正常"
*
* */