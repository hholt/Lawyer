package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.GCDetailsContract
import com.wl.lawyer.mvp.model.GCDetailsModel


@Module
//构建GCDetailsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class GCDetailsModule(private val view: GCDetailsContract.View) {
    @ActivityScope
    @Provides
    fun provideGCDetailsView(): GCDetailsContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideGCDetailsModel(model: GCDetailsModel): GCDetailsContract.Model {
        return model
    }
}
