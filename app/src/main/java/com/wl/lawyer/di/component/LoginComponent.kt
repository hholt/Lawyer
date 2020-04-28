package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.di.module.LoginModule
import com.wl.lawyer.mvp.ui.activity.LoginActivity
import dagger.Component


@ActivityScope
@Component(
    modules = [LoginModule::class],
    dependencies = [AppComponent::class]
)
interface LoginComponent {
    fun inject(activity: LoginActivity)
}
