package com.ruitukeji.zwbs.mission;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 异常上报
 * Created by Administrator on 2017/11/29.
 */

public class ExceptionReportingActivity extends BaseActivity {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_exceptionreporting);
    }

    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.exceptionReporting), true, R.id.titlebar);
    }


}
