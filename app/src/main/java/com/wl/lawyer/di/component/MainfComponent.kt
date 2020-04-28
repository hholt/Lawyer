package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.MainfModule

import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.mvp.ui.fragment.MainfFragment


@FragmentScope
@Component(modules = arrayOf(MainfModule::class), dependencies = arrayOf(AppComponent::class))
interface MainfComponent {
    fun inject(fragment: MainfFragment)
}
