package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.ModifyPhoneContract
import com.wl.lawyer.mvp.model.ModifyPhoneModel


@Module
//构建ModifyPhoneModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ModifyPhoneModule(private val view: ModifyPhoneContract.View) {
    @ActivityScope
    @Provides
    fun provideModifyPhoneView(): ModifyPhoneContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideModifyPhoneModel(model: ModifyPhoneModel): ModifyPhoneContract.Model {
        return model
    }
}
