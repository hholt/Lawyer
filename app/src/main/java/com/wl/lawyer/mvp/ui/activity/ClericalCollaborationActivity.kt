package com.wl.lawyer.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.click
import com.vincent.filepicker.Constant
import com.vincent.filepicker.activity.BaseActivity
import com.vincent.filepicker.activity.NormalFilePickActivity
import com.vincent.filepicker.filter.entity.NormalFile
import com.wl.lawyer.R
import com.wl.lawyer.app.*
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.utils.RVUtils
import com.wl.lawyer.di.component.DaggerClericalCollaborationComponent
import com.wl.lawyer.di.module.ClericalCollaborationModule
import com.wl.lawyer.mvp.contract.ClericalCollaborationContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.*
import com.wl.lawyer.mvp.presenter.ClericalCollaborationPresenter
import com.wl.lawyer.mvp.ui.adapter.ClericalServiceAdapter
import kotlinx.android.synthetic.main.activity_clerical_collaboration.*
import kotlinx.android.synthetic.main.include.*
import java.util.*

/**
 * 文书协作
 */
@Route(path = RouterPath.SERVICE_COLLABORATION)
class ClericalCollaborationActivity : BaseSupportActivity<ClericalCollaborationPresenter>(),
    ClericalCollaborationContract.View {

    @Autowired(name = RouterArgs.LAWYER)
    @JvmField
    var lawyer: LawyerBean? = null

    var mSelectedFilePath: String = ""

    var mSelectPriceBean: SpecPriceBean? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerClericalCollaborationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .clericalCollaborationModule(ClericalCollaborationModule(this))
            .build()
            .inject(this)
    }

    private val serviceAdapter by lazy {
        ClericalServiceAdapter(arrayListOf()).apply {
            selectListener = { arr ->
                mlog(arr.toString())
                mPresenter?.getSpecPrice(arr)
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_clerical_collaboration
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)

        tv_title.text = "文书协作"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        initServiceAdapter()
        mPresenter?.getSpecList()


    }

    private fun initServiceAdapter() {
        rv_service.layoutManager = LinearLayoutManager(this)
        rv_service.adapter = serviceAdapter
        RVUtils.myDivider(mContext, rv_service)
    }

    override fun onSpecListGet(data: List<SpecBean>) {
        lawyer?.apply {
            iv_lawyer_avatar.circleImage(Api.APP_DOMAIN + avatar)
            et_lawyer_name.text = "${nickname}律师"
            btn_confirm.click {
                // 创建订单
                mSelectPriceBean?.apply {
                    mPresenter?.createClericalOrder(lawyerId, id)
                }
            }
            val price = serviceList.filter {
                it.id == AppConstant.SERVICE_ID_COLLABORATION
            }[0].price
            et_price.text = "￥$price/次"
        }
        serviceAdapter.setNewData(data)
        btn_select_file.click {
            val intent4 = Intent(
                this,
                NormalFilePickActivity::class.java
            )
            intent4.putExtra(Constant.MAX_NUMBER, 1)
            intent4.putExtra(BaseActivity.IS_NEED_FOLDER_LIST, true)
            intent4.putExtra(
                NormalFilePickActivity.SUFFIX,
                arrayOf("doc", "docX", "docx")
            )
            startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE)
        }
    }

    override fun onSpecPriceGet(priceBean: SpecPriceBean) {
        mSelectPriceBean = priceBean
        tv_proposed_price.text = "￥${priceBean.price}/起"
    }

    override fun onOrderCreate(orderBean: ClericalOrderBean) {
//        跳转确认页面
        ARouter.getInstance().build(RouterPath.ORDER_COMFIRM)
            .withSerializable(RouterArgs.LAWYER, lawyer)
            .withSerializable(RouterArgs.CLERICAL_ORDER, orderBean)
            .withInt(RouterArgs.SERVICE_TYPE, AppConstant.SERVICE_ID_COLLABORATION)
            .navigation()
    }

    override fun getMemo() = et_set_desc.text.toString()

    override fun getFilePath() = mSelectedFilePath

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_CODE_PICK_FILE) {
            val list: ArrayList<NormalFile> =
                data!!.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE)
            mlog("received ${list[0].path}")
            tv_selected_file.text = list[0].name
            mSelectedFilePath = list[0].path
        }
    }
}