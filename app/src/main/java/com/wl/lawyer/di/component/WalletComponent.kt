package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.WalletModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.WalletActivity


@ActivityScope
@Component(modules = arrayOf(WalletModule::class), dependencies = arrayOf(AppComponent::class))
interface WalletComponent {
    fun inject(activity: WalletActivity)
}
