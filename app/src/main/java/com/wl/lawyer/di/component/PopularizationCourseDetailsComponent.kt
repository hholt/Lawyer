package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.PopularizationCourseDetailsModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.PopularizationCourseDetailsActivity


@ActivityScope
@Component(
    modules = arrayOf(PopularizationCourseDetailsModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface PopularizationCourseDetailsComponent {
    fun inject(activity: PopularizationCourseDetailsActivity)
}
