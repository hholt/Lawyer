package com.wl.lawyer.mvp.contract

import android.text.Editable
import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.wl.lawyer.mvp.model.api.BaseResponse
import com.wl.lawyer.mvp.model.bean.CategoryBean
import com.wl.lawyer.mvp.model.bean.FindLawyerBean
import com.wl.lawyer.mvp.model.bean.SearchBean
import io.reactivex.Observable


interface FindLawyerContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun updateAdapter(findLawyerBean: FindLawyerBean)
        fun onSearchFieldGet(searchBean: SearchBean)
        fun initProvinceAdapter(data: List<SearchBean.AreaBean>)
        fun updateCityAdapter(data: List<SearchBean.AreaBean>)
        fun updateBlockAdapter(data: List<SearchBean.AreaBean>)
        fun initCategoryAdapter(data: List<CategoryBean>)
        fun initSortAdapter(data: List<SearchBean.SortBean>)
        fun getKeyWord(): String
        fun getProvinceId(): Int
        fun getCityId(): Int
        fun getBlockId(): Int
        fun getCategoryId(): String
        fun getServiceId(): String
        fun getSortBy(): String
        fun onSearch(findLawyerBean: FindLawyerBean)
        fun onMoreData(findLawyerBean: FindLawyerBean)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun loadData() : Observable<BaseResponse<FindLawyerBean>>
        fun getSearchField() : Observable<BaseResponse<SearchBean>>
        fun getAreaData(id: Int): Observable<BaseResponse<List<SearchBean.AreaBean>>>
        fun search(
            page: Int,
            keyWord: String,
            provinceId: Int,
            cityId: Int,
            blockId: Int,
            categoryId: String,
            serviceId: String,
            sortBy: String
        ) : Observable<BaseResponse<FindLawyerBean>>
    }

}
