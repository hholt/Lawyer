package com.wl.lawyer.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.wl.lawyer.app.utils.RxCompose
import com.wl.lawyer.app.utils.RxView
import com.wl.lawyer.mvp.contract.FindLawyerContract
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.FindLawyerBean
import com.wl.lawyer.mvp.model.bean.SearchBean
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@FragmentScope
class FindLawyerPresenter
@Inject
constructor(model: FindLawyerContract.Model, rootView: FindLawyerContract.View) :
    BasePresenter<FindLawyerContract.Model, FindLawyerContract.View>(model, rootView) {

    fun loadData() {
        mModel.loadData()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<FindLawyerBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<FindLawyerBean>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.updateAdapter(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })

        mModel.getSearchField()
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<SearchBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<SearchBean>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.initProvinceAdapter(it.province)
                            mRootView.initCategoryAdapter(it.legalCategory)
                            mRootView.initSortAdapter(it.sortOrder)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun getSearchField() {
//        mModel.getSearchField()
    }

    fun loadMore(page: Int) {
        if (!isLoadingMore) {
            isLoadingMore = true
            val keyWord = mRootView.getKeyWord()
            mModel.search(
                1, keyWord,
                mRootView.getProvinceId(),
                mRootView.getCityId(),
                mRootView.getBlockId(),
                mRootView.getCategoryId(),
                mRootView.getServiceId(),
                mRootView.getSortBy()
            ).compose(RxCompose.transformer(mRootView))
                .subscribe(object :
                    ErrorHandleSubscriber<BaseResponse<FindLawyerBean>>(mErrorHandler) {
                    override fun onNext(t: BaseResponse<FindLawyerBean>) {
                        if (t.isSuccess) {
                            // 保存用户登录信息
                            t?.data?.let {
                                mRootView.onMoreData(it)
                            }
                        } else {
                            RxView.showErrorMsg(mRootView, t.msg)
                        }
                        isLoadingMore = false
                    }
                })
        }

    }

    fun loadCityData(id: Int) {
        mModel.getAreaData(id)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<List<SearchBean.AreaBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<List<SearchBean.AreaBean>>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.updateCityAdapter(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun loadBlockData(id: Int) {
        mModel.getAreaData(id)
            .compose(RxCompose.transformer(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseResponse<List<SearchBean.AreaBean>>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<List<SearchBean.AreaBean>>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.updateBlockAdapter(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })
    }

    fun search() {
        val keyWord = mRootView.getKeyWord()
        mModel.search(1,keyWord,
            mRootView.getProvinceId(),
            mRootView.getCityId(),
            mRootView.getBlockId(),
            mRootView.getCategoryId(),
            mRootView.getServiceId(),
            mRootView.getSortBy()
        ).compose(RxCompose.transformer(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseResponse<FindLawyerBean>>(mErrorHandler) {
                override fun onNext(t: BaseResponse<FindLawyerBean>) {
                    if (t.isSuccess) {
                        // 保存用户登录信息
                        t?.data?.let {
                            mRootView.onSearch(it)
                        }
                    } else {
                        RxView.showErrorMsg(mRootView, t.msg)
                    }
                }
            })

    }

    var isLoadingMore = false

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

}
