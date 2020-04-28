package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.QuickRegisterContract
import com.wl.lawyer.mvp.model.QuickRegisterModel


@Module
//构建QuickRegisterModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class QuickRegisterModule(private val view: QuickRegisterContract.View) {
    @ActivityScope
    @Provides
    fun provideQuickRegisterView(): QuickRegisterContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideQuickRegisterModel(model: QuickRegisterModel): QuickRegisterContract.Model {
        return model
    }
}
