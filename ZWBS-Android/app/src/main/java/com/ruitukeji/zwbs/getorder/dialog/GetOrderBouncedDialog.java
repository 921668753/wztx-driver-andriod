package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.entity.getorder.GetOrderBean.ResultBean.ListBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;

/**
 * 接单---确认接单弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class GetOrderBouncedDialog extends BaseDialog implements View.OnClickListener, GetOrderBouncedContract.View {

    private String money = "0.00";
    private Context context;
    private TextView tv_cancel;
    private TextView tv_determine;
    private int orderId;
    private GetOrderBouncedContract.Presenter mPresenter;

    public GetOrderBouncedDialog(Context context, int orderId, String money) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.orderId = orderId;
        this.money = money;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_getorderbounced);
        initView();
    }

    private void initView() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        mPresenter = new GetOrderBouncedPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
//                if (listBean.getMind_price() == null || listBean.getMind_price().equals("0.00")) {
//                    mPresenter.getQuoteAdd(listBean.getId(), listBean.getSystem_price(), 1);
//                    break;
//                }
                mPresenter.getQuoteAdd(orderId, money, 1);
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            confirm();
        }
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(GetOrderBouncedContract.Presenter presenter) {
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
