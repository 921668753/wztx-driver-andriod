package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.entity.getorder.GetOrderBean.ResultBean.ListBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;

import static com.ruitukeji.zwbs.utils.MathUtil.judgeTwoDecimal;

/**
 * 接单---发送报价弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class SendQuotationBouncedDialog extends BaseDialog implements View.OnClickListener, GetOrderBouncedContract.View {


    private int orderId = 0;
    private String money = "";
    private Context context;
    private TextView tv_firmQuotation;
    private EditText et_pleaseEnterPrice;
    private ImageView iv_cancel;

    private GetOrderBouncedContract.Presenter mPresenter;

    public SendQuotationBouncedDialog(Context context, int orderId, String money) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.orderId = orderId;
        this.money = money;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sendquotationbounced);
        initView();
    }

    private void initView() {
        tv_firmQuotation = (TextView) findViewById(R.id.tv_firmQuotation);
        tv_firmQuotation.setOnClickListener(this);
        TextView tv_estimatePrice = (TextView) findViewById(R.id.tv_estimatePrice);
        tv_estimatePrice.setText(money);
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
                    mPresenter.getQuoteAdd(orderId, moneys, 0);
                } else {
                    ViewInject.toast(context.getString(R.string.hintDriverBid1));
                }
                break;

        }
    }

    @Override
    public void setPresenter(GetOrderBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            confirm();
        }
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

