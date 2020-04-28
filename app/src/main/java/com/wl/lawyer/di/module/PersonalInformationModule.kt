package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.PersonalInformationContract
import com.wl.lawyer.mvp.model.PersonalInformationModel


@Module
//构建PersonalInformationModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PersonalInformationModule(private val view: PersonalInformationContract.View) {
    @ActivityScope
    @Provides
    fun providePersonalInformationView(): PersonalInformationContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePersonalInformationModel(model: PersonalInformationModel): PersonalInformationContract.Model {
        return model
    }
}
