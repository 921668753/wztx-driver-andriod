package com.ruitukeji.zwbs.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 分享--------评价分享弹框
 * Created by Administrator on 2017/2/21.
 */

public abstract class ShareBouncedDialog extends Dialog implements View.OnClickListener {


    private Context context;
    private LinearLayout ll_weChatFriends;
    private LinearLayout ll_circleFriends;
    private LinearLayout ll_QQFriends;
    private LinearLayout ll_Qzone;
    private TextView tv_cancel;

    public ShareBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sharebounced);
        initView();
    }

    private void initView() {
        ll_weChatFriends = (LinearLayout) findViewById(R.id.ll_weChatFriends);
        ll_weChatFriends.setOnClickListener(this);
        ll_circleFriends = (LinearLayout) findViewById(R.id.ll_circleFriends);
        ll_circleFriends.setOnClickListener(this);
        ll_QQFriends = (LinearLayout) findViewById(R.id.ll_QQFriends);
        ll_QQFriends.setOnClickListener(this);
        ll_Qzone = (LinearLayout) findViewById(R.id.ll_Qzone);
        ll_Qzone.setOnClickListener(this);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_weChatFriends:
                dismiss();
                share(SHARE_MEDIA.WEIXIN);
                break;

            case R.id.ll_circleFriends:
                dismiss();
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.ll_QQFriends:
                dismiss();
                share(SHARE_MEDIA.QQ);
                break;
            case R.id.ll_Qzone:
                dismiss();
                share(SHARE_MEDIA.QZONE);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public abstract void share(SHARE_MEDIA platform);


}
