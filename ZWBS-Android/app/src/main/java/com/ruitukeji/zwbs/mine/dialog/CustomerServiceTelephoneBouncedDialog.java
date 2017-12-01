package com.ruitukeji.zwbs.mine.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;

/**
 * 我的---客服电话弹框
 * Created by Administrator on 2017/11/28.
 */

public class CustomerServiceTelephoneBouncedDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tv_cancel;
    private TextView tv_determine;

    public CustomerServiceTelephoneBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customerservicetelephonebounced);
        initView();
    }

    private void initView() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_determine = (TextView) findViewById(R.id.tv_determine);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;

            case R.id.tv_determine:
                dismiss();
                break;
        }
    }
}
