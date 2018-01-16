package com.ruitukeji.zwbs.mine.mywallet;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.mine.mywallet.MyWalletBean;
import com.ruitukeji.zwbs.mine.mywallet.incomedetails.IncomeDetailsActivity;
import com.ruitukeji.zwbs.mine.mywallet.mybankcard.MyBankCardActivity;
import com.ruitukeji.zwbs.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword.ModifyPaymentPasswordActivity;
import com.ruitukeji.zwbs.mine.mywallet.paymentpasswordmanagement.setpaymentpassword.SetPaymentPasswordActivity;
import com.ruitukeji.zwbs.mine.mywallet.recharge.RechargeActivity;
import com.ruitukeji.zwbs.mine.mywallet.withdrawal.WithdrawalActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的钱包
 * Created by Administrator on 2017/2/10.
 */

public class MyWalletActivity extends BaseActivity implements MyWalletContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 账户总额
     */
    @BindView(id = R.id.tv_accountBalance)
    private TextView tv_accountBalance;

    /**
     * 可提现金额
     */
    @BindView(id = R.id.tv_withdrawalAmount)
    private TextView tv_withdrawalAmount;


    /**
     * 收入明细
     */
    @BindView(id = R.id.ll_incomeDetails, click = true)
    private LinearLayout ll_incomeDetails;


    /**
     * 充值
     */
    @BindView(id = R.id.ll_recharge, click = true)
    private LinearLayout ll_recharge;

    /**
     * 提现
     */
    @BindView(id = R.id.ll_cashWithdrawal, click = true)
    private LinearLayout ll_cashWithdrawal;

    /**
     * 我的銀行卡
     */
    @BindView(id = R.id.ll_myBankCard, click = true)
    private LinearLayout ll_myBankCard;

    /**
     * 支付密码管理
     */
    @BindView(id = R.id.ll_paymentPassword, click = true)
    private LinearLayout ll_paymentPassword;

    private Handler handler = null;

    private String bankName = "";
    private String bankCard = "";
    private int id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mywallet);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyWalletPresenter(this);
        handler = new Handler();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myWallet), true, R.id.titlebar);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_incomeDetails:
                ((MyWalletContract.Presenter) mPresenter).isLogin(1);
                break;
            case R.id.ll_recharge:
                showActivity(aty, RechargeActivity.class);
                break;
            case R.id.ll_cashWithdrawal:
                Intent intent = new Intent(aty, WithdrawalActivity.class);
                intent.putExtra("bankCardName", bankName);
                intent.putExtra("bankCardNun", bankCard);
                intent.putExtra("bankCardId", id);
                showActivity(aty, intent);
                break;
            case R.id.ll_myBankCard:
                ((MyWalletContract.Presenter) mPresenter).isLogin(2);
                break;
            case R.id.ll_paymentPassword:
                ((MyWalletContract.Presenter) mPresenter).isLogin(3);
                break;
        }
    }

    @Override
    public void setPresenter(MyWalletContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {

            //  PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMyWalletActivity", false);
            MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(success, MyWalletBean.class);
            if (StringUtils.isEmpty(myWalletBean.getResult().getBalance())) {
                tv_accountBalance.setText("0.00");
            } else {
                tv_accountBalance.setText(myWalletBean.getResult().getBalance());
            }
            if (StringUtils.isEmpty(myWalletBean.getResult().getWithdrawal_money())) {
                tv_withdrawalAmount.setText("0.00" + getString(R.string.yuan));
            } else {
                tv_withdrawalAmount.setText(myWalletBean.getResult().getWithdrawal_money());
            }
            bankName = myWalletBean.getResult().getBankName();
            bankCard = myWalletBean.getResult().getBankCard();
            id = myWalletBean.getResult().getId();
        } else if (flag == 1) {
            showActivity(aty, IncomeDetailsActivity.class);
        } else if (flag == 2) {
            showActivity(aty, MyBankCardActivity.class);
        } else if (flag == 3) {
            int is_pay_password = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "is_pay_password", 0);
            if (is_pay_password == 1) {
                showActivity(aty, ModifyPaymentPasswordActivity.class);
            } else if (is_pay_password == 0) {
                showActivity(aty, SetPaymentPasswordActivity.class);
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (toLigon1(msg)) {
            return;
        }
        if (flag == 0) {
            finish();
            return;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
//        boolean isRefreshMyWalletActivity = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshMyWalletActivity", false);
//        if (isRefreshMyWalletActivity) {
//            showLoadingDialog(getString(R.string.dataLoad));
//            ((MyWalletContract.Presenter) mPresenter).getMyWallet();
//        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyWalletContract.Presenter) mPresenter).getMyWallet();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        mRefreshLayout.endLoadingMore();
        return false;
    }


    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusWithdrawalEvent")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.beginRefreshing();
                }
            }, 600);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
