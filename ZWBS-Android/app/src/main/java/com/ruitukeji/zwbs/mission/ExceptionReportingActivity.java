package com.ruitukeji.zwbs.mission;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.ImagePickerAdapter;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
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
 * 异常上报
 * Created by Administrator on 2017/11/29.
 */

public class ExceptionReportingActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, ImagePickerAdapter.OnRecyclerViewItemClickListener, ExceptionReportingContract.View {

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    /**
     * 异常地点
     */
    @BindView(id = R.id.tv_abnormalLocation)
    private TextView tv_abnormalLocation;

    /**
     * 异常时间
     */
    @BindView(id = R.id.ll_abnormalTime, click = true)
    private LinearLayout ll_abnormalTime;
    @BindView(id = R.id.tv_abnormalTime)
    private TextView tv_abnormalTime;

    /**
     * 异常时间
     */
    @BindView(id = R.id.et_abnormalReason)
    private EditText et_abnormalReason;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;

    private ImagePickerAdapter adapter;

    private ArrayList<ImageItem> selImageList = null; //当前选择的所有图片

    private int maxImgCount = 3;
    private int order_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_exceptionreporting);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ExceptionReportingPresenter(this);
        order_id = getIntent().getIntExtra("order_id", 0);
        initView();
        initImagePicker();
    }

    private void initView() {
        selImageList = new ArrayList<ImageItem>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.exceptionReporting), true, R.id.titlebar);
        String currentLocationAddress = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationAddress", "");
        tv_abnormalLocation.setText(currentLocationAddress);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setMultiMode(true);
        // imagePicker.setShowCamera(false);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
//        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case NumericConstants.IMAGE_ITEM_ADD:
                choicePhotoWrapper();
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((ExceptionReportingContract.Presenter) mPresenter).postAbnormalInsert(order_id, selImageList, tv_abnormalLocation.getText().toString().trim(), et_abnormalReason.getText().toString().trim());
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
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
            Intent intent = new Intent(this, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, NumericConstants.REQUEST_CODE_SELECT);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
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
            Toast.makeText(this, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }


    ArrayList<ImageItem> images = null;


    @SuppressWarnings("unchecked")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    ((ExceptionReportingContract.Presenter) mPresenter).postUpLoadImg(images.get(0).path, 1);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            //  发送账单状态
            finish();
        } else if (flag == 1) {
            UploadImageBean uploadImageBean = (UploadImageBean) JsonUtil.getInstance().json2Obj(s, UploadImageBean.class);
            if (!(StringUtils.isEmpty(uploadImageBean.getResult().getFile().getUrl()))) {
                ImageItem imageItem = new ImageItem();
                imageItem.path = uploadImageBean.getResult().getFile().getUrl() + "?imageView2/1/w/300/h/500";
                selImageList.add(imageItem);
                adapter.setImages(selImageList);
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            Intent intent = new Intent(aty, LoginActivity.class);
            startActivity(intent);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(ExceptionReportingContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
