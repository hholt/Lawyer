package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.MainModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.MainActivity


@ActivityScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}
