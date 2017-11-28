package com.ruitukeji.zwbs.order;

import android.content.Intent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.dialog.ShareBouncedDialog;
import com.ruitukeji.zwbs.entity.EvaluationShareBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

/**
 * 评价分享
 * Created by Administrator on 2017/2/16.
 */

public class EvaluationShareActivity extends BaseActivity implements EvaluationShareContract.View {

    public ShareBouncedDialog shareBouncedDialog;
    private int order_id;

    @BindView(id = R.id.rb_serviceAttitude)
    private RatingBar rb_serviceAttitude;

    @BindView(id = R.id.rb_satisfaction)
    private RatingBar rb_satisfaction;


    @BindView(id = R.id.rb_deliveryTime)
    private RatingBar rb_deliveryTime;

    @BindView(id = R.id.tv_note)
    private TextView tv_note;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_evaluationshare);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new EvaluationSharePresenter(this);
        order_id = getIntent().getIntExtra("order_id", 0);
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        ((EvaluationShareContract.Presenter) mPresenter).getEvaluationShare(order_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
//        SimpleDelegate simpleDelegate = new SimpleDelegate() {
//
//
//            @Override
//            public void onClickLeftCtv() {
//                super.onClickLeftCtv();
//                aty.finish();
//            }
//
//            @Override
//            public void onClickRightCtv() {
//                super.onClickRightCtv();
//                // showActivity(aty, RecommendedRecordActivity.class);
//                if (shareBouncedDialog == null) {
//                    shareBouncedDialog = new ShareBouncedDialog(getApplicationContext()) {
//                        @Override
//                        public void share(SHARE_MEDIA platform) {
//                            umShare(platform);
//                            shareBouncedDialog = null;
//                        }
//                    };
//                }
//                shareBouncedDialog.show();
//                shareBouncedDialog.setCanceledOnTouchOutside(false);
//            }
//        };
//        ActivityTitleUtils.initToolbar(aty, getString(R.string.evaluation), getString(R.string.share), R.id.titlebar, simpleDelegate);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.evaluation), true, R.id.titlebar);
    }

    /**
     * 直接分享
     * SHARE_MEDIA.QQ
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb = new UMImage(this, R.drawable.ic_launcher);
        UMWeb web = new UMWeb("");
        web.setTitle("This is music title");//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription("my description");//描述
        new ShareAction(aty).setPlatform(platform)
//                .withText("hello")
//                .withMedia(thumb)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();

//        new ShareAction(aty).withText("hello")
//                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
//                .setCallback(umShareListener).open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            ViewInject.toast(getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ViewInject.toast(getString(R.string.shareError));
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ViewInject.toast(getString(R.string.shareError));
        }
    };


    @Override
    public void getSuccess(String s) {
        EvaluationShareBean evaluationShareBean = (EvaluationShareBean) JsonUtil.getInstance().json2Obj(s, EvaluationShareBean.class);
        rb_deliveryTime.setRating(evaluationShareBean.getResult().getLimit_ship());
        rb_serviceAttitude.setRating(evaluationShareBean.getResult().getAttitude());
        rb_satisfaction.setRating(evaluationShareBean.getResult().getSatisfaction());
        if (StringUtils.isEmpty(evaluationShareBean.getResult().getContent()) || evaluationShareBean.getResult().getStatus() == 1) {
            tv_note.setVisibility(View.GONE);
        } else {
            tv_note.setText(evaluationShareBean.getResult().getContent());
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(EvaluationShareContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shareBouncedDialog = null;
    }
}
