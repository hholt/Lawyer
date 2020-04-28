package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.SettingContract
import com.wl.lawyer.mvp.model.SettingModel


@Module
//构建SettingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class SettingModule(private val view: SettingContract.View) {
    @ActivityScope
    @Provides
    fun provideSettingView(): SettingContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSettingModel(model: SettingModel): SettingContract.Model {
        return model
    }
}
