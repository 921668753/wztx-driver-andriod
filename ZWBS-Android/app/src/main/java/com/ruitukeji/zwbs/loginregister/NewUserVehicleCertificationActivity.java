package com.ruitukeji.zwbs.loginregister;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.CodeBean;
import com.ruitukeji.zwbs.entity.PersonalCertificateBean;
import com.ruitukeji.zwbs.entity.UploadImageBean;
import com.ruitukeji.zwbs.main.MainActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.DataUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 新用户注册----车辆认证
 * Created by Administrator on 2017/2/15.
 */

public class NewUserVehicleCertificationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, NewUserVehicleCertificationContract.View {

    @BindView(id = R.id.tv_conductorModels, click = true)
    private TextView tv_conductorModels;

    @BindView(id = R.id.et_load)
    private TextView et_load;

    @BindView(id = R.id.et_carrierProduct)
    private TextView et_carrierProduct;

    @BindView(id = R.id.et_licenseNumber)
    private TextView et_licenseNumber;


    @BindView(id = R.id.ll_startingDatedriverLicense, click = true)
    private LinearLayout ll_startingDatedriverLicense;

    @BindView(id = R.id.tv_startingDatedriverLicense)
    private TextView tv_startingDatedriverLicense;

    @BindView(id = R.id.ll_closingDatedriverLicense, click = true)
    private LinearLayout ll_closingDatedriverLicense;

    @BindView(id = R.id.tv_closingDatedriverLicense)
    private TextView tv_closingDatedriverLicense;

    @BindView(id = R.id.ll_startingDatedrivingLicense, click = true)
    private LinearLayout ll_startingDatedrivingLicense;

    @BindView(id = R.id.tv_startingDatedrivingLicense)
    private TextView tv_startingDatedrivingLicense;

    @BindView(id = R.id.ll_registrationDeadline, click = true)
    private LinearLayout ll_registrationDeadline;

    @BindView(id = R.id.tv_registrationDeadline)
    private TextView tv_registrationDeadline;

    @BindView(id = R.id.ll_policydeadline, click = true)
    private LinearLayout ll_policydeadline;

    @BindView(id = R.id.tv_policydeadline)
    private TextView tv_policydeadline;

    @BindView(id = R.id.ll_policydeadline1, click = true)
    private LinearLayout ll_policydeadline1;

    @BindView(id = R.id.tv_policydeadline1)
    private TextView tv_policydeadline1;

    @BindView(id = R.id.ll_startingDateOperationCertificate, click = true)
    private LinearLayout ll_startingDateOperationCertificate;

    @BindView(id = R.id.tv_startingDateOperationCertificate)
    private TextView tv_startingDateOperationCertificate;

    @BindView(id = R.id.ll_closingDateOperationCertificate, click = true)
    private LinearLayout ll_closingDateOperationCertificate;

    @BindView(id = R.id.tv_closingDateOperationCertificate)
    private TextView tv_closingDateOperationCertificate;

    @BindView(id = R.id.img_carLicensePlateNumber, click = true)
    private ImageView img_carLicensePlateNumber;

    @BindView(id = R.id.img_driverLicense, click = true)
    private ImageView img_driverLicense;

    @BindView(id = R.id.img_vehicleLicense, click = true)
    private ImageView img_vehicleLicense;

    @BindView(id = R.id.img_insurancePolicy, click = true)
    private ImageView img_insurancePolicy;

    @BindView(id = R.id.img_operationCertificate, click = true)
    private ImageView img_operationCertificate;

    private TimePickerView pvTime;

    /**
     * 下一步
     */
    @BindView(id = R.id.tv_nextType, click = true)
    private TextView tv_nextType;


    private NewUserVehicleCertificationContract.Presenter mPresenter;
    private boolean isPreviewCarLicensePlateNumber = false;
    private String imgUrlCarLicensePlateNumber = null;
    private boolean isPreviewDriverLicense = false;
    private String imgUrlDriverLicense = null;
    private boolean isPreviewVehicleLicense = false;
    private String imgUrlVehicleLicense = null;
    private boolean isPreviewInsurancePolicy = false;
    private String imgUrlInsurancePolicy = null;
    private boolean isPreviewOperationCertificate = false;
    private String imgUrlOperationCertificate = null;

