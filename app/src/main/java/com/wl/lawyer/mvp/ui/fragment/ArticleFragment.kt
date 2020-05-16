package com.wl.lawyer.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.diff.BaseQuickDiffCallback
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.click
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportFragment
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerArticleFragmentComponent
import com.wl.lawyer.di.module.ArticleFragmentModule
import com.wl.lawyer.mvp.contract.ArticleFragmentContract
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean
import com.wl.lawyer.mvp.model.bean.LawyerBean
import com.wl.lawyer.mvp.model.bean.MyConsultOrderBean
import com.wl.lawyer.mvp.presenter.ArticleFragmentPresenter
import com.wl.lawyer.mvp.ui.adapter.ConsultingOrderAdapter
import com.wl.lawyer.mvp.ui.adapter.LawyerArticleAdapter
import kotlinx.android.synthetic.main.fragment_consult_order.*
import kotlinx.android.synthetic.main.include_find_law.*

class ArticleFragment(val type: Int, val lawyer: LawyerBean) :
    BaseSupportFragment<ArticleFragmentPresenter>(),
    ArticleFragmentContract.View {

    companion object {
        fun newInstance(type: Int, lawyer: LawyerBean): ArticleFragment {
            val fragment = ArticleFragment(type, lawyer)
            return fragment
        }
    }

    private var mKeyword: String = ""
    var lastData: BaseListBean<LawyerArticleDetailBean>? = null
    var dataList: MutableList<LawyerArticleDetailBean> = arrayListOf()
    var allLoad = false
    val mFooterView: View by lazy {
        RVUtils.myFooterView(mContext, rv_order)
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerArticleFragmentComponent
            .builder()
            .appComponent(appComponent)
            .articleFragmentModule(ArticleFragmentModule((this)))
            .build()
            .inject(this)

    }

    private val adapter by lazy {
        LawyerArticleAdapter(
            arrayListOf(
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                getItem(position)?.let {
                    ARouter.getInstance()
                        .build(RouterPath.LAWYER_ARTICLE_DETAIL)
                        .withSerializable(RouterArgs.LAWYER_ARTICLE_DETAIL, it)
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
        rv_order.adapter = adapter
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
                                    type,
                                    lawyer.lawyerId
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

    fun handleFooterView(data: BaseListBean<LawyerArticleDetailBean>) {
        allLoad = data.totalCount == dataList.size
        if (allLoad) {
            if (adapter.footerLayoutCount == 0) adapter.addFooterView(mFooterView)
        } else {
            if (adapter.footerLayoutCount == 1) adapter.removeFooterView(mFooterView)
        }
    }

    override fun onLawyerArticleGet(data: BaseListBean<LawyerArticleDetailBean>) {
        adapter.setNewData(data.list)
        adapter.emptyView = getMEmptyView()
        lastData = data
        dataList.clear()
        dataList.addAll(data.list)
        handleFooterView(data)
    }

    override fun onMoreArticleGet(data: BaseListBean<LawyerArticleDetailBean>) {
        lastData = data
        dataList.addAll(data.list)
        adapter.setNewDiffData(object : BaseQuickDiffCallback<LawyerArticleDetailBean>(dataList) {
            override fun areItemsTheSame(
                oldItem: LawyerArticleDetailBean,
                newItem: LawyerArticleDetailBean
            ) = oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: LawyerArticleDetailBean,
                newItem: LawyerArticleDetailBean
            ) = oldItem.id == newItem.id

        })
        handleFooterView(data)
    }

    override fun getKeyWord() = mKeyword
    fun search(keyword: String) {
        mKeyword = keyword
        mPresenter?.search(type,
            lawyer.lawyerId)
    }
}