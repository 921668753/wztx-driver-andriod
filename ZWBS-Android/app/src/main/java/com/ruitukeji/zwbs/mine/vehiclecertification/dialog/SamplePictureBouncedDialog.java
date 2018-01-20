package com.ruitukeji.zwbs.mine.vehiclecertification.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;

/**
 * 车辆认证---示例图片弹框
 * Created by Administrator on 2017/11/28.
 */

public class SamplePictureBouncedDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private int pic;
    private String samplePictureText;

    private ImageView img_samplePicture;
    private TextView tv_samplePicture;
    private ImageView img_del;


    public SamplePictureBouncedDialog(Context context, int pic, String samplePictureText) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.pic = pic;
        this.samplePictureText = samplePictureText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_samplepicturebounced);
        initView();
    }

    private void initView() {
        img_samplePicture = (ImageView) findViewById(R.id.img_samplePicture);
        tv_samplePicture = (TextView) findViewById(R.id.tv_samplePicture);
        setImgText(pic, samplePictureText);
        img_del = (ImageView) findViewById(R.id.img_del);
        img_del.setOnClickListener(this);
    }


    public void setImgText(int pic, String samplePictureText) {
        img_samplePicture.setImageResource(pic);
        tv_samplePicture.setText(samplePictureText);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_del:
                cancel();
                break;
        }
    }
}
