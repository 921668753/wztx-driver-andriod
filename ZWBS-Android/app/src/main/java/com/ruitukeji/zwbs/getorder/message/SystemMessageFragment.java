package com.ruitukeji.zwbs.getorder.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.MessageViewAdapter;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.MessageBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.DialogUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 系统消息
 * Created by Administrator on 2017/11/28.
 */

public class SystemMessageFragment extends BaseFragment implements MessageContract.View, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MessageActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private MessageViewAdapter mAdapter;

    @BindView(id = R.id.lv_message)
    private ListView lv_message;


    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;
    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;
    private String push_type = null;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MessageActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_systemmessage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MessagePresenter(this);
        mAdapter = new MessageViewAdapter(getActivity());
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        String title = aty.getIntent().getStringExtra("title");
        push_type = aty.getIntent().getStringExtra("push_type");
        if (StringUtils.isEmpty(push_type)) {
            aty.finish();
            return;
        }
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_message.setAdapter(mAdapter);
        lv_message.setOnItemClickListener(this);
        lv_message.setOnItemLongClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MessageContract.Presenter) mPresenter).getMessage(push_type, mMorePageNumber);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("messageId", mAdapter.getItem(position).getId());
        intent.setClass(aty, MessageDetailsActivity.class);
        aty.showActivity(aty, intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        DialogUtil.showAlertDialog(aty, R.string.deleteMessage, new DialogUtil.OnDialogSelectListener() {
            @Override
            public void onDialogSelect() {
                ((MessageContract.Presenter) mPresenter).postDeleteMessage(mAdapter.getItem(position).getId());
            }
        });
        return true;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MessageContract.Presenter) mPresenter).getMessage(push_type, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((MessageContract.Presenter) mPresenter).getMessage(push_type, mMorePageNumber);
        return true;
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
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageActivity", false);
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MessageBean messageBean = (MessageBean) JsonUtil.getInstance().json2Obj(s, MessageBean.class);
            mMorePageNumber = messageBean.getResult().getPage();
            totalPageNumber = messageBean.getResult().getPageTotal();
            if (messageBean.getResult().getList() == null || messageBean.getResult().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(messageBean.getResult().getList());
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(messageBean.getResult().getList());
                mRefreshLayout.endLoadingMore();
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {

        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        if (flag == 0) {
            isShowLoadingMore = false;
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setText(msg + getString(R.string.clickRefresh));
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
        } else if (flag == 1) {
            ViewInject.toast(msg);
        }
        dismissLoadingDialog();
    }


    @Override
    public void onResume() {
        super.onResume();
        boolean isRefreshMessageActivity = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshMessageActivity", false);
        if (isRefreshMessageActivity) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMessageCenterActivity", true);
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {
        mPresenter = presenter;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }


}
