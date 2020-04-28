package com.wl.lawyer.mvp.ui.activity.call

import android.os.Bundle
import com.tencent.trtc.TRTCCloudListener
import com.wl.lawyer.app.mlog


class TRTCListener private constructor() : TRTCCloudListener() {
    private val mList = ArrayList<TRTCCloudListener?>()

    fun addTRTCCloudListener(l: TRTCCloudListener?) {
        if (mList.contains(l)) {
            return
        }
        mList.add(l)
    }

    fun removeTRTCCloudListener(l: TRTCCloudListener?) {
        if (l == null) {
            mList.clear()
        } else {
            mList.remove(l)
        }
    }

    override fun onError(errCode: Int, errMsg: String?, extraInfo: Bundle?) {
        super.onError(errCode, errMsg, extraInfo)
        mlog("onError $errCode $errMsg")
        for (l in mList) {
            l?.onError(errCode, errMsg, extraInfo)
        }
    }

    override fun onWarning(warningCode: Int, warningMsg: String?, extraInfo: Bundle?) {
        super.onWarning(warningCode, warningMsg, extraInfo)
        mlog("onWarning $warningCode $warningMsg")
        for (l in mList) {
            l?.onWarning(warningCode, warningMsg, extraInfo)
        }
    }

    override fun onEnterRoom(elapsed: Long) {
        super.onEnterRoom(elapsed)
        mlog("onEnterRoom $elapsed")
        for (list in mList) {
            list?.onEnterRoom(elapsed)
        }
    }

    override fun onExitRoom(reason: Int) {
        super.onExitRoom(reason)
        mlog("onExitRoom $reason")
        for (l in mList) {
            l?.onExitRoom(reason)
        }
    }

    override fun onRemoteUserEnterRoom(userId: String?) {
        super.onRemoteUserEnterRoom(userId)
        mlog("onRemoteUserEnterRoom " + userId!!)
        for (l in mList) {
            l?.onRemoteUserEnterRoom(userId)
        }
    }

    override fun onRemoteUserLeaveRoom(userId: String?, reason: Int) {
        super.onRemoteUserLeaveRoom(userId, reason)
        mlog("onRemoteUserLeaveRoom $userId $reason")
        for (l in mList) {
            l?.onRemoteUserLeaveRoom(userId, reason)
        }
    }

    override fun onUserVideoAvailable(userId: String?, available: Boolean) {
        super.onUserVideoAvailable(userId, available)
        mlog("onUserVideoAvailable $userId $available")
        for (l in mList) {
            l?.onUserVideoAvailable(userId, available)
        }
    }

    override fun onFirstVideoFrame(userId: String?, steamType: Int, width: Int, height: Int) {
        super.onFirstVideoFrame(userId, steamType, width, height)
        mlog("onFirstVideoFrame $userId $steamType $width $height")
        for (l in mList) {
            l?.onFirstVideoFrame("-1", steamType, width, height)
        }
    }

    companion object {


        private var sTRTCListener: TRTCListener? = null

        val instance: TRTCListener
            get() {
                if (sTRTCListener == null) {
                    sTRTCListener = TRTCListener()
                }
                return sTRTCListener as TRTCListener
            }
    }

}
