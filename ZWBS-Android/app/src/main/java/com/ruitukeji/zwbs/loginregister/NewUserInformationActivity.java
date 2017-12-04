package com.ruitukeji.zwbs.loginregister;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.ruitukeji.zwbs.entity.PersonalCertificateBean;
import com.ruitukeji.zwbs.entity.UploadImageBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.PickerViewUtil;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 新用户注册----基本信息
 * Created by Administrator on 2017/2/15.
 */

public class NewUserInformationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, NewUserInformationContract.View {

    /**
     * 姓名
     */
    @BindView(id = R.id.et_realName)
    private EditText et_realName;

    /**
     * 性别选择
     */
    @BindView(id = R.id.img_man, click = true)
    private ImageView img_man;
    @BindView(id = R.id.img_woman, click = true)
    private ImageView img_woman;

    private int sex = 1;

    /**
     * 物流类型选择
     */
    @BindView(id = R.id.img_cityLogistics, click = true)
    private ImageView img_cityLogistics;
    @BindView(id = R.id.img_longDistanceLogistics, click = true)
    private ImageView img_longDistanceLogistics;
    private int logistics_type = 1;
    private int logistics_type1 = 4;
    private int logistics_type2 = 0;
    /**
     * 身份证号
     */
    @BindView(id = R.id.et_IdNumber)
    private EditText et_IdNumber;

    /**
     * 常住地址
     */
    @BindView(id = R.id.tv_permanentAddress, click = true)
    private TextView tv_permanentAddress;

    /**
     * 手持身份证照
     */
    @BindView(id = R.id.img_holdingIdentityCertification, click = true)
    private ImageView img_holdingIdentityCertification;
    /**
     * 下一步
     */
    @BindView(id = R.id.tv_nextType, click = true)
    private TextView tv_nextType;

    private ImagePicker imagePicker;

    private NewUserInformationContract.Presenter mPresenter;
    private boolean isPreview = false;
    private String imgUrl = null;
    private String auth_status = null;
    private String front_pic = null;
    private String back_pic = null;

