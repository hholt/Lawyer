package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.AccountSecurityContract


@ActivityScope
class AccountSecurityModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    AccountSecurityContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

}
