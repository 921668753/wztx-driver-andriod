package com.ruitukeji.zwbs.getorder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;

/**
 * 接单---发送报价弹框
 * Created by Administrator on 2017/11/28.
 */

public class SendQuotationBouncedDialog extends Dialog implements View.OnClickListener {


    private Context context;
    private TextView tv_firmQuotation;
    private EditText et_pleaseEnterPrice;
    private ImageView iv_cancel;

    public SendQuotationBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sendquotationbounced);
        initView();
    }

    private void initView() {
        tv_firmQuotation = (TextView) findViewById(R.id.tv_firmQuotation);
        et_pleaseEnterPrice = (EditText) findViewById(R.id.et_pleaseEnterPrice);
        iv_cancel = (ImageView) findViewById(R.id.iv_cancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_firmQuotation:
                dismiss();
                break;

            case R.id.iv_cancel:
                dismiss();
                break;
        }
    }

}

