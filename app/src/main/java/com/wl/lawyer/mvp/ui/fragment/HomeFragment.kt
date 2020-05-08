package com.wl.lawyer.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.lxj.androidktx.core.click
import com.vondear.rxtool.RxBarTool
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportFragment
import com.wl.lawyer.di.component.DaggerHomeComponent
import com.wl.lawyer.di.module.HomeModule
import com.wl.lawyer.mvp.contract.HomeContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.*
import com.wl.lawyer.mvp.presenter.HomePresenter
import com.wl.lawyer.mvp.ui.activity.*
import com.wl.lawyer.mvp.ui.adapter.BannerImageAdapter
import com.wl.lawyer.mvp.ui.adapter.HomeFuncAdapter
import com.wl.lawyer.mvp.ui.adapter.LawClassAdapter
import com.wl.lawyer.mvp.ui.adapter.RecommendedLawyerAdapter
import com.youth.banner.Banner
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.adapter_law_class.*
import kotlinx.android.synthetic.main.adapter_law_class_header.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_status_bar.*


class HomeFragment : BaseSupportFragment<HomePresenter>(), HomeContract.View {

    var firstLawyer: LawyerBean? = null

    lateinit var mLectureList: List<HomeDataBean.LawLectureBean>

    private val funcAdapter: HomeFuncAdapter by lazy {
        HomeFuncAdapter(funcItemData).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    0 -> {// 在线咨询
//                        mPresenter?.mAppManager?.startActivity(OnlineConsultationActivity::class.java)
                        ARouter.getInstance().build(RouterPath.SERVICE_CONSULTATION)
                            .withSerializable("lawyer", firstLawyer)
                            .navigation()
                    }
                    1 -> {// 发布图文咨询
                        mPresenter?.mAppManager?.startActivity(PublishGraphicConsultationActivity::class.java)
                    }
                    2 -> {// 文书协作
//                        mPresenter?.mAppManager?.startActivity(ClericalCollaborationActivity::class.java)
                        ARouter.getInstance().build(RouterPath.SERVICE_COLLABORATION)
                            .withSerializable("lawyer", firstLawyer)
                            .navigation()
                    }
                    3 -> {// 委托书报价单
//                        mPresenter?.mAppManager?.startActivity(PowerAttorneyActivity::class.java)
                        ARouter.getInstance().build(RouterPath.SERVICE_CASE)
                            .withSerializable("lawyer", firstLawyer)
                            .navigation()
                    }
                }
            }
        }
    }

    private val recommendedAdapter: RecommendedLawyerAdapter by lazy {
        RecommendedLawyerAdapter(recommendedData).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                // 律师详情
//                mPresenter?.mAppManager?.startActivity(LawyerActivity::class.java)
                getItem(position)?.lawyer?.let {
                    ARouter.getInstance().build(RouterPath.LAWYER_ACTIVITY)
                        .withSerializable("lawyer", it)
                        .navigation()
                }
            }
        }
    }

    private val lawClassAdapter: LawClassAdapter by lazy {
        LawClassAdapter(lawClassData).apply {

        }
    }

    override fun setData(data: Any?) {

    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        // 设置状态栏高度
        status_bar.layoutParams.height = RxBarTool.getStatusBarHeight(mContext)
        initBanner()
        initFuncRv()
        initRecommendedAdapter()
        initGetData()
    }

    private fun initGetData() {
        mPresenter?.indexData()
    }

    private fun initLawClass() {
        var index = 0
        initLecture(mLectureList[index])
        tv_change.click{
            index = (index + 1) % mLectureList.size
            initLecture(mLectureList[index])
        }
    }

    private fun initLecture(lecture: HomeDataBean.LawLectureBean) {
        lecture.apply {
            tv_law_class_title.text = title
            tv_law_class_content.text = text
        }
    }

    private fun initRecommendedAdapter() {
        rv_recommended_lawyer.layoutManager = LinearLayoutManager(mContext)
        rv_recommended_lawyer.adapter = recommendedAdapter
        rv_recommended_lawyer.isNestedScrollingEnabled = false
    }

    private fun initBanner() {
//        var data = arrayListOf<BannerDataBean>()
//        data.add(BannerDataBean(null, R.drawable.ic_banner, null))
//        data.add(BannerDataBean(null, R.drawable.ic_banner, null))
//        data.add(BannerDataBean(null, R.drawable.ic_banner, null))
//        banner.adapter = BannerImageAdapter(data)
//        banner.indicator = CircleIndicator(context)
//        banner.setIndicatorSelectedColor(resources.getColor(R.color.banner_indicator_select_color))
//        banner.setIndicatorNormalColor(resources.getColor(R.color.banner_indicator_normal_color))
//        banner.setIndicatorMargins(
//            IndicatorConfig.Margins(
//                0,
//                0,
//                0,
//                ArmsUtils.dip2px(context!!, 48f)
//            )
//        )
//        banner.setOrientation(Banner.HORIZONTAL)
    }

    private fun initFuncRv() {
        rv_func.layoutManager = GridLayoutManager(mContext, 4)
        rv_func.adapter = funcAdapter
        rv_func.isNestedScrollingEnabled = false
        rv_func.isFocusable = false
    }

    override fun onStart() {
        super.onStart()
        if (banner.adapter != null) {
            banner.start()
        }
    }

    override fun onStop() {
        super.onStop()
        banner.stop()
    }

    private val funcItemData: ArrayList<FuncDataBean>
        get() = arrayListOf(
            FuncDataBean(R.drawable.ic_advisory, "在线咨询", null),
            FuncDataBean(R.drawable.ic_post, "发帖咨询", null),
            FuncDataBean(R.drawable.ic_instrument, "文书代办", null),
            FuncDataBean(R.drawable.ic_commission, "案件委贷", null),
            FuncDataBean(R.drawable.ic_cost, "律师费估计", null),
            FuncDataBean(R.drawable.ic_find_regulations, "法律法规查询", null),
            FuncDataBean(R.drawable.ic_calculation, "诉讼费计算", null),
            FuncDataBean(R.drawable.ic_corporate_services, "企业服务", null)
        )
    private val recommendedData: ArrayList<RecommendedLawyerDataBean>
        get() = arrayListOf(
            RecommendedLawyerDataBean(true, "推荐律师"),
            RecommendedLawyerDataBean(
                false,
                "",
                "http://b-ssl.duitang.com/uploads/item/201709/02/20170902135603_2mYKC.thumb.700_0.jpeg",
                "jessehuan",
                "1",
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
            ),
            RecommendedLawyerDataBean(
                false,
                "",
                "http://b-ssl.duitang.com/uploads/item/201709/02/20170902135603_2mYKC.thumb.700_0.jpeg",
                "jessehuan",
                "3",
                "$33.33",
                "湖南张家界",
                "婚姻案件"
            )
        )

    private val lawClassData: ArrayList<LawClassDataBean>
        get() = arrayListOf(
            LawClassDataBean(true, "", null, null),
            LawClassDataBean(
                false,
                "",
                "没提前提离职，公司要求给违约金合法吗？",
                "公司要求违约金是不合法的。在法律上公司只能在劳动合同上限制约定违约金，其他违约金均不合法。在法律上公司只能在劳动合同上就培训服务期和竞业限制约定违约金，其他违约金均不合法。"
            ),
            LawClassDataBean(
                false,
                "",
                "没提前提离职，公司要求给违约金合法吗？",
                "公司要求违约金是不合法的。在法律上公司只能在劳动合同上限制约定违约金，其他违约金均不合法。在法律上公司只能在劳动合同上就培训服务期和竞业限制约定违约金，其他违约金均不合法。"
            )
        )


    override fun isImmersionBarEnabled(): Boolean {
        return false
    }

    override fun indexBannerData(bannerList: List<HomeDataBean.BannerBean>) {
        var data = arrayListOf<BannerDataBean>()
        bannerList?.let {
            for (bannerBean in it) {
                data.add(
                    BannerDataBean(
                        Api.APP_DOMAIN + bannerBean.image,
                        null,
                        bannerBean
                    )
                )
            }
        }
        banner.adapter = BannerImageAdapter(data)
        banner.indicator = CircleIndicator(context)
        banner.setIndicatorSelectedColor(resources.getColor(R.color.banner_indicator_select_color))
        banner.setIndicatorNormalColor(resources.getColor(R.color.banner_indicator_normal_color))
        banner.setIndicatorMargins(
            IndicatorConfig.Margins(
                0,
                0,
                0,
                ArmsUtils.dip2px(context!!, 48f)
            )
        )
        banner.setOnBannerListener(object : OnBannerListener<BannerDataBean> {
            override fun onBannerChanged(position: Int) {

            }

            override fun OnBannerClick(data: BannerDataBean?, position: Int) {
                data?.let {

                }
            }

        })
        banner.setOrientation(Banner.HORIZONTAL)
    }

    override fun indexLawyerList(lawyerList: List<LawyerBean>) {
        var data = arrayListOf<RecommendedLawyerDataBean>()
        data.add(RecommendedLawyerDataBean(true, "推荐律师"))
        lawyerList?.let {
            for (lawyer in it) {
                data.add(RecommendedLawyerDataBean(lawyer))
            }
            firstLawyer = it[0]
        }
        recommendedAdapter.setNewData(data)
    }

    override fun indexLawLectureList(lectureList: List<HomeDataBean.LawLectureBean>) {
        mLectureList = lectureList
        initLawClass()
    }
}