package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.PayModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.PayActivity


@ActivityScope
@Component(modules = arrayOf(PayModule::class), dependencies = arrayOf(AppComponent::class))
interface PayComponent {
    fun inject(activity: PayActivity)
}
