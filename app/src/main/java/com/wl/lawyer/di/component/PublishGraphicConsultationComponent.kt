package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.PublishGraphicConsultationModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.PublishGraphicConsultationActivity


@ActivityScope
@Component(
    modules = arrayOf(PublishGraphicConsultationModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface PublishGraphicConsultationComponent {
    fun inject(activity: PublishGraphicConsultationActivity)
}
