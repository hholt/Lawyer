package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.di.module.ChatListModule
import com.wl.lawyer.mvp.ui.activity.ChatListActivity
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(ChatListModule::class), dependencies = arrayOf(AppComponent::class))
interface ChatListComponent {
    fun inject(activity: ChatListActivity)
}