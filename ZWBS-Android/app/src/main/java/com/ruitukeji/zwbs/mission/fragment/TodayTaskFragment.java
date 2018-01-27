package com.ruitukeji.zwbs.mission.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.mission.TaskViewAdapter;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.dialog.NavigationBouncedDialog;
import com.ruitukeji.zwbs.entity.mission.TaskBean;
import com.ruitukeji.zwbs.getorder.OrderDetailsActivity;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.main.MainActivity;
import com.ruitukeji.zwbs.mine.dialog.CustomerServiceTelephoneBouncedDialog;
import com.ruitukeji.zwbs.mission.ExceptionReportingActivity;
import com.ruitukeji.zwbs.mission.UploadReceiptVoucherActivity;
import com.ruitukeji.zwbs.mission.dialog.CalendarBouncedDialog;
import com.ruitukeji.zwbs.mission.dialog.CancelOrderBouncedDialog;
import com.ruitukeji.zwbs.utils.DataUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_SELECT;

/**
 * 今日任务
 * Created by Administrator on 2017/12/5.
 */

public class TodayTaskFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, TaskContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {

    private MainActivity aty;

    private final int REQUEST_CODE_PERMISSION_CALL = 1;

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
    private int position = 0;
    private CalendarBouncedDialog calendarBouncedDialog = null;
    private CustomerServiceTelephoneBouncedDialog customerServiceTelephoneBouncedDialog = null;
    private NavigationBouncedDialog navigationBouncedDialog = null;
    private CancelOrderBouncedDialog cancelOrderBouncedDialog = null;

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
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.img_arrowLeft:
                dataLong = dataLong - 24 * 60 * 60;
                String arrowLeft = DataUtil.formatData(dataLong, "yyyy-MM-dd");
                tv_data.setText(arrowLeft);
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.img_arrowRight:
                dataLong = dataLong + 24 * 60 * 60;
                String arrowRight = DataUtil.formatData(dataLong, "yyyy-MM-dd");
                tv_data.setText(arrowRight);
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.img_calendar:
                if (calendarBouncedDialog != null && !calendarBouncedDialog.isShowing()) {
                    calendarBouncedDialog.show();
                    calendarBouncedDialog.setSingleDate(dataLong);
                    return;
                }
                calendarBouncedDialog = new CalendarBouncedDialog(aty, dataLong) {
                    @Override
                    public void confirm(String dataStr, long data) {
                        this.cancel();
                        tv_data.setText(dataStr);
                        dataLong = data;
                        mRefreshLayout.beginRefreshing();
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
        if (flag == 0) {
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            TaskBean taskBean = (TaskBean) JsonUtil.getInstance().json2Obj(s, TaskBean.class);
            mMorePageNumber = taskBean.getResult().getPage();
            totalPageNumber = taskBean.getResult().getPageTotal();
            if (taskBean.getResult().getList() == null || taskBean.getResult().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mAdapter.clear();
                mAdapter.addNewData(taskBean.getResult().getList());
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(taskBean.getResult().getList());
            }
        } else if (flag == 1) {
            Intent intent = new Intent(aty, OrderDetailsActivity.class);
            intent.putExtra("order_id", mAdapter.getItem(position).getId());
            aty.showActivity(aty, intent);
        } else if (flag == 2) {
            String address_name = "";
            String location = "";
            if (StringUtils.isEmpty(mAdapter.getItem(position).getArr_org_time_str())) {
                address_name = mAdapter.getItem(position).getOrg_address_name();
                location = mAdapter.getItem(position).getOrg_address_maps();
            } else if (!StringUtils.isEmpty(mAdapter.getItem(position).getStatus()) && mAdapter.getItem(position).getStatus().equals("distribute")
                    || !StringUtils.isEmpty(mAdapter.getItem(position).getStatus()) && mAdapter.getItem(position).getStatus().equals("arrive")) {
                address_name = mAdapter.getItem(position).getDest_address_name();
                location = mAdapter.getItem(position).getDest_address_maps();
            }
            if (navigationBouncedDialog == null) {
                navigationBouncedDialog = new NavigationBouncedDialog(aty, address_name, location);
            } else {
                navigationBouncedDialog.setDestination(address_name, location);
            }
            navigationBouncedDialog.show();
        } else if (flag == 3) {
            choiceCallWrapper(mAdapter.getItem(position).getPhone());
        } else if (flag == 4) {
            Intent intent = new Intent(aty, UploadReceiptVoucherActivity.class);
            intent.putExtra("order_id", mAdapter.getItem(position).getId());
            intent.putExtra("is_cargo_receipt", mAdapter.getItem(position).getIs_cargo_receipt());
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else if (flag == 5) {
            Intent intent = new Intent(aty, ExceptionReportingActivity.class);
            intent.putExtra("order_id", mAdapter.getItem(position).getId());
            aty.showActivity(aty, intent);
        } else if (flag == 6) {
            if (cancelOrderBouncedDialog == null) {
                cancelOrderBouncedDialog = new CancelOrderBouncedDialog(aty, mAdapter.getItem(position).getId()) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        TodayTaskFragment.this.getSuccess("", 7);
                    }
                };
            } else {
                cancelOrderBouncedDialog.setOrderId(mAdapter.getItem(position).getId());
            }
            cancelOrderBouncedDialog.show();
        } else if (flag == 7) {
            mRefreshLayout.beginRefreshing();
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN) && flag == 0) {
            dismissLoadingDialog();
            tv_hintText.setText(getString(R.string.login1));
            aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (flag == 0) {
            isShowLoadingMore = false;
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setText(msg + getString(R.string.clickRefresh));
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
        } else {
            if (!toLigon1(msg)) {
                return;
            }
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
        if (customerServiceTelephoneBouncedDialog != null) {
            customerServiceTelephoneBouncedDialog.cancel();
        }
        customerServiceTelephoneBouncedDialog = null;
        if (navigationBouncedDialog != null) {
            navigationBouncedDialog.cancel();
        }
        navigationBouncedDialog = null;
        if (calendarBouncedDialog != null) {
            calendarBouncedDialog.cancel();
        }
        if (cancelOrderBouncedDialog != null) {
            cancelOrderBouncedDialog.cancel();
        }
        cancelOrderBouncedDialog = null;
        calendarBouncedDialog = null;
        mAdapter = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
        ((TaskContract.Presenter) mPresenter).isLogin(1);
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View view, int i) {
        position = i;
        if (view.getId() == R.id.img_navigation) {
            ((TaskContract.Presenter) mPresenter).isLogin(2);
        } else if (view.getId() == R.id.img_phone) {
            ((TaskContract.Presenter) mPresenter).isLogin(3);
        } else if (view.getId() == R.id.img_start && StringUtils.isEmpty(mAdapter.getItem(i).getArr_org_time_str())) {
            showLoadingDialog(getString(R.string.submissionLoad));
            ((TaskContract.Presenter) mPresenter).postArrOrgTime(mAdapter.getItem(i).getId(), 0);
        } else if (view.getId() == R.id.img_car && StringUtils.isEmpty(mAdapter.getItem(i).getSend_time())) {
            showLoadingDialog(getString(R.string.submissionLoad));
            ((TaskContract.Presenter) mPresenter).postShipping(mAdapter.getItem(i).getId());
        } else if (view.getId() == R.id.img_end && StringUtils.isEmpty(mAdapter.getItem(i).getArr_time())) {
            showLoadingDialog(getString(R.string.submissionLoad));
            ((TaskContract.Presenter) mPresenter).postArrOrgTime(mAdapter.getItem(i).getId(), 1);
        } else if (view.getId() == R.id.tv_submitDocuments) {
            ((TaskContract.Presenter) mPresenter).isLogin(4);
        } else if (view.getId() == R.id.tv_exceptionReporting) {
            ((TaskContract.Presenter) mPresenter).isLogin(5);
        } else if (view.getId() == R.id.tv_cancelOrder) {
            ((TaskContract.Presenter) mPresenter).isLogin(6);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {// 如果等于1
            mRefreshLayout.beginRefreshing();
        }
    }

    @AfterPermissionGranted(REQUEST_CODE_PERMISSION_CALL)
    private void choiceCallWrapper(String phone) {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(aty, perms)) {
            if (customerServiceTelephoneBouncedDialog == null) {
                customerServiceTelephoneBouncedDialog = new CustomerServiceTelephoneBouncedDialog(getActivity(), phone);
            } else {
                customerServiceTelephoneBouncedDialog.setPhone(phone);
            }
            customerServiceTelephoneBouncedDialog.show();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.phoneCallPermissions), REQUEST_CODE_PERMISSION_CALL, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == REQUEST_CODE_PERMISSION_CALL) {
            ViewInject.toast(getString(R.string.phoneCallPermissions1));
        }
    }

}
