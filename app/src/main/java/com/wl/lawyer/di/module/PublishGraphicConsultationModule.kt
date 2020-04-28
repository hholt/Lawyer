package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.PublishGraphicConsultationContract
import com.wl.lawyer.mvp.model.PublishGraphicConsultationModel


@Module
//构建PublishGraphicConsultationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PublishGraphicConsultationModule(private val view: PublishGraphicConsultationContract.View) {
    @ActivityScope
    @Provides
    fun providePublishGraphicConsultationView(): PublishGraphicConsultationContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePublishGraphicConsultationModel(model: PublishGraphicConsultationModel): PublishGraphicConsultationContract.Model {
        return model
    }
}
