package com.wl.lawyer.app.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.StringRes
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastUtils
import com.jess.arms.base.BaseActivity
import com.jess.arms.mvp.IPresenter
import com.jess.arms.mvp.IView
import com.jess.arms.utils.ArmsUtils
import com.wl.base.ActivityCallback
import com.wl.base.IActivityForResult
import com.wl.lawyer.R
import me.yokeyword.fragmentation.*
import me.yokeyword.fragmentation.anim.FragmentAnimator
import java.util.*


abstract class BaseSupportActivity<P : IPresenter> : BaseActivity<P>(), ISupportActivity, IView,
    IActivityForResult {

    internal val mDelegate = SupportActivityDelegate(this)
    lateinit var mContext: BaseSupportActivity<*>

    /**
     * 得到位于栈顶Fragment
     */
    val topFragment: ISupportFragment
        get() = SupportHelper.getTopFragment(supportFragmentManager)

    override fun getSupportDelegate(): SupportActivityDelegate {
        return mDelegate
    }

    override fun post(runnable: Runnable?) {

    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    override fun extraTransaction(): ExtraTransaction {
        return mDelegate.extraTransaction()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        mDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        initImmersionBar()
    }

    open fun initImmersionBar() {
        ImmersionBar.with(this)
            .navigationBarColor(statusColor())
            .statusBarDarkFont(true)// 默认状态栏字体颜色为黑色
//            .keyboardEnable(true)  // 解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
            .init()
    }

    fun statusColor(): Int = R.color.status_color

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDelegate.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        mPresenter!!.onDestroy()
        super.onDestroy()
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }

    /**
     * 不建议复写该方法,请使用 [.onBackPressedSupport] 代替
     */
    override fun onBackPressed() {
        mDelegate.onBackPressed()
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    override fun onBackPressedSupport() {
        mDelegate.onBackPressedSupport()
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    override fun getFragmentAnimator(): FragmentAnimator {
        return mDelegate.fragmentAnimator
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        mDelegate.fragmentAnimator = fragmentAnimator
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     *
     *
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mDelegate.onCreateFragmentAnimator()
    }


    /**
     * startActivityForResult 方法优化
     */

    private var mActivityCallback: ActivityCallback? = null
    private var mActivityRequestCode: Int = 0

    override fun startActivityForResult(cls: Class<out Activity>, callback: ActivityCallback) {
        startActivityForResult(Intent(this, cls), null, callback)
    }

    override fun startActivityForResult(intent: Intent, callback: ActivityCallback) {
        startActivityForResult(intent, null, callback)
    }

    private fun startActivityForResult(
        intent: Intent,
        options: Bundle?,
        callback: ActivityCallback
    ) {
        // 回调还没有结束，所以不能再次调用此方法，这个方法只适合一对一回调，其他需求请使用原生的方法实现
        if (mActivityCallback == null) {
            mActivityCallback = callback
            // 随机生成请求码，这个请求码在 0 - 255 之间
            mActivityRequestCode = Random().nextInt(255)
            startActivityForResult(intent, mActivityRequestCode, options)
        }
    }

    override fun getCurrentActivity(): Activity {
        return this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (mActivityCallback != null && mActivityRequestCode == requestCode) {
            mActivityCallback!!.onActivityResult(resultCode, data)
            mActivityCallback = null
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

//    /**
//     * Activity 回调接口
//     */
//    interface ActivityCallback {
//
//        /**
//         * 结果回调
//         *
//         * @param resultCode 结果码
//         * @param data       数据
//         */
//        fun onActivityResult(resultCode: Int, data: Intent?)
//    }

    /****************************************以下为可选方法(Optional methods) */

    // 选择性拓展其他方法
    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        mDelegate.loadRootFragment(containerId, toFragment)
    }

    fun loadMultipleRootFragment(
        containerId: Int,
        showPosition: Int,
        vararg toFragments: ISupportFragment
    ) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, *toFragments)
    }

    fun start(toFragment: ISupportFragment) {
        mDelegate.start(toFragment)
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        mDelegate.start(toFragment, launchMode)
    }

    /**
     * Pop the fragment.
     */
    fun pop() {
        mDelegate.pop()
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * icon_back stack.
     */
    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment)
    }

    fun showHideFragment(showFragment: ISupportFragment) {
        mDelegate.showHideFragment(showFragment)
    }

    fun showHideFragment(showFragment: ISupportFragment, preFragment: ISupportFragment) {
        mDelegate.showHideFragment(showFragment, preFragment)
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable
    ) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
    }

    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable,
        popAnim: Int
    ) {
        mDelegate.popTo(
            targetFragmentClass,
            includeTargetFragment,
            afterPopTransactionRunnable,
            popAnim
        )
    }

    /**
     * 获取栈内的fragment对象
     */
    fun <T : ISupportFragment?> findFragment(fragmentClass: Class<T>): T? {
        return SupportHelper.findFragment(supportFragmentManager, fragmentClass)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        //ArmsUtils.snackbarText(message);
        //RxToast.info(message)
        ToastUtils.show(message)
    }


    fun showLongTimeMessage(message: String) {
        checkNotNull(message)
        //ArmsUtils.snackbarText(message);
        //RxToast.info(message, Toast.LENGTH_LONG)
    }

    fun showMessage(@StringRes message: Int) {
        checkNotNull(message)
        ArmsUtils.snackbarText(getString(message))
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        mPresenter?.onDestroy()
        finish()
    }

}