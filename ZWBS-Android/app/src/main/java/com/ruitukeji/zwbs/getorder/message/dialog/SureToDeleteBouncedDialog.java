package com.ruitukeji.zwbs.getorder.message.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;

/**
 * 确定删除
 * Created by Administrator on 2017/12/12.
 */

public class SureToDeleteBouncedDialog extends BaseDialog implements View.OnClickListener {

    private Context context;
    private TextView tv_content;
    private TextView tv_cancel;
    private TextView tv_determine;

    private SureToDeleteDialogCallBack callBack;//回调

    public SureToDeleteBouncedDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_markedasreadbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width= WindowManager.LayoutParams.MATCH_PARENT;
        lp.height= WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(context.getString(R.string.sureToDelete));
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        //  mPresenter = new MarkedAsReadBouncedPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_determine:
                if (callBack != null) {
                    callBack.confirm();
                }
                break;
        }
    }

//    @Override
//    public void getSuccess(String s, int flag) {
//        if (flag == 0) {
//            if (callBack != null) {
//                callBack.confirm();
//            }
//        }
//        dismissLoadingDialog();
//    }
//
//    @Override
//    public void setPresenter(MarkedAsReadBouncedContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    @Override
//    public void error(String msg) {
//        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
//            dismissLoadingDialog();
//            Intent intent = new Intent(context, LoginActivity.class);
//            context.startActivity(intent);
//            return;
//        }
//        dismissLoadingDialog();
//        ViewInject.toast(msg);
//    }


    public void setSureToDeleteDialogCallBack(SureToDeleteDialogCallBack callBack) {
        this.callBack = callBack;
    }


    public interface SureToDeleteDialogCallBack {

        void confirm();

    }


}
