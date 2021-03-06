package com.ruitukeji.zwbs.getorder.dialog;

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
 * 接单---确认接单弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class GetOrderBouncedDialog extends BaseDialog implements View.OnClickListener, SendQuotationBouncedContract.View {

    private String money = "0.00";
    private Context context;
    private TextView tv_cancel;
    private TextView tv_determine;
    private int orderId;
    private SendQuotationBouncedContract.Presenter mPresenter;

    public GetOrderBouncedDialog(Context context, int orderId, String money) {
        super(context, R.style.dialog);
        this.context = context;
        this.orderId = orderId;
        this.money = money;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_getorderbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        mPresenter = new SendQuotationBouncedPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                mPresenter.getQuoteAdd(orderId, money, 1);
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        confirm();
        dismissLoadingDialog();
    }

    public void setMoney(int orderId, String money) {
        this.orderId = orderId;
        this.money = money;
    }


    @Override
    public void setPresenter(SendQuotationBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void error(String msg) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    public abstract void confirm();
}