    private TimePickerView pvTime1;
    private String vehicleModel;
    private String vehicleLength;
    private ImagePicker imagePicker;
    private int vehicleModelId = 0;
    private int vehicleLengthId = 0;


    private long driving_deadline = 0;
    private long license_deadline = 0;
    private long policy_deadline = 0;
    private long operation_deadline = 0;

    private long driving_startline = 0;
    private long license_startline = 0;
    private long policy_startline = 0;
    private long operation_startline = 0;

    private SweetAlertDialog sweetAlertDialog = null;
    private String auth_status = null;

    Calendar calendar = Calendar.getInstance();
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    Calendar calendar3 = Calendar.getInstance();
    Calendar calendar4 = Calendar.getInstance();
    Calendar calendar5 = Calendar.getInstance();
    Calendar calendar6 = Calendar.getInstance();
    Calendar calendar7 = Calendar.getInstance();


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newuservehiclecertification);
    }

    @Override
    public void initData() {
        super.initData();
        initDialog();
        mPresenter = new NewUserVehicleCertificationPresenter(this);
        images = new ArrayList<>();
        startDatePicker();
        asOfTheDatePicker();
        initImagePicker();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.newUserRegister), true, R.id.titlebar);
        tv_nextType.setText(getString(R.string.submitAudit));
        auth_status = getIntent().getStringExtra("auth_status");
        if (!(StringUtils.isEmpty(auth_status) || auth_status.equals("init"))) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((NewUserVehicleCertificationContract.Presenter) mPresenter).getPersonalCertificate();
        }
    }

    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(false);
    }

    /**
     * 弹框设置
     */
    private void initDialog() {
        sweetAlertDialog = null;
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCancelText(getString(R.string.cancel))
                .setConfirmText(getString(R.string.confirm))
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_conductorModels:
                Intent intent = new Intent();
                intent.putExtra("vehicleModelId", vehicleModelId);
                intent.putExtra("vehicleLengthId", vehicleLengthId);
                intent.setClass(aty, ConductorModelsActivity.class);
                startActivityForResult(intent, 3);
                break;
            case R.id.img_carLicensePlateNumber:
                if (isPreviewCarLicensePlateNumber) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrlCarLicensePlateNumber;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_carLicensePlateNumber, images, NumericConstants.READ_AND_WRITE_CODE);
                } else {
                    choicePhotoWrapper(NumericConstants.REQUEST_CODE_CHOOSE_PHOTO);
                }
                break;
            case R.id.ll_startingDatedriverLicense:
                pvTime.setDate(calendar);
                //  pvTime.setDate(Calendar.getInstance());
                //弹出时间选择器
                pvTime.show(tv_startingDatedriverLicense);
                break;
            case R.id.ll_closingDatedriverLicense:
                pvTime1.setDate(calendar1);
                pvTime1.show(tv_closingDatedriverLicense);
                break;
            case R.id.img_driverLicense:
                if (isPreviewDriverLicense) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrlDriverLicense;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_driverLicense, images, NumericConstants.REQUEST_CODE_PREVIEW);
                } else {
                    choicePhotoWrapper(NumericConstants.REQUEST_CODE_PHOTO_PREVIEW);
                }
                break;
            case R.id.ll_startingDatedrivingLicense:
                pvTime.setDate(calendar2);
                //弹出时间选择器
                pvTime.show(tv_startingDatedrivingLicense);
                break;
            case R.id.ll_registrationDeadline:
                pvTime1.setDate(calendar3);
                pvTime1.show(tv_registrationDeadline);
                break;
            case R.id.img_vehicleLicense:
                if (isPreviewVehicleLicense) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrlVehicleLicense;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_vehicleLicense, images, NumericConstants.REQUEST_CODE_PREVIEW1);
                } else {
                    choicePhotoWrapper(NumericConstants.REQUEST_CODE_PHOTO_PREVIEW1);
                }
                break;
            case R.id.ll_policydeadline:
                pvTime.setDate(calendar4);
                //弹出时间选择器
                pvTime.show(tv_policydeadline);
                break;
            case R.id.ll_policydeadline1:
                pvTime1.setDate(calendar5);
                pvTime1.show(tv_policydeadline1);
                break;
            case R.id.img_insurancePolicy:
                if (isPreviewInsurancePolicy) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrlInsurancePolicy;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_insurancePolicy, images, NumericConstants.REQUEST_CODE_PREVIEW2);
                } else {
                    choicePhotoWrapper(NumericConstants.REQUEST_CODE_PHOTO_PREVIEW2);
                }
                break;
            case R.id.ll_startingDateOperationCertificate:
                pvTime.setDate(calendar6);
                //弹出时间选择器
                pvTime.show(tv_startingDateOperationCertificate);
                break;
            case R.id.ll_closingDateOperationCertificate:
                pvTime1.setDate(calendar7);
                pvTime1.show(tv_closingDateOperationCertificate);
                break;
            case R.id.img_operationCertificate:
                if (isPreviewOperationCertificate) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrlOperationCertificate;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_operationCertificate, images, NumericConstants.REQUEST_CODE_PREVIEW3);
                } else {
                    choicePhotoWrapper(NumericConstants.REQUEST_CODE_PHOTO_PREVIEW3);
                }
                break;
            case R.id.tv_nextType:
                if (sweetAlertDialog == null) {
                    initDialog();
                }
                ((NewUserVehicleCertificationContract.Presenter) mPresenter).postVehicleCertification(sweetAlertDialog,
                        vehicleLength, vehicleLengthId, vehicleModel, vehicleModelId, et_load.getText().toString(),
                        et_carrierProduct.getText().toString(), et_licenseNumber.getText().toString(), imgUrlCarLicensePlateNumber,
                        policy_startline, policy_deadline, imgUrlInsurancePolicy,
                        license_startline, license_deadline, imgUrlVehicleLicense,
                        driving_startline, driving_deadline, imgUrlDriverLicense,
                        operation_startline, operation_deadline, imgUrlOperationCertificate);

                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            CodeBean codeBean = (CodeBean) JsonUtil.json2Obj(s, CodeBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "map_code", codeBean.getResult());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "auth_status", "check");
            String refreshName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "refreshName");
            if (refreshName != null && refreshName.equals("MineFragment")) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods1", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", true);
            } else if (refreshName != null && refreshName.equals("SupplyGoodsFragment")) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo1", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", true);
            } else if (refreshName != null && refreshName.equals("GetOrderFragment")) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo1", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods1", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", true);
            }
            KJActivityStack.create().finishOthersActivity(MainActivity.class);
         //   auth_status = getIntent().getStringExtra("auth_status");
