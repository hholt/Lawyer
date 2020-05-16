package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.di.module.LawyerArticleDetailModule
import com.wl.lawyer.mvp.ui.activity.LawyerArticleDetailActivity
import dagger.Component

@ActivityScope
@Component(
    modules = arrayOf(LawyerArticleDetailModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface LawyerArticleDetailComponent {
    fun inject(activity: LawyerArticleDetailActivity)
}