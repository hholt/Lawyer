package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.MyModule

import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.mvp.ui.fragment.MyFragment


@FragmentScope
@Component(modules = arrayOf(MyModule::class), dependencies = arrayOf(AppComponent::class))
interface MyComponent {
    fun inject(fragment: MyFragment)
}
