package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.GraphicConsultationModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.GraphicConsultationActivity


@ActivityScope
@Component(
    modules = arrayOf(GraphicConsultationModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface GraphicConsultationComponent {
    fun inject(activity: GraphicConsultationActivity)
}