//            if (!(StringUtils.isEmpty(auth_status) || auth_status.equals("init"))) {
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", true);
//            } else {
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", true);
//            }
            ViewInject.toast(getString(R.string.submittedSuccessfully));
            skipActivity(aty, MainActivity.class);
        } else if (flag == NumericConstants.REQUEST_CODE_CHOOSE_PHOTO) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreviewCarLicensePlateNumber = true;
                imgUrlCarLicensePlateNumber = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrlCarLicensePlateNumber + "?imageView2/1/w/300/h/150", img_carLicensePlateNumber, 1);
            }
        } else if (flag == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreviewDriverLicense = true;
                imgUrlDriverLicense = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrlDriverLicense + "?imageView2/1/w/300/h/150", img_driverLicense, 1);
            }
        } else if (flag == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW1) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreviewVehicleLicense = true;
                imgUrlVehicleLicense = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrlVehicleLicense + "?imageView2/1/w/300/h/150", img_vehicleLicense, 1);
            }
        } else if (flag == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW2) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreviewInsurancePolicy = true;
                imgUrlInsurancePolicy = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrlInsurancePolicy + "?imageView2/1/w/300/h/150", img_insurancePolicy, 1);
            }
        } else if (flag == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW3) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreviewOperationCertificate = true;
                imgUrlOperationCertificate = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrlOperationCertificate + "?imageView2/1/w/300/h/150", img_operationCertificate, 1);
            }
        } else if (flag == -1) {
            PersonalCertificateBean personalCertificateBean = (PersonalCertificateBean) JsonUtil.json2Obj(s, PersonalCertificateBean.class);

            vehicleModel = personalCertificateBean.getResult().getCar_type();
            vehicleLength = personalCertificateBean.getResult().getCar_length();
            tv_conductorModels.setText(getString(R.string.conductor) + vehicleModel + "  " + getString(R.string.models) + vehicleLength);

            vehicleModelId = personalCertificateBean.getResult().getCar_style_type_id();
            vehicleLengthId = personalCertificateBean.getResult().getCar_style_length_id();

            et_load.setText(personalCertificateBean.getResult().getWeight());
            et_carrierProduct.setText(personalCertificateBean.getResult().getVolume());

            et_licenseNumber.setText(personalCertificateBean.getResult().getCard_number());

            isPreviewCarLicensePlateNumber = true;
            imgUrlCarLicensePlateNumber = personalCertificateBean.getResult().getIndex_pic();
            GlideImageLoader.glideLoader(this, imgUrlCarLicensePlateNumber + "?imageView2/1/w/300/h/150", img_carLicensePlateNumber, 1);

            String driving_startlineStr = personalCertificateBean.getResult().getDriving_startline();
            String driving_deadlineStr = personalCertificateBean.getResult().getDriving_deadline();
            tv_startingDatedriverLicense.setText(driving_startlineStr.substring(0, 10));
            tv_closingDatedriverLicense.setText(driving_deadlineStr.substring(0, 10));
            calendar.set(StringUtils.toInt(driving_startlineStr.substring(0, 4)), StringUtils.toInt(driving_startlineStr.substring(5, 7)) - 1, StringUtils.toInt(driving_startlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
            calendar1.set(StringUtils.toInt(driving_deadlineStr.substring(0, 4)), StringUtils.toInt(driving_deadlineStr.substring(5, 7)) - 1, StringUtils.toInt(driving_deadlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
            isPreviewDriverLicense = true;
            imgUrlDriverLicense = personalCertificateBean.getResult().getDriving_licence_pic();
            GlideImageLoader.glideLoader(this, imgUrlDriverLicense + "?imageView2/1/w/300/h/150", img_driverLicense, 1);
            driving_startline = calendar.getTimeInMillis() / 1000;
            driving_deadline = calendar1.getTimeInMillis() / 1000;

            String license_startlineStr = personalCertificateBean.getResult().getLicense_startline();
            String license_deadlineStr = personalCertificateBean.getResult().getLicense_deadline();
            tv_startingDatedrivingLicense.setText(license_startlineStr.substring(0, 10));
            tv_registrationDeadline.setText(license_deadlineStr.substring(0, 10));
            calendar2.set(StringUtils.toInt(license_startlineStr.substring(0, 4)), StringUtils.toInt(license_startlineStr.substring(5, 7)) - 1, StringUtils.toInt(license_startlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
            calendar3.set(StringUtils.toInt(license_deadlineStr.substring(0, 4)), StringUtils.toInt(license_deadlineStr.substring(5, 7)) - 1, StringUtils.toInt(license_deadlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
            isPreviewVehicleLicense = true;
            imgUrlVehicleLicense = personalCertificateBean.getResult().getVehicle_license_pic();
            GlideImageLoader.glideLoader(this, imgUrlVehicleLicense + "?imageView2/1/w/300/h/150", img_vehicleLicense, 1);
            license_deadline = calendar3.getTimeInMillis() / 1000;
            license_startline = calendar2.getTimeInMillis() / 1000;


            String policy_startlineStr = personalCertificateBean.getResult().getPolicy_startline();
            String policy_deadlineStr = personalCertificateBean.getResult().getPolicy_deadline();
            tv_policydeadline.setText(policy_startlineStr.substring(0, 10));
            tv_policydeadline1.setText(policy_deadlineStr.substring(0, 10));
            calendar4.set(StringUtils.toInt(policy_startlineStr.substring(0, 4)), StringUtils.toInt(policy_startlineStr.substring(5, 7)) - 1, StringUtils.toInt(policy_startlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
            calendar5.set(StringUtils.toInt(policy_deadlineStr.substring(0, 4)), StringUtils.toInt(policy_deadlineStr.substring(5, 7)) - 1, StringUtils.toInt(policy_deadlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
            isPreviewInsurancePolicy = true;
            imgUrlInsurancePolicy = personalCertificateBean.getResult().getPolicy_pic();
            GlideImageLoader.glideLoader(this, imgUrlInsurancePolicy + "?imageView2/1/w/300/h/150", img_insurancePolicy, 1);
            policy_startline = calendar4.getTimeInMillis() / 1000;
            policy_deadline = calendar5.getTimeInMillis() / 1000;


            String operation_startlineStr = personalCertificateBean.getResult().getOperation_startline();
            String operation_deadlineStr = personalCertificateBean.getResult().getOperation_deadline();
            if (StringUtils.isEmpty(operation_startlineStr)) {
                tv_startingDateOperationCertificate.setText(getString(R.string.pleaseSelect));
            } else {
                tv_startingDateOperationCertificate.setText(operation_startlineStr.substring(0, 10));
                calendar6.set(StringUtils.toInt(operation_startlineStr.substring(0, 4)), StringUtils.toInt(operation_startlineStr.substring(5, 7)) - 1, StringUtils.toInt(operation_startlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
                operation_startline = calendar6.getTimeInMillis() / 1000;
            }
            if (StringUtils.isEmpty(operation_deadlineStr)) {
                tv_closingDateOperationCertificate.setText(getString(R.string.pleaseSelect));
            } else {
                tv_closingDateOperationCertificate.setText(operation_deadlineStr.substring(0, 10));
                calendar7.set(StringUtils.toInt(operation_deadlineStr.substring(0, 4)), StringUtils.toInt(operation_deadlineStr.substring(5, 7)) - 1, StringUtils.toInt(operation_deadlineStr.substring(8, 10)), StringUtils.toInt(driving_startlineStr.substring(11, 13)), StringUtils.toInt(driving_startlineStr.substring(14, 16)), StringUtils.toInt(driving_startlineStr.substring(17)));
                operation_deadline = calendar7.getTimeInMillis() / 1000;
            }
            isPreviewOperationCertificate = true;
            imgUrlOperationCertificate = personalCertificateBean.getResult().getOperation_pic();
            GlideImageLoader.glideLoader(this, imgUrlOperationCertificate + "?imageView2/1/w/300/h/150", img_operationCertificate, 1);
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(NewUserVehicleCertificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper(int code) {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            Intent intent = new Intent(this, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
            //ImagePicker.getInstance().setSelectedImages(images);
            startActivityForResult(intent, code);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.needPermission), NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER) {
            ViewInject.toast(getString(R.string.denyPermission));
        }
    }


    ArrayList<ImageItem> images = null;

    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            selectImageReturn(requestCode, data);
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            if (data == null) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            //预览图片返回
            previewImageReturn(requestCode, data);
        } else if (requestCode == 3 && resultCode == RESULT_OK) {
            vehicleModel = data.getStringExtra("vehicleModel");
            vehicleLength = data.getStringExtra("vehicleLength");
            tv_conductorModels.setText(getString(R.string.conductor) + vehicleModel + "  " + getString(R.string.models) + vehicleLength);
            vehicleModelId = data.getIntExtra("vehicleModelId", 0);
            vehicleLengthId = data.getIntExtra("vehicleLengthId", 0);
        }
    }

    /**
     * 时间选择器----起始日期
     */
    public void startDatePicker() {
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendar.get(Calendar.YEAR) - 50, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() >= System.currentTimeMillis()) {
                    ViewInject.toast(getString(R.string.lessThanCurrent));
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // long time = StringUtils.toLong(DataUtil.getStringTime(format.format(date))) / 1000;
                if (v.getId() == R.id.tv_startingDatedriverLicense) {
                    driving_startline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_startingDatedrivingLicense) {
                    license_startline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_policydeadline) {
                    policy_startline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_startingDateOperationCertificate) {
                    operation_startline = date.getTime() / 1000;
                }
                ((TextView) v).setText(format.format(date));
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setContentSize(20)
                .setRangDate(startDate, endDate)
                .build();
    }


    /**
     * 时间选择器----截止日期
     */
    public void asOfTheDatePicker() {
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendar.get(Calendar.YEAR) + 99, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() <= System.currentTimeMillis()) {
                    ViewInject.toast(getString(R.string.aboveCurrentTime));
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //  long time = StringUtils.toLong(DataUtil.getStringTime(format.format(date))) / 1000;
                if (v.getId() == R.id.tv_closingDatedriverLicense) {
                    driving_deadline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_registrationDeadline) {
                    license_deadline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_policydeadline1) {
                    policy_deadline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_closingDateOperationCertificate) {
                    operation_deadline = date.getTime() / 1000;
                }
                ((TextView) v).setText(format.format(date));
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setContentSize(20)
                .setRangDate(startDate, endDate)
                .build();
    }

    //选择图片返回
    @SuppressWarnings("unchecked")
    private void selectImageReturn(int requestCode, Intent data) {
        if (data != null && requestCode == NumericConstants.REQUEST_CODE_CHOOSE_PHOTO) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            ((NewUserVehicleCertificationContract.Presenter) mPresenter).upLoadImg(images.get(0).path, NumericConstants.REQUEST_CODE_CHOOSE_PHOTO);
        } else if (data != null && requestCode == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            ((NewUserVehicleCertificationContract.Presenter) mPresenter).upLoadImg(images.get(0).path, NumericConstants.REQUEST_CODE_PHOTO_PREVIEW);
        } else if (data != null && requestCode == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW1) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            ((NewUserVehicleCertificationContract.Presenter) mPresenter).upLoadImg(images.get(0).path, NumericConstants.REQUEST_CODE_PHOTO_PREVIEW1);
        } else if (data != null && requestCode == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW2) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            ((NewUserVehicleCertificationContract.Presenter) mPresenter).upLoadImg(images.get(0).path, NumericConstants.REQUEST_CODE_PHOTO_PREVIEW2);
        } else if (data != null && requestCode == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW3) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            ((NewUserVehicleCertificationContract.Presenter) mPresenter).upLoadImg(images.get(0).path, NumericConstants.REQUEST_CODE_PHOTO_PREVIEW3);
        } else {
            ViewInject.toast(getString(R.string.noData));
        }
    }


    //预览图片返回
    @SuppressWarnings("unchecked")
    private void previewImageReturn(int requestCode, Intent data) {
        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
        //预览图片返回
        if (data != null && images != null && requestCode == NumericConstants.READ_AND_WRITE_CODE && images.size() == 0) {
            isPreviewCarLicensePlateNumber = false;
            imgUrlCarLicensePlateNumber = null;
            GlideImageLoader.glideLoader(this, R.mipmap.carlicenseplatenumber, img_carLicensePlateNumber, 1);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW && images.size() == 0) {
            isPreviewDriverLicense = false;
            imgUrlDriverLicense = null;
            GlideImageLoader.glideLoader(this, R.mipmap.driverlicense, img_driverLicense, 1);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW1 && images.size() == 0) {
            isPreviewVehicleLicense = false;
            imgUrlVehicleLicense = null;
            GlideImageLoader.glideLoader(this, R.mipmap.vehiclelicense, img_vehicleLicense, 1);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW2 && images.size() == 0) {
            isPreviewInsurancePolicy = false;
            imgUrlInsurancePolicy = null;
            GlideImageLoader.glideLoader(this, R.mipmap.insurancepolicy, img_insurancePolicy, 1);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW3 && images.size() == 0) {
            isPreviewOperationCertificate = false;
            imgUrlOperationCertificate = null;
            GlideImageLoader.glideLoader(this, R.mipmap.operationcertificate, img_operationCertificate, 1);
        }
    }

    //打开预览
    @SuppressWarnings("unchecked")
    private void toImagePreviewDelActivity(View view, ArrayList<ImageItem> list, int code) {
        Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, list);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        startActivityForResult(intentPreview, code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        images.clear();
        images = null;
        pvTime = null;
        pvTime1 = null;
    }


}
