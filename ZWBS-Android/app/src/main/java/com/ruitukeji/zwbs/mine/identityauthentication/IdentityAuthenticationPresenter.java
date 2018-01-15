package com.ruitukeji.zwbs.mine.identityauthentication;

import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.mine.identityauthentication.dialog.SubmitBouncedDialog;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.BitmapCoreUtil;
import com.ruitukeji.zwbs.utils.DataCleanManager;
import com.ruitukeji.zwbs.utils.DataUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/2/13.
 */

public class IdentityAuthenticationPresenter implements IdentityAuthenticationContract.Presenter {

    private IdentityAuthenticationContract.View mView;

    public IdentityAuthenticationPresenter(IdentityAuthenticationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void postUpLoadImg(String path, int flag) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad));
        if (StringUtils.isEmpty(path)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
            return;
        }
        File oldFile = new File(path);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), 0);
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
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postIdentityAuthentication(String real_name, String identity, long identity_pic_time, String front_pic, String back_pic, String hold_pic, long license_pic_time,
                                           String license_pic, long transport_pic_time, String transport_pic, String faith_pic) {

        if (StringUtils.isEmpty(real_name)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.realName), 0);
            return;
        }
        String all = "^[A-Za-z\\u4e00-\\u9fa5]{2,20}";//{2,10}表示字符的长度是2-10
        Pattern pattern = Pattern.compile(all);
        boolean tf = pattern.matches(all, real_name);
        if (!tf) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.hintName1), 0);
            return;
        }
        if (StringUtils.isEmpty(identity)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.IdNumber), 0);
            return;
        }
        if (identity.length() != 15 && identity.length() != 18) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.hintIDerrorText), 0);
            return;
        }

        if (identity_pic_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.validityIdentityCard), 0);
            return;
        }
        String dateStr = DataUtil.formatData(System.currentTimeMillis() / 1000, "yyyy-MM-dd");
        long dateLong = DataUtil.getStringToDate(dateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        if (identity_pic_time > dateLong) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.validityIdentityCard1), 0);
            return;
        }
//        if (StringUtils.isEmpty(front_pic)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadYourIdCard), 0);
//            return;
//        }
//        if (StringUtils.isEmpty(back_pic)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadClearYourIdCard), 0);
//            return;
//        }
//        if (StringUtils.isEmpty(hold_pic)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.uploudHoldingIdPhoto), 0);
//            return;
//        }
        if (license_pic_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.validityDrivingLicence), 0);
            return;
        }
        if (license_pic_time > dateLong) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.validityDrivingLicence1), 0);
            return;
        }
//        if (StringUtils.isEmpty(license_pic)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadDrivingLicensePhotos), 0);
//            return;
//        }
        if (transport_pic_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.roadTransportValidityPeriod), 0);
            return;
        }
        if (transport_pic_time > dateLong) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.roadTransportValidityPeriod1), 0);
            return;
        }
//        if (StringUtils.isEmpty(transport_pic)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadRoadQualification), 0);
//            return;
//        }
//        if (StringUtils.isEmpty(faith_pic)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadIntegrityAssessment), 0);
//            return;
//        }
        SubmitBouncedDialog submitBouncedDialog = new SubmitBouncedDialog(KJActivityStack.create().topActivity()) {
            @Override
            public void confirm() {
                this.cancel();
                mView.showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("real_name", real_name);
                map.put("identity", identity);
                map.put("identity_pic_time", identity_pic_time);
                map.put("hold_pic", "http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png");
                map.put("front_pic", "http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png");
                map.put("back_pic", "http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png");
                map.put("license_pic_time", license_pic_time);
                map.put("license_pic", "http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png");
                map.put("transport_pic_time", transport_pic_time);
                map.put("transport_pic", "http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png");
                map.put("faith_pic", "http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png");
                httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
                RequestClient.postDriverAuth(httpParams, new ResponseListener<String>() {
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
        };
        submitBouncedDialog.show();
    }


}
