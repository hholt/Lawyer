package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.LawyerDetailsModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.LawyerDetailsActivity


@ActivityScope
@Component(
    modules = arrayOf(LawyerDetailsModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface LawyerDetailsComponent {
    fun inject(activity: LawyerDetailsActivity)
}
