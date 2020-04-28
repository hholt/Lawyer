package com.wl.lawyer.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.FindLawyerContract
import com.wl.lawyer.mvp.model.FindLawyerModel


@Module
//构建FindLawyerModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class FindLawyerModule(private val view: FindLawyerContract.View) {
    @FragmentScope
    @Provides
    fun provideFindLawyerView(): FindLawyerContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideFindLawyerModel(model: FindLawyerModel): FindLawyerContract.Model {
        return model
    }
}
