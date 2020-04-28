package com.wl.lawyer.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.LegalUnderstandingContract
import com.wl.lawyer.mvp.model.LegalUnderstandingModel


@Module
//构建LegalUnderstandingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LegalUnderstandingModule(private val view: LegalUnderstandingContract.View) {
    @FragmentScope
    @Provides
    fun provideLegalUnderstandingView(): LegalUnderstandingContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideLegalUnderstandingModel(model: LegalUnderstandingModel): LegalUnderstandingContract.Model {
        return model
    }
}
