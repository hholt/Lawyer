package com.wl.lawyer.mvp.ui.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerLawyerArticleComponent
import com.wl.lawyer.di.module.LawyerArticleModule
import com.wl.lawyer.mvp.contract.LawyerArticleContract
import com.wl.lawyer.mvp.model.bean.LawyerBean
import com.wl.lawyer.mvp.presenter.LawyerArticlePresenter
import com.wl.lawyer.mvp.ui.adapter.CommonFragmentAdapter
import com.wl.lawyer.mvp.ui.fragment.ArticleFragment
import kotlinx.android.synthetic.main.activity_lawyer_article.*
import kotlinx.android.synthetic.main.include.*
import kotlinx.android.synthetic.main.include_find_law.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

@Route(path = RouterPath.LAWYER_ARTICLE_LIST)
class LawyerArticleActivity: BaseSupportActivity<LawyerArticlePresenter>(),
    LawyerArticleContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLawyerArticleComponent
            .builder()
            .appComponent(appComponent)
            .lawyerArticleModule(LawyerArticleModule(this))
            .build()
            .inject(this)
    }

    @Autowired(name = RouterArgs.LAWYER)
    @JvmField
    var lawyer: LawyerBean? = null

    val dataList = arrayListOf<String>("经典案例", "普法文章")

    private val adapter by lazy {
        CommonFragmentAdapter(
            arrayListOf(
                ArticleFragment.newInstance(1, lawyer),
                ArticleFragment.newInstance(2, lawyer)
            )
            , supportFragmentManager
        )
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_lawyer_article
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        tv_title.text = "最新文章"
        et_search.hint = "请输入文章名"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initMagicIndicator()

    }

    fun initMagicIndicator() {
        // 设置ViewPager
        view_pager.offscreenPageLimit = 2
        view_pager.adapter = adapter
        // 设置TabLayout
        val commonNavigator = CommonNavigator(mContext)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                // 设置Tab参数
                val simplePagerTitleView =
                    ColorTransitionPagerTitleView(mContext) as SimplePagerTitleView
                simplePagerTitleView.normalColor = Color.BLACK
                simplePagerTitleView.selectedColor = ContextCompat.getColor(mContext, R.color.app_font_blue)
                simplePagerTitleView.text = dataList[index]
                simplePagerTitleView.textSize = 16f
                // Tab的宽
                simplePagerTitleView.width = ArmsUtils.dip2px(mContext, 100f)
                simplePagerTitleView.setOnClickListener {
                    view_pager.currentItem = index
                }
                return simplePagerTitleView
            }

            override fun getCount(): Int = dataList.size

            override fun getIndicator(context: Context?): IPagerIndicator {
                // Tab下面指示线
                val linePagerIndicator = LinePagerIndicator(mContext)
                linePagerIndicator.mode = LinePagerIndicator.MODE_EXACTLY
                linePagerIndicator.lineWidth = ArmsUtils.dip2px(mContext, 53f) * 1f
                linePagerIndicator.lineHeight = ArmsUtils.dip2px(mContext, 2f) * 1f
                linePagerIndicator.setColors(Color.BLUE)
                return linePagerIndicator
            }
        }
        indicator.navigator = commonNavigator
        val titleContainer = commonNavigator.titleContainer
        titleContainer.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        titleContainer.dividerDrawable = object : ColorDrawable() {
            override fun getIntrinsicWidth(): Int {
                return ArmsUtils.dip2px(mContext, 15f)
            }
        }

        val fragmentContainerHelper = FragmentContainerHelper(indicator)
        fragmentContainerHelper.setInterpolator(OvershootInterpolator(2.0f))
        fragmentContainerHelper.setDuration(300)
        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                fragmentContainerHelper.handlePageSelected(position)
            }
        })
    }

}