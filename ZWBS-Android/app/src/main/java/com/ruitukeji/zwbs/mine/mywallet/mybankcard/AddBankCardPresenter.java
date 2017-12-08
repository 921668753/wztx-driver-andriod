package com.ruitukeji.zwbs.mine.mywallet.mybankcard;

import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/17.
 */

public class AddBankCardPresenter implements AddBankCardContract.Presenter {

    private AddBankCardContract.View mView;

    public AddBankCardPresenter(AddBankCardContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String type) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.hintAccountText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.inputPhone), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", phone);
        String codeI = String.valueOf(System.currentTimeMillis());
        String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
        map.put("codeId", codeId);
        String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
        String validationId = CipherUtils.md5(validationI);
        map.put("validationId", validationId);
        map.put("opt", type);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postCaptcha(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg,0);
            }
        });
    }

    @Override
    public void postAddBankCard(String cardholder, String bankCardNumber, String withdrawalsBank, String openingBank, String phone, String verificationCode) {
        if (StringUtils.isEmpty(cardholder)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.accountHolderName1), 0);
            return;
        }
        if (StringUtils.isEmpty(bankCardNumber)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.bankCardNumber1), 0);
            return;
        }
        if (StringUtils.isEmpty(withdrawalsBank)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.withdrawalsBank1), 0);
            return;
        }
        if (StringUtils.isEmpty(openingBank)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.openingBank1), 0);
            return;
        }
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.accountText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.inputPhone), 0);
            return;
        }
        if (StringUtils.isEmpty(verificationCode)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.errorCode), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("withdrawal_amount", phone);
        map.put("bank_name", phone);
        map.put("payment_account", phone);
        map.put("account_name", phone);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postWithdrawal(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}
