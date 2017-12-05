package com.ruitukeji.zwbs.mine.vehiclecertification;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 车辆认证
 * Created by Administrator on 2017/11/30.
 */

public class VehicleCertificationActivity extends BaseActivity{


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehiclecertification);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.vehicleCertification), true, R.id.titlebar);
    }




}
