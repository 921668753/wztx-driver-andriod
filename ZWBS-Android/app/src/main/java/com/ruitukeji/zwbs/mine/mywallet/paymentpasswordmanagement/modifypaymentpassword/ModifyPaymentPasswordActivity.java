package com.ruitukeji.zwbs.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword;

import android.view.View;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 修改支付密码
 * Created by Administrator on 2017/12/13.
 */

public class ModifyPaymentPasswordActivity extends BaseActivity {


    /**
     * 账号
     */
    @BindView(id = R.id.tv_accountNumber)
    private TextView tv_accountNumber;

    /**
     * 不记得
     */
    @BindView(id = R.id.tv_doNotRemember, click = true)
    private TextView tv_doNotRemember;

    /**
     * 记得
     */
    @BindView(id = R.id.tv_remember, click = true)
    private TextView tv_remember;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_modifypaymentpassword);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.modifyPaymentPassword), true, R.id.titlebar);
        String phone = PreferenceHelper.readString(aty, StringConstants.FILENAME, "phone", "");
        tv_accountNumber.setText(phone);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_doNotRemember:
                showActivity(aty, ModifyPaymentPassword1Activity.class);
                break;
            case R.id.tv_remember:
                showActivity(aty, ModifyPaymentPassword3Activity.class);
                break;
        }
    }
}
