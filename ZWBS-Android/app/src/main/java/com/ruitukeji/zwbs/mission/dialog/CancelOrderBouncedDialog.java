package com.ruitukeji.zwbs.mission.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.loginregister.LoginActivity;

/**
 * 任务---取消订单弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class CancelOrderBouncedDialog extends BaseDialog implements View.OnClickListener, CancelOrderBouncedContract.View {

    private int orderId = 0;
    private Context context;

    private CancelOrderBouncedContract.Presenter mPresenter;

    public CancelOrderBouncedDialog(Context context, int orderId) {
        super(context, R.style.dialog);
        this.context = context;
        this.orderId = orderId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customerservicetelephonebounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        mPresenter = new CancelOrderBouncedPresenter(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        TextView tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_phone.setText(context.getString(R.string.cancelOrder));
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                showLoadingDialog(context.getString(R.string.submissionLoad));
                mPresenter.postCancelOrder(orderId);
                break;
        }
    }

    @Override
    public void setPresenter(CancelOrderBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s, int flag) {
        confirm();
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public abstract void confirm();
}

