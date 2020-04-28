package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.ModifyPhoneModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.ModifyPhoneActivity


@ActivityScope
@Component(modules = arrayOf(ModifyPhoneModule::class), dependencies = arrayOf(AppComponent::class))
interface ModifyPhoneComponent {
    fun inject(activity: ModifyPhoneActivity)
}
