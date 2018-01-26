package com.ruitukeji.zwbs.mine.vehiclecertification;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.UploadImageBean;
import com.ruitukeji.zwbs.entity.loginregister.CodeBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ProvinceShortBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.VehicleBrandBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.VehicleCertificationBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.identityauthentication.IdentityAuthenticationContract;
import com.ruitukeji.zwbs.mine.vehiclecertification.dialog.SamplePictureBouncedDialog;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.DataUtil;
import com.ruitukeji.zwbs.utils.GetJsonDataUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.PickerViewUtil;
import com.ruitukeji.zwbs.utils.SoftKeyboardUtils;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_CHOOSE_PHOTO;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PHOTO_PREVIEW;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PHOTO_PREVIEW1;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PHOTO_PREVIEW2;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PHOTO_PREVIEW3;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PHOTO_PREVIEW4;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PHOTO_PREVIEW5;

/**
 * 车辆认证
 * Created by Administrator on 2017/11/30.
 */

public class VehicleCertificationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, VehicleCertificationContract.View {

    /**
     * 认证状态
     */
    @BindView(id = R.id.tv_certificationStatus)
    private TextView tv_certificationStatus;


    /**
     * 地址简称
     */
    @BindView(id = R.id.ll_addressAbbreviation, click = true)
    private LinearLayout ll_addressAbbreviation;
    private OptionsPickerView pvOptions;
    @BindView(id = R.id.tv_addressAbbreviation)
    private TextView tv_addressAbbreviation;
    @BindView(id = R.id.et_licenseNumber)
    private EditText et_licenseNumber;
    String addressAbbreviation = "";

    /**
     * 车长车型
     */
    @BindView(id = R.id.ll_conductorModels, click = true)
    private LinearLayout ll_conductorModels;
    @BindView(id = R.id.tv_conductorModels)
    private TextView tv_conductorModels;
    private int vehicleModelId = 0;
    private int vehicleLengthId = 0;
    String vehicleModel = "";
    String vehicleLength = "";

    /**
     * 车辆品牌
     */
    @BindView(id = R.id.ll_vehicleBrand, click = true)
    private LinearLayout ll_vehicleBrand;
    @BindView(id = R.id.tv_vehicleBrand)
    private TextView tv_vehicleBrand;
    private OptionsPickerView pvOptions1;
    /**
     * 车辆注册时间
     */
    @BindView(id = R.id.ll_vehicleRegistrationDate, click = true)
    private LinearLayout ll_vehicleRegistrationDate;
    @BindView(id = R.id.tv_vehicleRegistrationDate)
    private TextView tv_vehicleRegistrationDate;
    Calendar calendar = Calendar.getInstance();
    public long vehicleRegistrationDate = 0;

    /**
     * 常驻地地址
     */
    @BindView(id = R.id.ll_residentAddress, click = true)
    private LinearLayout ll_residentAddress;
    @BindView(id = R.id.tv_residentAddress)
    private TextView tv_residentAddress;
    private PickerViewUtil pickerViewUtil;
    private int area = 0;
    private int city = 0;
    private int province = 0;

    /**
     * 车辆正面照
     */
    @BindView(id = R.id.img_uploadFaceLight, click = true)
    private ImageView img_uploadFaceLight;
    String uploadFaceLightUrl = "";
    private boolean isUploadFaceLight = true;
    @BindView(id = R.id.img_uploadFaceLight1, click = true)
    private ImageView img_uploadFaceLight1;
    /**
     * 车辆侧身照
     */
    @BindView(id = R.id.img_uploadSiding, click = true)
    private ImageView img_uploadSiding;
    @BindView(id = R.id.img_carSide, click = true)
    private ImageView img_carSide;
    String uploadSidingUrl = "";
    private boolean isUploadSiding = true;

    /**
     * 车辆尾部照
     */
    @BindView(id = R.id.img_uploadRearLight, click = true)
    private ImageView img_uploadRearLight;
    @BindView(id = R.id.img_carInside, click = true)
    private ImageView img_carInside;
    String uploadRearLightUrl = "";
    private boolean isUploadRearLight = true;

