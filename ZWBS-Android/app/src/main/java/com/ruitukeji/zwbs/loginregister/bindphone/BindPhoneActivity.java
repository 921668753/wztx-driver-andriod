package com.ruitukeji.zwbs.loginregister.bindphone;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.loginregister.LoginBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;
import com.umeng.analytics.MobclickAgent;

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
     * 手机号
     */
    @BindView(id = R.id.et_phone)
    private EditText et_phone;

    @BindView(id = R.id.img_deletePhone, click = true)
    private ImageView img_deletePhone;

    /**
     * 验证码
     */
    @BindView(id = R.id.et_code)
    private EditText et_code;

    @BindView(id = R.id.img_deleteCode, click = true)
    private ImageView img_deleteCode;

    /**
     * 获取验证码
     */
    @BindView(id = R.id.tv_code, click = true)
    private TextView tv_code;

    /**
     * 确定
     */
    @BindView(id = R.id.tv_determine, click = true)
    private TextView tv_determine;

    /**
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String type = "bind";

    private String openid;
    private String from;
    private String nickname;
    private String head_pic;
    private int sex = 0;
    private long millisUntilFinished = 0;


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
        openid = getIntent().getStringExtra("openid");
        from = getIntent().getStringExtra("from");
        nickname = getIntent().getStringExtra("nickname");
        head_pic = getIntent().getStringExtra("head_pic");
        sex = getIntent().getIntExtra("sex", 0);
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
        changeInputView(et_phone, img_deletePhone);
        changeInputView(et_code, img_deleteCode);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        tv_code.setClickable(false);
        tv_determine.setClickable(false);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.bindPhone), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_deletePhone:
                et_phone.setText("");
                break;
            case R.id.tv_code:
                showLoadingDialog(MyApplication.getContext().getString(R.string.sendingLoad));
                ((BindPhoneContract.Presenter) mPresenter).postCode(et_phone.getText().toString(), type);
                break;
            case R.id.img_deleteCode:
                et_code.setText("");
                break;
            case R.id.tv_determine:
                tv_determine.setClickable(false);
                showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                ((BindPhoneContract.Presenter) mPresenter).postThirdLoginAdd(openid, from, nickname, head_pic, sex, et_code.getText().toString(), et_phone.getText().toString(), "");
                break;
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
            tv_code.setText(getString(R.string.revalidation));
            millisUntilFinished = 0;
            tv_code.setClickable(true);
            tv_code.setBackgroundResource(R.drawable.shape_login);
        }

        @Override
        public void onTick(long millisUntilFinished1) {// 计时过程显示
            millisUntilFinished = millisUntilFinished1;
            tv_code.setClickable(false);
            tv_code.setText(millisUntilFinished1 / 1000 + getString(R.string.toResend));
            tv_code.setBackgroundResource(R.drawable.shape_login1);
        }
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
                    if (editText.getId() == R.id.et_phone && millisUntilFinished == 0 && et_phone.getText().toString().trim().length() == 11) {
                        tv_code.setBackgroundResource(R.drawable.shape_login);
                        tv_code.setClickable(true);
                    }
                    if (et_phone.getText().toString().trim().length() == 11 && et_code.getText().length() >= 4) {
                        tv_determine.setClickable(true);
                        tv_determine.setBackgroundResource(R.drawable.shape_login);
                    } else {
                        tv_determine.setClickable(false);
                        tv_determine.setBackgroundResource(R.drawable.shape_login1);
                    }
                } else {
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }
                    if (editText.getId() == R.id.et_phone) {
                        tv_code.setClickable(false);
                        tv_code.setBackgroundResource(R.drawable.shape_login1);
                    }
                    tv_determine.setClickable(false);
                    tv_determine.setBackgroundResource(R.drawable.shape_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            dismissLoadingDialog();
            //    CodeBean bean = (CodeBean) JsonUtil.getInstance().json2Obj(s, CodeBean.class);
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            time.cancel();
            time = null;
            ViewInject.toast(getString(R.string.bindPhoneSuccessfully));
            Log.d("tag", s);
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
            PreferenceHelper.write(this, StringConstants.FILENAME, "accessToken", bean.getResult().getAccessToken());
            PreferenceHelper.write(this, StringConstants.FILENAME, "expireTime", bean.getResult().getExpireTime());
            PreferenceHelper.write(this, StringConstants.FILENAME, "refreshToken", bean.getResult().getRefreshToken());
            PreferenceHelper.write(this, StringConstants.FILENAME, "userId", bean.getResult().getUserId());
            PreferenceHelper.write(this, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
            /**
             * 发送消息
             */
            KJActivityStack.create().finishActivity(LoginActivity.class);
            RxBus.getInstance().post(new MsgEvent<String>("RxBusLoginEvent"));
            MobclickAgent.onProfileSignIn(openid);
            if (type != null && type.equals("personalCenter")) {
                PreferenceHelper.write(this, StringConstants.FILENAME, "isAvatar", false);
            } else {
                PreferenceHelper.write(this, StringConstants.FILENAME, "isAvatar", true);
            }
            dismissLoadingDialog();
            finish();
        }
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
        tv_determine.setClickable(true);
    }

    @Override
    public void setPresenter(BindPhoneContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
