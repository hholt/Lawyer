package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.RechargeModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.RechargeActivity


@ActivityScope
@Component(modules = arrayOf(RechargeModule::class), dependencies = arrayOf(AppComponent::class))
interface RechargeComponent {
    fun inject(activity: RechargeActivity)
}
