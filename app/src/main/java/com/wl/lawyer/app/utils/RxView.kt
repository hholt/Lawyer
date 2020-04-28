package com.wl.lawyer.app.utils

import com.jess.arms.mvp.IView

class RxView {

    companion object {
        // 显示错误信息
        fun showErrorMsg(view: IView?, msg: String?) {
            view?.let {
                msg?.let { m ->
                    it.showMessage(m)
                }
            }
        }

        fun showMsg(view: IView?, msg: String?) {
            showErrorMsg(view, msg)
        }
    }

}