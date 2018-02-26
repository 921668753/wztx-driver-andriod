package com.ruitukeji.zwbs.mine.setting;

import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/11.
 */

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {

    private ChangePasswordContract.View mView;

    public ChangePasswordPresenter(ChangePasswordContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postChangePassword(String originalPassword, String newPassword, String newPassword1) {
        if (StringUtils.isEmpty(originalPassword)) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.pleaseCurrentPassword));
            return;
        }
        if (originalPassword.length() < 6 || originalPassword.length() > 20) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.pleaseCurrentPassword1));
            return;
        }
        if (StringUtils.isEmpty(newPassword)) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.pleaseNewPassword));
            return;
        }
        if (newPassword.length() < 6 || newPassword.length() > 20) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.newPassword1));
            return;
        }
        if (StringUtils.isEmpty(newPassword1)) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.pleasePasswordAgain));
            return;
        }
        if (newPassword1.length() < 6 || newPassword1.length() > 20) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.newPassword2));
            return;
        }
        if (!(newPassword.equals(newPassword1))) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.passwordsNotMatch));
            mView.clear(3);
            return;
        }
        if (originalPassword.equals(newPassword)) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.notRepeated));
            mView.clear(3);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("old_password", CipherUtils.md5("WUZAI" + originalPassword + "TIANXIA"));
        map.put("new_password", CipherUtils.md5("WUZAI" + newPassword + "TIANXIA"));
        map.put("repeat_password", CipherUtils.md5("WUZAI" + newPassword1 + "TIANXIA"));
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postChangePassword(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }
}
