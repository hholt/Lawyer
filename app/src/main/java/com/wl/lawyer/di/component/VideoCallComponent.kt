package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.VideoCallModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.VideoCallActivity


@ActivityScope
@Component(modules = arrayOf(VideoCallModule::class), dependencies = arrayOf(AppComponent::class))
interface VideoCallComponent {
    fun inject(activity: VideoCallActivity)
}
