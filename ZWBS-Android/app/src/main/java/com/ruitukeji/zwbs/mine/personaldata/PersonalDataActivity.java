package com.ruitukeji.zwbs.mine.personaldata;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.UploadImageBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 个人资料
 * Created by Administrator on 2017/2/10.
 */

public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(id = R.id.img_user)
    private ImageView img_user;

    @BindView(id = R.id.ll_upload, click = true)
    private LinearLayout ll_upload;

    @BindView(id = R.id.ll_userName)
    private LinearLayout ll_userName;

    @BindView(id = R.id.tv_name, click = true)
    private TextView tv_name;

    @BindView(id = R.id.tv_phone)
    private TextView tv_phone;

    @BindView(id = R.id.ll_accountDeposit, click = true)
    private LinearLayout ll_accountDeposit;

    @BindView(id = R.id.tv_accountDeposit)
    private TextView tv_accountDeposit;

    private ImagePicker imagePicker;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personaldata);
    }

    @Override
    public void initData() {
        super.initData();
        initImagePicker();
        mPresenter = new PersonalDataPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.personalData), true, R.id.titlebar);
        //最好放到 Application oncreate执行
        String avatar = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "avatar");

        GlideImageLoader.glideLoader(KJActivityStack.create().topActivity(), avatar + "?imageView2/1/w/70/h/70", img_user, 0);

        String phone = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "phone");
        tv_phone.setText(phone);
        String real_name = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "real_name");
        if (StringUtils.isEmpty(real_name)) {
            ll_userName.setVisibility(View.GONE);
            tv_name.setText(real_name);
        } else {
            ll_userName.setVisibility(View.VISIBLE);
            tv_name.setText(real_name);
        }
    }

    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(false);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        int width = 300;
        int height = 300;
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, getResources().getDisplayMetrics());
        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources().getDisplayMetrics());
        imagePicker.setFocusWidth(width);
        imagePicker.setFocusHeight(height);
        imagePicker.setOutPutX(width);
        imagePicker.setOutPutY(height);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_upload:
                choicePhotoWrapper();
                break;
            case R.id.ll_accountDeposit:
                //  showActivity(aty, PayDepositActivity.class);
                break;
            case R.id.tv_name:
                //  img_user.setImageResource(R.mipmap.certification2);
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

    @Override
    public void setPresenter(PersonalDataContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s) {
        UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
        if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
            GlideImageLoader.glideLoader(this, uploadImageBean.getResult().getFile().getUrl() + "?imageView2/1/w/70/h/70", img_user, 0);
            PreferenceHelper.write(this, StringConstants.FILENAME, "avatar", uploadImageBean.getResult().getFile().getUrl());
            PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshAvatar", true);
//                /**
//                 * 发送消息
//                 */
//                MsgEvent msgEvent = new MsgEvent<String>("RxBusAvatarEvent");
//                msgEvent.setMsg(uploadImageBean.getResult().getFile().getUrl());
//                RxBus.getInstance().post(msgEvent);
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            showActivity(aty, LoginActivity.class);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
