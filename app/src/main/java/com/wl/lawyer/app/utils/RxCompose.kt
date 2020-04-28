package com.wl.lawyer.app.utils

import com.jess.arms.mvp.IView
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RxCompose {

    companion object {
        // 是否显示请求Dialog false不显示 true显示
        var IS_SHOW_DIALOG = false

        // 切换RxJava的工作线程和Callback线程
        fun <T> transformer(): ObservableTransformer<T, T> {
            return ObservableTransformer<T, T> { upstream ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        // 切换RxJava的工作线程和Callback线程
        fun <T> transformer(view: IView): ObservableTransformer<T, T> {
            return ObservableTransformer<T, T> { upstream ->
                upstream
                    .doOnSubscribe {
                        // 显示请求网络Dialog
                        if (IS_SHOW_DIALOG) {
                            view.showLoading()
                        }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally {
                        // 隐藏请求网络Dialog
                        if (IS_SHOW_DIALOG) {
                            view.hideLoading()
                        }
                    }
                    .compose(RxLifecycleUtils.bindToLifecycle(view)) // 绑定view生命周期
            }
        }

    }
}