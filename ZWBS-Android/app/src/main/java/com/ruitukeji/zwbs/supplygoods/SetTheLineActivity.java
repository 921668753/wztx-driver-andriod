package com.ruitukeji.zwbs.supplygoods;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.SetTheLineViewAdapter;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.SetTheLineBean;
import com.ruitukeji.zwbs.supplygoods.dialog.DeleteRouteBouncedDialog;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_SELECT;

/**
 * 设定路线
 * Created by Administrator on 2017/2/21.
 */

public class SetTheLineActivity extends BaseActivity implements SetTheLineContract.View, BGAOnItemChildClickListener, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_settheline)
    private ListView lv_settheline;

    private SetTheLineViewAdapter setTheLineViewAdapter;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;
    private DeleteRouteBouncedDialog deleteRouteBouncedDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_settheline);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SetTheLinePresenter(this);
        setTheLineViewAdapter = new SetTheLineViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, this, false);
        SimpleDelegate simpleDelegate = new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                Intent intent = new Intent(aty, AddTheLineActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.settheline), getString(R.string.addtheline), R.id.titlebar, simpleDelegate);
        lv_settheline.setAdapter(setTheLineViewAdapter);
        lv_settheline.setOnItemClickListener(this);
        setTheLineViewAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
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
            ll_commonError.setVisibility(View.GONE);
            lv_settheline.setVisibility(View.VISIBLE);
            SetTheLineBean setTheLineBean = (SetTheLineBean) JsonUtil.getInstance().json2Obj(s, SetTheLineBean.class);
            if (setTheLineBean.getResult().getList() == null || setTheLineBean.getResult().getList().size() == 0) {
                error(getString(R.string.serverReturnsDataNull));
                return;
            }
            setTheLineViewAdapter.clear();
            setTheLineViewAdapter.addMoreData(setTheLineBean.getResult().getList());
            dismissLoadingDialog();
        } else if (flag == 1) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void error(String msg) {
        if (!toLigon1(msg)) {
            finish();
            return;
        }
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(SetTheLineContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {
            int startProvinceName = data.getIntExtra("startProvinceName", 0);
            int startCityName = data.getIntExtra("startCityName", 0);
            int startAreaName = data.getIntExtra("startAreaName", 0);
            int endProvinceName = data.getIntExtra("endProvinceName", 0);
            int endCityName = data.getIntExtra("endCityName", 0);
            int endAreaName = data.getIntExtra("endAreaName", 0);
            String org_address = data.getStringExtra("org_address");
            String dest_address = data.getStringExtra("dest_address");
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("org_address", org_address);
            intent.putExtra("startProvinceName", startProvinceName);
            intent.putExtra("startCityName", startCityName);
            intent.putExtra("startAreaName", startAreaName);
            intent.putExtra("dest_address", dest_address);
            intent.putExtra("endProvinceName", endProvinceName);
            intent.putExtra("endCityName", endCityName);
            intent.putExtra("endAreaName", endAreaName);
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (deleteRouteBouncedDialog != null) {
            deleteRouteBouncedDialog.cancel();
        }
        deleteRouteBouncedDialog = null;
        setTheLineViewAdapter.clear();
        setTheLineViewAdapter = null;
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View view, int i) {
        if (view.getId() == R.id.iv_delete) {
            int id = setTheLineViewAdapter.getItem(i).getDrline_id();
            if (deleteRouteBouncedDialog != null && !deleteRouteBouncedDialog.isShowing()) {
                deleteRouteBouncedDialog.show();
                deleteRouteBouncedDialog.setRouteId(id);
                return;
            }
            deleteRouteBouncedDialog = new DeleteRouteBouncedDialog(aty, getString(R.string.deleteRoute), id) {
                @Override
                public void confirm(int id) {
                    this.cancel();
                    ((SetTheLineContract.Presenter) mPresenter).postDeleteRoute(id);
                }
            };
            deleteRouteBouncedDialog.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SetTheLineBean.ResultBean.ListBean listBean = setTheLineViewAdapter.getItem(position);
        PreferenceHelper.write(this, StringConstants.FILENAME, "line_id", listBean.getDrline_id());
        Intent intent = new Intent();
        // 获取内容
        intent.putExtra("org_address", listBean.getOrg_address());
        intent.putExtra("startProvinceName", listBean.getOrigin_province());
        intent.putExtra("startCityName", listBean.getOrigin_city());
        intent.putExtra("startAreaName", listBean.getOrigin_area());
        intent.putExtra("dest_address", listBean.getDest_address());
        intent.putExtra("endProvinceName", listBean.getDestination_province());
        intent.putExtra("endCityName", listBean.getDestination_city());
        intent.putExtra("endAreaName", listBean.getDestination_area());
        // 设置结果 结果码，一个数据
        setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        ((SetTheLineContract.Presenter) mPresenter).getRouteList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        mRefreshLayout.endLoadingMore();
        return false;
    }
}
