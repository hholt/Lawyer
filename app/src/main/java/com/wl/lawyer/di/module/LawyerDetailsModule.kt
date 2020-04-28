package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.LawyerDetailsContract
import com.wl.lawyer.mvp.model.LawyerDetailsModel


@Module
//构建LawyerDetailsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LawyerDetailsModule(private val view: LawyerDetailsContract.View) {
    @ActivityScope
    @Provides
    fun provideLawyerDetailsView(): LawyerDetailsContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideLawyerDetailsModel(model: LawyerDetailsModel): LawyerDetailsContract.Model {
        return model
    }
}
