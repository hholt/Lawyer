package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.OnlineConsultationContract
import com.wl.lawyer.mvp.model.OnlineConsultationModel


@Module
//构建OnlineConsultationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class OnlineConsultationModule(private val view: OnlineConsultationContract.View) {
    @ActivityScope
    @Provides
    fun provideOnlineConsultationView(): OnlineConsultationContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideOnlineConsultationModel(model: OnlineConsultationModel): OnlineConsultationContract.Model {
        return model
    }
}
