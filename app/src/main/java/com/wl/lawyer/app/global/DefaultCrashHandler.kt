package com.wl.lawyer.app.global

import android.os.Process
import com.wl.lawyer.app.mloge

class DefaultCrashHandler private constructor() : Thread.UncaughtExceptionHandler {

    companion object {
        val instance by lazy {
            DefaultCrashHandler()
        }
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        mloge("Thread:${t?.name} msg:${e?.message}")
        Process.killProcess(Process.myPid())
    }

}