    private PickerViewUtil pickerViewUtil;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newuserinformation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new NewUserInformationPresenter(this);
        images = new ArrayList<>();
        initImagePicker();
        //选项选择器
        initPickerView();
    }

    @Override
    public void initWidget() {
        super.initWidget();
       // ActivityTitleUtils.initToolbar(aty, getString(R.string.newUserRegister), true, R.id.titlebar);
        auth_status = getIntent().getStringExtra("auth_status");
        if (!(StringUtils.isEmpty(auth_status) || auth_status.equals("init"))) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((NewUserInformationContract.Presenter) mPresenter).getPersonalCertificate();
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

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_man:
                img_man.setImageResource(R.mipmap.selected);
                img_woman.setImageResource(R.mipmap.selected1);
                sex = 1;
                break;

            case R.id.img_woman:
                img_man.setImageResource(R.mipmap.selected1);
                img_woman.setImageResource(R.mipmap.selected);
                sex = 2;
                break;

            case R.id.img_cityLogistics:
                if (logistics_type == 3) {
                    img_cityLogistics.setImageResource(R.mipmap.selected);
                    //   img_longDistanceLogistics.setImageResource(R.mipmap.selected1);
                    logistics_type = 1;
                } else {
                    img_cityLogistics.setImageResource(R.mipmap.selected1);
                    logistics_type = 3;
                }
                break;
            case R.id.img_longDistanceLogistics:
                if (logistics_type1 == 4) {
                    img_longDistanceLogistics.setImageResource(R.mipmap.selected);
                    logistics_type1 = 2;
                } else {
                    img_longDistanceLogistics.setImageResource(R.mipmap.selected1);
                    logistics_type1 = 4;
                }
                break;
            case R.id.tv_permanentAddress:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }
                //点击弹出选项选择器
                pickerViewUtil.onoptionsSelectListener();
                break;

            case R.id.img_holdingIdentityCertification:
                if (isPreview) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrl;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_holdingIdentityCertification, images);
                } else {
                    choicePhotoWrapper();
                }
                break;
            case R.id.tv_nextType:
                ((NewUserInformationContract.Presenter) mPresenter).postInformation(et_realName.getText().toString(), sex, et_IdNumber.getText().toString(), tv_permanentAddress.getText().toString(), logistics_type, imgUrl);
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            if (logistics_type == 1 && logistics_type1 == 2) {
                logistics_type2 = 0;
            } else if (logistics_type == 1 && logistics_type1 == 4) {
                logistics_type2 = 1;
            } else if (logistics_type == 3 && logistics_type1 == 2) {
                logistics_type2 = 2;
            } else {
                ViewInject.toast("请选择物流类型");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("real_name", et_realName.getText().toString());
            intent.putExtra("sex", sex);
            intent.putExtra("identity", et_IdNumber.getText().toString());
            intent.putExtra("address", tv_permanentAddress.getText().toString());
            intent.putExtra("logistics_type", logistics_type2);
            intent.putExtra("hold_pic", imgUrl);
            intent.putExtra("auth_status", auth_status);
            intent.putExtra("front_pic", front_pic);
            intent.putExtra("back_pic", back_pic);
            intent.setClass(aty, NewUserIdentityAuthenticationActivity.class);
            showActivity(aty, intent);
        } else if (flag == 1) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreview = true;
                imgUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrl + "?imageView2/1/w/150/h/150", img_holdingIdentityCertification, 1);
            }
        } else if (flag == 2) {
            PersonalCertificateBean personalCertificateBean = (PersonalCertificateBean) JsonUtil.json2Obj(s, PersonalCertificateBean.class);

            personalCertificateBean.getResult().getSex();
            et_realName.setText(personalCertificateBean.getResult().getReal_name());


//            tv_realName.setText(personalCertificateBean.getResult().getReal_name());
//            tv_bindingMobilePhone.setText(personalCertificateBean.getResult().getPhone());
//            tv_idNumber.setText(personalCertificateBean.getResult().getIdentity());
            if (personalCertificateBean.getResult().getSex() == 1) {
                img_man.setImageResource(R.mipmap.selected);
                img_woman.setImageResource(R.mipmap.selected1);
                sex = 1;
            } else if (personalCertificateBean.getResult().getSex() == 2) {
                img_man.setImageResource(R.mipmap.selected1);
                img_woman.setImageResource(R.mipmap.selected);
                sex = 2;
            } else {
                img_man.setImageResource(R.mipmap.selected);
                img_woman.setImageResource(R.mipmap.selected1);
                sex = 1;
            }
            et_IdNumber.setText(personalCertificateBean.getResult().getIdentity());
            tv_permanentAddress.setText(personalCertificateBean.getResult().getAddress());

            if (personalCertificateBean.getResult().getLogistics_type() == 0) {
                img_cityLogistics.setImageResource(R.mipmap.selected);
                img_longDistanceLogistics.setImageResource(R.mipmap.selected);
                logistics_type2 = 0;
                logistics_type = 1;
                logistics_type1 = 2;
            } else if (personalCertificateBean.getResult().getLogistics_type() == 1) {
                img_cityLogistics.setImageResource(R.mipmap.selected);
                img_longDistanceLogistics.setImageResource(R.mipmap.selected1);
                logistics_type2 = 1;
                logistics_type = 1;
                logistics_type1 = 4;
            } else if (personalCertificateBean.getResult().getLogistics_type() == 2) {
                img_cityLogistics.setImageResource(R.mipmap.selected1);
                img_longDistanceLogistics.setImageResource(R.mipmap.selected);
                logistics_type2 = 2;
                logistics_type = 3;
                logistics_type1 = 2;
            } else {
                img_cityLogistics.setImageResource(R.mipmap.selected);
                img_longDistanceLogistics.setImageResource(R.mipmap.selected1);
                logistics_type2 = 1;
                logistics_type = 1;
                logistics_type1 = 4;
            }
            isPreview = true;
            imgUrl = personalCertificateBean.getResult().getHold_pic();
            GlideImageLoader.glideLoader(this, imgUrl + "?imageView2/1/w/150/h/150", img_holdingIdentityCertification, 1);
            front_pic = personalCertificateBean.getResult().getFront_pic();
            back_pic = personalCertificateBean.getResult().getBack_pic();
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(NewUserInformationContract.Presenter presenter) {
        mPresenter = presenter;
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
            //选择图片返回
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
        }
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
            ((NewUserInformationContract.Presenter) mPresenter).upLoadImg(images.get(0).path);
        } else {
            ViewInject.toast(getString(R.string.noData));
        }
    }


    //打开预览
    @SuppressWarnings("unchecked")
    private void toImagePreviewDelActivity(View view, ArrayList<ImageItem> list) {
        Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, list);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
    }

    //预览图片返回
    @SuppressWarnings("unchecked")
    private void previewImageReturn(int requestCode, Intent data) {
        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
        //预览图片返回
        if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW && images.size() == 0) {
            isPreview = false;
            imgUrl = null;
            GlideImageLoader.glideLoader(this, R.mipmap.holdingidentitycertification, img_holdingIdentityCertification, 1);
        }
    }


    /**
     * 选项选择器
     */
    private void initPickerView() {
        if (pickerViewUtil == null) {
            pickerViewUtil = new PickerViewUtil(this, 0) {
                @Override
                public void getAddress(String address) {
                    tv_permanentAddress.setText(address);
                }
            };
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pickerViewUtil != null && pickerViewUtil.isShowing()) {
            pickerViewUtil.onDismiss();
        }
        pickerViewUtil = null;
        images.clear();
        images = null;
    }
}
