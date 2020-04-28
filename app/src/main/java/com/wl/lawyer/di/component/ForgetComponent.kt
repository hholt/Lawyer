package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.ForgetModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.ForgetActivity


@ActivityScope
@Component(modules = arrayOf(ForgetModule::class), dependencies = arrayOf(AppComponent::class))
interface ForgetComponent {
    fun inject(activity: ForgetActivity)
}
