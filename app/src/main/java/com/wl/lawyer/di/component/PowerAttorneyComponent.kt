package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.PowerAttorneyModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.PowerAttorneyActivity


@ActivityScope
@Component(
    modules = arrayOf(PowerAttorneyModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface PowerAttorneyComponent {
    fun inject(activity: PowerAttorneyActivity)
}
