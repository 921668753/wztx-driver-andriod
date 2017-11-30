package com.ruitukeji.zwbs.mine.vehiclecertification.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;

/**
 * 车辆认证---示例图片弹框
 * Created by Administrator on 2017/11/28.
 */

public class SamplePictureBouncedDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tv_transparent;
    private ChildLiistView lv_models;

    public SamplePictureBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_samplepicturebounced);
        initView();
    }

    private void initView() {
//        lv_models = (ChildLiistView) findViewById(R.id.lv_models);
//        tv_transparent = (TextView) findViewById(R.id.tv_transparent);
//        tv_transparent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


//            case R.id.tv_transparent:
//                dismiss();
//                break;
        }
    }
}
