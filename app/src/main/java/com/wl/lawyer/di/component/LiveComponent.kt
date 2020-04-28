package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.LiveModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.LiveActivity


@ActivityScope
@Component(modules = arrayOf(LiveModule::class), dependencies = arrayOf(AppComponent::class))
interface LiveComponent {
    fun inject(activity: LiveActivity)
}
