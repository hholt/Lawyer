package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.WindowManager
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerMainComponent
import com.wl.lawyer.di.module.MainModule
import com.wl.lawyer.mvp.contract.MainContract
import com.wl.lawyer.mvp.presenter.MainPresenter
import com.wl.lawyer.mvp.ui.fragment.MainfFragment

/**
 * 首页
 */
class MainActivity : BaseSupportActivity<MainPresenter>(), MainContract.View {
    /*
        private val mHomeTab: BottomBarTab by lazy {
            BottomBarTab(mContext, R.drawable.ic_home, getString(R.string.tab_home))
        }
        private val mFindLawyerTab: BottomBarTab by lazy {
            BottomBarTab(mContext, R.drawable.ic_find_lawyer, getString(R.string.tab_find_lawyer))
        }
        private val mLegalUnderstandingTab: BottomBarTab by lazy {
            BottomBarTab(
                mContext,
                R.drawable.ic_legal_understanding,
                getString(R.string.tab_understanding)
            )
        }
        private val mMyTab: BottomBarTab by lazy {
            BottomBarTab(mContext, R.drawable.ic_mine, getString(R.string.tab_my))
        }

        private val mFragments = arrayOfNulls<ISupportFragment>(4)
    */
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?) {
        ImmersionBar.with(this).init()
        if (findFragment(MainfFragment::class.java) == null) {
            loadRootFragment(R.id.content, MainfFragment.newInstance())
        }
        /*bottom_bar.addItem(mHomeTab)
        bottom_bar.addItem(mFindLawyerTab)
        bottom_bar.addItem(mLegalUnderstandingTab)
        bottom_bar.addItem(mMyTab)
        bottom_bar.setOnTabSelectedListener(object : BottomBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int, prePosition: Int) {
                showHideFragment(mFragments[position]!!, mFragments[prePosition]!!)
            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabReselected(position: Int) {

            }
        })
        addFragment()*/
    }
/*
    private fun addFragment() {
        var homeFragment: ISupportFragment? = findFragment(HomeFragment::class.java)
        if (homeFragment == null) {
            mFragments[0] = HomeFragment.newInstance()
            mFragments[1] = FindLawyerFragment.newInstance()
            mFragments[2] = LegalUnderstandingFragment.newInstance()
            mFragments[3] = MyFragment.newInstance()
            loadMultipleRootFragment(
                R.id.fragment_contain, 0, mFragments[0]!!, mFragments[1]!!,
                mFragments[2]!!, mFragments[3]!!
            )
        } else {
            mFragments[0] = findFragment(HomeFragment::class.java)
            mFragments[1] = findFragment(FindLawyerFragment::class.java)
            mFragments[2] = findFragment(LegalUnderstandingFragment::class.java)
            mFragments[3] = findFragment(MyFragment::class.java)
        }
    }

    override fun initImmersionBar() {
        // super.initImmersionBar()
    }*/

    override fun initImmersionBar() {
        // super.initImmersionBar()
    }
}