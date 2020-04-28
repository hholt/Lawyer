package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.OnlineConsultationModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.OnlineConsultationActivity


@ActivityScope
@Component(
    modules = arrayOf(OnlineConsultationModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface OnlineConsultationComponent {
    fun inject(activity: OnlineConsultationActivity)
}
