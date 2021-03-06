package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.loginregister.LoginActivity;

import static com.ruitukeji.zwbs.utils.MathUtil.judgeTwoDecimal;

/**
 * 接单---发送报价弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class SendQuotationBouncedDialog extends BaseDialog implements View.OnClickListener, SendQuotationBouncedContract.View {


    private int orderId = 0;
    private String money = "";
    private Context context;
    private EditText et_pleaseEnterPrice;
    private ImageView iv_cancel;

    private SendQuotationBouncedContract.Presenter mPresenter;

    public SendQuotationBouncedDialog(Context context, int orderId, String money) {
        super(context, R.style.dialog);
        this.context = context;
        this.orderId = orderId;
        this.money = money;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sendquotationbounced);
        Window dialogWindow = getWindow();
        //让布局向上移来显示软键盘
//        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        mPresenter = new SendQuotationBouncedPresenter(this);
        TextView tv_firmQuotation = (TextView) findViewById(R.id.tv_firmQuotation);
        tv_firmQuotation.setOnClickListener(this);
        TextView tv_estimatePrice = (TextView) findViewById(R.id.tv_estimatePrice);
        tv_estimatePrice.setText(context.getString(R.string.estimatePrice) + money);
        et_pleaseEnterPrice = (EditText) findViewById(R.id.et_pleaseEnterPrice);
        iv_cancel = (ImageView) findViewById(R.id.iv_cancel);
        iv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_cancel:
                cancel();
                break;
            case R.id.tv_firmQuotation:
                String moneys = et_pleaseEnterPrice.getText().toString().trim();
                if (judgeTwoDecimal(moneys)) {
                    showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                    mPresenter.getQuoteAdd(orderId, moneys, 0);
                } else {
                    ViewInject.toast(context.getString(R.string.hintDriverBid1));
                }
                break;
        }
    }

    @Override
    public void setPresenter(SendQuotationBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setSysMoney(int orderId, String money) {
        this.orderId = orderId;
        this.money = money;
        et_pleaseEnterPrice.setText("");
        TextView tv_estimatePrice = (TextView) findViewById(R.id.tv_estimatePrice);
        tv_estimatePrice.setText(context.getString(R.string.estimatePrice) + money);
    }


    @Override
    public void getSuccess(String s, int flag) {
        et_pleaseEnterPrice.setText("");
        confirm();
        dismissLoadingDialog();
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

