package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.wl.lawyer.mvp.contract.ChatListContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.api.service.CommonService
import com.wl.lawyer.mvp.model.api.service.TencentCloudService
import com.wl.lawyer.mvp.model.bean.ChatBean
import com.wl.lawyer.mvp.model.bean.UserBean
import io.reactivex.Observable
import javax.inject.Inject

@ActivityScope
class ChatListModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ChatListContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getUserChatList() = mRepositoryManager.obtainRetrofitService(CommonService::class.java).getChatList()

    override fun getUserProfile(id: Int) = mRepositoryManager.obtainRetrofitService(CommonService::class.java).lawyerData(id)
}