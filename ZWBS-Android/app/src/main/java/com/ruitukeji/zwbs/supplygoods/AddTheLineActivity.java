package com.ruitukeji.zwbs.supplygoods;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
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
import com.ruitukeji.zwbs.supplygoods.dialog.AddLineProvinceBouncedDialog;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;

import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_SELECT;

/**
 * 添加路线
 * Created by Administrator on 2017/2/21.
 */

public class AddTheLineActivity extends BaseActivity implements AddTheLineContract.View {

    @BindView(id = R.id.ll_start, click = true)
    private LinearLayout ll_start;
    @BindView(id = R.id.tv_start)
    private TextView tv_start;
    @BindView(id = R.id.img_start)
    private ImageView img_start;

    @BindView(id = R.id.ll_stop, click = true)
    private LinearLayout ll_stop;
    @BindView(id = R.id.tv_stop)
    private TextView tv_stop;
    @BindView(id = R.id.img_stop)
    private ImageView img_stop;
    private AddLineProvinceBouncedDialog stopProvinceBouncedDialog = null;
    private AddLineProvinceBouncedDialog startProvinceBouncedDialog = null;

    private int origin_province = 0;
    private int origin_city = 0;
    private int origin_area = 0;
    private int destination_province = 0;
    private int destination_city = 0;
    private int destination_area = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addtheline);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddTheLinePresenter(this);
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
                ((AddTheLineContract.Presenter) mPresenter).postRoute(tv_start.getText().toString(), tv_stop.getText().toString(), origin_province, origin_city, origin_area,
                        destination_province, destination_city, destination_area);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addtheline), getString(R.string.determine), R.id.titlebar, simpleDelegate);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_start:
                img_start.setImageResource(R.mipmap.ic_arrow_up);
                if (startProvinceBouncedDialog != null && !startProvinceBouncedDialog.isShowing()) {
                    startProvinceBouncedDialog.show();
                    return;
                }
                startProvinceBouncedDialog = new AddLineProvinceBouncedDialog(aty, "", 0, 0) {
                    @Override
                    public void confirmProvince(String provinceName, int provinceId, int cityId, int areaId) {
                        this.cancel();
                        origin_province = provinceId;
                        origin_city = cityId;
                        origin_area = areaId;
                        tv_start.setText(provinceName);
                    }
                };
                startProvinceBouncedDialog.show();
                startProvinceBouncedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        img_start.setImageResource(R.mipmap.ic_arrow_down);
                    }
                });
                break;
            case R.id.ll_stop:
                img_stop.setImageResource(R.mipmap.ic_arrow_up);
                if (stopProvinceBouncedDialog != null && !stopProvinceBouncedDialog.isShowing()) {
                    stopProvinceBouncedDialog.show();
                    return;
                }
                stopProvinceBouncedDialog = new AddLineProvinceBouncedDialog(aty, "", 0, 1) {
                    @Override
                    public void confirmProvince(String provinceName, int provinceId, int cityId, int areaId) {
                        this.cancel();
                        destination_province = provinceId;
                        destination_city = cityId;
                        destination_area = areaId;
                        tv_stop.setText(provinceName);
                    }
                };
                stopProvinceBouncedDialog.show();
                stopProvinceBouncedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        img_stop.setImageResource(R.mipmap.ic_arrow_down);
                    }
                });
                break;

        }
    }

    @Override
    public void getSuccess(String s) {
        AddTheLineBean addTheLineBean = (AddTheLineBean) JsonUtil.getInstance().json2Obj(s, AddTheLineBean.class);
        PreferenceHelper.write(this, StringConstants.FILENAME, "line_id", addTheLineBean.getResult().getDrline_id() + "");
        dismissLoadingDialog();
        Intent intent = new Intent();
        // 获取内容
        intent.putExtra("org_address", tv_start.getText().toString());
        intent.putExtra("startProvinceName", origin_province);
        intent.putExtra("startCityName", origin_city);
        intent.putExtra("startAreaName", origin_area);
        intent.putExtra("dest_address", tv_stop.getText().toString());
        intent.putExtra("endProvinceName", destination_province);
        intent.putExtra("endCityName", destination_city);
        intent.putExtra("endAreaName", destination_area);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (startProvinceBouncedDialog != null) {
            startProvinceBouncedDialog.cancel();
        }
        if (stopProvinceBouncedDialog != null) {
            stopProvinceBouncedDialog.cancel();
        }
        stopProvinceBouncedDialog = null;
        startProvinceBouncedDialog = null;
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusAddTheLineFinishEvent")) {
            aty.finish();
        } else if (((String) msgEvent.getData()).equals("RxBusAddTheLineDetermineEvent")) {
            ViewInject.toast(getString(R.string.pleaseSetPointDeparture));
        }
    }
}
