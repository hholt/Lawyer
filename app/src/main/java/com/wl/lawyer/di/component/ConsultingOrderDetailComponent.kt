package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.ConsultingOrderDetailModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.ConsultingOrderDetailActivity


@ActivityScope
@Component(
    modules = arrayOf(ConsultingOrderDetailModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface ConsultingOrderDetailComponent {
    fun inject(activity: ConsultingOrderDetailActivity)
}
