package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.PersonalInformationModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.PersonalInformationActivity


@ActivityScope
@Component(
    modules = arrayOf(PersonalInformationModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface PersonalInformationComponent {
    fun inject(activity: PersonalInformationActivity)
}
