package com.ruitukeji.zwbs.mine.mywallet;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.MyWalletBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * 我的钱包
 * Created by Administrator on 2017/2/10.
 */

public class MyWalletActivity extends BaseActivity implements MyWalletContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_withdrawalAmount, click = true)
    private LinearLayout ll_withdrawalAmount;

    @BindView(id = R.id.ll_withdrawalRecord, click = true)
    private LinearLayout ll_withdrawalRecord;
    /**
     * 未结算单量
     */
    @BindView(id = R.id.tv_outstandingSingular)
    private TextView tv_outstandingSingular;

    @BindView(id = R.id.tv_outstandingAmount)
    private TextView tv_outstandingAmount;

    @BindView(id = R.id.tv_accountBalance)
    private TextView tv_accountBalance;

    @BindView(id = R.id.tv_withdrawalAmount)
    private TextView tv_withdrawalAmount;

    @BindView(id = R.id.tv_myReferralBonuses)
    private TextView tv_myReferralBonuses;

    @BindView(id = R.id.tv_lastMonthCumulativeSingular)
    private TextView tv_lastMonthCumulativeSingular;

    @BindView(id = R.id.tv_aggregateAmountOfLastMonth)
    private TextView tv_aggregateAmountOfLastMonth;

    @BindView(id = R.id.tv_thisMonthAccumulativeSingular)
    private TextView tv_thisMonthAccumulativeSingular;

    @BindView(id = R.id.tv_cumulativeAmountThisMonth)
    private TextView tv_cumulativeAmountThisMonth;

    @BindView(id = R.id.tv_inAggregateAmount)
    private TextView tv_inAggregateAmount;

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
        BGATitleBar.SimpleDelegate simpleDelegate = new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                finish();
            }

            @Override
            public void onClickRightCtv() {
                showActivity(aty, BillActivity.class);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myWallet), getString(R.string.bill), R.id.titlebar, simpleDelegate);
    }

    @Override
    public void getSuccess(String s) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMyWalletActivity", false);
        MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(s, MyWalletBean.class);
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
        if (StringUtils.isEmpty(myWalletBean.getResult().getBonus())) {
            tv_myReferralBonuses.setText("0.00");
        } else {
            tv_myReferralBonuses.setText(myWalletBean.getResult().getBonus());
        }
//        if (StringUtils.isEmpty(myWalletBean.getResult().getUninvoicing_singular_total_money())) {
//            tv_outstandingSingular.setText("0");
//        } else {
        tv_outstandingSingular.setText(String.valueOf(myWalletBean.getResult().getUninvoicing_singular_total_order()));
        //     }
        if (StringUtils.isEmpty(myWalletBean.getResult().getUninvoicing_singular_total_money())) {
            tv_outstandingAmount.setText("0.00");
        } else {
            tv_outstandingAmount.setText(myWalletBean.getResult().getUninvoicing_singular_total_money());
        }

//        if (StringUtils.isEmpty(myWalletBean.getResult().getPre_month_total_money())) {
//            tv_thisMonthAccumulativeSingular.setText("0");
//        } else {
        tv_thisMonthAccumulativeSingular.setText(String.valueOf(myWalletBean.getResult().getCur_month_total_order()));
        //       }

//        if (StringUtils.isEmpty(myWalletBean.getResult().get())) {
//            tv_cumulativeAmountThisMonth.setText("0.00");
//        } else {
        tv_cumulativeAmountThisMonth.setText(myWalletBean.getResult().getMonth_total_money());
        //       }

        tv_lastMonthCumulativeSingular.setText(String.valueOf(myWalletBean.getResult().getPre_month_total_order()));

        if (StringUtils.isEmpty(myWalletBean.getResult().getPre_month_total_money())) {
            tv_aggregateAmountOfLastMonth.setText("0.00");
        } else {
            tv_aggregateAmountOfLastMonth.setText(myWalletBean.getResult().getPre_month_total_money());
        }
        if (StringUtils.isEmpty(myWalletBean.getResult().getYear_total_money())) {
            tv_inAggregateAmount.setText("0.00");
        } else {
            tv_inAggregateAmount.setText(myWalletBean.getResult().getYear_total_money());
        }
        dismissLoadingDialog();
    }


    @Override
    public void error(String msg) {
        toLigon(msg);
        dismissLoadingDialog();
        //  ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(MyWalletContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_withdrawalAmount:
                showActivity(aty, WithdrawalActivity.class);
                break;
            case R.id.ll_withdrawalRecord:
                showActivity(aty, WithdrawalRecordActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isRefreshMyWalletActivity = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshMyWalletActivity", false);
        if (isRefreshMyWalletActivity) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((MyWalletContract.Presenter) mPresenter).getMyWallet();
        }
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
