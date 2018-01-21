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
import com.ruitukeji.zwbs.supplygoods.dialog.AuthenticationBouncedDialog;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.DialogUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_SELECT;

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
                Intent intent = new Intent(aty, AddTheLineActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {
            String startProvinceName = data.getStringExtra("startProvinceName");
            String startCityName = data.getStringExtra("startCityName");
            String startAreaName = data.getStringExtra("startAreaName");
            String endProvinceName = data.getStringExtra("endProvinceName");
            String endCityName = data.getStringExtra("endCityName");
            String endAreaName = data.getStringExtra("endAreaName");
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("startProvinceName", startProvinceName);
            intent.putExtra("startCityName", startCityName);
            intent.putExtra("startAreaName", startAreaName);
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
        setTheLineViewAdapter.clear();
        setTheLineViewAdapter = null;
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View view, int i) {
        if (view.getId() == R.id.iv_delete) {
            AuthenticationBouncedDialog authenticationBouncedDialog = new AuthenticationBouncedDialog(aty, getString(R.string.deleteRoute)) {
                @Override
                public void confirm() {
                    this.cancel();
                    showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
                    ((SetTheLineContract.Presenter) mPresenter).postDeleteRoute(setTheLineViewAdapter.getItem(i).getDrline_id());
                }
            };
            authenticationBouncedDialog.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PreferenceHelper.write(this, StringConstants.FILENAME, "line_id", setTheLineViewAdapter.getItem(position).getDrline_id() + "");
        Intent intent = new Intent();
        // 获取内容
//        intent.putExtra("startProvinceName", startProvinceName);
//        intent.putExtra("startCityName", startCityName);
//        intent.putExtra("startAreaName", startAreaName);
//        intent.putExtra("endProvinceName", endProvinceName);
//        intent.putExtra("endCityName", endCityName);
//        intent.putExtra("endAreaName", endAreaName);
        // 设置结果 结果码，一个数据
        setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();
    }
}
