package com.ruitukeji.zwbs.mine.onlineservice;

import android.Manifest;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 在线客服
 * Created by Administrator on 2017/2/10.
 */

public class OnlineServiceActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private final int REQUEST_CODE_PERMISSION_CALL = 1;

    @BindView(id = R.id.ll_servicePhone, click = true)
    private LinearLayout servicePhone;

    @BindView(id = R.id.tv_servicePhone)
    private TextView tv_servicePhone;

    @BindView(id = R.id.ll_complaints, click = true)
    private LinearLayout complaints;

    @BindView(id = R.id.tv_complaints)
    private TextView tv_complaints;

    private SweetAlertDialog sweetAlertDialog = null;

    @BindView(id = R.id.emailAddress, click = true)
    private LinearLayout emailAddress;
    @BindView(id = R.id.tv_emailAddress)
    private TextView tv_emailAddress;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_onlineservice);
    }

    @Override
    public void initData() {
        super.initData();
        initDialog();

    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.online_service), true, R.id.titlebar);
        String custom_phone = PreferenceHelper.readString(this, StringConstants.FILENAME, "custom_phone");
        tv_servicePhone.setText(custom_phone);
        String custom_email = PreferenceHelper.readString(this, StringConstants.FILENAME, "custom_email");
        tv_emailAddress.setText(custom_email);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_servicePhone:
                choiceCallWrapper(getString(R.string.customerServiceTelephone1), tv_servicePhone.getText().toString());
                break;
            case R.id.ll_complaints:
                choiceCallWrapper(getString(R.string.complaintTelphone), tv_complaints.getText().toString());
                break;
//            case R.id.emailAddress:
////                emailAddress.setOnLongClickListener(new View.OnLongClickListener() {
////                    @Override
////                    public boolean onLongClick(View v) {
////                        ClipboardManager cmb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
////                        cmb.setText(textview.getText());
////                        ToastUtil.show("已复制");
////                        return false;
////                    }
////                });
//                break;
        }
    }

    @AfterPermissionGranted(REQUEST_CODE_PERMISSION_CALL)
    private void choiceCallWrapper(String title, String phone) {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            showDialog(title, phone);
        } else {
            EasyPermissions.requestPermissions(this, "拨打电话选择需要以下权限:\n\n访问设备上的电话拨打权限", REQUEST_CODE_PERMISSION_CALL, perms);
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
        if (requestCode == REQUEST_CODE_PERMISSION_CALL) {
            ViewInject.toast("您拒绝了「拨打电话」所需要的相关权限!");
        }
    }


    public void showDialog(String title, String phone) {
        if (sweetAlertDialog == null) {
            initDialog();
        }
        sweetAlertDialog.setTitleText(title)
                .setContentText(phone)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        sDialog = null;
                        Uri uri = Uri.parse("tel:" + phone);
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        //     系统打电话界面：
                        startActivity(intent);
                    }
                }).show();
    }


    public void initDialog() {
        sweetAlertDialog = null;
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCancelText(getString(R.string.cancel))
                .setConfirmText(getString(R.string.confirm))
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sweetAlertDialog = null;
    }
}
