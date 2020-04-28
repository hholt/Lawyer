package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.QuickRegisterModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.QuickRegisterActivity


@ActivityScope
@Component(
    modules = arrayOf(QuickRegisterModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface QuickRegisterComponent {
    fun inject(activity: QuickRegisterActivity)
}
