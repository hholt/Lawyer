package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import com.tencent.rtmp.TXLivePlayer
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerLiveComponent
import com.wl.lawyer.di.module.LiveModule
import com.wl.lawyer.mvp.contract.LiveContract
import com.wl.lawyer.mvp.presenter.LivePresenter
import kotlinx.android.synthetic.main.activity_live.*


class LiveActivity : BaseSupportActivity<LivePresenter>(), LiveContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLiveComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .liveModule(LiveModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_live
    }

    override fun initData(savedInstanceState: Bundle?) {
        var player = TXLivePlayer(this)
        player.setPlayerView(cloud_video_view)
        val flvUrl = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8"
        player.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_HLS)
    }

}
