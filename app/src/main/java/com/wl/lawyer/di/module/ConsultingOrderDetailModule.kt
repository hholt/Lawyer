package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.ConsultingOrderDetailContract
import com.wl.lawyer.mvp.model.ConsultingOrderDetailModel


@Module
//构建ConsultingOrderDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ConsultingOrderDetailModule(private val view: ConsultingOrderDetailContract.View) {
    @ActivityScope
    @Provides
    fun provideConsultingOrderDetailView(): ConsultingOrderDetailContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideConsultingOrderDetailModel(model: ConsultingOrderDetailModel): ConsultingOrderDetailContract.Model {
        return model
    }
}
