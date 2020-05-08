package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.di.component.AppComponent
import com.wl.lawyer.R
import com.wl.lawyer.app.RouterArgs
import com.wl.lawyer.app.RouterPath
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.app.onBack
import com.wl.lawyer.di.component.DaggerGCDetailsComponent
import com.wl.lawyer.di.module.GCDetailsModule
import com.wl.lawyer.mvp.contract.GCDetailsContract
import com.wl.lawyer.mvp.model.bean.CommentAdapterBean
import com.wl.lawyer.mvp.model.bean.CommentRefreshBean
import com.wl.lawyer.mvp.model.bean.ConsulationCommentBean
import com.wl.lawyer.mvp.model.bean.GraphicConsultationBean
import com.wl.lawyer.mvp.presenter.GCDetailsPresenter
import com.wl.lawyer.mvp.ui.adapter.CommentAdapter
import com.wl.lawyer.mvp.ui.callback.CommentQuickDiff
import kotlinx.android.synthetic.main.activity_gcdetails.*
import kotlinx.android.synthetic.main.adapter_recommended_lawyer_header.*
import kotlinx.android.synthetic.main.include.*

/**
 * 图文咨询详情 Graphic Consultation Details
 */
@Route(path = RouterPath.GRAPHIC_CONSULE_DETAIL)
class GCDetailsActivity : BaseSupportActivity<GCDetailsPresenter>(), GCDetailsContract.View {

    @Autowired(name = RouterArgs.GRAPHIC_CONSULATION)
    @JvmField
    var consulation: GraphicConsultationBean? = null

    private val adapter by lazy {
        CommentAdapter(
            arrayListOf(
            )
        ).apply {

        }
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerGCDetailsComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .gCDetailsModule(GCDetailsModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_gcdetails
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        tv_title.text = "图文咨询详情"
        tv_recommended.text = "回复区"
        tv_more.visibility = View.GONE
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        rv_comment.layoutManager = LinearLayoutManager(mContext)
        rv_comment.adapter = adapter
        rv_comment.isNestedScrollingEnabled = false
        rv_comment.isFocusable = false

        consulation?.let {
            mPresenter?.getDetail(it.id)
        }
    }

    override fun onDetailGet(consultationBean: GraphicConsultationBean) {
        var data = arrayListOf<CommentAdapterBean>()
        tv_gc_title.text = consultationBean.title
        tv_gc_desc.text = consultationBean.content
        consultationBean.apply {
            comments.takeIf { comments.isNotEmpty() }?.forEach {
                data.add(CommentAdapterBean(CommentAdapterBean.TYPE_LAWYER, it))
                if (it.children.isNotEmpty()) {
                    it.children.forEach {
                        data.add(CommentAdapterBean(CommentAdapterBean.TYPE_REPLY, it))
                    }
                }
            }
        }
        adapter.setNewDiffData(CommentQuickDiff(data))
    }

    override fun onCommentsGet(commentRefreshBean: CommentRefreshBean) {
        var data = arrayListOf<CommentAdapterBean>()
        commentRefreshBean.apply {
            comments.takeIf { comments.isNotEmpty() }?.forEach {
                data.add(CommentAdapterBean(CommentAdapterBean.TYPE_LAWYER, it))
                if (it.children.isNotEmpty()) {
                    it.children.forEach {
                        data.add(CommentAdapterBean(CommentAdapterBean.TYPE_REPLY, it))
                    }
                }
            }

        }
        adapter.setNewDiffData(CommentQuickDiff(data))
    }

}