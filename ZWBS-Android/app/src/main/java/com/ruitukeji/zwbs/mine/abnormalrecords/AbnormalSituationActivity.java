package com.ruitukeji.zwbs.mine.abnormalrecords;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 异常情况
 * Created by Administrator on 2017/12/1.
 */

public class AbnormalSituationActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_abnormalsituation);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        //  RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.abnormalSituation), true, R.id.titlebar);
    }
}