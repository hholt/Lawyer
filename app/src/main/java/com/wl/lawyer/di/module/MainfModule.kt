package com.wl.lawyer.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.MainfContract
import com.wl.lawyer.mvp.model.MainfModel


@Module
//构建MainfModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MainfModule(private val view: MainfContract.View) {
    @FragmentScope
    @Provides
    fun provideMainfView(): MainfContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMainfModel(model: MainfModel): MainfContract.Model {
        return model
    }
}
