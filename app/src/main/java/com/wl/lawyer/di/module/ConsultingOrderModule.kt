package com.wl.lawyer.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.wl.lawyer.mvp.contract.ConsultingOrderContract
import com.wl.lawyer.mvp.model.ConsultingOrderModel


@Module
//构建ConsultingOrderModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ConsultingOrderModule(private val view: ConsultingOrderContract.View) {
    @ActivityScope
    @Provides
    fun provideConsultingOrderView(): ConsultingOrderContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideConsultingOrderModel(model: ConsultingOrderModel): ConsultingOrderContract.Model {
        return model
    }
}
