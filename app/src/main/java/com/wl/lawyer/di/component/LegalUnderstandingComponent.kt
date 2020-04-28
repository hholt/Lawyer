package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.LegalUnderstandingModule

import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.mvp.ui.fragment.LegalUnderstandingFragment


@FragmentScope
@Component(
    modules = arrayOf(LegalUnderstandingModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface LegalUnderstandingComponent {
    fun inject(fragment: LegalUnderstandingFragment)
}
