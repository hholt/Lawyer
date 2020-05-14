package com.wl.lawyer.di.module

import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.mvp.contract.ArticleFragmentContract
import com.wl.lawyer.mvp.model.ArticleFragmentModel
import dagger.Module
import dagger.Provides

@Module
//ArticleFragmentModule,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ArticleFragmentModule (private val view: ArticleFragmentContract.View) {
    @FragmentScope
    @Provides
    fun provideMyView(): ArticleFragmentContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMyModel(model: ArticleFragmentModel): ArticleFragmentContract.Model {
        return model
    }
}