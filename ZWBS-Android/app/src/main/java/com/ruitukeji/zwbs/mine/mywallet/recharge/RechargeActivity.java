package com.ruitukeji.zwbs.mine.mywallet.recharge;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

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
     * 输入充值金额
     */
    @BindView(id = R.id.tv_prepaidImmediately, click = true)
    private TextView tv_prepaidImmediately;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recharge);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.recharge), true, R.id.titlebar);
        listenTopUpAmount();
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
                    et_topUpAmount.getPaint().setFakeBoldText(true);
                    et_topUpAmount.setTop(0);
                    et_topUpAmount.setBottom(0);
                    tv_prepaidImmediately.setClickable(true);
                    tv_prepaidImmediately.setBackgroundResource(R.drawable.shape_sendquotation);
                } else {
                    et_topUpAmount.setTop(10);
                    et_topUpAmount.setBottom(10);
                    et_topUpAmount.setTextSize(26);
                    et_topUpAmount.getPaint().setFakeBoldText(false);
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
