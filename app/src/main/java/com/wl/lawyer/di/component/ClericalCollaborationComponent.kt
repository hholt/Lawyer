package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.ClericalCollaborationModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.ClericalCollaborationActivity


@ActivityScope
@Component(
    modules = arrayOf(ClericalCollaborationModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface ClericalCollaborationComponent {
    fun inject(activity: ClericalCollaborationActivity)
}
