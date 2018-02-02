package com.ruitukeji.zwbs.order;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
 * 订单拍照凭证
 * Created by Administrator on 2017/2/15.
 */

public class OrderVoucherActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, ImagePickerAdapter.OnRecyclerViewItemClickListener, OrderVoucherContract.View {

    private OrderVoucherContract.Presenter mPresenter;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    @BindView(id = R.id.tv_nextType, click = true)
    private TextView tv_nextType;


    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList = null; //当前选择的所有图片
    private int maxImgCount = 5;               //允许选择图片最大数
    private int order_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_ordervoucher);
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        mPresenter = new OrderVoucherPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.uploadDocuments), true, R.id.titlebar);
        order_id = getIntent().getIntExtra("order_id", 0);
        tv_nextType.setText(getString(R.string.submit));
        //最好放到 Application oncreate执行
        initImagePicker();

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

    private void initView() {
        selImageList = new ArrayList<ImageItem>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
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
            case R.id.tv_nextType:
                //  choicePhotoWrapper();
                showLoadingDialog(getString(R.string.submissionLoad));
                ((OrderVoucherContract.Presenter) mPresenter).postOrderVoucher(order_id, selImageList);
                break;
//            case R.id.tv_moment_add_publish:
//                ViewInject.toast(et_moment_add_content.getText().toString().trim());
//                ViewInject.toast(mPhotosSnpl.getData().toString());
//                break;
            default:
                break;
        }
    }


    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
//            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");
//            startActivityForResult(BGAPhotoPickerActivity.newIntent(this, true ? takePhotoDir : null, mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount(), null, true), NumericConstants.REQUEST_CODE_CHOOSE_PHOTO);
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
                    ((OrderVoucherContract.Presenter) mPresenter).postUpLoadImg(images.get(0).path, 1);
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
            ViewInject.toast(getString(R.string.submittedSuccessfully));
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
    public void error(String msg) {
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
    public void setPresenter(OrderVoucherContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
