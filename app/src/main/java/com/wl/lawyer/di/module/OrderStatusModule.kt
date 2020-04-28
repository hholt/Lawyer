package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.OrderStatusContract
import com.wl.lawyer.mvp.model.OrderStatusModel


@Module
//构建OrderStatusModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class OrderStatusModule(private val view: OrderStatusContract.View) {
    @ActivityScope
    @Provides
    fun provideOrderStatusView(): OrderStatusContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideOrderStatusModel(model: OrderStatusModel): OrderStatusContract.Model {
        return model
    }
}
