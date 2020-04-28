package com.wl.lawyer.mvp.ui.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerConsultingOrderComponent
import com.wl.lawyer.di.module.ConsultingOrderModule
import com.wl.lawyer.mvp.contract.ConsultingOrderContract
import com.wl.lawyer.mvp.presenter.ConsultingOrderPresenter
import com.wl.lawyer.mvp.ui.adapter.ConsultingOrderPagerAdapter
import kotlinx.android.synthetic.main.activity_consulting_order.*
import kotlinx.android.synthetic.main.include.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * 咨询订单
 */
class ConsultingOrderActivity : BaseSupportActivity<ConsultingOrderPresenter>(),
    ConsultingOrderContract.View {

    val dataList = arrayListOf<String>("全部", "咨询中", "已失效")

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerConsultingOrderComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .consultingOrderModule(ConsultingOrderModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_consulting_order
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "我的咨询订单"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initMagicIndicator()
    }

    private fun initMagicIndicator() {
        val adapter = ConsultingOrderPagerAdapter(dataList)
        // 点击事件
        adapter.onClickListener = object : ConsultingOrderPagerAdapter.OnClickListener {
            override fun onClick(type: Int?, position: Int?) {
                // 订单详情
                mPresenter?.mAppManager?.startActivity(ConsultingOrderDetailActivity::class.java)
            }
        }
        // 设置ViewPager
        view_pager.adapter = adapter
        // 设置TabLayout
        val commonNavigator = CommonNavigator(mContext)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                // 设置Tab参数
                val simplePagerTitleView =
                    ColorTransitionPagerTitleView(mContext) as SimplePagerTitleView
                simplePagerTitleView.normalColor = Color.BLACK
                simplePagerTitleView.selectedColor = Color.BLUE
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
