package com.ruitukeji.zwbs.mine.mywallet.mybankcard;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.entity.mine.mywallet.mybankcard.BankBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.SoftKeyboardUtils;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 添加银行卡
 * Created by Administrator on 2017/11/30.
 */

public class AddBankCardActivity extends BaseActivity implements AddBankCardContract.View {

    /**
     * 持卡人
     */
    @BindView(id = R.id.et_cardholder)
    private EditText et_cardholder;

    /**
     * 银行卡号
     */
    @BindView(id = R.id.et_bankCardNumber)
    private EditText et_bankCardNumber;

    /**
     * 提现银行
     */
    @BindView(id = R.id.ll_withdrawalsBank, click = true)
    private LinearLayout ll_withdrawalsBank;
    @BindView(id = R.id.tv_withdrawalsBank)
    private TextView tv_withdrawalsBank;

    /**
     * 开户行
     */
    @BindView(id = R.id.et_openingBank)
    private EditText et_openingBank;

    /**
     * 手机号
     */
    @BindView(id = R.id.et_phone)
    private EditText et_phone;

    /**
     * 倒计时内部类
     */
    private TimeCount time;

    /**
     * t验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String type = "reg";

    /**
     * 验证码
     */
    @BindView(id = R.id.et_verificationCode)
    private EditText et_verificationCode;
    @BindView(id = R.id.tv_verificationCode, click = true)
    private TextView tv_verificationCode;

    /**
     * 确认添加
     */
    @BindView(id = R.id.tv_prepaidImmediately, click = true)
    private TextView tv_prepaidImmediately;

    private OptionsPickerView pvOptions;
    private List<BankBean.ResultBean> bankList;

    private int bank_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addbankcard);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddBankCardPresenter(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        selectBankName();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AddBankCardContract.Presenter) mPresenter).getBank();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addBankCard), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_withdrawalsBank:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_withdrawalsBank);
                break;
            case R.id.tv_verificationCode:
                showLoadingDialog(getString(R.string.sendingLoad));
                ((AddBankCardContract.Presenter) mPresenter).postCode(et_phone.getText().toString(), type);
                break;
            case R.id.tv_prepaidImmediately:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((AddBankCardContract.Presenter) mPresenter).postAddBankCard(et_cardholder.getText().toString().trim(), et_bankCardNumber.getText().toString().trim(),
                        bank_id , et_openingBank.getText().toString().trim(), et_phone.getText().toString().trim(),
                        et_verificationCode.getText().toString().trim());
                break;
        }
    }


    /**
     * 选择银行名称
     */
    @SuppressWarnings("unchecked")
    private void selectBankName() {
        pvOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                bank_id = bankList.get(options1).getId();
                ((TextView) v).setText(bankList.get(options1).getBank());
            }
        }).build();
    }

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_verificationCode.setText(getString(R.string.revalidation));
            tv_verificationCode.setClickable(true);
            tv_verificationCode.setTextColor(getResources().getColor(R.color.announcementCloseColors));
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_verificationCode.setClickable(false);
            tv_verificationCode.setText(millisUntilFinished / 1000 + getString(R.string.toResend));
            tv_verificationCode.setTextColor(getResources().getColor(R.color.hintcolors));
        }
    }


    @Override
    public void setPresenter(AddBankCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            BankBean bankBean = (BankBean) JsonUtil.json2Obj(success, BankBean.class);
            bankList = bankBean.getResult();
            if (bankList != null && bankList.size() > 0) {
                pvOptions.setPicker(bankList);
            }
        } else if (flag == 2) {
            RxBus.getInstance().post(new MsgEvent<String>("RxBusAddBankCardEvent"));
            finish();
            return;
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
