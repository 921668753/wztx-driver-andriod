package com.ruitukeji.zwbs.getorder.message;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.MessageCenterViewAdapter;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.MessageCenterBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 消息中心
 * Created by Admin on 2017/7/28.
 */
public class MessageCenterActivity extends BaseActivity implements MessageCenterContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;


    @BindView(id = R.id.lv_messagecenter)
    private ListView lv_messagecenter;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    private MessageCenterViewAdapter messageCenterViewAdapter;
    private int position1 = -1;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_messagecenter);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MessageCenterPresenter(this);
        messageCenterViewAdapter = new MessageCenterViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.message), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        lv_messagecenter.setAdapter(messageCenterViewAdapter);
        lv_messagecenter.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MessageCenterContract.Presenter) mPresenter).getUnRead();
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }


    @Override
    public void setPresenter(MessageCenterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void getSuccess(String s, int flag) {
        PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshMessageCenterActivity", false);
        if (flag == 0) {
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MessageCenterBean messageCenterBean = (MessageCenterBean) JsonUtil.getInstance().json2Obj(s, MessageCenterBean.class);
            messageCenterViewAdapter.clear();
            messageCenterViewAdapter.addNewData(messageCenterBean.getResult().getList());
        } else {
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra("title", messageCenterViewAdapter.getItem(position1).getName());
            intent.putExtra("push_type", messageCenterViewAdapter.getItem(position1).getPush_type());
            showActivity(this, intent);
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg, int flag) {
        if (flag == 0) {
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setText(msg + getString(R.string.clickRefresh));
        } else {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                showActivity(aty, LoginActivity.class);
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position1 = position;
        if (messageCenterViewAdapter.getItem(position).getPush_type().equals("private")) {
            ((MessageCenterContract.Presenter) mPresenter).isLogin(1);
        } else {
            getSuccess("", 1);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MessageCenterContract.Presenter) mPresenter).getUnRead();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isRefreshMessageCenterActivity = PreferenceHelper.readBoolean(this, StringConstants.FILENAME, "isRefreshMessageCenterActivity", false);
        if (isRefreshMessageCenterActivity) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

}
