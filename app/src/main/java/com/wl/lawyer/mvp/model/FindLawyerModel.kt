package com.wl.lawyer.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.wl.lawyer.mvp.contract.FindLawyerContract


@FragmentScope
class FindLawyerModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    FindLawyerContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

}
