package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.FindLawyerModule

import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.mvp.ui.fragment.FindLawyerFragment


@FragmentScope
@Component(modules = arrayOf(FindLawyerModule::class), dependencies = arrayOf(AppComponent::class))
interface FindLawyerComponent {
    fun inject(fragment: FindLawyerFragment)
}
