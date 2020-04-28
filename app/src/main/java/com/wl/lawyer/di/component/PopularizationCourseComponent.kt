package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.PopularizationCourseModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.PopularizationCourseActivity


@ActivityScope
@Component(
    modules = arrayOf(PopularizationCourseModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface PopularizationCourseComponent {
    fun inject(activity: PopularizationCourseActivity)
}
