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

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/2/15.
 */
public class NewUserVehicleCertificationPresenter implements NewUserVehicleCertificationContract.Presenter {

    private NewUserVehicleCertificationContract.View mView;

    public NewUserVehicleCertificationPresenter(NewUserVehicleCertificationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void postVehicleCertification(SweetAlertDialog sweetAlertDialog, String vehicleLength, int car_style_length_id, String vehicleModel, int car_style_type_id, String load,
                                         String carrierProduct, String licensePlateNumber, String index_pic,
                                         long policy_startline, long policy_deadline, String policy_pic,
                                         long license_startline, long license_deadline, String vehicle_license_pic,
                                         long driving_startline, long driving_deadline, String driving_licence_pic,
                                         long operation_startline, long operation_deadline, String operation_pic) {
        if (StringUtils.isEmpty(vehicleLength)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.conductorModels));
            return;
        }
        if (StringUtils.isEmpty(vehicleModel)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.conductorModels));
            return;
        }
        if (StringUtils.isEmpty(load)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.load));
            return;
        }
        if (StringUtils.isEmpty(carrierProduct)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseFillOut) + MyApplication.getContext().getString(R.string.carrierProduct));
            return;
        }
        if (StringUtils.isEmpty(licensePlateNumber)) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseLicensePlateNumber));
            return;
        }
        if (StringUtils.isEmpty(index_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.pleasecarLicensePlateNumbe));
            return;
        }
//驾驶证
        if (driving_startline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.startingDatedriverLicense));
            return;
        }
        if (driving_deadline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.closingDatedriverLicense));
            return;
        }
        if (StringUtils.isEmpty(driving_licence_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.uploadDatedriverLicense));
            return;
        }
//行驶证
        if (license_startline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.startingDatedrivingLicense));
            return;
        }
        if (license_deadline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.registrationDeadline));
            return;
        }
        if (StringUtils.isEmpty(vehicle_license_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.uploadDeadline));
            return;
        }

        //保险单
        if (policy_startline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.policydeadline));
            return;
        }
        if (policy_deadline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.policydeadline1));
            return;
        }
        if (StringUtils.isEmpty(policy_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.uploadpolicy));
            return;
        }

        //营运证
        if (operation_startline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.startingDateOperationCertificate));
            return;
        }
        if (operation_deadline <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.pleaseSelect) + MyApplication.getContext().getString(R.string.closingDateOperationCertificate));
            return;
        }
        if (StringUtils.isEmpty(operation_pic)) {
            mView.error(MyApplication.getContext().getString(R.string.uploadDateOperationCertificate));
            return;
        }
        sweetAlertDialog.setTitleText(KJActivityStack.create().topActivity().getString(R.string.submittedInformation));
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                mView.showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("car_type", vehicleModel);
                map.put("car_style_type_id", car_style_type_id);
                map.put("car_length", vehicleLength);
                map.put("car_style_length_id", car_style_length_id);
                map.put("weight", load);
                map.put("volume", carrierProduct);
                map.put("card_number", licensePlateNumber);
                map.put("index_pic", index_pic);

                map.put("policy_startline", policy_startline);
                map.put("policy_deadline", policy_deadline);
                map.put("policy_pic", policy_pic);

                map.put("license_startline", license_startline);
                map.put("license_deadline", license_deadline);
                map.put("vehicle_license_pic", vehicle_license_pic);

                map.put("driving_startline", driving_startline);
                map.put("driving_deadline", driving_deadline);
                map.put("driving_licence_pic", driving_licence_pic);

                map.put("operation_startline", operation_startline);
                map.put("operation_deadline", operation_deadline);
                map.put("operation_pic", operation_pic);
                httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
                RequestClient.postVehicleCertification(httpParams, new ResponseListener<String>() {
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
        }).show();
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

    @Override
    public void getPersonalCertificate() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getAuthInfo(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, -1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.error(msg);
//            }
//        });
    }


}
