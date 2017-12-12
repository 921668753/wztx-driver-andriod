package com.ruitukeji.zwbs.mine.helpcenter;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 帮助中心
 * Created by Administrator on 2017/12/12.
 */

public class HelpCenterActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_helpcenter);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.helpCenter), true, R.id.titlebar);
    }
}
