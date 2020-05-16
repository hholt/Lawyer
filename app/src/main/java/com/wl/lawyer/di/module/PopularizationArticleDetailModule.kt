package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.contract.PopularizationArticleDetailContract
import com.wl.lawyer.mvp.model.ArticleDetailModel
import dagger.Module
import dagger.Provides

//构建PopularizationArticleDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
@Module
class PopularizationArticleDetailModule(private val view: PopularizationArticleDetailContract.View) {
    @ActivityScope
    @Provides
    fun providePopularizationArticleDetailView(): PopularizationArticleDetailContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePopularizationArticleDetailModel(model: ArticleDetailModel): PopularizationArticleDetailContract.Model {
        return model
    }
}