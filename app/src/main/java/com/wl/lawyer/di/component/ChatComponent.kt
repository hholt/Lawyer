package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.ChatModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.ChatActivity


@ActivityScope
@Component(modules = arrayOf(ChatModule::class), dependencies = arrayOf(AppComponent::class))
interface ChatComponent {
    fun inject(activity: ChatActivity)
}
