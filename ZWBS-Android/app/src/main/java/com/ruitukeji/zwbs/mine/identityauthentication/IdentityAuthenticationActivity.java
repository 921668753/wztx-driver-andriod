package com.ruitukeji.zwbs.mine.identityauthentication;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.mine.personaldata.PersonalDataContract;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 身份认证
 * Created by Administrator on 2017/11/30.
 */

public class IdentityAuthenticationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

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
    @BindView(id = R.id.ll_validityIdentityCard)
    private LinearLayout ll_validityIdentityCard;
    @BindView(id = R.id.tv_validityIdentityCard)
    private TextView tv_validityIdentityCard;

    private TimePickerView pvTime;
    Calendar calendar = Calendar.getInstance();

    /**
     * 身份证正
     */
    @BindView(id = R.id.img_uploadYourIdCard)
    private ImageView img_uploadYourIdCard;

    /**
     * 身份证反
     */
    @BindView(id = R.id.img_uploadClearYourIdCard)
    private ImageView img_uploadClearYourIdCard;

    /**
     * 手持身份证
     */
    @BindView(id = R.id.img_uploudHoldingIdPhoto)
    private ImageView img_uploudHoldingIdPhoto;

    /**
     * 驾驶证有效期
     */
    @BindView(id = R.id.ll_validityDrivingLicence, click = true)
    private LinearLayout ll_validityDrivingLicence;
    @BindView(id = R.id.tv_validityDrivingLicence)
    private TextView tv_validityDrivingLicence;


    /**
     * 驾驶证
     */
    @BindView(id = R.id.img_uploadDrivingLicensePhotos)
    private ImageView img_uploadDrivingLicensePhotos;

    /**
     * 道路运输从业资格证
     */
    @BindView(id = R.id.ll_roadTransportValidityPeriod, click = true)
    private LinearLayout ll_roadTransportValidityPeriod;
    @BindView(id = R.id.tv_roadTransportValidityPeriod)
    private TextView tv_roadTransportValidityPeriod;
    @BindView(id = R.id.img_uploadRoadQualification)
    private ImageView img_uploadRoadQualification;


    /**
     * 诚信考核记录
     */
    @BindView(id = R.id.img_uploadIntegrityAssessment)
    private ImageView img_uploadIntegrityAssessment;

    private ImagePicker imagePicker;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_identityauthentication);
    }

    @Override
    public void initData() {
        super.initData();
        initImagePicker();
        asOfTheDatePicker();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.identityAuthentication), true, R.id.titlebar);
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
                //  pvTime.setDate(Calendar.getInstance());
                //弹出时间选择器
                pvTime.show(tv_validityIdentityCard);
                break;
            case R.id.img_uploadYourIdCard:


                break;
            case R.id.img_uploadClearYourIdCard:


                break;
            case R.id.img_uploudHoldingIdPhoto:


                break;
            case R.id.ll_validityDrivingLicence:
                pvTime.setDate(calendar);
                //  pvTime.setDate(Calendar.getInstance());
                //弹出时间选择器
                pvTime.show(tv_validityDrivingLicence);
                break;
            case R.id.img_uploadDrivingLicensePhotos:


                break;

            case R.id.ll_roadTransportValidityPeriod:
                pvTime.setDate(calendar);
                //  pvTime.setDate(Calendar.getInstance());
                //弹出时间选择器
                pvTime.show(tv_roadTransportValidityPeriod);
                break;
            case R.id.img_uploadRoadQualification:

                break;
            case R.id.img_uploadIntegrityAssessment:

                break;
            default:
                break;

        }
    }

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            Intent intent = new Intent(this, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
            //ImagePicker.getInstance().setSelectedImages(images);
            startActivityForResult(intent, NumericConstants.REQUEST_CODE_CHOOSE_PHOTO);
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
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_CHOOSE_PHOTO) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images == null || images.size() == 0) {
                    ViewInject.toast(getString(R.string.noData));
                    return;
                }
                String filePath = images.get(0).path;
                ((PersonalDataContract.Presenter) mPresenter).postUpLoadImg(filePath);
            } else {
                ViewInject.toast(getString(R.string.noData));
            }
        }
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
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() <= System.currentTimeMillis()) {
                    ViewInject.toast(getString(R.string.aboveCurrentTime));
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //  long time = StringUtils.toLong(DataUtil.getStringTime(format.format(date))) / 1000;
                if (v.getId() == R.id.tv_closingDatedriverLicense) {
                    //  driving_deadline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_registrationDeadline) {
                    //   license_deadline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_policydeadline1) {
                    //   policy_deadline = date.getTime() / 1000;
                } else if (v.getId() == R.id.tv_closingDateOperationCertificate) {
                    //   operation_deadline = date.getTime() / 1000;
                }
                ((TextView) v).setText(format.format(date));
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setContentSize(20)
                .setRangDate(startDate, endDate)
                .build();
    }


}
