package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.VideoCallContract
import com.wl.lawyer.mvp.model.VideoCallModel


@Module
//构建VideoCallModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class VideoCallModule(private val view: VideoCallContract.View) {
    @ActivityScope
    @Provides
    fun provideVideoCallView(): VideoCallContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideVideoCallModel(model: VideoCallModel): VideoCallContract.Model {
        return model
    }
}
