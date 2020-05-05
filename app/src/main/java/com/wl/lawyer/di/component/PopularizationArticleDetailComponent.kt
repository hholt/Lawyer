package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.di.module.PopularizationArticleDetailModule
import com.wl.lawyer.mvp.ui.activity.ArticleDetailActivity
import dagger.Component

@ActivityScope
@Component(
    modules = arrayOf(PopularizationArticleDetailModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface PopularizationArticleDetailComponent {
    fun inject(activity: ArticleDetailActivity)
}