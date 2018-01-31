package com.ruitukeji.zwbs.mine.identityauthentication;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
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
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.UploadImageBean;
import com.ruitukeji.zwbs.entity.mine.identityauthentication.IdentityAuthenticationBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.vehiclecertification.dialog.SamplePictureBouncedDialog;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.DataUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
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

/**
 * 身份认证
 * Created by Administrator on 2017/11/30.
 */

public class IdentityAuthenticationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, IdentityAuthenticationContract.View {

    /**
     * 认证状态
     */
    @BindView(id = R.id.tv_certificationStatus)
    private TextView tv_certificationStatus;

    /**
     * 头像
     */
    @BindView(id = R.id.ll_uploadHeadPortrait, click = true)
    private LinearLayout ll_uploadHeadPortrait;
    @BindView(id = R.id.img_user)
    private ImageView img_user;

    /**
     * 姓名
     */
    @BindView(id = R.id.et_name)
    private EditText et_name;

    /**
     * 身份账号
     */
    @BindView(id = R.id.et_IdNumber)
    private EditText et_IdNumber;

    /**
     * 身份证有效期
     */
    @BindView(id = R.id.ll_validityIdentityCard, click = true)
    private LinearLayout ll_validityIdentityCard;
    @BindView(id = R.id.tv_validityIdentityCard)
    private TextView tv_validityIdentityCard;

    private TimePickerView pvTime;
    Calendar calendar = Calendar.getInstance();

    /**
     * 身份证正
     */
    @BindView(id = R.id.img_uploadYourIdCard, click = true)
    private ImageView img_uploadYourIdCard;
    String uploadYourIdCardUrl = "";
    private boolean isUploadYourIdCard = true;
    @BindView(id = R.id.img_idcardFront, click = true)
    private ImageView img_idcardFront;
    /**
     * 身份证反
     */
    @BindView(id = R.id.img_uploadClearYourIdCard, click = true)
    private ImageView img_uploadClearYourIdCard;
    String uploadClearYourIdCardUrl = "";
    private boolean isUploadClearYourIdCard = true;
    @BindView(id = R.id.img_idcardBack, click = true)
    private ImageView img_idcardBack;

    /**
     * 手持身份证
     */
    @BindView(id = R.id.img_uploudHoldingIdPhoto, click = true)
    private ImageView img_uploudHoldingIdPhoto;
    @BindView(id = R.id.img_idcardInhand, click = true)
    private ImageView img_idcardInhand;
    String uploudHoldingIdPhotoUrl = "";
    private boolean isUploudHoldingIdPhoto = true;
    /**
     * 驾驶证有效期
     */
    @BindView(id = R.id.ll_validityDrivingLicence, click = true)
    private LinearLayout ll_validityDrivingLicence;
    @BindView(id = R.id.tv_validityDrivingLicence)
    private TextView tv_validityDrivingLicence;
    Calendar calendar1 = Calendar.getInstance();

    /**
     * 驾驶证
     */
    @BindView(id = R.id.img_uploadDrivingLicensePhotos, click = true)
    private ImageView img_uploadDrivingLicensePhotos;
    String uploadDrivingLicensePhotosUrl = "";
    private boolean isUploadDrivingLicensePhotos = true;
    @BindView(id = R.id.img_driverLicence, click = true)
    private ImageView img_driverLicence;

    /**
     * 道路运输从业资格证
     */
    @BindView(id = R.id.ll_roadTransportValidityPeriod, click = true)
    private LinearLayout ll_roadTransportValidityPeriod;
    @BindView(id = R.id.tv_roadTransportValidityPeriod)
    private TextView tv_roadTransportValidityPeriod;
    @BindView(id = R.id.img_uploadRoadQualification, click = true)
    private ImageView img_uploadRoadQualification;
    @BindView(id = R.id.img_roadQualification, click = true)
    private ImageView img_roadQualification;

    String uploadRoadQualificationUrl = "";
    private boolean isUploadRoadQualification = true;
    Calendar calendar2 = Calendar.getInstance();

