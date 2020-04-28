package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.FeedbackModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.FeedbackActivity


@ActivityScope
@Component(modules = arrayOf(FeedbackModule::class), dependencies = arrayOf(AppComponent::class))
interface FeedbackComponent {
    fun inject(activity: FeedbackActivity)
}
