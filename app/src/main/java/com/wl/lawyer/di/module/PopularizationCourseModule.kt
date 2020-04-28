package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.PopularizationCourseContract
import com.wl.lawyer.mvp.model.PopularizationCourseModel


@Module
//构建PopularizationCourseModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PopularizationCourseModule(private val view: PopularizationCourseContract.View) {
    @ActivityScope
    @Provides
    fun providePopularizationCourseView(): PopularizationCourseContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePopularizationCourseModel(model: PopularizationCourseModel): PopularizationCourseContract.Model {
        return model
    }
}
