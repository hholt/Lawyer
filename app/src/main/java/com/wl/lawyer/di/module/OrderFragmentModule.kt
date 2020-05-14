package com.wl.lawyer.di.module

import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.mvp.contract.OrderFragmentContract
import com.wl.lawyer.mvp.model.OrderFragmentModel
import dagger.Module
import dagger.Provides

@Module
//OrderFragmentModule,将View的实现类传进来,这样就可以提供View的实现类给presenter
class OrderFragmentModule(private val view: OrderFragmentContract.View) {
    @FragmentScope
    @Provides
    fun provideMyView(): OrderFragmentContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMyModel(model: OrderFragmentModel): OrderFragmentContract.Model {
        return model
    }
}