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
import com.ruitukeji.zwbs.mine.recommendcourteous.RecommendedRecordActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 提现
 * Created by Administrator on 2017/2/17.
 */

public class WithdrawalActivity extends BaseActivity
        //   implements WithdrawalContract.View
{

//    @BindView(id = R.id.et_withdrawalAmount1)
//    private EditText et_withdrawalAmount1;
//
//    @BindView(id = R.id.et_bankName)
//    private EditText et_bankName;
//
//    @BindView(id = R.id.et_paymentAccount)
//    private EditText et_paymentAccount;
//
//    @BindView(id = R.id.et_openAccountName)
//    private EditText et_openAccountName;
//
//    @BindView(id = R.id.tv_withdrawalInstructions, click = true)
//    private TextView tv_withdrawalInstructions;
//
//    @BindView(id = R.id.tv_hintWithdrawal1)
//    private TextView tv_hintWithdrawal1;
//
//    @BindView(id = R.id.tv_confirmSubmit, click = true)
//    private TextView tv_confirmSubmit;
//
//
//    private SweetAlertDialog sweetAlertDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawal);
    }

    @Override
    public void initData() {
        super.initData();
        //  mPresenter = new WithdrawalPresenter(this);
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
                showActivity(aty, RecommendedRecordActivity.class);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdrawal), getString(R.string.withdrawalRecord), R.id.titlebar, simpleDelegate);
        String withdraw_begintime = PreferenceHelper.readString(this, StringConstants.FILENAME, "withdraw_begintime");
        String withdraw_endtime = PreferenceHelper.readString(this, StringConstants.FILENAME, "withdraw_endtime");
        //  tv_hintWithdrawal1.setText("提现日期每月" + withdraw_begintime + "号—" + withdraw_endtime + "号");
    }


//    @Override
//    public void widgetClick(View v) {
//        super.widgetClick(v);
//        switch (v.getId()) {
//
//            case R.id.tv_withdrawalInstructions:
//                Intent intent = new Intent(aty, AboutUsActivity.class);
//                intent.putExtra("type", "driver_withdrawa_description");
//                showActivity(aty, intent);
//                break;
//
//            case R.id.tv_confirmSubmit:
//                if (sweetAlertDialog == null) {
//                    initDialog();
//                }
//                ((WithdrawalContract.Presenter) mPresenter).postWithdrawal(sweetAlertDialog, et_withdrawalAmount1.getText().toString(), et_bankName.getText().toString(), et_paymentAccount.getText().toString(), et_openAccountName.getText().toString());
//                break;
//        }
//    }
//
//    @Override
//    public void getSuccess(String s) {
//        dismissLoadingDialog();
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
//    }
//
//    @Override
//    public void error(String msg) {
//        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
//            dismissLoadingDialog();
//            showActivity(aty, LoginActivity.class);
//            return;
//        }
//        dismissLoadingDialog();
//        ViewInject.toast(msg);
//    }
//
//    @Override
//    public void setPresenter(WithdrawalContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
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
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        sweetAlertDialog = null;
//    }
}
