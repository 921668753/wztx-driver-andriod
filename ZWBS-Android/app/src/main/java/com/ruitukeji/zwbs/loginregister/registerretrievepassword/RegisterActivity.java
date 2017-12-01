package com.ruitukeji.zwbs.loginregister.registerretrievepassword;

import android.content.Intent;
import android.os.CountDownTimer;
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
import com.ruitukeji.zwbs.loginregister.NewUserInformationActivity;
import com.ruitukeji.zwbs.mine.aboutus.AboutUsActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.umeng.analytics.MobclickAgent;


/**
 * 注册
 * Created by Administrator on 2017/2/17.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    private String selectRegisterType;
    /**
     * 倒计时内部类
     */
    private TimeCount time;


    private boolean isClick = true;
    /**
     * 注册协议
     */
    @BindView(id = R.id.tv_agreement, click = true)
    private TextView tv_agreement;
    /**
     * 手机号
     */
    @BindView(id = R.id.et_phone)
    private EditText et_phone;
    /**
     * 验证码
     */
    @BindView(id = R.id.et_code)
    private EditText et_code;
    /**
     * 获取验证码
     */
    @BindView(id = R.id.tv_code, click = true)
    private TextView tv_code;
    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;
    /**
     * 密码
     */
    @BindView(id = R.id.et_referralCode)
    private EditText et_referralCode;

    /**
     * 注册
     */
    @BindView(id = R.id.tv_registe, click = true)
    private TextView tv_registe;

    /**
     * t验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String type = "reg";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new RegisterPresenter(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
    }

    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.newUserRegister), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_code:
                showLoadingDialog(MyApplication.getContext().getString(R.string.sendingLoad));
                ((RegisterContract.Presenter) mPresenter).postCode(et_phone.getText().toString(), type);
                break;
            case R.id.tv_registe:
                if (isClick) {
                    showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                    tv_registe.setEnabled(false);
                    ((RegisterContract.Presenter) mPresenter).postRegister(et_phone.getText().toString(), et_code.getText().toString(), et_pwd.getText().toString(), et_referralCode.getText().toString());
                } else {
                    ViewInject.toast(getString(R.string.agreement1));
                }
                break;
            case R.id.tv_agreement:
                // 注册协议
                Intent intent = new Intent(aty, AboutUsActivity.class);
                intent.putExtra("type", "driver_registration_protocol");
                showActivity(aty, intent);
                break;
//            case R.id.img_agreement:
//                if (isClick) {
//                    img_agreement.setImageResource(R.mipmap.agreement1);
//                    isClick = false;
//                } else {
//                    img_agreement.setImageResource(R.mipmap.agreement);
//                    isClick = true;
//                }
//                break;

            default:
                break;
        }


    }

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_code.setText("重新验证");
            tv_code.setClickable(true);
            tv_code.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_code.setBackgroundResource(R.drawable.shape_code);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_code.setClickable(false);
            tv_code.setText(millisUntilFinished / 1000 + "秒");
            tv_code.setTextColor(getResources().getColor(R.color.titledivider));
            tv_code.setBackgroundResource(R.drawable.shape_code1);
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            tv_registe.setEnabled(true);
            //    CodeBean bean = (CodeBean) JsonUtil.getInstance().json2Obj(s, CodeBean.class);
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            time.cancel();
            time = null;
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
            MobclickAgent.onProfileSignIn(et_phone.getText().toString());//账号统计
            String refreshName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "refreshName");
            if (refreshName != null && refreshName.equals("GetOrderFragment")) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo1", true);
            } else if (refreshName != null && refreshName.equals("MineFragment")) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", true);
            }
            PreferenceHelper.write(this, StringConstants.FILENAME, "accessToken", bean.getResult().getAccessToken());
            PreferenceHelper.write(this, StringConstants.FILENAME, "expireTime", bean.getResult().getExpireTime() + "");
            PreferenceHelper.write(this, StringConstants.FILENAME, "refreshToken", bean.getResult().getRefreshToken());
            PreferenceHelper.write(this, StringConstants.FILENAME, "userId", bean.getResult().getUserId());
            PreferenceHelper.write(this, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
            skipActivity(aty, NewUserInformationActivity.class);
        }
    }


    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
        tv_registe.setEnabled(true);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
