package com.ruitukeji.zwbs.mine.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    private TextView tv_phone;
    private String phone;

    public CustomerServiceTelephoneBouncedDialog(Context context, String phone) {
        super(context, R.style.dialog);
        this.context = context;
        this.phone = phone;
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
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_phone.setText(phone);
        tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setText(context.getString(R.string.call));
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                Uri uri = Uri.parse("tel:" + tv_phone.getText().toString().trim());
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                //     系统打电话界面：
                context.startActivity(intent);
                cancel();
                break;
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
        tv_phone.setText(phone);
    }

}
