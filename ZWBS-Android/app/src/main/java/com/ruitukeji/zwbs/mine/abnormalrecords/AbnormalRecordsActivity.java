package com.ruitukeji.zwbs.mine.abnormalrecords;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.RecommendedRecordViewAdapter;
import com.ruitukeji.zwbs.adapter.mine.abnormalrecords.AbnormalRecordsViewAdapter;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.entity.mine.abnormalrecords.AbnormalRecordsBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 异常记录
 * Created by Administrator on 2017/12/1.
 */

public class AbnormalRecordsActivity extends BaseActivity implements AbnormalRecordsContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private AbnormalRecordsViewAdapter mAdapter;

    @BindView(id = R.id.lv_abnormalRecords)
    private ListView lv_abnormalRecords;

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


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_abnormalrecords);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new AbnormalRecordsPresenter(this);
        mAdapter = new AbnormalRecordsViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.abnormalRecords), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_abnormalRecords.setAdapter(mAdapter);
        lv_abnormalRecords.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = 0;
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AbnormalRecordsContract.Presenter) mPresenter).getAbnormalRecords(mMorePageNumber);
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
        ((AbnormalRecordsContract.Presenter) mPresenter).getAbnormalRecords(mMorePageNumber);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, AbnormalSituationActivity.class);
        intent.putExtra("id", mAdapter.getItem(i).getId());
        showActivity(aty, intent);
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
    public void getSuccess(String s) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        AbnormalRecordsBean abnormalRecordsBean = (AbnormalRecordsBean) JsonUtil.getInstance().json2Obj(s, AbnormalRecordsBean.class);
        mMorePageNumber = abnormalRecordsBean.getResult().getPage();
        totalPageNumber = abnormalRecordsBean.getResult().getPageTotal();
        if (abnormalRecordsBean.getResult().getList() == null || abnormalRecordsBean.getResult().getList().size() == 0) {
            error(getString(R.string.serverReturnsDataNull));
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(abnormalRecordsBean.getResult().getList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(abnormalRecordsBean.getResult().getList());
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        toLigon(msg);
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
        }
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(AbnormalRecordsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }
}