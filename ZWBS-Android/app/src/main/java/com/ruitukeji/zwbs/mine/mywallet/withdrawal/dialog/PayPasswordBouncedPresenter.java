package com.ruitukeji.zwbs.mine.mywallet.withdrawal.dialog;

import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.MathUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付密码弹框
 * Created by Administrator on 2017/2/21.
 */

public class PayPasswordBouncedPresenter implements PayPasswordBouncedContract.Presenter {

    private PayPasswordBouncedContract.View mView;

    public PayPasswordBouncedPresenter(PayPasswordBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postOldPayPassword(String oldPaymentPassword) {
        if (oldPaymentPassword.length() != 6) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseEnterPaymentPassword1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("old_password", CipherUtils.md5("WUZAI" + oldPaymentPassword + "TIANXIA"));
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.getCheckPayPassword(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


    @Override
    public void postWithdrawal(String withdrawalAmount, int bankId) {
        if (StringUtils.isEmpty(withdrawalAmount)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.notHigherWithdrawalLimit1), 0);
            return;
        }
        if (!(MathUtil.judgeTwoDecimal(withdrawalAmount))) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.notHigherWithdrawalLimit1), 0);
            return;
        }
        if (StringUtils.toDouble(withdrawalAmount) <= 0) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.notHigherWithdrawalLimit1), 0);
            return;
        }
        if (bankId < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.withdrawalsBank1), 0);
            return;
        }
//        SubmitBouncedDialog submitBouncedDialog = new SubmitBouncedDialog(KJActivityStack.create().topActivity()) {
//            @Override
//            public void confirm() {
//                this.cancel();
        //  mView.showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("withdrawal_amount", withdrawalAmount);
        map.put("bank_id", bankId);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postWithdrawal(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
//            }
//        };
//        submitBouncedDialog.show();
    }
}
