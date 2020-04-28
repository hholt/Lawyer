package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.ForgetContract
import com.wl.lawyer.mvp.model.ForgetModel


@Module
//构建ForgetModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ForgetModule(private val view: ForgetContract.View) {
    @ActivityScope
    @Provides
    fun provideForgetView(): ForgetContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideForgetModel(model: ForgetModel): ForgetContract.Model {
        return model
    }
}
