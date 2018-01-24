package com.ruitukeji.zwbs.mission;

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
import com.ruitukeji.zwbs.entity.mission.UploadReceiptVoucherBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_CHOOSE_PHOTO;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_SELECT;

/**
 * 上传收货凭证
 * Created by Administrator on 2017/11/29.
 */

public class UploadReceiptVoucherActivity extends BaseActivity implements UploadReceiptVoucherContract.View, EasyPermissions.PermissionCallbacks {


    /**
     * 上传凭证地址
     */
    @BindView(id = R.id.tv_submissionCredentials)
    private TextView tv_submissionCredentials;

    /**
     * 上传图片
     */
    @BindView(id = R.id.img_uploadPictures, click = true)
    private ImageView img_uploadPictures;

    /**
     * 完成订单
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;

    private ImagePicker imagePicker;

    String uploadPicturesUrl = "";
    private boolean isUploadPictures = true;

    private ArrayList<ImageItem> images = null;
    private int order_id = 0;
    private int is_cargo_receipt = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_uploadreceiptvoucher);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new UploadReceiptVoucherPresenter(this);
        initImagePicker();
        order_id = getIntent().getIntExtra("order_id", 0);
        is_cargo_receipt = getIntent().getIntExtra("is_cargo_receipt", 0);
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
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.uploadReceiptVoucher), true, R.id.titlebar);
        if (is_cargo_receipt != 0) {
            tv_submit.setText(getString(R.string.submit));
        } else {
            tv_submit.setText(getString(R.string.completeOrder));
        }
        String currentLocationAddress = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationAddress", "");
        tv_submissionCredentials.setText(currentLocationAddress);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_uploadPictures:
                showLoadingDialog(getString(R.string.crossLoad));
                if (isUploadPictures) {
                    choicePhotoWrapper(REQUEST_CODE_CHOOSE_PHOTO);
                    break;
                }
                images.clear();
                ImageItem imageItem = new ImageItem();
                imageItem.path = uploadPicturesUrl;
                images.add(imageItem);
                //打开预览
                toImagePreviewDelActivity(images, NumericConstants.REQUEST_CODE_PREVIEW);
                break;
            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((UploadReceiptVoucherContract.Presenter) mPresenter).uploadCerPic(order_id, uploadPicturesUrl);
                break;
        }
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
                ((UploadReceiptVoucherContract.Presenter) mPresenter).postUpLoadImg(filePath, requestCode);
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
        } else if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {// 如果等于1
            Intent intent = new Intent();
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }

    //预览图片返回
    @SuppressWarnings("unchecked")
    private void previewImageReturn(int requestCode, Intent data) {
        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
        //预览图片返回
        if (data != null && images != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW && images.size() == 0) {
            isUploadPictures = true;
            uploadPicturesUrl = null;
            img_uploadPictures.setImageDrawable(null);
        }
    }

    //打开预览
    @SuppressWarnings("unchecked")
    private void toImagePreviewDelActivity(ArrayList<ImageItem> list, int code) {
        Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, list);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        startActivityForResult(intentPreview, code);
    }

    @Override
    public void setPresenter(UploadReceiptVoucherContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            UploadReceiptVoucherBean uploadReceiptVoucherBean = (UploadReceiptVoucherBean) JsonUtil.json2Obj(success, UploadReceiptVoucherBean.class);
            UploadReceiptVoucherBean.ResultBean resultBean = uploadReceiptVoucherBean.getResult();
            if (resultBean.getIs_cargo_receipt() == 0) {
                Intent intent = new Intent();
                // 设置结果 结果码，一个数据
                setResult(RESULT_OK, intent);
                // 结束该activity 结束之后，前面的activity才可以处理结果
                aty.finish();
                dismissLoadingDialog();
                return;
            }
            Intent intent = new Intent(aty, FillOutExpressActivity.class);
            intent.putExtra("order_id", order_id);
            intent.putExtra("cargo_address", resultBean.getCargo_address());
            intent.putExtra("cargo_address_detail", resultBean.getCargo_address_detail());
            intent.putExtra("cargo_is_express", resultBean.getCargo_is_express());
            intent.putExtra("cargo_man", resultBean.getCargo_man());
            intent.putExtra("cargo_tel", resultBean.getCargo_tel());
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else if (flag == REQUEST_CODE_CHOOSE_PHOTO) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                uploadPicturesUrl = uploadImageBean.getResult().getFile().getUrl();
                GlideImageLoader.glideOrdinaryLoader(this, uploadPicturesUrl + "?imageView2/1/w/300/h/150", img_uploadPictures);
                isUploadPictures = false;
            }
        }

        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (!toLigon1(msg)) {
            return;
        }
    }
}
