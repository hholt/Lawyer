package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.GraphicConsultationContract
import com.wl.lawyer.mvp.model.GraphicConsultationModel


@Module
//构建GraphicConsultationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class GraphicConsultationModule(private val view: GraphicConsultationContract.View) {
    @ActivityScope
    @Provides
    fun provideGraphicConsultationView(): GraphicConsultationContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideGraphicConsultationModel(model: GraphicConsultationModel): GraphicConsultationContract.Model {
        return model
    }
}
