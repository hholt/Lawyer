package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.LawyerModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.LawyerActivity


@ActivityScope
@Component(modules = arrayOf(LawyerModule::class), dependencies = arrayOf(AppComponent::class))
interface LawyerComponent {
    fun inject(activity: LawyerActivity)
}
