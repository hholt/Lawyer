package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.contract.LawyerArticleContract
import com.wl.lawyer.mvp.model.LawyerArticleModel
import dagger.Module
import dagger.Provides

@Module
//LawyerArticleModule,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LawyerArticleModule(private val view: LawyerArticleContract.View) {
    @ActivityScope
    @Provides
    fun provideLawyerArticleView(): LawyerArticleContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideLawyerArticleModel(model: LawyerArticleModel): LawyerArticleContract.Model {
        return model
    }
}