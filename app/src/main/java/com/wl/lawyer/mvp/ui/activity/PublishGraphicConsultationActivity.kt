package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.click
import com.wl.common.activity.PhotoActivity
import com.wl.lawyer.R
import com.wl.lawyer.app.AppConstant
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.mlog
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerPublishGraphicConsultationComponent
import com.wl.lawyer.di.module.PublishGraphicConsultationModule
import com.wl.lawyer.mvp.contract.PublishGraphicConsultationContract
import com.wl.lawyer.mvp.model.bean.GraphicAddBean
import com.wl.lawyer.mvp.model.bean.PtcCategoryBean
import com.wl.lawyer.mvp.model.bean.PublishResultBean
import com.wl.lawyer.mvp.presenter.PublishGraphicConsultationPresenter
import com.wl.lawyer.mvp.ui.adapter.CategorySpinnerAdapter
import com.wl.lawyer.mvp.ui.adapter.GraphicAdapter
import com.wl.lawyer.mvp.ui.adapter.GraphicAddAdapter
import kotlinx.android.synthetic.main.activity_publish_graphic_consultation.*
import kotlinx.android.synthetic.main.include.*
import kotlinx.android.synthetic.main.include.tv_title

/**
 * 发布图文咨询
 */
@Route(path = RouterPath.PUBLISH_GRAPHIC_CONSULE)
class PublishGraphicConsultationActivity :
    BaseSupportActivity<PublishGraphicConsultationPresenter>(),
    PublishGraphicConsultationContract.View {

    var selectSet: PtcCategoryBean? = null


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPublishGraphicConsultationComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .publishGraphicConsultationModule(PublishGraphicConsultationModule(this))
            .build()
            .inject(this)
    }

    private val adapter by lazy {
        GraphicAddAdapter(
            arrayListOf(
                GraphicAddBean(GraphicAddBean.TYPE_ADD)
            )
        ).apply {
            setOnItemClickListener{_, _, position ->
                when (getItem(position)?.type) {
                    GraphicAddBean.TYPE_ADD -> {
                        PhotoActivity.start(
                            mContext,
                            AppConstant.MAX_GRAPHIC_SIZE - data.size + 1,//可选数量
                            object : PhotoActivity.OnPhotoSelectListener {
                                override fun onSelect(data: MutableList<String>?) {
                                    var adapterData = getData()
                                    data?.let {
                                        it.forEach{
                                            adapterData.add(adapterData.size - 1, GraphicAddBean(filePath = it))
                                        }
                                        if (adapterData.size > AppConstant.MAX_GRAPHIC_SIZE) {
                                            adapterData.removeAt(adapterData.size - 1)
                                        }
                                        notifyDataSetChanged()
                                    }

                                }

                                override fun onCancel() {
                                    mlog("取消选择图片")
                                }
                            })
                    }
                }
            }

            setOnItemChildClickListener { adapter, view, position ->
                if (view.id == R.id.iv_clear) {
                    remove(position)
                }
                addItemToLast(GraphicAddBean(type = GraphicAddBean.TYPE_ADD))
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_publish_graphic_consultation
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_title.text = "发布图文咨询"
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        psv_set.lifecycleOwner = this
        psv_set.setOnSpinnerOutsideTouchListener { _, _ ->
            psv_set.dismiss()
        }

        mPresenter?.getCatagories()

        btn_common.click {
            checkBeforeSubmit()
            submit()
        }

        initGraphic()
    }

    fun initGraphic() {
        rv_graphic_add.layoutManager = GridLayoutManager(mContext, 4)
        rv_graphic_add.adapter = adapter
    }


    override fun initCategories(categoryBeans: List<PtcCategoryBean>) {
        selectSet = categoryBeans[0]
        psv_set.text = categoryBeans[0].name
        psv_set.apply {
            text = categoryBeans[0].name
            setSpinnerAdapter(
                CategorySpinnerAdapter(
                    psv_set,
                    categoryBeans
                ).apply {
                    setOnItemClickListener { adpter, view, position ->
                        this.notifyItemSelected(position)
                    }
                }
            )
            setOnSpinnerItemSelectedListener<PtcCategoryBean> { position, item ->
                selectSet = item
            }
        }
    }

    fun checkBeforeSubmit() {

    }
    fun submit() {
        mPresenter?.createConsult(selectSet!!.id)
    }

    override fun getTitleText() = et_consulation_title.text.toString()

    override fun getContent() = et_consulation_detail.text.toString()

    override fun getImages(): List<String> {
        val list = arrayListOf<String>()
        adapter.data.forEach{
            it.takeIf { it.type == GraphicAddBean.TYPE_PIC }?.apply {
                list.add(filePath)
            }
        }
        mlog(list.toString())
        return list
    }



    override fun onPublishResult(result: PublishResultBean) {

    }

}
