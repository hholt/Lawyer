package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.LiveContract
import com.wl.lawyer.mvp.model.LiveModel


@Module
//构建LiveModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LiveModule(private val view: LiveContract.View) {
    @ActivityScope
    @Provides
    fun provideLiveView(): LiveContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideLiveModel(model: LiveModel): LiveContract.Model {
        return model
    }
}
