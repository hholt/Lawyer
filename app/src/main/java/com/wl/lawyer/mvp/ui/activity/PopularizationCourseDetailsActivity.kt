package com.wl.lawyer.mvp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.di.component.AppComponent
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.wl.lawyer.R
import com.wl.lawyer.app.*
import com.wl.lawyer.app.base.BaseSupportActivity
import com.wl.lawyer.di.component.DaggerPopularizationCourseDetailsComponent
import com.wl.lawyer.di.module.PopularizationCourseDetailsModule
import com.wl.lawyer.mvp.contract.PopularizationCourseDetailsContract
import com.wl.lawyer.mvp.model.api.Api
import com.wl.lawyer.mvp.model.bean.LiveBean
import com.wl.lawyer.mvp.model.bean.PopularizationCourseReviewsBean
import com.wl.lawyer.mvp.presenter.PopularizationCourseDetailsPresenter
import com.wl.lawyer.mvp.ui.adapter.PopularizationCourseReviewsAdapter
import kotlinx.android.synthetic.main.activity_popularization_course_details.*
import kotlinx.android.synthetic.main.include.*
import kotlinx.android.synthetic.main.include_input.*

/**
 * 普法课程详情
 */
@Route(path=RouterPath.POPULARIZATION_COURSE_DETAIL)
class PopularizationCourseDetailsActivity :
    BaseSupportActivity<PopularizationCourseDetailsPresenter>(),
    PopularizationCourseDetailsContract.View {

    @Autowired(name=RouterArgs.LIVE)
    @JvmField
    var liveBean: LiveBean? = null

    @Autowired(name=RouterArgs.POPULARIZATION_TYPE)
    @JvmField
    var type: String? = AppConstant.KEY_POPULARIZATION

    //    private val url = "http://jessehuan.fun:38080/s/stKB5gjfzxjJXcn"
//    private val url = "http://jessehuan.fun:38080/s/stKB5gjfzxjJXcn/download"

    private val adapter by lazy {
        PopularizationCourseReviewsAdapter(
            arrayListOf(
                PopularizationCourseReviewsBean(
                    true,"评论"
                )
                /*
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在，专业到位，直指问题所在，专业到位，直指问题所在专业到位，直指问题所在专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                ),
                PopularizationCourseReviewsBean(
                    "http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg",
                    "coconut",
                    "2019.12.31  10:23",
                    "专业到位，直指问题所在。"
                )*/
            )
        ).apply {

        }
    }

   /* private val pinnedHeaderItemDecoration by lazy {
        PinnedHeaderItemDecoration.Builder(PopularizationCourseReviewsAdapter.TYPE_HEADER)
            .setDividerId(R.drawable.divider)
            .enableDivider(false)
            .disableHeaderClick(true)
            .create()
    }*/

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPopularizationCourseDetailsComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .popularizationCourseDetailsModule(PopularizationCourseDetailsModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_popularization_course_details
    }

    override fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)

        tv_title.text = when (type) {
            AppConstant.KEY_POPULARIZATION -> {
                "法律课程"
            }
            AppConstant.KEY_LIVE -> {
                "直播普法"
            }
            else -> {
                "法律课程"
            }
        }

        iv_back.setOnClickListener { mPresenter?.mAppManager?.onBack() }
        liveBean?.apply {
            tv_course_title.text = name
            tv_course_time.text = startTimeText
            tv_course_desc.text = desc
        }

        initVideoView()


        rv_item.layoutManager = LinearLayoutManager(mContext)
        rv_item.adapter = adapter
        // rv_item.addItemDecoration(pinnedHeaderItemDecoration)

        iv_input_avatar.circleImage("http://b-ssl.duitang.com/uploads/item/201901/17/20190117230425_eofqv.thumb.700_0.jpg")

    }

    private fun initVideoView() {
        liveBean?.apply {
//            video_player.setUp("rtmp://58.200.131.2:1935/livetv/hunantv", true, name)
            video_player.setUp(pushAddress, true, name)
            val imageView = AppCompatImageView(mContext)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.image(Api.APP_DOMAIN + image)
            video_player.thumbImageView = imageView
        }
        video_player.backButton.visibility = View.GONE
        video_player.fullscreenButton.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        video_player.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        video_player.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

}
