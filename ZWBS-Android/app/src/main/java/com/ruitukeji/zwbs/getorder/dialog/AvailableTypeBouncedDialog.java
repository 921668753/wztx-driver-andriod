package com.ruitukeji.zwbs.getorder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;

/**
 * 接单---可接单类型弹框
 * Created by Administrator on 2017/11/28.
 */

public class AvailableTypeBouncedDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tv_transparent;
    private ChildLiistView lv_models;

    public AvailableTypeBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modelsbounced);
        initView();
    }

    private void initView() {
        lv_models = (ChildLiistView) findViewById(R.id.lv_models);
        tv_transparent = (TextView) findViewById(R.id.tv_transparent);
        tv_transparent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.tv_transparent:
                dismiss();
                break;
        }
    }
}
