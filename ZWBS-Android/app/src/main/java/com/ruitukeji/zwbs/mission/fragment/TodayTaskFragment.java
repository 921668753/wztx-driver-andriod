package com.ruitukeji.zwbs.mission.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.mission.TaskViewAdapter;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.dialog.NavigationBouncedDialog;
import com.ruitukeji.zwbs.entity.OrderBean;
import com.ruitukeji.zwbs.getorder.OrderDetailsActivity;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.main.MainActivity;
import com.ruitukeji.zwbs.mission.dialog.CalendarBouncedDialog;
import com.ruitukeji.zwbs.utils.DataUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 今日任务
 * Created by Administrator on 2017/12/5.
 */

public class TodayTaskFragment extends BaseFragment implements TaskContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {

    private MainActivity aty;

    @BindView(id = R.id.tv_today, click = true)
    private TextView tv_today;

    @BindView(id = R.id.img_arrowLeft, click = true)
    private ImageView img_arrowLeft;

    @BindView(id = R.id.tv_data)
    private TextView tv_data;

    @BindView(id = R.id.img_arrowRight, click = true)
    private ImageView img_arrowRight;

    @BindView(id = R.id.img_calendar, click = true)
    private TextView img_calendar;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private TaskViewAdapter mAdapter;

    @BindView(id = R.id.lv_mission)
    private ListView lv_mission;

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

    /**
     * 订单状态
     */
    private int type = 0;
    private long dataLong = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_task, null);
    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter = new TaskPresenter(this);
        mAdapter = new TaskViewAdapter(getActivity());
        dataLong = System.currentTimeMillis() / 1000;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_mission.setAdapter(mAdapter);
        lv_mission.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        String todayStr = DataUtil.formatData(dataLong, "yyyy-MM-dd");
        tv_data.setText(todayStr);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, OrderDetailsActivity.class);
        intent.putExtra("order_id", mAdapter.getItem(i).getOrder_id());
        aty.showActivity(aty, intent);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((TaskContract.Presenter) mPresenter).getTask(mMorePageNumber, dataLong, type);
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
        ((TaskContract.Presenter) mPresenter).getTask(mMorePageNumber, dataLong, type);
        return true;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_today:
                dataLong = System.currentTimeMillis() / 1000;
                String todayStr = DataUtil.formatData(dataLong, "yyyy-MM-dd");
                tv_data.setText(todayStr);
                break;
            case R.id.img_arrowLeft:
                dataLong = dataLong - 24 * 60 * 60;
                String arrowLeft = DataUtil.formatData(dataLong, "yyyy-MM-dd");
                tv_data.setText(arrowLeft);
                break;
            case R.id.img_arrowRight:
                dataLong = dataLong + 24 * 60 * 60;
                String arrowRight = DataUtil.formatData(dataLong, "yyyy-MM-dd");
                tv_data.setText(arrowRight);
                break;
            case R.id.img_calendar:
                CalendarBouncedDialog calendarBouncedDialog = new CalendarBouncedDialog(aty, dataLong) {
                    @Override
                    public void confirm(String dataStr, long data) {
                        this.dismiss();
                        tv_data.setText(dataStr);
                        dataLong = data;
                    }
                };
                calendarBouncedDialog.show();
                break;
            case R.id.tv_hintText:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    aty.showActivity(aty, LoginActivity.class);
                    break;
                }
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        OrderBean orderBean = (OrderBean) JsonUtil.getInstance().json2Obj(s, OrderBean.class);
        mMorePageNumber = orderBean.getResult().getPage();
        totalPageNumber = orderBean.getResult().getPageTotal();
        if (orderBean.getResult().getList() == null || orderBean.getResult().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mAdapter.clear();
            mAdapter.addNewData(orderBean.getResult().getList());
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(orderBean.getResult().getList());
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            tv_hintText.setText(getString(R.string.login1));
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
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
    public void setPresenter(TaskContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View view, int i) {
        if (!mAdapter.getItem(i).getStatus().equals("distribute")) {
            Intent intent = new Intent(aty, OrderDetailsActivity.class);
            intent.putExtra("order_id", mAdapter.getItem(i).getOrder_id());
            aty.showActivity(aty, intent);
            return;
        }
        if (view.getId() == R.id.rl_navigation) {
            NavigationBouncedDialog navigationBouncedDialog = new NavigationBouncedDialog(aty, mAdapter.getItem(i).getDest_address_name());
            navigationBouncedDialog.show();
        }
    }

}
