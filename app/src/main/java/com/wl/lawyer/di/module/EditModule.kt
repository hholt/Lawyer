package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.EditContract
import com.wl.lawyer.mvp.model.EditModel


@Module
//构建EditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EditModule(private val view: EditContract.View) {
    @ActivityScope
    @Provides
    fun provideEditView(): EditContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEditModel(model: EditModel): EditContract.Model {
        return model
    }
}
