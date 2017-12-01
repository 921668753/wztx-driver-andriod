package com.ruitukeji.zwbs.loginregister.bindphone;

import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ruitu on 2016/9/24.
 */
public class BindPhonePresenter implements BindPhoneContract.Presenter {

    private BindPhoneContract.View mView;

    public BindPhonePresenter(BindPhoneContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String type) {
        if (StringUtils.isEmpty(phone)) {
            mView.error(MyApplication.getContext().getString(R.string.hintAccountText));
            return;
        }
        if (phone.length() != 11) {
            mView.error(MyApplication.getContext().getString(R.string.inputPhone));
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
                mView.error(msg);
            }
        });
    }

    @Override
    public void postBindPhone(String phone, String code) {
        if (StringUtils.isEmpty(phone)) {
            mView.error(MyApplication.getContext().getString(R.string.hintAccountText));
            return;
        }
        if (phone.length() != 11) {
            mView.error(MyApplication.getContext().getString(R.string.inputPhone));
            return;
        }
        if (StringUtils.isEmpty(code)) {
            mView.error(MyApplication.getContext().getString(R.string.errorCode));
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", phone);
        map.put("captcha", code);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postResetpwd(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }

    @Override
    public void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //   Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("openid", openid);
        httpParams.put("from", from);
        httpParams.put("nickname", nickname);
        httpParams.put("head_pic", head_pic);
        httpParams.put("sex", sex);
        httpParams.put("push_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postThirdLogin(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 2);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
    }


}
