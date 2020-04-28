package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.EditModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.EditActivity


@ActivityScope
@Component(modules = arrayOf(EditModule::class), dependencies = arrayOf(AppComponent::class))
interface EditComponent {
    fun inject(activity: EditActivity)
}
