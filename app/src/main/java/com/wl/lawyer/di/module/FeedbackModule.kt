package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.FeedbackContract
import com.wl.lawyer.mvp.model.FeedbackModel


@Module
//构建FeedbackModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class FeedbackModule(private val view: FeedbackContract.View) {
    @ActivityScope
    @Provides
    fun provideFeedbackView(): FeedbackContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideFeedbackModel(model: FeedbackModel): FeedbackContract.Model {
        return model
    }
}
