package com.ruitukeji.zwbs.mine.mywallet;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.entity.MyWalletBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.mywallet.incomedetails.IncomeDetailsActivity;
import com.ruitukeji.zwbs.mine.mywallet.mybankcard.MyBankCardActivity;
import com.ruitukeji.zwbs.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword.ModifyPaymentPasswordActivity;
import com.ruitukeji.zwbs.mine.mywallet.paymentpasswordmanagement.setpaymentpassword.SetPaymentPasswordActivity;
import com.ruitukeji.zwbs.mine.mywallet.recharge.RechargeActivity;
import com.ruitukeji.zwbs.mine.mywallet.withdrawal.WithdrawalActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

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

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mywallet);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyWalletPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyWalletContract.Presenter) mPresenter).getMyWallet();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myWallet), true, R.id.titlebar);
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
                showActivity(aty, WithdrawalActivity.class);
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
//        if (StringUtils.isEmpty(myWalletBean.getResult().getBonus())) {
//            tv_myReferralBonuses.setText("0.00");
//        } else {
//            tv_myReferralBonuses.setText(myWalletBean.getResult().getBonus());
//        }
////        if (StringUtils.isEmpty(myWalletBean.getResult().getUninvoicing_singular_total_money())) {
////            tv_outstandingSingular.setText("0");
////        } else {
//        tv_outstandingSingular.setText(String.valueOf(myWalletBean.getResult().getUninvoicing_singular_total_order()));
//        //     }
//        if (StringUtils.isEmpty(myWalletBean.getResult().getUninvoicing_singular_total_money())) {
//            tv_outstandingAmount.setText("0.00");
//        } else {
//            tv_outstandingAmount.setText(myWalletBean.getResult().getUninvoicing_singular_total_money());
//        }
//
////        if (StringUtils.isEmpty(myWalletBean.getResult().getPre_month_total_money())) {
////            tv_thisMonthAccumulativeSingular.setText("0");
////        } else {
//        tv_thisMonthAccumulativeSingular.setText(String.valueOf(myWalletBean.getResult().getCur_month_total_order()));
//        //       }
//
////        if (StringUtils.isEmpty(myWalletBean.getResult().get())) {
////            tv_cumulativeAmountThisMonth.setText("0.00");
////        } else {
//        tv_cumulativeAmountThisMonth.setText(myWalletBean.getResult().getMonth_total_money());
//        //       }
//
//        tv_lastMonthCumulativeSingular.setText(String.valueOf(myWalletBean.getResult().getPre_month_total_order()));
//
//        if (StringUtils.isEmpty(myWalletBean.getResult().getPre_month_total_money())) {
//            tv_aggregateAmountOfLastMonth.setText("0.00");
//        } else {
//            tv_aggregateAmountOfLastMonth.setText(myWalletBean.getResult().getPre_month_total_money());
//        }
//        if (StringUtils.isEmpty(myWalletBean.getResult().getYear_total_money())) {
//            tv_inAggregateAmount.setText("0.00");
//        } else {
//            tv_inAggregateAmount.setText(myWalletBean.getResult().getYear_total_money());
//        }
        } else if (flag == 1) {
            showActivity(aty, IncomeDetailsActivity.class);
        } else if (flag == 2) {
            showActivity(aty, MyBankCardActivity.class);
        } else if (flag == 3) {
            showActivity(aty, ModifyPaymentPasswordActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            showActivity(aty, LoginActivity.class);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
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

}
