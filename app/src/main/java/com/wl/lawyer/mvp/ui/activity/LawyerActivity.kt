package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.*
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerLawyerComponent
import com.wl.lawyer.di.module.LawyerModule
import com.wl.lawyer.mvp.contract.LawyerContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.*
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
@Route(path = RouterPath.LAWYER_ACTIVITY)
class LawyerActivity : BaseSupportActivity<LawyerPresenter>(), LawyerContract.View {

    @Autowired(name = "lawyer")
    @JvmField
    var lawyer: LawyerBean? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLawyerComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .lawyerModule(LawyerModule(this))
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    private val serviceAdapter by lazy {
        ServiceAdapter(
            arrayListOf(
                ServiceBean(true, "服务项目")
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (getItem(position)?.serviceBean?.id) {
                    // 这里添加header 所以这里是1
                    AppConstant.SERVICE_ID_CONSULTATION -> {
                        // 在线咨询
                        ARouter.getInstance().build(RouterPath.SERVICE_CONSULTATION)
                            .withSerializable(RouterArgs.LAWYER, lawyer)
                            .withSerializable(RouterArgs.LAWYER_SERVICE, getItem(position)?.serviceBean)
                            .navigation()
//                        mPresenter?.mAppManager?.onStartActivityWithSingleTop(OnlineConsultationActivity::class.java)
                    }
                    AppConstant.SERVICE_ID_COLLABORATION -> {
                        // 文书协助
                        ARouter.getInstance().build(RouterPath.SERVICE_COLLABORATION)
                            .withSerializable(RouterArgs.LAWYER, lawyer)
                            .withSerializable(RouterArgs.LAWYER_SERVICE, getItem(position)?.serviceBean)
                            .navigation()
//                        mPresenter?.mAppManager?.onStartActivityWithSingleTop(ClericalCollaborationActivity::class.java)
                    }
                    AppConstant.SERVICE_ID_COOPERATION -> {
                        // 律师合作
                        ARouter.getInstance().build(RouterPath.SERVICE_COOPER)
                            .withSerializable(RouterArgs.LAWYER, lawyer)
                            .withSerializable(RouterArgs.LAWYER_SERVICE, getItem(position)?.serviceBean)
                            .navigation()
//                        mPresenter?.mAppManager?.onStartActivityWithSingleTop(LawyerCooperationActivity::class.java)
                    }
                    AppConstant.SERVICE_ID_CASE -> {
                        // 案件委托
                        ARouter.getInstance().build(RouterPath.SERVICE_CASE)
                            .withSerializable(RouterArgs.LAWYER, lawyer)
                            .withSerializable(RouterArgs.LAWYER_SERVICE, getItem(position)?.serviceBean)
                            .navigation()
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
                )/*,
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
                )*/
            )
        ).apply {

        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_lawyer
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "律师详情"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }
        tv_price.visibility = View.GONE
        lawyer?.let {
            mPresenter?.initData(it.lawyerId)
        }
        recommended_lawyer.dividing_line.visibility = View.GONE
        tv_job_title.visibility = View.VISIBLE
        iv_right.visibility = View.VISIBLE
        iv_right.image(R.drawable.ic_share_it)
        iv_right.setOnClickListener { }
//        iv_recommended_avatar.circleImage("http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg")
//        tv_simple_desc.text =
//            "本人专职律师，毕业于中国政法大学，主任律师，十年律师从业经验，近十年法院工作经验。本人专职律师，毕业于中国政法大学，主任律师，十年律师从业经验，近十年法院工作经验。"

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
        rv_service_project.isNestedScrollingEnabled = false
        rv_service_project.isFocusable = false
        /*lawyer?.serviceList?.let {
            var data = arrayListOf<ServiceBean>()
            ServiceBean(true, "服务项目")
            for (service: LawyerServiceBean in it) {
                data.add(ServiceBean(service))
            }
            serviceAdapter.setNewData(data)
        }*/
        // 设置分割线
        // RVUtils.myDottedDivider(mContext, rv_service_project)
    }

    private fun initLatestArticlesAdapter() {
        rv_latest_articles.layoutManager = LinearLayoutManager(mContext)
        rv_latest_articles.adapter = latestArticlesAdapter
        rv_latest_articles.isNestedScrollingEnabled = false
        rv_latest_articles.isFocusable = false
        // 设置分割线
        // RVUtils.myDottedDivider(mContext, rv_service_project)
    }

    private fun initReviewsAdapter() {
        rv_reviews.layoutManager = LinearLayoutManager(mContext)
        rv_reviews.adapter = reviewsAdapter
        rv_reviews.isNestedScrollingEnabled = false
        rv_reviews.isFocusable = false
        // 设置分割线
        // RVUtils.myDottedDivider(mContext, rv_reviews)
    }

    override fun initLawyerInfo(lawyerInfo: LawyerDetailBean) {
        lawyerInfo.lawyer.let {
            iv_recommended_avatar.circleImage(Api.APP_DOMAIN + lawyerInfo?.avatar)
            tv_name.text = lawyerInfo.userName
            tv_time.text =
                "执业${it.lawyerOld}年  |  ${it.cityText + it.countryText}"
            when (it.isRecommend) {
                1 -> iv_recommended_approve.setImageResource(
                    R.drawable.ic_online
                )
                else -> iv_recommended_approve.setImageResource(
                    R.drawable.ic_offline
                )
            }
            it.categoryList?.let {
                var categories = arrayListOf<String?>()
                for (category: CategoryBean in it) {
                    categories.add(category.name)
                }
                lw_type.setData(categories)
            }
            tv_simple_desc.text = it.introduce
            tv_praise.text = it.score
            tv_consulting_count.text = it.legalServiceNum.toString()
        }
    }

    override fun initService(services: List<LawyerServiceBean>) {
        var data = arrayListOf<ServiceBean>()
        data.add(ServiceBean(true, "服务项目"))
        for (service: LawyerServiceBean in services) {
            data.add(ServiceBean(service))
        }
        serviceAdapter.setNewData(data)
    }

    override fun initArticle(articles: List<LawyerArticleBean>) {
        var data = arrayListOf(
            LawPopularizationDataBean(true, "最新文章"))
        for (article: LawyerArticleBean in articles) {
            data.add(LawPopularizationDataBean(article))
        }
        latestArticlesAdapter.setNewData(data)
    }
}
