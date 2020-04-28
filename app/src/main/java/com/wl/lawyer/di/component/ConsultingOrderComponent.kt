package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.ConsultingOrderModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.ConsultingOrderActivity


@ActivityScope
@Component(
    modules = arrayOf(ConsultingOrderModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface ConsultingOrderComponent {
    fun inject(activity: ConsultingOrderActivity)
}
