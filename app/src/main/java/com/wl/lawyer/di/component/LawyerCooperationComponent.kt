package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.LawyerCooperationModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.LawyerCooperationActivity


@ActivityScope
@Component(
    modules = arrayOf(LawyerCooperationModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface LawyerCooperationComponent {
    fun inject(activity: LawyerCooperationActivity)
}
