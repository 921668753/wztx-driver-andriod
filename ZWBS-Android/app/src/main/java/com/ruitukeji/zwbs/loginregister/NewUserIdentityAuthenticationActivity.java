package com.ruitukeji.zwbs.loginregister;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;


import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 新用户注册----身份认证
 * Created by Administrator on 2017/2/15.
 */

public class NewUserIdentityAuthenticationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, NewUserIdentityAuthenticationContract.View {

    @BindView(id = R.id.img_idCardIs, click = true)
    private ImageView img_idCardIs;

    @BindView(id = R.id.img_idCardBack, click = true)
    private ImageView img_idCardBack;

    //判断点击了那个按钮

    private ImagePicker imagePicker;
    /**
     * 下一步
     */
    @BindView(id = R.id.tv_nextType, click = true)
    private TextView tv_nextType;

    private NewUserIdentityAuthenticationContract.Presenter mPresenter;
    private boolean isPreviewIs = false;
    private boolean isPreviewIsBack = false;

    private String imgUrl = null;
    private String imgUrlBack = null;
    private int sex;
    private String real_name;
    private String identity;
    private String adress;
    private int logistics_type;
    private String hold_pic;
    private String auth_status = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newuseridentityauthentication);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new NewUserIdentityAuthenticationPresenter(this);
        images = new ArrayList<>();
        initImagePicker();
    }

    @Override
    public void initWidget() {
        super.initWidget();
      //  ActivityTitleUtils.initToolbar(aty, getString(R.string.newUserRegister), true, R.id.titlebar);
        real_name = getIntent().getStringExtra("real_name");
        sex = getIntent().getIntExtra("sex", 1);
        identity = getIntent().getStringExtra("identity");
        adress = getIntent().getStringExtra("address");
        logistics_type = getIntent().getIntExtra("logistics_type", 1);
        hold_pic = getIntent().getStringExtra("hold_pic");
        auth_status = getIntent().getStringExtra("auth_status");
        if (!(StringUtils.isEmpty(auth_status) || auth_status.equals("init"))) {
            imgUrl = getIntent().getStringExtra("front_pic");
            isPreviewIs = true;
            GlideImageLoader.glideLoader(this, imgUrl + "?imageView2/1/w/300/h/150", img_idCardIs, 1);
            imgUrlBack = getIntent().getStringExtra("back_pic");
            isPreviewIsBack = true;
            GlideImageLoader.glideLoader(this, imgUrlBack + "?imageView2/1/w/300/h/150", img_idCardBack, 1);
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
            case R.id.img_idCardIs:
                if (isPreviewIs) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrl;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_idCardIs, images, NumericConstants.REQUEST_CODE_PREVIEW);
                } else {
                    choicePhotoWrapper(NumericConstants.REQUEST_CODE_CHOOSE_PHOTO);
                }
                break;
            case R.id.img_idCardBack:
                if (isPreviewIsBack) {
                    images.clear();
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = imgUrlBack;
                    images.add(imageItem);
                    //打开预览
                    toImagePreviewDelActivity(img_idCardBack, images, NumericConstants.REQUEST_CODE_PREVIEW1);
                } else {
                    choicePhotoWrapper(NumericConstants.REQUEST_CODE_PHOTO_PREVIEW);
                }
                break;
            case R.id.tv_nextType:
                ((NewUserIdentityAuthenticationContract.Presenter) mPresenter).postIdentityAuthentication(real_name, sex, identity, adress, logistics_type, hold_pic, imgUrl, imgUrlBack);
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == NumericConstants.REQUEST_CODE_CHOOSE_PHOTO) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreviewIs = true;
                imgUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrl + "?imageView2/1/w/300/h/150", img_idCardIs, 1);
            }
        } else if (flag == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                isPreviewIsBack = true;
                imgUrlBack = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideLoader(this, imgUrlBack + "?imageView2/1/w/300/h/150", img_idCardBack, 1);
            }
        } else if (flag == 0) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", true);
            Intent intent = new Intent(aty, NewUserVehicleCertificationActivity.class);
            intent.putExtra("auth_status", auth_status);
            showActivity(aty, intent);
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(NewUserIdentityAuthenticationContract.Presenter presenter) {
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
            ((NewUserIdentityAuthenticationContract.Presenter) mPresenter).upLoadImg(images.get(0).path, NumericConstants.REQUEST_CODE_CHOOSE_PHOTO);
        } else if (data != null && requestCode == NumericConstants.REQUEST_CODE_PHOTO_PREVIEW) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            ((NewUserIdentityAuthenticationContract.Presenter) mPresenter).upLoadImg(images.get(0).path, NumericConstants.REQUEST_CODE_PHOTO_PREVIEW);
        } else {
            ViewInject.toast(getString(R.string.noData));
        }
    }


    //预览图片返回
    @SuppressWarnings("unchecked")
    private void previewImageReturn(int requestCode, Intent data) {
        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
        //预览图片返回
        if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW && images.size() == 0) {
            isPreviewIs = false;
            imgUrl = null;
            GlideImageLoader.glideLoader(this, R.mipmap.idcardis, img_idCardIs, 1);
        } else if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW1 && images.size() == 0) {
            isPreviewIsBack = false;
            imgUrlBack = null;
            GlideImageLoader.glideLoader(this, R.mipmap.idcardback, img_idCardBack, 1);
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
    }
}
