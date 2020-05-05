package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleDetailBean(
    val id: Int,
    val title: String,
    val image: String,
    val author: String,
    val desc: String,
    val content: String,
    val status: Int,
    @SerializedName("read_count")val readCount: Int,
    @SerializedName("virtual_read_count")val virtualReadCount: Int,
    @SerializedName("createtime")val createTime: Long,
    @SerializedName("updatetime")val updateTimeval: Long,
    val deleteTime: Any,
    @SerializedName("status_text")val statusText: String
) : Serializable
/*

    "id": 43,
    "title": "法在心中",
    "image": "/uploads/20200425/0b5f394f4c84b97aa4ec9758c2edb030.jpg",
    "author": "万狼",
    "desc": " “法律，是由立法机关制定、颁布的各种强制行为规则的总称。”这是《新华词典》对法律的解释。但这种解释对年少的我来说太模糊，太抽象了。法律，它到底是什么？为什么这样至高无上，人人都要遵守？",
    "content": "<p style=\"text-indent:2em;\"><span style=\"color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\">&nbsp;“法律，是由立法机关制定、颁布的各种强制行为规则的总称。”这是《新华词典》对法律的解释。但这种解释对年少的我来说太模糊，太抽象了。法律，它到底是什么？为什么这样至高无上，人人都要遵守？是儿时的游戏，让我懂得了规则多制定和遵守；是《青少年保护法》让我真正明确了法律条文；是学校的普法教育，让我学到了法律常识。渐渐的，我知道了学法、守法的重要性，更知道遵纪守法要从点滴做起。</span><br style=\"content:&quot;&quot;;display:block;width:705px;height:0px;margin:20px 0px;color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\" /><span style=\"color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\">&emsp;&emsp;“勿以恶小而为之”，这是三国时刘备告勉儿子刘禅的一句话，这位古代政治家的至理名言虽然历经1700多年，但它的哲理光芒永存。“小恶不制，必然发展”，看看社会中的那些犯罪分子，哪个不是从“小恶”开始一步步走向犯罪道路的呢？就如一只小白蚁在船板上咬一个小洞是很不起眼的，但如果任其发展起来，船就会沉没。我们青少年预防犯罪要从预防不良行为的发生做起。</span><br style=\"content:&quot;&quot;;display:block;width:705px;height:0px;margin:20px 0px;color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\" /><span style=\"color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\">&emsp;&emsp;人们常说，家庭是我们的第一课堂，父母是我们的第一任老师。记得班里曾做过一次试验。老师问：“同学们，如果有人欺负你，你怎么办？”竟然有半数同学回答：“打他，跟他拼了。”老师接着问：“为什么？”那些同学便说：“爸爸妈妈告诉我，人在社会上要厉害一些，不能受窝囊气。”我庆幸，我没有生活在那样的家庭里，我庆幸，爸爸妈妈从没有用这样不正当的思想教育我。但是，同龄人的话仍让我吃惊。</span><br style=\"content:&quot;&quot;;display:block;width:705px;height:0px;margin:20px 0px;color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\" /><span style=\"color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\">&emsp;&emsp;再看看社会上的种种现象吧。一些不法商人大肆造假、售假；贪脏法，金钱获得了很多，但等待他们的是法院的判决书。他们破坏了社会治安，全然不顾法律的存在。试想，如果每个人都自由自在，不把法律放在眼里，那想将来的社会会是怎样的呢？那些由于缺少知识而心灵受到污染的孩子们，又能做到学法、知法、守法、用法、护法呢？</span><br style=\"content:&quot;&quot;;display:block;width:705px;height:0px;margin:20px 0px;color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\" /><span style=\"color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\">&emsp;&emsp;目前，有迹象表明，越来越多的青少年开始犯罪。自私与无知多么可怕，对法的无知又更是多么可怕啊！无数事实说明，青少年自身的道德品质和法律观念起着决定作用。</span><br style=\"content:&quot;&quot;;display:block;width:705px;height:0px;margin:20px 0px;color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\" /><span style=\"color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\">&emsp;&emsp;法无处不在，但不要将它变成生活的负担，而要将它作为生活的准则来规范和约束自己的行为。只有现在认真学法，才能在未来做一个懂法的人；因为只有懂法，才能成为一个守法的公民，只有我们自己守法，才能拿起法律武器来保护自己的权利，进而维护他人的权益，维护法律至高无上的尊言！</span><br style=\"content:&quot;&quot;;display:block;width:705px;height:0px;margin:20px 0px;color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\" /><span style=\"color:#333333;font-family:&quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma;font-size:16px;white-space:normal;background-color:#FFFFFF;\">&emsp;&emsp;未来的社会，必然是法制的社会，而法制的社会只有尊重法律的人民才能创造出来，正如大哲学家苏格拉底所说：“守法精神比法律本身重要的多”。所以，我们青少年应当把法“根植”在心中。成为一个理性的守法公民，是时代赋予我们的使命。“法在我心中”，我们无愧于自己，无愧于法律至高无上的尊严。</span></p>",
    "status": "1",
    "read_count": 50,
    "virtual_read_count": 80,
    "createtime": 1580808426,
    "updatetime": 1588661132,
    "deletetime": null,
    "status_text": "Status 1"
* */