    /**
     * 行驶证有效期
     */
    @BindView(id = R.id.ll_validityLicense, click = true)
    private LinearLayout ll_validityLicense;
    @BindView(id = R.id.tv_validityLicense)
    private TextView tv_validityLicense;
    @BindView(id = R.id.img_uploadDrivingLicensePhotos, click = true)
    private ImageView img_uploadDrivingLicensePhotos;
    @BindView(id = R.id.img_driverLicence1, click = true)
    private ImageView img_driverLicence1;
    String uploadDrivingLicensePhotosUrl = "";
    private boolean isUploadDrivingLicensePhotos = true;
    Calendar calendar1 = Calendar.getInstance();
    public long validityLicense = 0;

    /**
     * 道路运输证有效期
     */
    @BindView(id = R.id.ll_roadTransportValidityPeriodDate, click = true)
    private LinearLayout ll_roadTransportValidityPeriodDate;
    @BindView(id = R.id.tv_roadTransportValidityPeriodDate)
    private TextView tv_roadTransportValidityPeriodDate;
    @BindView(id = R.id.img_uploadRoadQualification1, click = true)
    private ImageView img_uploadRoadQualification1;
    @BindView(id = R.id.img_roadQualification1, click = true)
    private ImageView img_roadQualification1;
    String uploadRoadQualification1Url = "";
    private boolean isUploadRoadQualification1 = true;
    Calendar calendar2 = Calendar.getInstance();
    public long roadTransportValidityPeriodDate = 0;

    /**
     * 商业险保单有效期
     */
    @BindView(id = R.id.ll_durationInsurance, click = true)
    private LinearLayout ll_durationInsurance;
    @BindView(id = R.id.tv_durationInsurance)
    private TextView tv_durationInsurance;
    @BindView(id = R.id.img_uploadBusinessInsurancePolicyPhoto, click = true)
    private ImageView img_uploadBusinessInsurancePolicyPhoto;
    @BindView(id = R.id.img_commericalInsurance, click = true)
    private ImageView img_commericalInsurance;
    String uploadBusinessInsurancePolicyPhotoUrl = "";
    private boolean isUploadBusinessInsurancePolicyPhoto = true;
    Calendar calendar3 = Calendar.getInstance();
    public long durationInsurance = 0;

    /**
     * 交强险有效期
     */
    @BindView(id = R.id.ll_durationInsurance1, click = true)
    private LinearLayout ll_durationInsurance1;
    @BindView(id = R.id.tv_durationInsurance1)
    private TextView tv_durationInsurance1;
    @BindView(id = R.id.img_uploadhandHandPolicyPhoto, click = true)
    private ImageView img_uploadhandHandPolicyPhoto;
    @BindView(id = R.id.img_compulsoryInsurance, click = true)
    private ImageView img_compulsoryInsurance;
    String uploadhandHandPolicyPhotoUrl = "";
    private boolean isUploadhandHandPolicyPhoto = true;
    Calendar calendar4 = Calendar.getInstance();
    public long durationInsurance1 = 0;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;


    private ImagePicker imagePicker;

