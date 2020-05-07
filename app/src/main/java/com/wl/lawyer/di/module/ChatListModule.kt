package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.model.ChatListModule
import dagger.Module
import dagger.Provides

@Module
//ChatListModule,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ChatListModule(private val view: ChatListContract.View) {
    @ActivityScope
    @Provides
    fun provideChatView(): ChatListContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideChatModel(model: ChatListModule): ChatListContract.Model {
        return model
    }
}