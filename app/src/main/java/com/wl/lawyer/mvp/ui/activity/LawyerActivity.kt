package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.circleImage
import com.wl.lawyer.app.image
import com.wl.lawyer.app.onBack
import com.wl.lawyer.app.onStartActivityWithSingleTop
import com.wl.lawyer.di.component.DaggerLawyerComponent
import com.wl.lawyer.di.module.LawyerModule
import com.wl.lawyer.mvp.contract.LawyerContract
import com.wl.lawyer.mvp.model.bean.LawPopularizationDataBean
import com.wl.lawyer.mvp.model.bean.PopularizationCourseReviewsBean
import com.wl.lawyer.mvp.model.bean.ServiceBean
import com.wl.lawyer.mvp.presenter.LawyerPresenter
import com.wl.lawyer.mvp.ui.adapter.LawPopularizationAdapter
import com.wl.lawyer.mvp.ui.adapter.PopularizationCourseReviewsAdapter
import com.wl.lawyer.mvp.ui.adapter.ServiceAdapter
import kotlinx.android.synthetic.main.activity_lawyer.*
import kotlinx.android.synthetic.main.adapter_recommended_lawyer.*
import kotlinx.android.synthetic.main.adapter_recommended_lawyer.view.*
import kotlinx.android.synthetic.main.adapter_simple_desc.*
import kotlinx.android.synthetic.main.include.*

/**
 * 律师详情
 */
class LawyerActivity : BaseSupportActivity<LawyerPresenter>(), LawyerContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLawyerComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .lawyerModule(LawyerModule(this))
            .build()
            .inject(this)
    }

    private val serviceAdapter by lazy {
        ServiceAdapter(
            arrayListOf(
                ServiceBean(true, "服务项目"),
                ServiceBean(R.drawable.ic_online_consultation, "在线咨询", "多种咨询方式满足您的所有需求", "￥49/起"),
                ServiceBean(R.drawable.ic_clerical_assistance, "文书协助", "撰写、审核、修订一站式服务", "￥30/起"),
                ServiceBean(R.drawable.ic_lawyer_cooperation, "律师合作", "见证、委托手续", "500/起"),
                ServiceBean(R.drawable.ic_case_commission, "案件委托定金", "打官司、约见律师", "500定金/起")
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    // 这里添加header 所以这里是1
                    1 -> {
                        // 在线咨询
                        mPresenter?.mAppManager?.onStartActivityWithSingleTop(OnlineConsultationActivity::class.java)
                    }
                    2 -> {
                        // 文书协助
                        mPresenter?.mAppManager?.onStartActivityWithSingleTop(ClericalCollaborationActivity::class.java)
                    }
                    3 -> {
                        // 律师合作
                        mPresenter?.mAppManager?.onStartActivityWithSingleTop(LawyerCooperationActivity::class.java)
                    }

                }
            }
        }
    }

    private val latestArticlesAdapter by lazy {
        LawPopularizationAdapter(
            arrayListOf(
                LawPopularizationDataBean(true, "最新文章"),
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
            // addFooterView(RVUtils.myFooterView(mContext, rv_law))
        }
    }

    private val reviewsAdapter by lazy {
        PopularizationCourseReviewsAdapter(
            arrayListOf(
                PopularizationCourseReviewsBean(
                    true, "评论"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在，专业到位，直指问题所在，专业到位，直指问题所在专业到位，直指问题所在专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                )
            )
        ).apply {

        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_lawyer
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "律师详情"
        tv_price.visibility = View.GONE
        recommended_lawyer.dividing_line.visibility = View.GONE
        tv_job_title.visibility = View.VISIBLE
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }
        iv_right.visibility = View.VISIBLE
        iv_right.image(R.drawable.ic_share_it)
        iv_right.setOnClickListener { }
        iv_recommended_avatar.circleImage("http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg")
        tv_simple_desc.text =
            "本人专职律师，毕业于中国政法大学，主任律师，十年律师从业经验，近十年法院工作经验。本人专职律师，毕业于中国政法大学，主任律师，十年律师从业经验，近十年法院工作经验。"

        // 服务项目
        initServiceAdapter()
        // 最新文章
        initLatestArticlesAdapter()
        // 评论
        initReviewsAdapter()
    }

    private fun initServiceAdapter() {
        rv_service_project.layoutManager = LinearLayoutManager(mContext)
        rv_service_project.adapter = serviceAdapter
        // 设置分割线
        // RVUtils.myDottedDivider(mContext, rv_service_project)
    }

    private fun initLatestArticlesAdapter() {
        rv_latest_articles.layoutManager = LinearLayoutManager(mContext)
        rv_latest_articles.adapter = latestArticlesAdapter
        // 设置分割线
        // RVUtils.myDottedDivider(mContext, rv_service_project)
    }

    private fun initReviewsAdapter() {
        rv_reviews.layoutManager = LinearLayoutManager(mContext)
        rv_reviews.adapter = reviewsAdapter
        // 设置分割线
        // RVUtils.myDottedDivider(mContext, rv_reviews)
    }
}
