package com.ruitukeji.zwbs.mine.mywallet.recharge;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 充值
 * Created by Administrator on 2017/11/30.
 */

public class RechargeActivity extends BaseActivity {

    /**
     * 输入充值金额
     */
    @BindView(id = R.id.et_topUpAmount)
    private EditText et_topUpAmount;

    /**
     * 微信支付
     */
    @BindView(id = R.id.img_weChatPay)
    private ImageView img_weChatPay;

    /**
     * 支付宝支付
     */
    @BindView(id = R.id.img_alipayPay)
    private ImageView img_alipayPay;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_prepaidImmediately, click = true)
    private TextView tv_prepaidImmediately;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recharge);
    }


    @Override
    public void initData() {
        super.initData();
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
                break;
            case R.id.img_alipayPay:
                img_weChatPay.setImageResource(R.mipmap.ic_checkbox_unselect);
                img_alipayPay.setImageResource(R.mipmap.ic_checkbox_select);
                break;
            case R.id.tv_prepaidImmediately:

                break;
            default:
                break;
        }
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
                    tv_prepaidImmediately.setBackgroundResource(R.drawable.shape_sendquotation);
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
