package com.ruitukeji.zwbs.supplygoods;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.AddTheLineBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.PickerViewUtil;

import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * 添加路线
 * Created by Administrator on 2017/2/21.
 */

public class AddTheLineActivity extends BaseActivity implements AddTheLineContract.View {

    @BindView(id = R.id.ll_start, click = true)
    private LinearLayout ll_start;
    @BindView(id = R.id.tv_start)
    private TextView tv_start;
//    @BindView(id = R.id.img_start)
//    private ImageView img_start;

    @BindView(id = R.id.ll_stop, click = true)
    private LinearLayout ll_stop;
    @BindView(id = R.id.tv_stop)
    private TextView tv_stop;
//    @BindView(id = R.id.img_stop)
//    private ImageView img_stop;

    private PickerViewUtil pickerViewUtil;
    private PickerViewUtil pickerViewUtil1;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addtheline);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddTheLinePresenter(this);
        //选项选择器
        initPickerView();
    }

    /**
     * 选项选择器
     */
    private void initPickerView() {
        if (pickerViewUtil == null) {
            pickerViewUtil = new PickerViewUtil(this, 1) {
                @Override
                public void getAddress(String address) {
                    tv_start.setText(address);
                }
            };
        }
        if (pickerViewUtil1 == null) {
            pickerViewUtil1 = new PickerViewUtil(this, 1) {
                @Override
                public void getAddress(String address) {
                    //    img_stop.setRotation(360);
                    tv_stop.setText(address);
                }
            };
        }
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
                showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                ((AddTheLineContract.Presenter) mPresenter).postRoute(tv_start.getText().toString(), tv_stop.getText().toString());
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addtheline), getString(R.string.determine), R.id.titlebar, simpleDelegate);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_start:
                //  img_start.setRotation(180);
                //点击弹出选项选择器
                pickerViewUtil.onoptionsSelectListener();
                break;
            case R.id.ll_stop:
                // img_stop.setRotation(180);
                pickerViewUtil1.onoptionsSelectListener();
                break;

        }
    }

    @Override
    public void getSuccess(String s) {
        AddTheLineBean addTheLineBean = (AddTheLineBean) JsonUtil.getInstance().json2Obj(s, AddTheLineBean.class);
        PreferenceHelper.write(this, StringConstants.FILENAME, "line_id", addTheLineBean.getResult().getDrline_id() + "");
        KJActivityStack.create().finishActivity(SetTheLineActivity.class);
        dismissLoadingDialog();
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

    @Override
    public void error(String msg) {
        if (msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            Intent intent = new Intent(aty, LoginActivity.class);
            startActivity(intent);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(AddTheLineContract.Presenter presenter) {
        mPresenter = presenter;
    }


    /**
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pickerViewUtil != null && pickerViewUtil.isShowing()) {
//                img_start.setRotation(360);
//                img_stop.setRotation(360);
                pickerViewUtil.onDismiss();
                return true;
            }
            if (pickerViewUtil1 != null && pickerViewUtil1.isShowing()) {
//                img_start.setRotation(360);
//                img_stop.setRotation(360);
                pickerViewUtil1.onDismiss();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pickerViewUtil != null && pickerViewUtil.isShowing()) {
            pickerViewUtil.onDismiss();
        }
        pickerViewUtil = null;
        if (pickerViewUtil1 != null && pickerViewUtil1.isShowing()) {
            pickerViewUtil1.onDismiss();
        }
        pickerViewUtil1 = null;
    }
}
