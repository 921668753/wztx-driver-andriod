package com.ruitukeji.zwbs.mine.mywallet.recharge;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.EnvUtils;
import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.mine.mywallet.recharge.AlipayBean;
import com.ruitukeji.zwbs.entity.mine.mywallet.recharge.WxPayBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.PayUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 充值
 * Created by Administrator on 2017/2/17.
 */

public class RechargeActivity extends BaseActivity implements RechargeContract.View {
    

    /**
     * 输入充值金额
     */
    @BindView(id = R.id.et_topUpAmount)
    private EditText et_topUpAmount;
    
    /**
     * 微信支付
     */
    @BindView(id = R.id.img_weChatPay, click = true)
    private ImageView img_weChatPay;

    /**
     * 支付宝支付
     */
    @BindView(id = R.id.img_alipayPay, click = true)
    private ImageView img_alipayPay;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_prepaidImmediately, click = true)
    private TextView tv_prepaidImmediately;

    /**
     * 支付方式 1=支付宝，2=微信
     */
    private int type = 2;

    private PayUtils payUtils = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new RechargePresenter(this);
        payUtils = new PayUtils(this, PaySuccessActivity.class);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                showActivity(aty, RechargeRecordActivity.class);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.recharge), getString(R.string.rechargeRecord), R.id.titlebar, simpleDelegate);
        listenTopUpAmount();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_weChatPay:
                img_weChatPay.setImageResource(R.mipmap.ic_checkbox_select);
                img_alipayPay.setImageResource(R.mipmap.ic_checkbox_unselect);
                type = 2;
                break;
            case R.id.img_alipayPay:
                img_weChatPay.setImageResource(R.mipmap.ic_checkbox_unselect);
                img_alipayPay.setImageResource(R.mipmap.ic_checkbox_select);
                type = 1;
                break;
            case R.id.tv_prepaidImmediately:
                showLoadingDialog(getString(R.string.submissionLoad));
                PreferenceHelper.write(this, StringConstants.FILENAME, "rechargeMoney", et_topUpAmount.getText().toString());
                ((RechargeContract.Presenter) mPresenter).postRecharge(et_topUpAmount.getText().toString(), type);
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 1) {
            //支付宝
            AlipayBean alipayBean = (AlipayBean) JsonUtil.getInstance().json2Obj(s, AlipayBean.class);
            if (payUtils == null) {
                payUtils = new PayUtils(this, PaySuccessActivity.class);
            }
            if (alipayBean.getResult().getIsUseSandbox().equals("1")) {
                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
            }
            payUtils.doAlipay(alipayBean.getResult().getOrderString());
        } else if (flag == 2) {
            //微信
            WxPayBean wxPayBean = (WxPayBean) JsonUtil.getInstance().json2Obj(s, WxPayBean.class);
            if (payUtils == null) {
                payUtils = new PayUtils(this, PaySuccessActivity.class);
            }
            payUtils.doWxPay(wxPayBean.getResult().getAppid(), wxPayBean.getResult().getPartnerid(), wxPayBean.getResult().getPrepayid(), wxPayBean.getResult().getPackageX(), wxPayBean.getResult().getNoncestr(), wxPayBean.getResult().getTimestamp(), wxPayBean.getResult().getSign());
        }

        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            showActivity(aty, LoginActivity.class);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(RechargeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        payUtils = null;
    }

    /**
     * 监听输入充值金额
     */
    private void listenTopUpAmount() {

        et_topUpAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_topUpAmount.getText().toString().length() > 0) {
                    et_topUpAmount.setTextSize(50);
                    //   et_topUpAmount.getPaint().setFakeBoldText(true);
                    tv_prepaidImmediately.setClickable(true);
                    tv_prepaidImmediately.setBackgroundResource(R.drawable.shape_login);
                } else {
                    et_topUpAmount.setTextSize(26);
                    //    et_topUpAmount.getPaint().setFakeBoldText(false);
                    tv_prepaidImmediately.setClickable(false);
                    tv_prepaidImmediately.setBackgroundResource(R.drawable.shape_login1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
