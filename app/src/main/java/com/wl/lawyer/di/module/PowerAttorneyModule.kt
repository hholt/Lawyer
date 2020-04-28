package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.PowerAttorneyContract
import com.wl.lawyer.mvp.model.PowerAttorneyModel


@Module
//构建PowerAttorneyModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PowerAttorneyModule(private val view: PowerAttorneyContract.View) {
    @ActivityScope
    @Provides
    fun providePowerAttorneyView(): PowerAttorneyContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePowerAttorneyModel(model: PowerAttorneyModel): PowerAttorneyContract.Model {
        return model
    }
}
