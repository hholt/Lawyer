package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.model.api.service.TencentCloudService
import javax.inject.Inject

@ActivityScope
class ChatListModule
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ChatListContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getUserSignature() = mRepositoryManager.obtainRetrofitService(TencentCloudService::class.java).getSignature()
}