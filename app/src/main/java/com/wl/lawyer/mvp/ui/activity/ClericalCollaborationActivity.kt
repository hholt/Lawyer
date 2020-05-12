package com.wl.lawyer.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
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
import com.wl.lawyer.mvp.ui.adapter.CommonAdapter
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

    var selectedFilePath: String = ""

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerClericalCollaborationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .clericalCollaborationModule(ClericalCollaborationModule(this))
            .build()
            .inject(this)
    }

    private val desc = "1、支付定金后，系统将g即将你的订单派发至指定律，优推荐，如平小时丙无律师接革，将会自动追款至您的余额中。\n\n" +
            "2、县体价格造与律师商议，参考价格仅为建议，实际价格请以律师报价为准。\n\n" +
            "3、如订单达成，定金在支付订单费用时自动抵扣，订单未达成，则定金作为前期服务费，不予退款。\n\n" +
            "4、律师有权不回答与代写文书无关的法律问题。\n\n" +
            "5、文书付款半小时内申请退款的，可退款，超过半小时的，不子追款.\n\n" +
            "6、文书出现三次或以上明显法律错误的，可退款。\n\n" +
            "7、文书首次交付延时的，可退款：若因为用户未能即时交付。"

    private val adapter by lazy {
        CommonAdapter(
            arrayListOf(
                CommonBean(
                    CommonBean.TYPE_SIMPLE_CIRCLE_IMAGE,
                    "目前你选择的是：",
                    "古润玉律师",
                    "http://jessehuan.fun:38080/apps/files_sharing/publicpreview/a6CPDjJDYTLTaw8?x=2880&y=732&a=true&file=img_video.png&scalingup=0"
                ),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_DESC,
                    "我们针对不同的情况，定制了不同的咨询套餐，提供了不同的咨询方式和收费标准，请按照您的需要进行选择。"
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "舒服类型：", "文书处理"),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "文书类型：", "文书"),
                CommonBean(CommonBean.TYPE_SIMPLE_DOWN_DROP, "协助方式：", "审查"),
                CommonBean(CommonBean.TYPE_SIMPLE, "参考价格：", "¥299/次"),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT,
                    "备注：",
                    "您可以在输入框简单描述文书服务需求",
                    true,
                    80
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_COLOR, "价格：", "¥30/次", Color.RED),
                CommonBean(
                    CommonBean.TYPE_SIMPLE_DESC,
                    "为更方便快捷的让律师浏览审查文书，请上传文书源文件，目前仅支持doc、docx两种形式。"
                ),
                CommonBean(CommonBean.TYPE_SIMPLE_FILE, "选择文件", "未选择任何文件"),
                CommonBean(CommonBean.TYPE_SIMPLE_MULTIPLE_TEXT, "说明：", desc, false, 350),
                CommonBean(CommonBean.TYPE_SIMPLE_BUTTON, "支付下单")
            )
        ).apply {
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    5 -> {// 支付费用
                        mPresenter?.mAppManager?.startActivity(PayActivity::class.java)
                    }
                    11 -> {//
                        mPresenter?.mAppManager?.startActivity(OrderStatusActivity::class.java)
                    }
                }
            }
        }
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

//        rv_item.layoutManager = LinearLayoutManager(mContext)
//        rv_item.adapter = adapter

//        RVUtils.myDivider(mContext, rv_item)
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
            et_lawyer_name.hint = "${nickname}律师"
            btn_confirm.click {
//                mPresenter?.createClericalOrder()
                /*ARouter.getInstance().build(RouterPath.ORDER_COMFIRM)
                    .withSerializable(RouterArgs.LAWYER, lawyer)
                    .withSerializable(RouterArgs.SERVICE_TYPE, AppConstant.SERVICE_ID_COLLABORATION)
                    .withSerializable(RouterArgs.SERVICE_SET, selectSet)
                    .navigation()*/
            }
            /*psv_set.text = typeList[0].name*/
            et_price.text = "￥${serviceList.filter {
                it.id == AppConstant.SERVICE_ID_COLLABORATION
            }[0].price}/次"
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

    override fun getFilePath() = selectedFilePath

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_CODE_PICK_FILE) {
            val list: ArrayList<NormalFile> =
                data!!.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE)
            mlog("received ${list[0].path}")
            tv_selected_file.text = list[0].name
            selectedFilePath = list[0].path
        }
    }
}