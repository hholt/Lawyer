package com.wl.lawyer.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.getObject
import com.lxj.androidktx.core.sp
import com.wl.base.BaseDialog
import com.wl.common.activity.PhotoActivity
import com.wl.common.widget.MenuDialog
import com.wl.lawyer.R
import com.wl.lawyer.app.*
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.utils.ActivityUtils
import com.wl.lawyer.app.utils.AppUtils
import com.wl.lawyer.di.component.DaggerPersonalInformationComponent
import com.wl.lawyer.di.module.PersonalInformationModule
import com.wl.lawyer.mvp.contract.PersonalInformationContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.SettingDataBean
import com.wl.lawyer.mvp.model.bean.UserBean
import com.wl.lawyer.mvp.presenter.PersonalInformationPresenter
import com.wl.lawyer.mvp.ui.adapter.SettingAdapter
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.include.*

/**
 * 个人信息
 */
class PersonalInformationActivity : BaseSupportActivity<PersonalInformationPresenter>(),
    PersonalInformationContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPersonalInformationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .personalInformationModule(PersonalInformationModule(this))
            .build()
            .inject(this)
    }

    private var userBean: UserBean? = null
    private val mIsChangeAvatar = false

    private fun getAdapterData(): ArrayList<SettingDataBean> {
        userBean = sp().getObject<UserBean>(AppConstant.SP_USER)
        return arrayListOf(
            SettingDataBean(
                null,
                "${Api.APP_DOMAIN}${userBean?.userinfo?.avatar}",
                "头像",
                SettingDataBean.SETTING_TYPE_2,
                null
            ),
            SettingDataBean(
                null,
                null,
                "昵称",
                SettingDataBean.SETTING_TYPE_1,
                "${userBean?.userinfo?.nickname}"
            ),
            SettingDataBean(
                null,
                null,
                "性别",
                SettingDataBean.SETTING_TYPE_1,
                "${AppUtils.getSexByNum(userBean?.userinfo?.gender)}"
            ),
            SettingDataBean(
                null,
                null,
                "个人简介",
                SettingDataBean.SETTING_TYPE_1,
                "${userBean?.userinfo?.bio}"
            ),
            SettingDataBean(
                null,
                null,
                "所在地址",
                SettingDataBean.SETTING_TYPE_1,
                "${userBean?.userinfo?.address}"
            ),
            SettingDataBean(
                "保存",
                SettingDataBean.SETTING_TYPE_4
            )
        )
    }

    private val settingAdapter by lazy {
        SettingAdapter(getAdapterData()).apply {
            onItemClickListener =
                BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                    when (position) {
                        2 -> {//性别
                            showSexDialog(object : MenuDialog.OnListener<String> {
                                override fun onSelected(
                                    dialog: BaseDialog?,
                                    position: Int,
                                    t: String?
                                ) {
                                    mlog("选择图片 $t")
                                    // 更新性别
                                    t?.let {
                                        updateSubTextWithPosition(2, it)
                                        userBean?.userinfo?.gender = position
                                    }
                                }

                                override fun onCancel(dialog: BaseDialog?) {
                                    mlog("取消选择图片")
                                }

                            })
                        }
                        0 -> {// 选择头像
                            PhotoActivity.start(
                                mContext,
                                object : PhotoActivity.OnPhotoSelectListener {
                                    override fun onSelect(data: MutableList<String>?) {
                                        data?.get(0)?.let {
                                            setAvatar(1, it)
                                            mlog("选择图片 $it")
                                        }
                                    }

                                    override fun onCancel() {
                                        mlog("取消选择图片")
                                    }
                                })
                        }
                        1 -> {// 编辑昵称
                            ActivityUtils.goEditNicknameActivity()
                        }
                        3 -> {// 编辑个人简介
                            ActivityUtils.goEditProfileActivity()
                        }
                        4 -> {
                            ActivityUtils.goEditAddressActivity()
                        }
                        5 -> {// 保存按钮
                            // 提交修改类型
                            mPresenter?.updateProfileUser()
                        }
                    }
                }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_personal_information
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "个人资料"
        iv_back.setOnClickListener {
            mPresenter?.mAppManager?.onBack()
        }

        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = settingAdapter

        // 获取用户信息 主要用户图片地址 (src) 在服务器的绝对地址
        // 不需要在onResume()中调用 只需要加载一次即可
        mPresenter?.profileUser()
    }

    // 一些属性值需要跳转到其他的页面，这里获取些返回值
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mlog("requestCode $requestCode, resultCode $resultCode")
        val infoValue = data?.getStringExtra(AppConstant.INTENT_EDIT_VALUE)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                AppConstant.REQUEST_CODE_NICKNAME -> {// 昵称
                    infoValue?.let {
                        settingAdapter.updateSubTextWithPosition(1, it)
                        userBean?.userinfo?.nickname = it
                    }
                }
                AppConstant.REQUEST_CODE_PERSONAL_PROFILE -> {// 个人信息
                    infoValue?.let {
                        settingAdapter.updateSubTextWithPosition(3, it)
                        userBean?.userinfo?.bio = it
                    }
                }
                AppConstant.REQUEST_CODE_ADDRESS -> {// 地址信息
                    infoValue?.let {
                        settingAdapter.updateSubTextWithPosition(4, it)
                        userBean?.userinfo?.address = it
                    }
                }
            }
        }
    }

    // 第一个参数为从服务器获取的用户数据
    override fun getProfileUserBean(userinfoBean: UserBean.UserinfoBean?): UserBean.UserinfoBean? {
        if (isChangeAvatar()) {// 如果修改了用户头像，则传入图片绝对地址
            userinfoBean?.avatar = settingAdapter.data[0].path
        }// 如果没有修改则使用服务器获取到的用户图片地址 src
        userinfoBean?.nickname = settingAdapter.data[1].subText
        userinfoBean?.gender = settingAdapter.data[2].subText?.toAge() ?: 0 // 默认为男
        userinfoBean?.bio = settingAdapter.data[3].subText
        userinfoBean?.address = settingAdapter.data[4].subText
        mlog("UserInfo ${userinfoBean.toString()}")
        return userinfoBean
    }

    override fun isChangeAvatar(): Boolean {
        return mIsChangeAvatar
    }

}