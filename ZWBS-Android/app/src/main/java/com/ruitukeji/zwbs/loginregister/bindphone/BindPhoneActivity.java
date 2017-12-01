package com.ruitukeji.zwbs.loginregister.bindphone;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.loginregister.registerretrievepassword.RegisterPresenter;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 绑定手机号
 * Created by ruitu ck on 2016/9/14.
 */

public class BindPhoneActivity extends BaseActivity implements BindPhoneContract.View {

    /**
     * 倒计时内部类
     */
    private TimeCount time;

    /**
     * 注册协议
     */
//    @BindView(id = R.id.tv_agreement, click = true)
//    private TextView tv_agreement;


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
     * 注册
     */
    @BindView(id = R.id.tv_registe, click = true)
    private TextView tv_registe;

    /**
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String type = "resetpwd";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bindphone);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new BindPhonePresenter(this);
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
        String title = getIntent().getStringExtra("title");
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
//        if (StringUtils.isEmpty(title)) {
//            type = 1;
//        } else if (title.equals(getString(R.string.retrievePassword))) {
//            tv_agreement.setVisibility(View.GONE);
        tv_registe.setText(getString(R.string.resetPassword));
//            type = 2;
//        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_code:
                showLoadingDialog(MyApplication.getContext().getString(R.string.sendingLoad));
                ((BindPhoneContract.Presenter) mPresenter).postCode(et_phone.getText().toString(), type);
                break;
            case R.id.tv_registe:
                tv_registe.setEnabled(false);
//                if (type == 1) {
//                    mPresenter.postRegister(et_phone.getText().toString(), et_code.getText().toString(), et_pwd.getText().toString());
//                } else if (type == 2) {
                showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                ((BindPhoneContract.Presenter) mPresenter).postResetpwd(et_phone.getText().toString(), et_code.getText().toString(), et_pwd.getText().toString());
                //   }
                break;
//            case R.id.tv_agreement:
//                // 注册协议
//                showActivity(aty, RegistrationAgreementActivity.class);
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
        }
//        else if (flag == 1) {
//            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
//            MobclickAgent.onProfileSignIn(et_phone.getText().toString());//账号统计
//            PreferenceHelper.write(this, StringConstants.FILENAME, "accessToken", bean.getResult().getAccessToken());
//            PreferenceHelper.write(this, StringConstants.FILENAME, "expireTime", bean.getResult().getExpireTime());
//            PreferenceHelper.write(this, StringConstants.FILENAME, "refreshToken", bean.getResult().getRefreshToken());
//            PreferenceHelper.write(this, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
//            finish();
//        }

        else if (flag == 2) {
            time.cancel();
            time = null;
            ViewInject.toast(getString(R.string.resetpwd));
            finish();
        }
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
        tv_registe.setEnabled(true);
    }

    @Override
    public void setPresenter(BindPhoneContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
