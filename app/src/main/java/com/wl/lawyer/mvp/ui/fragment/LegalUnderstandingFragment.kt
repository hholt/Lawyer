package com.wl.lawyer.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.vondear.rxtool.RxBarTool
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportFragment
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerLegalUnderstandingComponent
import com.wl.lawyer.di.module.LegalUnderstandingModule
import com.wl.lawyer.mvp.contract.LegalUnderstandingContract
import com.wl.lawyer.mvp.model.bean.BannerDataBean
import com.wl.lawyer.mvp.model.bean.LawPopularizationDataBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean
import com.wl.lawyer.mvp.presenter.LegalUnderstandingPresenter
import com.wl.lawyer.mvp.ui.activity.PopularizationCourseActivity
import com.wl.lawyer.mvp.ui.adapter.BannerImageAdapter
import com.wl.lawyer.mvp.ui.adapter.LawPopularizationAdapter
import com.wl.lawyer.mvp.ui.widget.RectangleIndicator
import com.youth.banner.Banner
import com.youth.banner.config.IndicatorConfig
import kotlinx.android.synthetic.main.fragment_find_lawyer.*
import kotlinx.android.synthetic.main.fragment_home.banner
import kotlinx.android.synthetic.main.fragment_legal_understanding.*
import kotlinx.android.synthetic.main.include_status_bar.*

/**
 * 法律明白人
 */
class LegalUnderstandingFragment : BaseSupportFragment<LegalUnderstandingPresenter>(),
    LegalUnderstandingContract.View {

    companion object {
        fun newInstance(): LegalUnderstandingFragment {
            val fragment = LegalUnderstandingFragment()
            return fragment
        }
    }

    private val bannerImageAdapter by lazy {
        BannerImageAdapter(
            arrayListOf(
                BannerDataBean(null, R.drawable.ic_banner_understanding, null),
                BannerDataBean(null, R.drawable.ic_banner_understanding, null),
                BannerDataBean(null, R.drawable.ic_banner_understanding, null)
            )
        ).apply {

        }
    }

    private val lawPopularizationAdapter by lazy {
        LawPopularizationAdapter(
            arrayListOf(
                LawPopularizationDataBean(true, "普法动态"),
                LawPopularizationDataBean(
                    false,
                    "这是标题",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580887823981&di=a5db067cd8b4284fcaa78a1b0a6c59a7&imgtype=0&src=http%3A%2F%2Fphoto.orsoon.com%2F180320%2F180320_50%2FbughwyXT4O_small.jpg",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                LawPopularizationDataBean(
                    false,
                    "这是标题",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580887823981&di=a5db067cd8b4284fcaa78a1b0a6c59a7&imgtype=0&src=http%3A%2F%2Fphoto.orsoon.com%2F180320%2F180320_50%2FbughwyXT4O_small.jpg",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                LawPopularizationDataBean(
                    false,
                    "这是标题",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580887823981&di=a5db067cd8b4284fcaa78a1b0a6c59a7&imgtype=0&src=http%3A%2F%2Fphoto.orsoon.com%2F180320%2F180320_50%2FbughwyXT4O_small.jpg",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                LawPopularizationDataBean(
                    false,
                    "这是标题",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580887823981&di=a5db067cd8b4284fcaa78a1b0a6c59a7&imgtype=0&src=http%3A%2F%2Fphoto.orsoon.com%2F180320%2F180320_50%2FbughwyXT4O_small.jpg",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                ),
                LawPopularizationDataBean(
                    false,
                    "这是标题",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580887823981&di=a5db067cd8b4284fcaa78a1b0a6c59a7&imgtype=0&src=http%3A%2F%2Fphoto.orsoon.com%2F180320%2F180320_50%2FbughwyXT4O_small.jpg",
                    "原告与被告经媒人介绍认识，2018年正月初六经媒人邢某向我索要彩礼钱2万元，买东西花费1800元，见面礼300..."
                )
            )
        ).apply {
            setOnItemClickListener { _, _, position ->
                getItem(position)?.lawyerArticle?.let {
                    ARouter.getInstance()
                        .build(RouterPath.LAWYER_ARTICLE)
                        .withSerializable(RouterArgs.ARTICLE, it)
                        .navigation()
                }
            }
            setOnItemChildClickListener { adapter, view, position ->
                view?.let {
                    if (it.id == R.id.tv_more) {
                        ARouter.getInstance()
                            .build(RouterPath.LAWYER_ARTICLE_LIST)
                            .navigation()
                    }
                }
            }
            addFooterView(RVUtils.myFooterView(mContext, null))
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerLegalUnderstandingComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .legalUnderstandingModule(LegalUnderstandingModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_legal_understanding, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        // 设置状态栏高度
        status_bar.layoutParams.height = RxBarTool.getStatusBarHeight(mContext)
        initBanner()
        initLawPopularization()
        // 普法课程
        ll_general_course.setOnClickListener {
            ARouter.getInstance().build(RouterPath.POPULARIZATION_COURSE)
                .withString(RouterArgs.POPULARIZATION_TYPE, AppConstant.KEY_POPULARIZATION)
                .navigation()
            /*val intent = Intent(mContext, PopularizationCourseActivity::class.java)
            intent.putExtra(AppConstant.INTENT_POPULARIZATION, AppConstant.KEY_POPULARIZATION)
            mPresenter?.mAppManager?.startActivity(intent)*/
        }
        // 直播普法
        ll_live_popular_law.setOnClickListener {
            ARouter.getInstance().build(RouterPath.POPULARIZATION_COURSE)
                .withString(RouterArgs.POPULARIZATION_TYPE, AppConstant.KEY_LIVE)
                .navigation()
            /*val intent = Intent(mContext, PopularizationCourseActivity::class.java)
            intent.putExtra(AppConstant.INTENT_POPULARIZATION, AppConstant.KEY_LIVE)
            mPresenter?.mAppManager?.startActivity(intent)*/
        }
    }

    //普法动态
    private fun initLawPopularization() {
        rv_law_popularization.isNestedScrollingEnabled = false
        rv_law_popularization.isFocusable = false
        rv_law_popularization.layoutManager = LinearLayoutManager(mContext)
        rv_law_popularization.adapter = lawPopularizationAdapter
        mPresenter?.getArticles()
    }

    //普法Banner
    private fun initBanner() {
        banner.adapter = bannerImageAdapter
        banner.indicator = RectangleIndicator(context!!)
        banner.setIndicatorSelectedColor(resources.getColor(R.color.banner_law_under_indicator_select_color))
        banner.setIndicatorNormalColor(resources.getColor(R.color.banner_law_under_indicator_normal_color))
        banner.setOrientation(Banner.HORIZONTAL)
        banner.setIndicatorWidth(
            ArmsUtils.dip2px(mContext, 22f),
            ArmsUtils.dip2px(mContext, 22f)
        )
        banner.setIndicatorMargins(
            IndicatorConfig.Margins(
                0,
                0,
                0,
                ArmsUtils.dip2px(context!!, 14f)
            )
        )
    }

    override fun setData(data: Any?) {
    }

    override fun onGetArticles(articles: List<LawyerArticleBean>) {
        var data = arrayListOf(
            LawPopularizationDataBean(true, "普法动态")
        )
        for (article in articles) {
            data.add(LawPopularizationDataBean(article))
        }
        lawPopularizationAdapter.setNewData(data)
    }

}
