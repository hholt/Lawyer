package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.di.module.LawyerArticleModule
import com.wl.lawyer.mvp.ui.activity.LawyerArticleActivity
import dagger.Component


@ActivityScope
@Component(modules = arrayOf(LawyerArticleModule::class), dependencies = arrayOf(AppComponent::class))
interface LawyerArticleComponent {
    fun inject(activity: LawyerArticleActivity)
}