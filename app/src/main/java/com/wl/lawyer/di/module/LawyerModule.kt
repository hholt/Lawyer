package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.LawyerContract
import com.wl.lawyer.mvp.model.LawyerModel


@Module
//构建LawyerModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LawyerModule(private val view: LawyerContract.View) {
    @ActivityScope
    @Provides
    fun provideLawyerView(): LawyerContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideLawyerModel(model: LawyerModel): LawyerContract.Model {
        return model
    }
}
