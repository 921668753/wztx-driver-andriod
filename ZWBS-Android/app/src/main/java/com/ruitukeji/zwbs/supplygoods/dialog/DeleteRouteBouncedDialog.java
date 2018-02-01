package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;

/**
 * 个人中心---删除路线弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class DeleteRouteBouncedDialog extends BaseDialog implements View.OnClickListener {

    private int id = 0;
    private String content = "";
    private Context context;
    private TextView tv_cancel;
    private TextView tv_determine;
    private TextView tv_content;

    public DeleteRouteBouncedDialog(Context context, String content, int id) {
        super(context, R.style.dialog);
        this.context = context;
        this.content = content;
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_markedasreadbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(content);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                confirm(id);
                break;
        }
    }

    public void setRouteId(int id) {
        this.id = id;
    }

    public abstract void confirm(int id);
}
