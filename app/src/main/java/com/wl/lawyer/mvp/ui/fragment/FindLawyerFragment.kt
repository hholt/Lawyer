package com.wl.lawyer.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.vondear.rxtool.RxBarTool
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportFragment
import com.wl.lawyer.app.onStartActivityWithSingleTop
import com.wl.lawyer.di.component.DaggerFindLawyerComponent
import com.wl.lawyer.di.module.FindLawyerModule
import com.wl.lawyer.mvp.contract.FindLawyerContract
import com.wl.lawyer.mvp.model.bean.RecommendedLawyerDataBean
import com.wl.lawyer.mvp.presenter.FindLawyerPresenter
import com.wl.lawyer.mvp.ui.activity.LawyerDetailsActivity
import com.wl.lawyer.mvp.ui.adapter.LawAdapter
import kotlinx.android.synthetic.main.fragment_find_lawyer.*
import kotlinx.android.synthetic.main.include_status_bar.*

/**
 * 找律师
 */
class FindLawyerFragment : BaseSupportFragment<FindLawyerPresenter>(), FindLawyerContract.View {
    companion object {
        fun newInstance(): FindLawyerFragment {
            val fragment = FindLawyerFragment()
            return fragment
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerFindLawyerComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .findLawyerModule(FindLawyerModule(this))
            .build()
            .inject(this)
    }

    private val lawAdapter by lazy {
        LawAdapter(
            arrayListOf(
                RecommendedLawyerDataBean(
                    false,
                    "",
                    "http://b-ssl.duitang.com/uploads/item/201709/02/20170902135603_2mYKC.thumb.700_0.jpeg",
                    "jessehuan",
                    "2",
                    "$33.33",
                    "湖南张家界",
                    "婚姻案件"
                ),
                RecommendedLawyerDataBean(
                    false,
                    "",
                    "http://b-ssl.duitang.com/uploads/item/201709/02/20170902135603_2mYKC.thumb.700_0.jpeg",
                    "jessehuan",
                    "2",
                    "$33.33",
                    "湖南张家界",
                    "婚姻案件"
                )
            )
        ).apply {
            addFooterView(getFooterView())
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {// 律师详情
                    else -> {
                        mPresenter?.mAppManager?.onStartActivityWithSingleTop(LawyerDetailsActivity::class.java)
                    }
                }
            }
        }
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_find_lawyer, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        // 设置状态栏高度
        status_bar.layoutParams.height = RxBarTool.getStatusBarHeight(mContext)
        rv_law.layoutManager = LinearLayoutManager(mContext)
        rv_law.adapter = lawAdapter
    }

    override fun onStart() {
        super.onStart()
        lawAdapter.emptyView = getMEmptyView()
    }

    override fun setData(data: Any?) {

    }

    private fun getFooterView(): View {
        return layoutInflater.inflate(R.layout.include_law_footer, rv_law, false)
    }

    private fun getMEmptyView(): View {
        return layoutInflater.inflate(R.layout.layout_no_data, rv_law, false)
    }


}
