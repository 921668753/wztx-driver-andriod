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
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/15.
 */

public class NewUserInformationPresenter implements NewUserInformationContract.Presenter {

    private NewUserInformationContract.View mView;

    public NewUserInformationPresenter(NewUserInformationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void postInformation(String name, int sex, String IdNumber, String address, int wltype, String hold_pic) {
        if (StringUtils.isEmpty(name)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.realName));
            return;
        }

        String all = "^[A-Za-z\\u4e00-\\u9fa5]{2,10}";//{2,10}表示字符的长度是2-10
        Pattern pattern = Pattern.compile(all);
        boolean tf = Pattern.matches(all, name);
        if (!tf) {
            mView.error(MyApplication.getContext().getString(R.string.hintName1));
            return;
        }
        if (StringUtils.isEmpty(IdNumber)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.IdNumber));
            return;
        }
        if (IdNumber.length() != 15 && IdNumber.length() != 18) {
            mView.error(MyApplication.getContext().getString(R.string.hintIDerrorText));
            return;
        }
        if (StringUtils.isEmpty(address)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.permanentAddress));
            return;
        }
        if (StringUtils.isEmpty(hold_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.uploudHoldingIdPhoto));
            return;
        }
        mView.getSuccess("", 0);
    }

    @Override
    public void upLoadImg(String path) {
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
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }


    @Override
    public void getPersonalCertificate() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getAuthInfo(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 2);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.error(msg);
//            }
//        });
    }


}
