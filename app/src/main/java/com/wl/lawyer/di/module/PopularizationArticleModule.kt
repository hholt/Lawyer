package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.contract.PopularizationArticleContract
import com.wl.lawyer.mvp.model.PopularizationArticleModel
import dagger.Module
import dagger.Provides

//构建PopularizationArticleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
@Module
class PopularizationArticleModule(private val view: PopularizationArticleContract.View) {
    @ActivityScope
    @Provides
    fun providePopularizationArticleView(): PopularizationArticleContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePopularizationArticleModel(model: PopularizationArticleModel): PopularizationArticleContract.Model {
        return model
    }
}
