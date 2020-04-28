package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.RechargeContract
import com.wl.lawyer.mvp.model.RechargeModel


@Module
//构建RechargeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class RechargeModule(private val view: RechargeContract.View) {
    @ActivityScope
    @Provides
    fun provideRechargeView(): RechargeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideRechargeModel(model: RechargeModel): RechargeContract.Model {
        return model
    }
}
