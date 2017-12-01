package com.ruitukeji.zwbs.loginregister;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.LoginBean;
import com.ruitukeji.zwbs.loginregister.registerretrievepassword.RegisterActivity;
import com.ruitukeji.zwbs.loginregister.registerretrievepassword.RetrievePasswordActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.umeng.analytics.MobclickAgent;


import cn.bingoogolapple.titlebar.BGATitleBar;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

/**
 * 登录
 * Created by ruitu ck on 2016/9/14.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    /**
     * 账号
     */
    @BindView(id = R.id.et_accountNumber)
    private EditText et_accountNumber;
    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;
    /**
     * 忘记密码
     */
    @BindView(id = R.id.tv_forgotPassword, click = true)
    private TextView tv_forgotPassword;
    /**
     * 登录
     */
    @BindView(id = R.id.tv_login, click = true)
    private TextView tv_login;
    /**
     * 注册
     */
    @BindView(id = R.id.tv_register, click = true)
    private TextView tv_register;
    /**
     * 取消
     */
    @BindView(id = R.id.img_quxiao, click = true)
    private ImageView img_quxiao;
    @BindView(id = R.id.img_quxiao1, click = true)
    private ImageView img_quxiao1;

    /**
     * 密码显示
     */
    @BindView(id = R.id.img_biyan, click = true)
    private ImageView img_biyan;

//    private String name = null;
//    private String refreshName = null;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_login);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new LoginPresenter(this);
//        name = getIntent().getStringExtra("name");
//        refreshName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "refreshName");
    }

    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        //   tv_login.setFocusable(false);
        tv_login.setClickable(false);//不可点击
        changeInputView(et_accountNumber, img_quxiao);
        changeInputView(et_pwd, img_quxiao1);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.login), true, R.id.titlebar);
    }

    /**
     * view监听事件
     *
     * @param v
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.tv_login:
                showLoadingDialog(MyApplication.getContext().getString(R.string.loggingLoad));
                ((LoginContract.Presenter) mPresenter).postToLogin(et_accountNumber.getText().toString(), et_pwd.getText().toString());
                break;
            case R.id.img_quxiao:
                et_accountNumber.setText("");
                break;
            case R.id.img_quxiao1:
                et_pwd.setText("");
                break;
            case R.id.img_biyan:
                if (et_pwd.getInputType() == 0x00000081) {
                    img_biyan.setImageResource(R.mipmap.ic_zhengkai);
                    et_pwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    img_biyan.setImageResource(R.mipmap.ic_biyan);
                    et_pwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.tv_forgotPassword:
                Intent intent = new Intent();
                intent.setClass(aty, RetrievePasswordActivity.class);
                intent.putExtra("title", getString(R.string.retrievePassword));
                showActivity(aty, intent);
                break;
            case R.id.tv_register:
                showActivity(aty, RegisterActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void getSuccess(String s) {
        LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
//        if (refreshName != null && refreshName.equals("GetOrderFragment")) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", true);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo1", true);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", false);
//        } else if (refreshName != null && refreshName.equals("MineFragment")) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", true);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", true);
//        } else if (refreshName != null && refreshName.equals("SupplyGoodsFragment")) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods1", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", true);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo1", true);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", false);
//        }
        PreferenceHelper.write(this, StringConstants.FILENAME, "accessToken", bean.getResult().getAccessToken());
        PreferenceHelper.write(this, StringConstants.FILENAME, "expireTime", bean.getResult().getExpireTime() + "");
        PreferenceHelper.write(this, StringConstants.FILENAME, "refreshToken", bean.getResult().getRefreshToken());
        PreferenceHelper.write(this, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
        MobclickAgent.onProfileSignIn(et_accountNumber.getText().toString());
        dismissLoadingDialog();
        finish();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 监听EditText输入改变
     */
    public void changeInputView(EditText editText, View view) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().toString().length() > 0) {
                    if (view != null) {
                        view.setVisibility(View.VISIBLE);
                    }
                    if (et_accountNumber.getText().length() > 0 && et_pwd.getText().length() > 0) {
                        tv_login.setClickable(true);
                        tv_login.setBackgroundResource(R.drawable.shape_login);
                        tv_login.setTextColor(getResources().getColor(R.color.mainColor));
                    } else {
                        tv_login.setClickable(false);
                        tv_login.setBackgroundResource(R.drawable.shape_login1);
                        tv_login.setTextColor(getResources().getColor(R.color.mainColor));
                    }
                } else {
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }
                    tv_login.setClickable(false);
                    tv_login.setBackgroundResource(R.drawable.shape_login1);
                    tv_login.setTextColor(getResources().getColor(R.color.mainColor));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
