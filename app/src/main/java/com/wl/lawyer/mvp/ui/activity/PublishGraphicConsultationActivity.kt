package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.lxj.androidktx.core.click
import com.wl.common.activity.PhotoActivity
import com.wl.lawyer.R
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
//        GraA()
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
                            object : PhotoActivity.OnPhotoSelectListener {
                                override fun onSelect(data: MutableList<String>?) {
                                    data?.get(0)?.let {
                                        mlog("选择图片 $it")
                                    }
                                }

                                override fun onCancel() {
                                    mlog("取消选择图片")
                                }
                            })
                    }
                }
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
        mPresenter?.createConsult(
            "这是标题",
            "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容",
            "/uploads/20200507/38ffd676fc8804ff7cf6daa7b83f4ab3.png",
            selectSet!!.id
        )
    }

    override fun onPublishResult(result: PublishResultBean) {

    }

}
