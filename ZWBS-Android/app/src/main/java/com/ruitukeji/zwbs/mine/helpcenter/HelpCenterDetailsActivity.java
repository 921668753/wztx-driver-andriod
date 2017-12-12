package com.ruitukeji.zwbs.mine.helpcenter;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 帮助中心详情
 * Created by Administrator on 2017/12/12.
 */

public class HelpCenterDetailsActivity extends BaseActivity {



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_helpcenterdetails);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.helpCenter), true, R.id.titlebar);
    }
}
