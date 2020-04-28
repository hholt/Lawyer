package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.ClericalCollaborationContract
import com.wl.lawyer.mvp.model.ClericalCollaborationModel


@Module
//构建ClericalCollaborationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ClericalCollaborationModule(private val view: ClericalCollaborationContract.View) {
    @ActivityScope
    @Provides
    fun provideClericalCollaborationView(): ClericalCollaborationContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideClericalCollaborationModel(model: ClericalCollaborationModel): ClericalCollaborationContract.Model {
        return model
    }
}
