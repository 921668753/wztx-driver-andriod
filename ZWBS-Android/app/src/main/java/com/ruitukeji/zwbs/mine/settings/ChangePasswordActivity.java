package com.ruitukeji.zwbs.mine.settings;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 修改密码
 * Created by Administrator on 2017/2/11.
 */

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordContract.View {


    @BindView(id = R.id.et_originalPassword)
    private EditText originalPassword;


    @BindView(id = R.id.et_newPassword)
    private EditText newPassword;

    @BindView(id = R.id.et_newPassword1)
    private EditText newPassword1;

    @BindView(id = R.id.tv_submit, click = true)
    private TextView submit;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_changepassword);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ChangePasswordPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.changePassword), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_submit:
                showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                ((ChangePasswordContract.Presenter) mPresenter).postChangePassword(originalPassword.getText().toString(), newPassword.getText().toString(), newPassword1.getText().toString());
                break;
        }
    }

    /**
     * 清空输入框
     */
    public void clearOriginalPassword() {
        originalPassword.setText("");
    }

    public void clearNewPassword() {
        newPassword.setText("");
    }

    public void clearNewPassword1() {
        newPassword1.setText("");
    }

    @Override
    public void getSuccess(String s) {
        KJActivityStack.create().finishActivity(SettingsActivity.class);
        dismissLoadingDialog();
        PreferenceHelper.clean(this, StringConstants.FILENAME);
        skipActivity(aty, LoginActivity.class);
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void clear(int flag) {
        if (flag == 0) {
            clearOriginalPassword();
        } else if (flag == 1) {
            clearNewPassword();
        } else if (flag == 2) {
            clearNewPassword1();
        } else if (flag == 3) {
            clearNewPassword();
            clearNewPassword1();
        }
    }

    @Override
    public void setPresenter(ChangePasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
