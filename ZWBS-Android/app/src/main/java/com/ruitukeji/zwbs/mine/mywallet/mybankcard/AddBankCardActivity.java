package com.ruitukeji.zwbs.mine.mywallet.mybankcard;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 添加银行卡
 * Created by Administrator on 2017/11/30.
 */

public class AddBankCardActivity extends BaseActivity {
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addbankcard);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addBankCard), true, R.id.titlebar);
    }
}
