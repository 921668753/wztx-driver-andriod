package com.ruitukeji.zwbs.mission;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 上传收货凭证
 * Created by Administrator on 2017/11/29.
 */

public class UploadReceiptVoucherActivity extends BaseActivity {



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
    }
}
