package com.ruitukeji.zwbs.mine.vehiclecertification;

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

/**
 * Created by Administrator on 2017/2/13.
 */

public class VehicleCertificationPresenter implements VehicleCertificationContract.Presenter {

    private VehicleCertificationContract.View mView;

    public VehicleCertificationPresenter(VehicleCertificationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getVehicleBrand() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getVehicleBrand(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getCarAuthInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCarAuthInfo(httpParams, new ResponseListener<String>() {
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
    public void postVehicleCertification(String card_number, String car_type, int car_style_type_id, String car_length, int car_style_length_id, String car_band, long car_registered_time,
                                         String permanent_address, String car_front_pic, String car_bank_pic, String car_tail_pic, long license_time, String license_pic, long transport_pic_time,
                                         String transport_pic, long policy_time, String policy_pic, long insurance_time, String insurance_pic, int province1, int city1, int area1) {
        if (StringUtils.isEmpty(card_number.substring(1))) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.licensePlateNumber), 0);
            return;
        }
        if (StringUtils.isEmpty(car_type)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.IdNumber), 0);
            return;
        }
        if (car_style_type_id < 1 || car_style_length_id < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.conductormodels), 0);
            return;
        }
        if (StringUtils.isEmpty(car_band)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.vehicleBrand), 0);
            return;
        }
        if (car_registered_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.vehicleRegistrationDate), 0);
            return;
        }
        if (car_registered_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.vehicleRegistrationDate), 0);
            return;
        }
        if (car_registered_time > System.currentTimeMillis() / 1000) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.vehicleRegistrationDate1), 0);
            return;
        }
        if (StringUtils.isEmpty(permanent_address)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.residentAddress), 0);
            return;
        }
        if (permanent_address.contains("苏州") && car_band.contains("江淮")) {
            mView.errorMsg("暂不支持苏州地区江淮品牌车辆认证！如有疑问请联系客服！", 0);
            return;
        }
        if (StringUtils.isEmpty(car_front_pic)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadFaceLight), 0);
            return;
        }
        if (StringUtils.isEmpty(car_bank_pic)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadSiding), 0);
            return;
        }
        if (StringUtils.isEmpty(car_tail_pic)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadRearLight), 0);
            return;
        }
        String dateStr = DataUtil.formatData(System.currentTimeMillis() / 1000, "yyyy-MM-dd");
        long dateLong = DataUtil.getStringToDate(dateStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss") / 1000;
        if (license_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.validityLicense), 0);
            return;
        }
        if (license_time < dateLong) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.validityLicense1), 0);
            return;
        }
        if (StringUtils.isEmpty(license_pic)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadDrivingLicensePhotos1), 0);
            return;
        }
        if (transport_pic_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.roadTransportValidityPeriodDate), 0);
            return;
        }
        if (transport_pic_time < dateLong) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.roadTransportValidityPeriodDate1), 0);
            return;
        }
        if (StringUtils.isEmpty(transport_pic)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadRoadQualification1), 0);
            return;
        }
        if (policy_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.businessInsurancePolicy), 0);
            return;
        }
        if (policy_time < dateLong) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.businessInsurancePolicy1), 0);
            return;
        }
        if (StringUtils.isEmpty(policy_pic)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.uploadBusinessInsurancePolicyPhoto1), 0);
            return;
        }
        if (insurance_time < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.validityInsurance), 0);
            return;
        }
        if (insurance_time < dateLong) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.validityInsurance1), 0);
            return;
        }
        if (StringUtils.isEmpty(insurance_pic)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.handHandPolicyPhoto1), 0);
            return;
        }
        SubmitBouncedDialog submitBouncedDialog = new SubmitBouncedDialog(KJActivityStack.create().topActivity()) {
            @Override
            public void confirm() {
                this.cancel();
                mView.showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("card_number", card_number);
                map.put("car_type", car_type);
                map.put("car_style_type_id", car_style_type_id);
                map.put("car_length", car_length);
                map.put("car_style_length_id", car_style_length_id);
                map.put("car_band", car_band);
                map.put("car_registered_time", car_registered_time);
                map.put("car_front_pic", car_front_pic);
                map.put("car_bank_pic", car_bank_pic);
                map.put("car_tail_pic", car_tail_pic);
                map.put("license_time", license_time);
                map.put("license_pic", license_pic);
                map.put("transport_pic_time", transport_pic_time);
                map.put("transport_pic", transport_pic);
                map.put("policy_time", policy_time);
                map.put("policy_pic", policy_pic);
                map.put("insurance_time", insurance_time);
                map.put("insurance_pic", insurance_pic);
                map.put("permanent_address", permanent_address);
                map.put("province", province1);
                map.put("city", city1);
                map.put("area", area1);
                httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
                RequestClient.postVehicleCertification(httpParams, new ResponseListener<String>() {
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
        };
        submitBouncedDialog.show();
    }


    @Override
    public void postUpLoadImg(String path, int flag) {
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


}