    /**
     * 诚信考核记录
     */
    @BindView(id = R.id.img_uploadIntegrityAssessment, click = true)
    private ImageView img_uploadIntegrityAssessment;
    @BindView(id = R.id.img_integrityAssessment, click = true)
    private ImageView img_integrityAssessment;
    String uploadIntegrityAssessmentUrl = "";
    private boolean isUploadIntegrityAssessment = true;
    private ImagePicker imagePicker;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;


    public long validityIdentityCard = 0;
    public long validityDrivingLicence = 0;
    public long roadTransportValidityPeriod = 0;
    private SamplePictureBouncedDialog samplePictureBouncedDialog;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_identityauthentication);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new IdentityAuthenticationPresenter(this);
        images = new ArrayList<>();
        samplePictureBouncedDialog = new SamplePictureBouncedDialog(this, R.mipmap.pic_car_front, getString(R.string.licensePlatesClear));
        initImagePicker();
        asOfTheDatePicker();
        String auth_status = PreferenceHelper.readString(aty, StringConstants.FILENAME, "auth_status", "init");
        if (auth_status != null && auth_status.equals("init")) {
            return;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((IdentityAuthenticationContract.Presenter) mPresenter).getDriverAuthInfo();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.identityAuthentication), true, R.id.titlebar);
        String auth_status = PreferenceHelper.readString(aty, StringConstants.FILENAME, "auth_status", "init");
        if (auth_status != null && auth_status.equals("pass")) {
            tv_certificationStatus.setText(getString(R.string.certified));
        } else if (auth_status != null && auth_status.equals("check")) {
            tv_certificationStatus.setText(getString(R.string.inAuthentication));
        } else if (auth_status != null && auth_status.equals("refuse")) {
            tv_certificationStatus.setText(getString(R.string.authenticationFailure));
        } else {
            tv_certificationStatus.setText(getString(R.string.unauthorized));
        }
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

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_validityIdentityCard:
                pvTime.setDate(calendar);
                //弹出时间选择器
                pvTime.show(tv_validityIdentityCard);
                break;
            case R.id.img_uploadYourIdCard:
                if (isUploadYourIdCard) {
                    choicePhotoWrapper(REQUEST_CODE_CHOOSE_PHOTO);
                    break;
                }
                images.clear();
                ImageItem imageItem = new ImageItem();
                imageItem.path = uploadYourIdCardUrl;
                images.add(imageItem);
                //打开预览
                toImagePreviewDelActivity(img_uploadYourIdCard, images, NumericConstants.REQUEST_CODE_PREVIEW);
                break;
            case R.id.img_idcardFront:
                show(R.mipmap.pic_idcard_front, getString(R.string.makeClear));
                break;
            case R.id.img_uploadClearYourIdCard:
                if (isUploadClearYourIdCard) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW);
                    break;
                }
                images.clear();
                ImageItem imageItem1 = new ImageItem();
                imageItem1.path = uploadClearYourIdCardUrl;
                images.add(imageItem1);
                //打开预览
                toImagePreviewDelActivity(img_uploadClearYourIdCard, images, NumericConstants.REQUEST_CODE_PREVIEW1);
                break;
            case R.id.img_idcardBack:
                show(R.mipmap.pic_idcard_back, getString(R.string.makeClear));
                break;
            case R.id.img_uploudHoldingIdPhoto:
                if (isUploudHoldingIdPhoto) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW1);
                    break;
                }
                images.clear();
                ImageItem imageItem2 = new ImageItem();
                imageItem2.path = uploudHoldingIdPhotoUrl;
                images.add(imageItem2);
                //打开预览
                toImagePreviewDelActivity(img_uploudHoldingIdPhoto, images, NumericConstants.REQUEST_CODE_PREVIEW2);
                break;
            case R.id.img_idcardInhand:
                show(R.mipmap.pic_idcard_inhand, getString(R.string.makeClear));
                break;
            case R.id.ll_validityDrivingLicence:
                pvTime.setDate(calendar1);
                //弹出时间选择器
                pvTime.show(tv_validityDrivingLicence);
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

            case R.id.img_driverLicence:
                show(R.mipmap.pic_driver_licence, getString(R.string.makeClear));
                break;
            case R.id.ll_roadTransportValidityPeriod:
                pvTime.setDate(calendar2);
                //弹出时间选择器
                pvTime.show(tv_roadTransportValidityPeriod);
                break;
            case R.id.img_uploadRoadQualification:
                if (isUploadRoadQualification) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW3);
                    break;
                }
                images.clear();
                ImageItem imageItem4 = new ImageItem();
                imageItem4.path = uploadRoadQualificationUrl;
                images.add(imageItem4);
                //打开预览
                toImagePreviewDelActivity(img_uploadRoadQualification, images, NumericConstants.REQUEST_CODE_PREVIEW4);
                break;
            case R.id.img_roadQualification:
                show(R.mipmap.congyezigezhao, getString(R.string.makeClear));
                break;
            case R.id.img_uploadIntegrityAssessment:
                if (isUploadIntegrityAssessment) {
                    choicePhotoWrapper(REQUEST_CODE_PHOTO_PREVIEW4);
                    break;
                }
                images.clear();
                ImageItem imageItem5 = new ImageItem();
                imageItem5.path = uploadRoadQualificationUrl;
                images.add(imageItem5);
                //打开预览
                toImagePreviewDelActivity(img_uploadIntegrityAssessment, images, NumericConstants.REQUEST_CODE_PREVIEW5);
                break;
            case R.id.img_integrityAssessment:
                show(R.mipmap.chengxinkaohezhao, getString(R.string.makeClear));
                break;
            case R.id.tv_submit:
                ((IdentityAuthenticationContract.Presenter) mPresenter).postIdentityAuthentication(et_name.getText().toString().trim(), et_IdNumber.getText().toString().trim(), validityIdentityCard,
                        uploadYourIdCardUrl, uploadClearYourIdCardUrl, uploudHoldingIdPhotoUrl, validityDrivingLicence, uploadDrivingLicensePhotosUrl, roadTransportValidityPeriod,
                        uploadRoadQualificationUrl, uploadIntegrityAssessmentUrl);
                break;
            default:
                break;
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
                showLoadingDialog(getString(R.string.crossLoad));
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
        }
    }

    //预览图片返回
    @SuppressWarnings("unchecked")
    private void previewImageReturn(int requestCode, Intent data) {
        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
        //预览图片返回
        if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW && images.size() == 0) {
            isUploadYourIdCard = true;
            uploadYourIdCardUrl = null;
            img_uploadYourIdCard.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW1 && images.size() == 0) {
            isUploadClearYourIdCard = true;
            uploadClearYourIdCardUrl = null;
            img_uploadClearYourIdCard.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW2 && images.size() == 0) {
            isUploudHoldingIdPhoto = true;
            uploudHoldingIdPhotoUrl = null;
            img_uploudHoldingIdPhoto.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW3 && images.size() == 0) {
            isUploadDrivingLicensePhotos = true;
            uploadDrivingLicensePhotosUrl = null;
            img_uploadDrivingLicensePhotos.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW4 && images.size() == 0) {
            isUploadRoadQualification = true;
            uploadRoadQualificationUrl = null;
            img_uploadRoadQualification.setImageDrawable(null);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW5 && images.size() == 0) {
            isUploadIntegrityAssessment = true;
            uploadIntegrityAssessmentUrl = null;
            img_uploadIntegrityAssessment.setImageDrawable(null);
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
                if (v.getId() == R.id.tv_validityIdentityCard) {
                    validityIdentityCard = date.getTime() / 1000;
                    calendar.setTime(date);
                } else if (v.getId() == R.id.tv_validityDrivingLicence) {
                    validityDrivingLicence = date.getTime() / 1000;
                    calendar1.setTime(date);
                } else if (v.getId() == R.id.tv_roadTransportValidityPeriod) {
                    roadTransportValidityPeriod = date.getTime() / 1000;
                    calendar2.setTime(date);
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
    public void setPresenter(IdentityAuthenticationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            IdentityAuthenticationBean identityAuthenticationBean = (IdentityAuthenticationBean) JsonUtil.getInstance().json2Obj(success, IdentityAuthenticationBean.class);
            et_name.setText(identityAuthenticationBean.getResult().getReal_name());
            et_IdNumber.setText(identityAuthenticationBean.getResult().getIdentity());
            uploadYourIdCardUrl = identityAuthenticationBean.getResult().getFront_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadYourIdCardUrl + "?imageView2/1/w/161/h/103", img_uploadYourIdCard);
            isUploadYourIdCard = false;
            validityIdentityCard = StringUtils.toLong(identityAuthenticationBean.getResult().getIdentity_pic_time());
            String d = DataUtil.formatData(validityIdentityCard, "yyyy-MM-dd");
            tv_validityIdentityCard.setText(d);
            Date date = new Date(validityIdentityCard * 1000);
            calendar.setTime(date);
            uploadClearYourIdCardUrl = identityAuthenticationBean.getResult().getBack_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadClearYourIdCardUrl + "?imageView2/1/w/161/h/103", img_uploadClearYourIdCard);
            isUploadClearYourIdCard = false;
            uploudHoldingIdPhotoUrl = identityAuthenticationBean.getResult().getHold_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploudHoldingIdPhotoUrl + "?imageView2/1/w/161/h/103", img_uploudHoldingIdPhoto);
            isUploudHoldingIdPhoto = false;
            validityDrivingLicence = StringUtils.toLong(identityAuthenticationBean.getResult().getLicense_pic_time());
            String d1 = DataUtil.formatData(validityDrivingLicence, "yyyy-MM-dd");
            tv_validityDrivingLicence.setText(d1);
            Date date1 = new Date(validityDrivingLicence * 1000);
            calendar1.setTime(date1);
            uploadDrivingLicensePhotosUrl = identityAuthenticationBean.getResult().getLicense_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadDrivingLicensePhotosUrl + "?imageView2/1/w/161/h/103", img_uploadDrivingLicensePhotos);
            isUploadDrivingLicensePhotos = false;
            roadTransportValidityPeriod = StringUtils.toLong(identityAuthenticationBean.getResult().getTransport_pic_time());
            String d2 = DataUtil.formatData(roadTransportValidityPeriod, "yyyy-MM-dd");
            tv_roadTransportValidityPeriod.setText(d2);
            Date date2 = new Date(roadTransportValidityPeriod * 1000);
            calendar2.setTime(date2);
            uploadRoadQualificationUrl = identityAuthenticationBean.getResult().getTransport_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadRoadQualificationUrl + "?imageView2/1/w/161/h/103", img_uploadRoadQualification);
            isUploadRoadQualification = false;
            uploadIntegrityAssessmentUrl = identityAuthenticationBean.getResult().getFaith_pic();
            GlideImageLoader.glideOrdinaryLoader(this, uploadIntegrityAssessmentUrl + "?imageView2/1/w/161/h/103", img_uploadIntegrityAssessment);
            isUploadIntegrityAssessment = false;
        } else if (flag == 1) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "auth_status", "check");
            ViewInject.toast(getString(R.string.submittedSuccessfully));
            tv_certificationStatus.setText(getString(R.string.inAuthentication));
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusIdentityAuthenticationEvent"));
            dismissLoadingDialog();
            aty.finish();
            return;
        } else if (flag == REQUEST_CODE_CHOOSE_PHOTO) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadYourIdCardUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadYourIdCardUrl + "?imageView2/1/w/161/h/103", img_uploadYourIdCard);
                isUploadYourIdCard = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadClearYourIdCardUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadClearYourIdCardUrl + "?imageView2/1/w/161/h/103", img_uploadClearYourIdCard);
                isUploadClearYourIdCard = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW1) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploudHoldingIdPhotoUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploudHoldingIdPhotoUrl + "?imageView2/1/w/161/h/103", img_uploudHoldingIdPhoto);
                isUploudHoldingIdPhoto = false;
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
                uploadRoadQualificationUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadRoadQualificationUrl + "?imageView2/1/w/161/h/103", img_uploadRoadQualification);
                isUploadRoadQualification = false;
            }
        } else if (flag == REQUEST_CODE_PHOTO_PREVIEW4) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadIntegrityAssessmentUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadIntegrityAssessmentUrl + "?imageView2/1/w/161/h/103", img_uploadIntegrityAssessment);
                isUploadIntegrityAssessment = false;
            }
        }
        dismissLoadingDialog();
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
        images.clear();
        samplePictureBouncedDialog = null;
        images = null;
        pvTime = null;
    }
}
