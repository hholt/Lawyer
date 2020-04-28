package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.SettingModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.SettingActivity


@ActivityScope
@Component(modules = arrayOf(SettingModule::class), dependencies = arrayOf(AppComponent::class))
interface SettingComponent {
    fun inject(activity: SettingActivity)
}
