package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.WalletContract
import com.wl.lawyer.mvp.model.WalletModel


@Module
//构建WalletModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WalletModule(private val view: WalletContract.View) {
    @ActivityScope
    @Provides
    fun provideWalletView(): WalletContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideWalletModel(model: WalletModel): WalletContract.Model {
        return model
    }
}
