package com.wl.lawyer.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.wl.lawyer.di.module.AccountSecurityModule

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.ui.activity.AccountSecurityActivity


@ActivityScope
@Component(
    modules = arrayOf(AccountSecurityModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface AccountSecurityComponent {
    fun inject(activity: AccountSecurityActivity)
}
