package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.GCDetailsModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.GCDetailsActivity


@ActivityScope
@Component(modules = arrayOf(GCDetailsModule::class), dependencies = arrayOf(AppComponent::class))
interface GCDetailsComponent {
    fun inject(activity: GCDetailsActivity)
}
