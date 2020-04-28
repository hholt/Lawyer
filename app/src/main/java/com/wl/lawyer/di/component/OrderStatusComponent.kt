package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.OrderStatusModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.OrderStatusActivity


@ActivityScope
@Component(modules = arrayOf(OrderStatusModule::class), dependencies = arrayOf(AppComponent::class))
interface OrderStatusComponent {
    fun inject(activity: OrderStatusActivity)
}
