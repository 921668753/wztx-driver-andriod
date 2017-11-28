package com.ruitukeji.zwbs.mine.personalcertificate;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.ImageViewAdapter;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.ImagePreviewNoDelActivity;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.PersonalCertificateBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.ArrayList;

import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * 个人认证
 * Created by Administrator on 2017/2/14.
 */

public class PersonalCertificateActivity extends BaseActivity implements PersonalCertificateContract.View, AdapterView.OnItemClickListener {


    @BindView(id = R.id.tv_realName)
    private TextView tv_realName;

    @BindView(id = R.id.tv_bindingMobilePhone)
    private TextView tv_bindingMobilePhone;

    @BindView(id = R.id.tv_idNumber)
    private TextView tv_idNumber;

    @BindView(id = R.id.tv_sex)
    private TextView tv_sex;

    @BindView(id = R.id.tv_permanentAddress)
    private TextView tv_permanentAddress;

    @BindView(id = R.id.tv_licenseNumber1)
    private TextView tv_licenseNumber1;

    @BindView(id = R.id.tv_conductor)
    private TextView tv_conductor;

    @BindView(id = R.id.tv_load)
    private TextView tv_load;

    @BindView(id = R.id.tv_carrierProduct)
    private TextView tv_carrierProduct;

    @BindView(id = R.id.tv_models)
    private TextView tv_models;

    @BindView(id = R.id.lv_img)
    private ListView lv_img;

    private ImageViewAdapter imageViewAdapter;

    private ArrayList<ImageItem> arrayList = new ArrayList<ImageItem>();

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personalcertificate);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PersonalCertificatePresenter(this);
        imageViewAdapter = new ImageViewAdapter(this);
        initImagePicker();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        SimpleDelegate simpleDelegate = new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                showActivity(aty, CheckDocumentsActivity.class);
//                Intent intent = new Intent(aty, CheckDocumentsActivity.class);
//                intent.putExtra("driving_licence_pic", driving_licence_pic);
//                intent.putExtra("operation_pic", operation_pic);
//                intent.putExtra("policy_pic", policy_pic);
//                intent.putExtra("vehicle_license_pic", vehicle_license_pic);
//                showActivity(aty, intent);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.ownerInformation), getString(R.string.checkDocuments), R.id.titlebar, simpleDelegate);
//        ActivityTitleUtils.initToolbar(aty, getString(R.string.ownerInformation), true, R.id.titlebar);
        lv_img.setAdapter(imageViewAdapter);
        lv_img.setOnItemClickListener(this);
        tv_realName.setFocusable(true);
        tv_realName.setFocusableInTouchMode(true);
        tv_realName.requestFocus();
        tv_realName.requestFocusFromTouch();

        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        ((PersonalCertificateContract.Presenter) mPresenter).getPersonalCertificate();
    }


    /**
     * 图片库设置
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(false);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(false);
    }


    @Override
    public void getSuccess(String s) {
        PersonalCertificateBean personalCertificateBean = (PersonalCertificateBean) JsonUtil.json2Obj(s, PersonalCertificateBean.class);
        tv_realName.setText(personalCertificateBean.getResult().getReal_name());
        tv_bindingMobilePhone.setText(personalCertificateBean.getResult().getPhone());
        tv_idNumber.setText(personalCertificateBean.getResult().getIdentity());
        if (personalCertificateBean.getResult().getSex() == 1) {
            tv_sex.setText(getString(R.string.man));
        } else if (personalCertificateBean.getResult().getSex() == 2) {
            tv_sex.setText(getString(R.string.woman));
        } else {
            tv_sex.setText("未知");
        }

        tv_permanentAddress.setText(personalCertificateBean.getResult().getAddress());

        tv_licenseNumber1.setText(personalCertificateBean.getResult().getCard_number());

        tv_conductor.setText(personalCertificateBean.getResult().getCar_length());

        tv_models.setText(personalCertificateBean.getResult().getCar_type());

        tv_load.setText(personalCertificateBean.getResult().getWeight() + "吨");

        tv_carrierProduct.setText(personalCertificateBean.getResult().getVolume() + "立方米");

        imageViewAdapter.clear();

        ImageItem imageItem = new ImageItem();
        imageItem.path = personalCertificateBean.getResult().getFront_pic();
        arrayList.add(imageItem);

        ImageItem imageItem1 = new ImageItem();
        imageItem1.path = personalCertificateBean.getResult().getBack_pic();
        arrayList.add(imageItem1);

        ImageItem imageItem2 = new ImageItem();
        imageItem2.path = personalCertificateBean.getResult().getIndex_pic();
        arrayList.add(imageItem2);

        imageViewAdapter.addNewData(arrayList);

        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        toLigon(msg);
        dismissLoadingDialog();
   //     ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(PersonalCertificateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageViewAdapter.clear();
        imageViewAdapter = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toImagePreviewDelActivity(arrayList, position);
    }

    //打开预览
    @SuppressWarnings("unchecked")
    private void toImagePreviewDelActivity(ArrayList<ImageItem> list, int position) {
        Intent intentPreview = new Intent(this, ImagePreviewNoDelActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, list);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        showActivity(aty, intentPreview);
    }

}
