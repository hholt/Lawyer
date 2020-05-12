package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerGraphicConsultationComponent
import com.wl.lawyer.di.module.GraphicConsultationModule
import com.wl.lawyer.mvp.contract.GraphicConsultationContract
import com.wl.lawyer.mvp.model.bean.BaseListBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import com.wl.lawyer.mvp.presenter.GraphicConsultationPresenter
import com.wl.lawyer.mvp.ui.adapter.GraphicConsultationAdapter
import com.wl.lawyer.mvp.ui.callback.ConsulationQuickDiff
import kotlinx.android.synthetic.main.activity_graphic_consultation.*
import kotlinx.android.synthetic.main.include.*

/**
 * 图文咨询列表
 */

@Route(path = RouterPath.GRAPHIC_CONSULE)
class GraphicConsultationActivity : BaseSupportActivity<GraphicConsultationPresenter>(),
    GraphicConsultationContract.View {

    var lastData: BaseListBean<GraphicConsultationBean>? = null
    var consultList: MutableList<GraphicConsultationBean> = arrayListOf<GraphicConsultationBean>()
    var allLoad = false

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerGraphicConsultationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .graphicConsultationModule(GraphicConsultationModule(this))
            .build()
            .inject(this)
    }

    private val adapter by lazy {
        GraphicConsultationAdapter(
            arrayListOf(
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
//                mPresenter?.mAppManager?.startActivity(GCDetailsActivity::class.java)
                ARouter.getInstance()
                    .build(RouterPath.GRAPHIC_CONSULE_DETAIL)
                    .withSerializable(RouterArgs.GRAPHIC_CONSULATION, getItem(position))
                    .navigation()
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_graphic_consultation
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "我的图文咨询"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter
        rv_item.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!mPresenter!!.isLoadingMore && !allLoad) {
                    var manager = recyclerView.layoutManager
                    manager?.getChildAt(0)?.let {
                        var position = recyclerView.getChildViewHolder(it).adapterPosition

                        if (position + manager.childCount == consultList.size) {
                            mPresenter?.loadMore(consultList.size / 10 + 2)
                        }
                    }
                }
            }
        })

        mPresenter?.getPTCList()
    }

    override fun onPTCListGet(listBean: BaseListBean<GraphicConsultationBean>) {
        consultList.clear()
        lastData = listBean
        consultList.addAll(listBean.list)
        updateAdapter()
    }

    override fun onPTCListMore(listBean: BaseListBean<GraphicConsultationBean>) {
        lastData = listBean
        consultList.addAll(listBean.list)
        updateAdapter()
    }

    fun updateAdapter() {
        adapter.setNewDiffData(ConsulationQuickDiff(consultList))
        consultList?.let {
            if (it.size == lastData?.totalCount) {
                allLoad = true
                adapter.addFooterView(RVUtils.myFooterView(mContext, null))
            }
        }
    }
}