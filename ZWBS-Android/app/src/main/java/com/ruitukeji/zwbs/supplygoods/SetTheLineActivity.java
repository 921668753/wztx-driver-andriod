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
import com.ruitukeji.zwbs.main.MainActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.DialogUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * 设定路线
 * Created by Administrator on 2017/2/21.
 */

public class SetTheLineActivity extends BaseActivity implements SetTheLineContract.View, BGAOnItemChildClickListener, AdapterView.OnItemClickListener {


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
        SimpleDelegate simpleDelegate = new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                showActivity(aty, AddTheLineActivity.class);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.settheline), getString(R.string.addtheline), R.id.titlebar, simpleDelegate);
        lv_settheline.setAdapter(setTheLineViewAdapter);
        lv_settheline.setOnItemClickListener(this);
        setTheLineViewAdapter.setOnItemChildClickListener(this);
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        ((SetTheLineContract.Presenter) mPresenter).getRouteList();
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
                ((SetTheLineContract.Presenter) mPresenter).getRouteList();
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        //setTheLineViewAdapter.addNewData();
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
            showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
            ((SetTheLineContract.Presenter) mPresenter).getRouteList();
        }
    }

    @Override
    public void error(String msg) {
        toLigon(msg);
        lv_settheline.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(SetTheLineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setTheLineViewAdapter.clear();
        setTheLineViewAdapter = null;
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View view, int i) {
        if (view.getId() == R.id.iv_delete) {
            DialogUtil.showAlertDialog(this, R.string.deleteRoute, new DialogUtil.OnDialogSelectListener() {
                @Override
                public void onDialogSelect() {
                    showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
                    ((SetTheLineContract.Presenter) mPresenter).postDeleteRoute(setTheLineViewAdapter.getItem(i).getDrline_id());
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods", true);
        PreferenceHelper.write(this, StringConstants.FILENAME, "line_id", setTheLineViewAdapter.getItem(position).getDrline_id() + "");
        Intent intent = new Intent();
        intent.putExtra("newChageIcon", 0);
        intent.setClass(getApplicationContext(), MainActivity.class);
        skipActivity(aty, intent);
    }
}