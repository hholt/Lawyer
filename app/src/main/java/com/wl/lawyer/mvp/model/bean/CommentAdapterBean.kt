package com.wl.lawyer.mvp.model.bean

class CommentAdapterBean(val type: Int, val commentBean: ConsulationCommentBean) {
    companion object{
        const val TYPE_LAWYER = 1000
        const val TYPE_USER = 2000
        const val TYPE_REPLY = 3000
    }
}