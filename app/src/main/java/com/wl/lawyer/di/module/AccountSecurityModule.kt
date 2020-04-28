package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.AccountSecurityContract
import com.wl.lawyer.mvp.model.AccountSecurityModel


@Module
//构建AccountSecurityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AccountSecurityModule(private val view: AccountSecurityContract.View) {
    @ActivityScope
    @Provides
    fun provideAccountSecurityView(): AccountSecurityContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAccountSecurityModel(model: AccountSecurityModel): AccountSecurityContract.Model {
        return model
    }
}
