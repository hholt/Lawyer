package com.wl.lawyer.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.HomeContract
import com.wl.lawyer.mvp.model.HomeModel


@Module
//构建HomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class HomeModule(private val view: HomeContract.View) {
    @FragmentScope
    @Provides
    fun provideHomeView(): HomeContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideHomeModel(model: HomeModel): HomeContract.Model {
        return model
    }
}
