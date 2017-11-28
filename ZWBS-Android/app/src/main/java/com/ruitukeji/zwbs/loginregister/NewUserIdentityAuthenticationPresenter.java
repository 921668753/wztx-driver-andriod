package com.ruitukeji.zwbs.loginregister;

import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.BitmapCoreUtil;
import com.ruitukeji.zwbs.utils.DataCleanManager;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */

public class NewUserIdentityAuthenticationPresenter implements NewUserIdentityAuthenticationContract.Presenter {

    private NewUserIdentityAuthenticationContract.View mView;

    public NewUserIdentityAuthenticationPresenter(NewUserIdentityAuthenticationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postIdentityAuthentication(String name, int sex, String IdNumber, String address, int wltype, String hold_pic, String front_pic, String back_pic) {
        if (StringUtils.isEmpty(front_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.uploudFrontIdPhoto));
            return;
        }
        if (StringUtils.isEmpty(back_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.uploudBackIdPhoto));
            return;
        }
        mView.showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("real_name", name);
        map.put("sex", sex);
        map.put("identity", IdNumber);
        map.put("address", address);
        map.put("logistics_type", wltype);
        map.put("hold_pic", hold_pic);
        map.put("front_pic", front_pic);
        map.put("back_pic", back_pic);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postInformation(httpParams, new ResponseListener<String>() {
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
    public void upLoadImg(String path, int flag) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad));
        File oldFile = new File(path);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.imagePathError));
            return;
        }
        long fileSize = 0;
        try {
            fileSize = DataCleanManager.getFileSize(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileSize = 0;
        }
        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
            oldFile = BitmapCoreUtil.customCompression(oldFile);
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("file", oldFile);
        RequestClient.upLoadImg(httpParams, 1, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }
}
