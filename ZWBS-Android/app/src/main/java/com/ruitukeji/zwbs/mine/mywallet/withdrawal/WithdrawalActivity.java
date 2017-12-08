package com.ruitukeji.zwbs.mine.mywallet.withdrawal;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.aboutus.AboutUsActivity;
import com.ruitukeji.zwbs.mine.mywallet.mybankcard.MyBankCardActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;


/**
 * 提现
 * Created by Administrator on 2017/2/17.
 */

public class WithdrawalActivity extends BaseActivity implements WithdrawalContract.View {

    /**
     * 提险金额
     */
    @BindView(id = R.id.et_withdrawalAmount1)
    private EditText et_withdrawalAmount1;

    @BindView(id = R.id.tv_money)
    private TextView tv_money;

    /**
     * 全部提现
     */
    @BindView(id = R.id.tv_allWithdrawal, click = true)
    private TextView tv_allWithdrawal;

    /**
     * 中国银行（尾号3215）
     */
    @BindView(id = R.id.tv_withdrawalBank)
    private TextView tv_withdrawalBank;

    /**
     * 选择银行卡
     */
    @BindView(id = R.id.tv_modification, click = true)
    private TextView tv_modification;

    /**
     * 协议
     */
    @BindView(id = R.id.tv_driverWithdrawAgreement)
    private TextView tv_driverWithdrawAgreement;


    /**
     * 确定
     */
    @BindView(id = R.id.tv_confirmSubmit, click = true)
    private TextView tv_confirmSubmit;
    private String bankCardName = "";
    private String bankCardNun = "";
    private String bankCardId = "";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawal);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new WithdrawalPresenter(this);
        // initDialog();
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
                showActivity(aty, WithdrawalRecordActivity.class);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdrawal), getString(R.string.withdrawalRecord), R.id.titlebar, simpleDelegate);
        String withdraw_begintime = PreferenceHelper.readString(this, StringConstants.FILENAME, "withdraw_begintime");
        String withdraw_endtime = PreferenceHelper.readString(this, StringConstants.FILENAME, "withdraw_endtime");
        //  tv_hintWithdrawal1.setText("提现日期每月" + withdraw_begintime + "号—" + withdraw_endtime + "号");
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_allWithdrawal:
                et_withdrawalAmount1.setText(tv_money.getText().toString());
                et_withdrawalAmount1.setSelection(et_withdrawalAmount1.getText().toString().trim().length());
                et_withdrawalAmount1.requestFocus();
                break;
            case R.id.tv_modification:
                ((WithdrawalContract.Presenter) mPresenter).isLogin(1);
                break;
            case R.id.tv_driverWithdrawAgreement:
                Intent intentDriver = new Intent(aty, AboutUsActivity.class);
                intentDriver.putExtra("type", "driver_withdrawa_description");
                showActivity(aty, intentDriver);
                break;
            case R.id.tv_confirmSubmit:
                //    ((WithdrawalContract.Presenter) mPresenter).postWithdrawal(sweetAlertDialog, et_withdrawalAmount1.getText().toString(), et_bankName.getText().toString(), et_paymentAccount.getText().toString(), et_openAccountName.getText().toString());
                break;
        }
    }


    @Override
    public void setPresenter(WithdrawalContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 1) {
            Intent intent = new Intent(aty, MyBankCardActivity.class);
            intent.putExtra("bankCardName", bankCardName);
            intent.putExtra("bankCardNun", bankCardNun);
            intent.putExtra("bankCardId", bankCardId);
            intent.putExtra("type", 1);
            startActivityForResult(intent, 1);
        }
        dismissLoadingDialog();
//        if (sweetAlertDialog == null) {
//            initDialog();
//        }
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMyWalletActivity", true);
//        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//        sweetAlertDialog.setTitleText(getString(R.string.confirmSubmit2))
//                .setConfirmText(getString(R.string.confirm))
//                .showCancelButton(false)
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismiss();
//                        sDialog = null;
//                        aty.finish();
//                    }
//                }).show();
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

    //    /**
//     * 弹框设置
//     */
//    private void initDialog() {
//        sweetAlertDialog = null;
//        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
//        sweetAlertDialog.setCancelable(false);
//        sweetAlertDialog.setTitleText(getString(R.string.confirmSubmit1))
//                .setCancelText(getString(R.string.cancel))
//                .setConfirmText(getString(R.string.confirm))
//                .showCancelButton(true)
//                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        sweetAlertDialog.dismiss();
//                    }
//                });
//    }
//


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            bankCardName = data.getStringExtra("bankCardName");
            bankCardNun = data.getStringExtra("bankCardNun");
            bankCardId = data.getStringExtra("bankCardId");
            tv_withdrawalBank.setText(bankCardName + "(" + getString(R.string.tail) + bankCardNun + ")");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // sweetAlertDialog = null;
    }
}
