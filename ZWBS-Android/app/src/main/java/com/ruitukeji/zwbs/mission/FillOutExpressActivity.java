package com.ruitukeji.zwbs.mission;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 填写快递单
 * Created by Administrator on 2017/11/29.
 */

public class FillOutExpressActivity extends BaseActivity {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_filloutexpress);
    }

    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.fillOutExpress), true, R.id.titlebar);
    }
}
