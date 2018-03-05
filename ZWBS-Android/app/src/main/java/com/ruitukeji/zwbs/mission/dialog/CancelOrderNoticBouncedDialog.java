package com.ruitukeji.zwbs.mission.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.getorder.OrderDetailsActivity;

/**
 * 任务---取消订单通知弹框
 * Created by Administrator on 2017/11/28.
 */

public class CancelOrderNoticBouncedDialog extends BaseDialog implements View.OnClickListener {

    private String orderCode = "";
    private int orderId = 0;
    private Context context;
    private TextView tv_orderNumber;

    public CancelOrderNoticBouncedDialog(Context context, int orderId, String orderCode) {
        super(context, R.style.dialog);
        this.context = context;
        this.orderId = orderId;
        this.orderCode = orderCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_cancelordernoticbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        ImageView img_cancel = (ImageView) findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener(this);
        TextView tv_checkDetails = (TextView) findViewById(R.id.tv_checkDetails);
        tv_checkDetails.setOnClickListener(this);
        tv_orderNumber = (TextView) findViewById(R.id.tv_orderNumber);
        tv_orderNumber.setText(context.getString(R.string.orderNumber1) + orderCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cancel:
                cancel();
                break;
            case R.id.tv_checkDetails:
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("order_id", orderId);
                intent.putExtra("is_cancel", 2);
                context.startActivity(intent);
                cancel();
                break;
        }
    }

    public void setOrderId(int orderId, String orderCode) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        tv_orderNumber.setText(context.getString(R.string.orderNumber1) + orderCode);
    }

}

