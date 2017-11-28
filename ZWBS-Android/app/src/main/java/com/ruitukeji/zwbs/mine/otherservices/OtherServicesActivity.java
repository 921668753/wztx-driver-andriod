package com.ruitukeji.zwbs.mine.otherservices;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.bean.ImageItem;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.BannerViewAdapter;
import com.ruitukeji.zwbs.adapter.ImageViewAdapter;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.entity.BannerBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.ArrayList;


/**
 * 其他服务
 * Created by Administrator on 2017/2/10.
 */

public class OtherServicesActivity extends BaseActivity implements OtherServicesContract.View, AdapterView.OnItemClickListener {

    private BannerViewAdapter mAdapter;

    @BindView(id = R.id.lv_Banner)
    private ListView lv_Banner;


    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_otherservices);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new OtherServicesPresenter(this);
        mAdapter = new BannerViewAdapter(this);
    }

    @Override

    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.otherServices), true, R.id.titlebar);
        lv_Banner.setAdapter(mAdapter);
        lv_Banner.setOnItemClickListener(this);
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        ((OtherServicesContract.Presenter) mPresenter).getOtherServices();
    }

    @Override
    public void setPresenter(OtherServicesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (StringUtils.isEmpty(mAdapter.getItem(position).getUrl())) {
            return;
        }
        Intent bannerDetails = new Intent(this, BannerDetailsActivity.class);
//        bannerDetails.putExtra("title", mAdapter.getItem(position).getTitle());
        bannerDetails.putExtra("url", mAdapter.getItem(position).getUrl());
        showActivity(aty, bannerDetails);
    }


    @Override
    public void getSuccess(String s) {
        ll_commonError.setVisibility(View.GONE);
        lv_Banner.setVisibility(View.VISIBLE);
        BannerBean bannerBean = (BannerBean) JsonUtil.json2Obj(s, BannerBean.class);
        if (bannerBean.getResult().getList() == null || bannerBean.getResult().getList().size() == 0) {
            error(getString(R.string.serverReturnsDataNull));
            return;
        }
        mAdapter.clear();
        mAdapter.addMoreData(bannerBean.getResult().getList());
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        lv_Banner.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        dismissLoadingDialog();
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_hintText:
                showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
                ((OtherServicesContract.Presenter) mPresenter).getOtherServices();
                break;
        }
    }
}
