package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.di.module.PopularizationArticleModule
import com.wl.lawyer.di.module.PopularizationCourseModule
import com.wl.lawyer.mvp.ui.activity.PopularizationArticleActivity
import dagger.Component

@ActivityScope
@Component(
    modules = arrayOf(PopularizationArticleModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface PopularizationArticleComponent {
    fun inject(activity: PopularizationArticleActivity)
}