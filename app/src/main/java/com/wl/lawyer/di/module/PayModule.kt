package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.PayContract
import com.wl.lawyer.mvp.model.PayModel


@Module
//构建PayModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PayModule(private val view: PayContract.View) {
    @ActivityScope
    @Provides
    fun providePayView(): PayContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePayModel(model: PayModel): PayContract.Model {
        return model
    }
}
