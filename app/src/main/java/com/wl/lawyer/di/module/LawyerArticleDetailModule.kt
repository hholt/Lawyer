package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.contract.LawyerArticleDetailContract
import com.wl.lawyer.mvp.model.LawyerArticleDetailModel
import dagger.Module
import dagger.Provides

@Module
class LawyerArticleDetailModule(private val view: LawyerArticleDetailContract.View) {
    @ActivityScope
    @Provides
    fun providePopularizationArticleDetailView(): LawyerArticleDetailContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePopularizationArticleDetailModel(model: LawyerArticleDetailModel): LawyerArticleDetailContract.Model {
        return model
    }
}