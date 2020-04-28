package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.ChatContract
import com.wl.lawyer.mvp.model.ChatModel


@Module
//构建ChatModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ChatModule(private val view: ChatContract.View) {
    @ActivityScope
    @Provides
    fun provideChatView(): ChatContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideChatModel(model: ChatModel): ChatContract.Model {
        return model
    }
}