    private TimePickerView pvTime;
    private TimePickerView pvTime1;
    private SamplePictureBouncedDialog samplePictureBouncedDialog = null;
    private List<ProvinceShortBean.ResultBean> provinceShortList = null;
    private List<VehicleBrandBean.ResultBean> vehicleBrandList = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehiclecertification);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new VehicleCertificationPresenter(this);
        images = new ArrayList<>();
        // samplePictureBouncedDialog = new SamplePictureBouncedDialog(this, R.mipmap.pic_car_front, getString(R.string.licensePlatesClear));
        showLoadingDialog(getString(R.string.dataLoad));
        vehicleBrandAbbreviation();
        ((VehicleCertificationContract.Presenter) mPresenter).getVehicleBrand();
        initImagePicker();
        asOfTheDatePicker();
        asOfTheDatePicker1();
        addressAbbreviation();
        initPickerView();
        String car_auth_status = PreferenceHelper.readString(aty, StringConstants.FILENAME, "car_auth_status", "init");
        if (car_auth_status != null && car_auth_status.equals("init")) {
            return;
        }
        ((VehicleCertificationContract.Presenter) mPresenter).getCarAuthInfo();
    }

    /**
     * 图片设置
     */
    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(false);
    }

    /**
     * 时间选择器----截止日期
     */
    @SuppressWarnings("deprecation")
    public void asOfTheDatePicker() {
        boolean[] type = new boolean[]{true, true, true, false, false, false};//显示类型 默认全部显示
        //控制时间范围
        Calendar calendarSet = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendarSet.get(Calendar.YEAR), calendarSet.get(Calendar.MONTH), calendarSet.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendarSet.get(Calendar.YEAR) + 99, calendarSet.get(Calendar.MONTH), calendarSet.get(Calendar.DAY_OF_MONTH));
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() <= System.currentTimeMillis()) {
                    ViewInject.toast(getString(R.string.aboveCurrentTime));
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                if (v.getId() == R.id.tv_vehicleRegistrationDate) {
                    vehicleRegistrationDate = date.getTime() / 1000;
                    calendar.setTime(date);
                } else if (v.getId() == R.id.tv_validityLicense) {
                    validityLicense = date.getTime() / 1000;
                    calendar1.setTime(date);
                } else if (v.getId() == R.id.tv_roadTransportValidityPeriodDate) {
                    roadTransportValidityPeriodDate = date.getTime() / 1000;
                    calendar2.setTime(date);
                } else if (v.getId() == R.id.tv_durationInsurance) {
                    durationInsurance = date.getTime() / 1000;
                    calendar3.setTime(date);
                } else if (v.getId() == R.id.tv_durationInsurance1) {
                    durationInsurance1 = date.getTime() / 1000;
                    calendar4.setTime(date);
                }
                ((TextView) v).setText(format.format(date));
            }
        }).setType(type)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setContentSize(20)
                .setRangDate(startDate, endDate)
                .build();
    }

    @SuppressWarnings("deprecation")
    public void asOfTheDatePicker1() {
        boolean[] type = new boolean[]{true, true, true, false, false, false};//显示类型 默认全部显示
        //控制时间范围
        Calendar calendarSet = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendarSet.get(Calendar.YEAR) - 99, calendarSet.get(Calendar.MONTH), calendarSet.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendarSet.get(Calendar.YEAR), calendarSet.get(Calendar.MONTH), calendarSet.get(Calendar.DAY_OF_MONTH));
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() >= System.currentTimeMillis()) {
                    ViewInject.toast(getString(R.string.vehicleRegistrationDate1));
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                if (v.getId() == R.id.tv_vehicleRegistrationDate) {
                    vehicleRegistrationDate = date.getTime() / 1000;
                    calendar.setTime(date);
                }
                ((TextView) v).setText(format.format(date));
            }
        }).setType(type)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setContentSize(20)
                .setRangDate(startDate, endDate)
                .build();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.vehicleCertification), true, R.id.titlebar);
        String car_auth_status = PreferenceHelper.readString(aty, StringConstants.FILENAME, "car_auth_status", "init");
        if (car_auth_status != null && car_auth_status.equals("pass")) {
            tv_certificationStatus.setText(getString(R.string.certified));
        } else if (car_auth_status != null && car_auth_status.equals("check")) {
            tv_certificationStatus.setText(getString(R.string.inAuthentication));
        } else if (car_auth_status != null && car_auth_status.equals("refuse")) {
            tv_certificationStatus.setText(getString(R.string.authenticationFailure));
        } else {
            tv_certificationStatus.setText(getString(R.string.unauthorized));
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_addressAbbreviation:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_addressAbbreviation);
                break;
            case R.id.ll_conductorModels:
                Intent intent = new Intent(aty, ConductorModelsActivity.class);
                intent.putExtra("vehicleModelId", vehicleModelId);
                intent.putExtra("vehicleLengthId", vehicleLengthId);
                startActivityForResult(intent, 3);
                break;
            case R.id.ll_vehicleBrand:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions1.show(tv_vehicleBrand);
                break;
            case R.id.ll_vehicleRegistrationDate:
                pvTime1.setDate(calendar);
                //弹出时间选择器
                pvTime1.show(tv_vehicleRegistrationDate);
                break;
            case R.id.ll_residentAddress:
                SoftKeyboardUtils.packUpKeyboard(this);
                //点击弹出选项选择器
                pickerViewUtil.onoptionsSelectListener();
                if (province == 0 && city == 0 && area == 0) {
                    break;
                }
                pickerViewUtil.onoptionsSelect(province, city, area);
                break;
            case R.id.img_uploadFaceLight:
                if (isUploadFaceLight) {
                    choicePhotoWrapper(REQUEST_CODE_CHOOSE_PHOTO);
                    break;
                }
                images.clear();
                ImageItem imageItem = new ImageItem();
                imageItem.path = uploadFaceLightUrl;
                images.add(imageItem);
                //打开预览
                toImagePreviewDelActivity(img_uploadFaceLight, images, NumericConstants.REQUEST_CODE_PREVIEW);
                break;
            case R.id.img_uploadFaceLight1:
                show(R.mipmap.pic_car_front, getString(R.string.licensePlatesClear));
                break;
            case R.id.img_uploadSiding:
                if (isUploadSiding) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW);
                    break;
                }
                images.clear();
                ImageItem imageItem1 = new ImageItem();
                imageItem1.path = uploadSidingUrl;
                images.add(imageItem1);
                //打开预览
                toImagePreviewDelActivity(img_uploadSiding, images, NumericConstants.REQUEST_CODE_PREVIEW1);
                break;
            case R.id.img_carSide:
                show(R.mipmap.pic_car_side, getString(R.string.picCarSide));
                break;
            case R.id.img_uploadRearLight:
                if (isUploadRearLight) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW1);
                    break;
                }
                images.clear();
                ImageItem imageItem2 = new ImageItem();
                imageItem2.path = uploadRearLightUrl;
                images.add(imageItem2);
                //打开预览
                toImagePreviewDelActivity(img_uploadRearLight, images, NumericConstants.REQUEST_CODE_PREVIEW2);
                break;
            case R.id.img_carInside:
                show(R.mipmap.pic_car_inside, getString(R.string.picCarInside));
                break;
            case R.id.ll_validityLicense:
                pvTime.setDate(calendar1);
                //弹出时间选择器
                pvTime.show(tv_validityLicense);
                break;
            case R.id.img_uploadDrivingLicensePhotos:
                if (isUploadDrivingLicensePhotos) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW2);
                    break;
                }
                images.clear();
                ImageItem imageItem3 = new ImageItem();
                imageItem3.path = uploadDrivingLicensePhotosUrl;
                images.add(imageItem3);
                //打开预览
                toImagePreviewDelActivity(img_uploadDrivingLicensePhotos, images, NumericConstants.REQUEST_CODE_PREVIEW3);
                break;
            case R.id.img_driverLicence1:
                show(R.mipmap.pic_driver_licence1, getString(R.string.makeClear));
                break;
            case R.id.ll_roadTransportValidityPeriodDate:
                pvTime.setDate(calendar2);
                //弹出时间选择器
                pvTime.show(tv_roadTransportValidityPeriodDate);
                break;
            case R.id.img_uploadRoadQualification1:
                if (isUploadRoadQualification1) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW3);
                    break;
                }
                images.clear();
                ImageItem imageItem4 = new ImageItem();
                imageItem4.path = uploadRoadQualification1Url;
                images.add(imageItem4);
                //打开预览
                toImagePreviewDelActivity(img_uploadRoadQualification1, images, NumericConstants.REQUEST_CODE_PREVIEW4);
                break;
            case R.id.img_roadQualification1:
                show(R.mipmap.pic_road_qualification, getString(R.string.makeClear));
                break;
            case R.id.ll_durationInsurance:
                pvTime.setDate(calendar3);
                //弹出时间选择器
                pvTime.show(tv_durationInsurance);
                break;
            case R.id.img_uploadBusinessInsurancePolicyPhoto:
                if (isUploadBusinessInsurancePolicyPhoto) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW4);
                    break;
                }
                images.clear();
                ImageItem imageItem5 = new ImageItem();
                imageItem5.path = uploadBusinessInsurancePolicyPhotoUrl;
                images.add(imageItem5);
                //打开预览
                toImagePreviewDelActivity(img_uploadBusinessInsurancePolicyPhoto, images, NumericConstants.REQUEST_CODE_PREVIEW5);
                break;
            case R.id.img_commericalInsurance:
                show(R.mipmap.pic_commerical_insurance, getString(R.string.picCompulsoryInsurance));
                break;
            case R.id.ll_durationInsurance1:
                pvTime.setDate(calendar4);
                //弹出时间选择器
                pvTime.show(tv_durationInsurance1);
                break;
            case R.id.img_uploadhandHandPolicyPhoto:
                if (isUploadhandHandPolicyPhoto) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW5);
                    break;
                }
                images.clear();
                ImageItem imageItem6 = new ImageItem();
                imageItem6.path = uploadhandHandPolicyPhotoUrl;
                images.add(imageItem6);
                //打开预览
                toImagePreviewDelActivity(img_uploadhandHandPolicyPhoto, images, NumericConstants.REQUEST_CODE_PREVIEW6);
                break;
            case R.id.img_compulsoryInsurance:
                show(R.mipmap.pic_compulsory_insurance, getString(R.string.picCompulsoryInsurance));
                break;
            case R.id.tv_submit:
                ((VehicleCertificationContract.Presenter) mPresenter).postVehicleCertification((tv_addressAbbreviation.getText().toString() + et_licenseNumber.getText().toString().trim()),
                        vehicleModel, vehicleModelId, vehicleLength, vehicleLengthId, tv_vehicleBrand.getText().toString().trim(), vehicleRegistrationDate, tv_residentAddress.getText().toString().trim(),
                        uploadFaceLightUrl, uploadSidingUrl, uploadRearLightUrl, validityLicense, uploadDrivingLicensePhotosUrl, roadTransportValidityPeriodDate, uploadRoadQualification1Url,
                        durationInsurance, uploadBusinessInsurancePolicyPhotoUrl, durationInsurance1, uploadhandHandPolicyPhotoUrl, province, city, area);
                break;
            default:
                break;
        }
    }

    /**
     * 选择省简称
     */
    @SuppressWarnings("unchecked")
    private void addressAbbreviation() {
        pvOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //  car_type_id = carInfoBean.getResult().get(options1).getId();
                PreferenceHelper.write(aty, StringConstants.FILENAME, "province_short", provinceShortList.get(options1).getName());
                ((TextView) v).setText(provinceShortList.get(options1).getName());
            }
        }).build();
        String jsonData = new GetJsonDataUtil().getJson(this, "provinceshort.json");
        ProvinceShortBean provinceShortBean = (ProvinceShortBean) JsonUtil.getInstance().json2Obj(jsonData, ProvinceShortBean.class);
        provinceShortList = provinceShortBean.getResult();
        String currentLocationProvince = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationProvince", "");
        String provinceShort = PreferenceHelper.readString(aty, StringConstants.FILENAME, "province_short", "");
        if (provinceShortList != null && provinceShortList.size() > 0) {
            pvOptions.setPicker(provinceShortBean.getResult());
            for (int i = 0; i < provinceShortList.size(); i++) {
                if (!StringUtils.isEmpty(provinceShort) && provinceShortList.get(i).getName().equals(provinceShort)) {
                    tv_addressAbbreviation.setText(provinceShort);
                    pvOptions.setSelectOptions(i);
                    return;
                } else if (!StringUtils.isEmpty(currentLocationProvince) && StringUtils.isEmpty(provinceShort) && provinceShortList.get(i).getProvinceName().equals(currentLocationProvince)) {
                    tv_addressAbbreviation.setText(provinceShortList.get(i).getName());
                    pvOptions.setSelectOptions(i);
                    return;
                }
            }
        }
    }

    /**
     * 选择车辆品牌
     */
    @SuppressWarnings("unchecked")
    private void vehicleBrandAbbreviation() {
        pvOptions1 = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //  car_type_id = carInfoBean.getResult().get(options1).getId();
                ((TextView) v).setText(vehicleBrandList.get(options1).getName());
            }
        }).build();
    }

    /**
     * 选项选择器
     */
    private void initPickerView() {
        if (pickerViewUtil == null) {
            pickerViewUtil = new PickerViewUtil(this, 0) {
                @Override
                public void getAddress(String address, int province1, int city1, int area1) {
                    province = province1;
                    city = city1;
                    area = area1;
                    tv_residentAddress.setText(address);
                }
            };
        }
    }


    /**
     * 显示实例图片
     *
     * @param pic
     * @param samplePictureText
     */
    private void show(int pic, String samplePictureText) {
        samplePictureBouncedDialog = null;
        samplePictureBouncedDialog = new SamplePictureBouncedDialog(this, pic, samplePictureText);
        samplePictureBouncedDialog.show();
    }


    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper(int code) {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            Intent intent = new Intent(this, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
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
            if (data != null && requestCode == requestCode) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images == null || images.size() == 0) {
                    ViewInject.toast(getString(R.string.noData));
                    return;
                }
                String filePath = images.get(0).path;
                ((IdentityAuthenticationContract.Presenter) mPresenter).postUpLoadImg(filePath, requestCode);
            } else {
                ViewInject.toast(getString(R.string.noData));
            }
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
            tv_conductorModels.setText(getString(R.string.conductor) + vehicleLength + "  " + getString(R.string.models) + vehicleModel);
            vehicleModelId = data.getIntExtra("vehicleModelId", 0);
            vehicleLengthId = data.getIntExtra("vehicleLengthId", 0);
        }
    }

    //预览图片返回
    @SuppressWarnings("unchecked")
    private void previewImageReturn(int requestCode, Intent data) {
        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
        //预览图片返回
        if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW && images.size() == 0) {
            isUploadFaceLight = true;
            uploadFaceLightUrl = null;
            img_uploadFaceLight.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW1 && images.size() == 0) {
            isUploadSiding = true;
            uploadSidingUrl = null;
            img_uploadSiding.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW2 && images.size() == 0) {
            isUploadRearLight = true;
            uploadRearLightUrl = null;
            img_uploadRearLight.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW3 && images.size() == 0) {
            isUploadDrivingLicensePhotos = true;
            uploadDrivingLicensePhotosUrl = null;
            img_uploadDrivingLicensePhotos.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW4 && images.size() == 0) {
            isUploadRoadQualification1 = true;
            uploadRoadQualification1Url = null;
            img_uploadRoadQualification1.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW5 && images.size() == 0) {
            isUploadBusinessInsurancePolicyPhoto = true;
            uploadBusinessInsurancePolicyPhotoUrl = null;
            img_uploadBusinessInsurancePolicyPhoto.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW6 && images.size() == 0) {
            isUploadhandHandPolicyPhoto = true;
            uploadhandHandPolicyPhotoUrl = null;
            img_uploadhandHandPolicyPhoto.setImageDrawable(null);
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
    public void setPresenter(VehicleCertificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == REQUEST_CODE_CHOOSE_PHOTO) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadFaceLightUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadFaceLightUrl + "?imageView2/1/w/161/h/103", img_uploadFaceLight);
                isUploadFaceLight = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadSidingUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadSidingUrl + "?imageView2/1/w/161/h/103", img_uploadSiding);
                isUploadSiding = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW1) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadRearLightUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadRearLightUrl + "?imageView2/1/w/161/h/103", img_uploadRearLight);
                isUploadRearLight = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW2) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadDrivingLicensePhotosUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadDrivingLicensePhotosUrl + "?imageView2/1/w/161/h/103", img_uploadDrivingLicensePhotos);
                isUploadDrivingLicensePhotos = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW3) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadRoadQualification1Url = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadRoadQualification1Url + "?imageView2/1/w/161/h/103", img_uploadRoadQualification1);
                isUploadRoadQualification1 = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW4) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadBusinessInsurancePolicyPhotoUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadBusinessInsurancePolicyPhotoUrl + "?imageView2/1/w/161/h/103", img_uploadBusinessInsurancePolicyPhoto);
                isUploadBusinessInsurancePolicyPhoto = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW5) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadhandHandPolicyPhotoUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadhandHandPolicyPhotoUrl + "?imageView2/1/w/161/h/103", img_uploadhandHandPolicyPhoto);
                isUploadhandHandPolicyPhoto = false;
            }
        } else if (flag == 0) {
            VehicleCertificationBean vehicleCertificationBean = (VehicleCertificationBean) JsonUtil.getInstance().json2Obj(success, VehicleCertificationBean.class);
            String card_number = vehicleCertificationBean.getResult().getCard_number();
            tv_addressAbbreviation.setText(card_number.substring(0, 1));
            et_licenseNumber.setText(card_number.substring(1));
            vehicleModel = vehicleCertificationBean.getResult().getCar_type();
            vehicleLength = vehicleCertificationBean.getResult().getCar_length();
            tv_conductorModels.setText(getString(R.string.conductor) + vehicleLength + "  " + getString(R.string.models) + vehicleModel);
            vehicleModelId = vehicleCertificationBean.getResult().getCar_style_type_id();
            vehicleLengthId = vehicleCertificationBean.getResult().getCar_style_length_id();

            if (!StringUtils.isEmpty(vehicleCertificationBean.getResult().getCar_band())) {
                tv_vehicleBrand.setText(vehicleCertificationBean.getResult().getCar_band());
                setVehicleBrand(vehicleCertificationBean.getResult().getCar_band());
            }

            vehicleRegistrationDate = StringUtils.toLong(vehicleCertificationBean.getResult().getCar_registered_time());
            String d = DataUtil.formatData(vehicleRegistrationDate, "yyyy-MM-dd");
            tv_vehicleRegistrationDate.setText(d);
            Date date = new Date(vehicleRegistrationDate * 1000);
            calendar.setTime(date);

            if (!StringUtils.isEmpty(vehicleCertificationBean.getResult().getPermanent_address())) {
                tv_residentAddress.setText(vehicleCertificationBean.getResult().getPermanent_address());
                province = vehicleCertificationBean.getResult().getProvince();
                city = vehicleCertificationBean.getResult().getCity();
                area = vehicleCertificationBean.getResult().getArea();
            }

            uploadFaceLightUrl = vehicleCertificationBean.getResult().getCar_front_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadFaceLightUrl + "?imageView2/1/w/161/h/103", img_uploadFaceLight);
            isUploadFaceLight = false;
            uploadSidingUrl = vehicleCertificationBean.getResult().getCar_bank_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadSidingUrl + "?imageView2/1/w/161/h/103", img_uploadSiding);
            isUploadSiding = false;
            uploadRearLightUrl = vehicleCertificationBean.getResult().getCar_tail_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadRearLightUrl + "?imageView2/1/w/161/h/103", img_uploadRearLight);
            isUploadRearLight = false;


            validityLicense = StringUtils.toLong(vehicleCertificationBean.getResult().getLicense_time());
            String d1 = DataUtil.formatData(validityLicense, "yyyy-MM-dd");
            tv_validityLicense.setText(d1);
            Date date1 = new Date(validityLicense * 1000);
            calendar1.setTime(date1);

            uploadDrivingLicensePhotosUrl = vehicleCertificationBean.getResult().getLicense_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadDrivingLicensePhotosUrl + "?imageView2/1/w/161/h/103", img_uploadDrivingLicensePhotos);
            isUploadDrivingLicensePhotos = false;


            roadTransportValidityPeriodDate = StringUtils.toLong(vehicleCertificationBean.getResult().getTransport_pic_time());
            String d2 = DataUtil.formatData(roadTransportValidityPeriodDate, "yyyy-MM-dd");
            tv_roadTransportValidityPeriodDate.setText(d2);
            Date date2 = new Date(roadTransportValidityPeriodDate * 1000);
            calendar2.setTime(date2);
            uploadRoadQualification1Url = vehicleCertificationBean.getResult().getTransport_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadRoadQualification1Url + "?imageView2/1/w/161/h/103", img_uploadRoadQualification1);
            isUploadRoadQualification1 = false;

            durationInsurance = StringUtils.toLong(vehicleCertificationBean.getResult().getPolicy_time());
            String d3 = DataUtil.formatData(durationInsurance, "yyyy-MM-dd");
            tv_durationInsurance.setText(d3);
            Date date3 = new Date(durationInsurance * 1000);
            calendar3.setTime(date3);

            uploadBusinessInsurancePolicyPhotoUrl = vehicleCertificationBean.getResult().getLicense_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadBusinessInsurancePolicyPhotoUrl + "?imageView2/1/w/161/h/103", img_uploadBusinessInsurancePolicyPhoto);
            isUploadBusinessInsurancePolicyPhoto = false;

            durationInsurance1 = StringUtils.toLong(vehicleCertificationBean.getResult().getInsurance_time());
            String d4 = DataUtil.formatData(durationInsurance1, "yyyy-MM-dd");
            tv_durationInsurance1.setText(d4);
            Date date4 = new Date(durationInsurance1 * 1000);
            calendar4.setTime(date4);
            uploadhandHandPolicyPhotoUrl = vehicleCertificationBean.getResult().getInsurance_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadhandHandPolicyPhotoUrl + "?imageView2/1/w/161/h/103", img_uploadhandHandPolicyPhoto);
            isUploadhandHandPolicyPhoto = false;
        } else if (flag == 1) {
            CodeBean codeBean = (CodeBean) JsonUtil.json2Obj(success, CodeBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "map_code", codeBean.getResult());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "car_auth_status", "check");
            ViewInject.toast(getString(R.string.submittedSuccessfully));
            tv_certificationStatus.setText(getString(R.string.inAuthentication));
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusVehicleCertificationEvent"));
            dismissLoadingDialog();
            aty.finish();
            return;
        } else if (flag == 2) {
            VehicleBrandBean vehicleBrandBean = (VehicleBrandBean) JsonUtil.json2Obj(success, VehicleBrandBean.class);
            vehicleBrandList = vehicleBrandBean.getResult();
            if (vehicleBrandList != null && vehicleBrandList.size() > 0) {
                pvOptions1.setPicker(vehicleBrandList);
            }
        }
        dismissLoadingDialog();
    }

    /**
     * @param name 设置车辆品牌
     */

    private void setVehicleBrand(String name) {
        for (int i = 0; i < vehicleBrandList.size(); i++) {
            if (name.equals(vehicleBrandList.get(i).getName())) {
                pvOptions1.setSelectOptions(i);
                return;
            }
        }
    }


    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
        dismissLoadingDialog();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pickerViewUtil != null && pickerViewUtil.isShowing()) {
            pickerViewUtil.onDismiss();
        }
        pickerViewUtil = null;
        images.clear();
        pvOptions = null;
        pvOptions1 = null;
        samplePictureBouncedDialog = null;
        images = null;
        pvTime = null;
        pvTime1 = null;
    }
}
