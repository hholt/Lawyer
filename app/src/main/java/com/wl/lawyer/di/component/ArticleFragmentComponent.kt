package com.wl.lawyer.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import com.wl.lawyer.di.module.ArticleFragmentModule
import com.wl.lawyer.mvp.ui.fragment.ArticleFragment
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(ArticleFragmentModule::class), dependencies = arrayOf(AppComponent::class))
interface ArticleFragmentComponent {
    fun inject(fragment: ArticleFragment)
}