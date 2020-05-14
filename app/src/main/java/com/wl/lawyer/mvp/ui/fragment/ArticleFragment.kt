package com.wl.lawyer.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportFragment
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.mvp.contract.ArticleFragmentContract
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean
import com.wl.lawyer.mvp.model.bean.LawyerBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import com.wl.lawyer.mvp.presenter.ArticleFragmentPresenter
import com.wl.lawyer.mvp.ui.adapter.ConsultingOrderAdapter
import kotlinx.android.synthetic.main.fragment_consult_order.*

class ArticleFragment(val type: Int, val lawyer: LawyerBean ) : BaseSupportFragment<ArticleFragmentPresenter>(),
    ArticleFragmentContract.View {

    companion object {
        fun newInstance(type: Int, lawyer: LawyerBean): ArticleFragment {
            val fragment = ArticleFragment(type, lawyer)
            return fragment
        }
    }

    var lastData: BaseListBean<MyConsultOrderBean>? = null
    var dataList: MutableList<MyConsultOrderBean> = arrayListOf<MyConsultOrderBean>()
    var allLoad = false
    val mFooterView: View by lazy {
        RVUtils.myFooterView(mContext, rv_order)
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    private val adapter by lazy {
        ConsultingOrderAdapter(
            arrayListOf(
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                getItem(position)?.let {
                    ARouter.getInstance()
                        .build(RouterPath.CONSULT_ORDER_DETAIL)
                        .withSerializable(RouterArgs.CONSULT_ORDER, it)
                        .navigation()
                }
            }
        }
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_consult_order, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
//        mPresenter?.getOrderList(status)

        initRecycleView()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mPresenter?.getLawyerArticle(type, lawyer.lawyerId)
    }

    fun initRecycleView() {
        rv_order.layoutManager = LinearLayoutManager(context)
        adapter.emptyView = getMEmptyView()
        rv_order.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!allLoad && !mPresenter!!.isLoadingMore) {
                    var manager = recyclerView.layoutManager
                    manager?.getChildAt(0)?.let {
                        var position = recyclerView.getChildViewHolder(it).adapterPosition
                        lastData?.let {
                            if (position + manager.childCount == dataList.size) {
                                mPresenter?.loadMore(
                                    dataList.size / AppConstant.PAGE_SIZE + 1,
                                    type
                                )
                            }
                        }
                    }
                }
            }
        })
    }

    private fun getMEmptyView(): View {
        return layoutInflater.inflate(R.layout.layout_no_data, rv_order, false)
    }

    override fun setData(data: Any?) {
    }

    /*override fun onOrderListGet(data: BaseListBean<MyConsultOrderBean>) {
        adapter.setNewData(data.list)
        rv_order.adapter = adapter
        lastData = data
        dataList.clear()
        dataList.addAll(data.list)
        handleFooterView(data)
    }*/

    fun handleFooterView(data: BaseListBean<MyConsultOrderBean>) {
        allLoad = data.totalCount == dataList.size
        if (allLoad) adapter.addFooterView(mFooterView) else adapter.removeFooterView(mFooterView)
    }

    override fun onLawyerArticleGet(data: BaseListBean<LawyerArticleDetailBean>) {
        /*adapter.setNewData(data.list)
        rv_order.adapter = adapter
        lastData = data
        dataList.clear()
        dataList.addAll(data.list)
        handleFooterView(data)*/
    }
}