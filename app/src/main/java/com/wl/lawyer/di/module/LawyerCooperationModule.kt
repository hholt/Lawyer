package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.LawyerCooperationContract
import com.wl.lawyer.mvp.model.LawyerCooperationModel


@Module
//构建LawyerCooperationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LawyerCooperationModule(private val view: LawyerCooperationContract.View) {
    @ActivityScope
    @Provides
    fun provideLawyerCooperationView(): LawyerCooperationContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideLawyerCooperationModel(model: LawyerCooperationModel): LawyerCooperationContract.Model {
        return model
    }
}
