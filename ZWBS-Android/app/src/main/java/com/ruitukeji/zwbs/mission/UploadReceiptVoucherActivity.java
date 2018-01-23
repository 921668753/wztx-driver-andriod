package com.ruitukeji.zwbs.mission;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 上传收货凭证
 * Created by Administrator on 2017/11/29.
 */

public class UploadReceiptVoucherActivity extends BaseActivity implements UploadReceiptVoucherContract.View {

    /**
     * 上传图片
     */
    @BindView(id = R.id.img_uploadPictures, click = true)
    private ImageView img_uploadPictures;


    /**
     * 完成订单
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_uploadreceiptvoucher);
    }

    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.uploadReceiptVoucher), true, R.id.titlebar);
        if (true) {
            tv_submit.setText(getString(R.string.submit));
        } else {
            tv_submit.setText(getString(R.string.completeOrder));
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_uploadPictures:
                showLoadingDialog(getString(R.string.crossLoad));

                break;
            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.submissionLoad));

                break;
        }
    }


    @Override
    public void setPresenter(UploadReceiptVoucherContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (!toLigon1(msg)) {
            return;
        }
    }
}
