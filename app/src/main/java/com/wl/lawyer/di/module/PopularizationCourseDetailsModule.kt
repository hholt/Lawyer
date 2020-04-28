package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.PopularizationCourseDetailsContract
import com.wl.lawyer.mvp.model.PopularizationCourseDetailsModel


@Module
//构建PopularizationCourseDetailsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PopularizationCourseDetailsModule(private val view: PopularizationCourseDetailsContract.View) {
    @ActivityScope
    @Provides
    fun providePopularizationCourseDetailsView(): PopularizationCourseDetailsContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePopularizationCourseDetailsModel(model: PopularizationCourseDetailsModel): PopularizationCourseDetailsContract.Model {
        return model
    }
}
