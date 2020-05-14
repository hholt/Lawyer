package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.di.module.OrderFragmentModule
import com.wl.lawyer.mvp.ui.fragment.ConsultOrderFragment
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(OrderFragmentModule::class), dependencies = arrayOf(AppComponent::class))
interface OrderFragmentComponent {
    fun inject(fragment: ConsultOrderFragment)
}