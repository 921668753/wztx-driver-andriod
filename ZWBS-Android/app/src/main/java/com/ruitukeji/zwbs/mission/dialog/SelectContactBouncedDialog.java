package com.ruitukeji.zwbs.mission.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;

/**
 * 任务---选择联系人弹框
 * Created by Administrator on 2018/2/1.
 */

public class SelectContactBouncedDialog extends BaseDialog implements View.OnClickListener {

    private String org_phone = "";
    private String org_telphone = "";
    private String dest_phone = "";
    private String dest_telphone = "";
    private Context context;
    private TextView tv_orgPhone;
    private TextView tv_orgTelphone;
    private TextView tv_destPhone;
    private TextView tv_destTelphone;
    private TextView tv_divider;
    private TextView tv_divider1;
    private TextView tv_divider2;

    public SelectContactBouncedDialog(Context context, String org_phone, String org_telphone, String dest_phone, String dest_telphone) {
        super(context, R.style.dialog);
        this.context = context;
        this.org_phone = org_phone;
        this.org_telphone = org_telphone;
        this.dest_phone = dest_phone;
        this.dest_telphone = dest_telphone;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectcontactbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        tv_orgPhone = (TextView) findViewById(R.id.tv_orgPhone);
        tv_orgPhone.setOnClickListener(this);
        tv_divider = (TextView) findViewById(R.id.tv_divider);
        tv_orgTelphone = (TextView) findViewById(R.id.tv_orgTelphone);
        tv_orgTelphone.setOnClickListener(this);
        tv_divider1 = (TextView) findViewById(R.id.tv_divider1);
        tv_destPhone = (TextView) findViewById(R.id.tv_destPhone);
        tv_destPhone.setOnClickListener(this);
        tv_divider2 = (TextView) findViewById(R.id.tv_divider2);
        tv_destTelphone = (TextView) findViewById(R.id.tv_destTelphone);
        tv_destTelphone.setOnClickListener(this);
        setText();
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_orgPhone:
                setPhoneUri(org_phone);
                cancel();
                break;
            case R.id.tv_orgTelphone:
                setPhoneUri(org_telphone);
                cancel();
                break;
            case R.id.tv_destPhone:
                setPhoneUri(dest_phone);
                cancel();
                break;
            case R.id.tv_destTelphone:
                setPhoneUri(dest_telphone);
                cancel();
                break;
            case R.id.tv_cancel:
                cancel();
                break;
        }
    }

    public void setPhone(String org_phone, String org_telphone, String dest_phone, String dest_telphone) {
        this.org_phone = org_phone;
        this.org_telphone = org_telphone;
        this.dest_phone = dest_phone;
        this.dest_telphone = dest_telphone;
        setText();
    }

    private void setPhoneUri(String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        //     系统打电话界面：
        context.startActivity(intent);
    }


    private void setText() {
        if (StringUtils.isEmpty(org_phone)) {
            tv_orgPhone.setVisibility(View.GONE);
        } else {
            tv_orgPhone.setVisibility(View.VISIBLE);
        }
        if (StringUtils.isEmpty(org_telphone)) {
            tv_orgTelphone.setVisibility(View.GONE);
            tv_divider.setVisibility(View.GONE);
        } else {
            tv_orgTelphone.setVisibility(View.VISIBLE);
            tv_divider.setVisibility(View.VISIBLE);
        }
        if (StringUtils.isEmpty(dest_phone)) {
            tv_destPhone.setVisibility(View.GONE);
            tv_divider1.setVisibility(View.GONE);
        } else {
            tv_destPhone.setVisibility(View.VISIBLE);
            tv_divider1.setVisibility(View.VISIBLE);
        }
        if (StringUtils.isEmpty(dest_telphone)) {
            tv_divider2.setVisibility(View.GONE);
            tv_destTelphone.setVisibility(View.GONE);
        } else {
            tv_divider2.setVisibility(View.VISIBLE);
            tv_destTelphone.setVisibility(View.VISIBLE);
        }
    }

}
