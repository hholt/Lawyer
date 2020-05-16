package com.wl.lawyer.mvp.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.jess.arms.di.component.AppComponent
import com.tencent.qcloud.tim.uikit.utils.DateTimeUtil
import com.wl.lawyer.R
import com.wl.lawyer.app.*
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerPopularizationArticleDetailComponent
import com.wl.lawyer.di.module.PopularizationArticleDetailModule
import com.wl.lawyer.mvp.contract.PopularizationArticleDetailContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.ArticleDetailBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleBean
import com.wl.lawyer.mvp.model.bean.LawyerArticleDetailBean
import com.wl.lawyer.mvp.presenter.ArticleDetailPresenter
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.include.*
import kotlin.time.days

@Route(path = RouterPath.LAWYER_ARTICLE)
class ArticleDetailActivity: BaseSupportActivity<ArticleDetailPresenter>(),
    PopularizationArticleDetailContract.View  {

    @Autowired(name = RouterArgs.ARTICLE)
    @JvmField
    var article: LawyerArticleBean? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPopularizationArticleDetailComponent
            .builder()
            .appComponent(appComponent)
            .popularizationArticleDetailModule(PopularizationArticleDetailModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_article_detail
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)

        tv_title.text = "律师详情"
        tv_title.setTextColor(Color.WHITE)
        iv_right.visibility = View.VISIBLE
        dividing_line.visibility = View.GONE
        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }

        article?.apply {
            mlog("load article")
            tv_article_title.text = title
            mPresenter?.getArticleDetail(id)
        }

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this)
            .transparentStatusBar()
            .statusBarDarkFont(false)
            .titleBar(tab_bar)
            .init()
    }

    override fun initArticle(articleBean: ArticleDetailBean) {
        articleBean.apply {
            iv_header.image(Api.APP_DOMAIN + image)
            tv_article_title.text = title
            tv_author.text = "作者：${author}"
//            createTime.toTime("yyyy.MM.dd")
            tv_date.text = "时间：${createTime.toTime("yyyy.MM.dd")}"
            tv_count.text = "浏览次数：100${readCount}"
//            tv_content.text = HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_COMPACT)
            webview_article.loadDataWithBaseURL(
                Api.APP_DOMAIN,
                HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
                "text/html",
                "UTF-8",
                null)
        }
    }

}