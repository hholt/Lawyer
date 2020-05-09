package com.wl.lawyer.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.getObject
import com.lxj.androidktx.core.sp
import com.vondear.rxtool.RxKeyboardTool
import com.wl.common.widget.BottomBar
import com.wl.common.widget.BottomBarTab
import com.wl.lawyer.BuildConfig
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.base.BaseSupportFragment
import com.wl.lawyer.app.mlog
import com.wl.lawyer.di.component.DaggerMainfComponent
import com.wl.lawyer.di.module.MainfModule
import com.wl.lawyer.mvp.contract.MainfContract
import com.wl.lawyer.mvp.model.bean.UserBean
import com.wl.lawyer.mvp.presenter.MainfPresenter
import kotlinx.android.synthetic.main.fragment_mainf.*
import me.yokeyword.fragmentation.ISupportFragment


class MainfFragment : BaseSupportFragment<MainfPresenter>(), MainfContract.View {

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

    override fun setData(data: Any?) {

    }

    fun getBottomBar() = bottom_bar

    companion object {
        fun newInstance(): MainfFragment {
            val fragment = MainfFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMainfComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .mainfModule(MainfModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mainf, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            val userBean = sp().getObject<UserBean>(AppConstant.SP_USER)
            userBean?.let {
                mlog(it.userinfo.toString())
            }
        }
        bottom_bar.addItem(mHomeTab)
        bottom_bar.addItem(mFindLawyerTab)
        bottom_bar.addItem(mLegalUnderstandingTab)
        bottom_bar.addItem(mMyTab)
        bottom_bar.setOnTabSelectedListener(object : BottomBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int, prePosition: Int) {
                // 隐藏键盘
                RxKeyboardTool.hideSoftInput(activity)
                showHideFragment(mFragments[position]!!, mFragments[prePosition]!!)
            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabReselected(position: Int) {

            }
        })
        addFragment()
    }

    private fun addFragment() {
        var homeFragment: ISupportFragment? = findChildFragment(HomeFragment::class.java)
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
            mFragments[0] = findChildFragment(HomeFragment::class.java)
            mFragments[1] = findChildFragment(FindLawyerFragment::class.java)
            mFragments[2] = findChildFragment(LegalUnderstandingFragment::class.java)
            mFragments[3] = findChildFragment(MyFragment::class.java)
        }
    }


    override fun onNewBundle(args: Bundle) {
        super.onNewBundle(args)
    }
}
