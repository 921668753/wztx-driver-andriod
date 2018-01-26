package com.ruitukeji.zwbs.mine.setting;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.common.SystemTool;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.BaseResult;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.setting.aboutus.AboutUsActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.DataCleanManager;
import com.ruitukeji.zwbs.utils.FileNewUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 设置
 * Created by Administrator on 2017/2/10.
 */

public class SettingsActivity extends BaseActivity implements SettingsContract.View, EasyPermissions.PermissionCallbacks {


    @BindView(id = R.id.ll_changePassword, click = true)
    private LinearLayout changePassword;

    @BindView(id = R.id.ll_systemVersion, click = true)
    private LinearLayout systemVersion;

    @BindView(id = R.id.tv_version)
    private TextView version;

    @BindView(id = R.id.ll_clearCache, click = true)
    private LinearLayout clearCache;

    @BindView(id = R.id.tv_cache)
    private TextView cache;

    @BindView(id = R.id.ll_aboutUs, click = true)
    private LinearLayout ll_aboutUs;

    @BindView(id = R.id.tv_logOut, click = true)
    private TextView tv_logOut;
    private String updateAppUrl = null;
    private boolean isUpdateApp = false;
    private SweetAlertDialog sweetAlertDialog = null;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void initData() {
        super.initData();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        mPresenter = new SettingsPresenter(this);
        ((SettingsContract.Presenter) mPresenter).isLogin(1);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.settings), true, R.id.titlebar);
        initDialog();
        isUpdateApp = PreferenceHelper.readBoolean(MyApplication.getContext(), StringConstants.FILENAME, "isUpdate", false);
        if (isUpdateApp) {
            version.setText(getString(R.string.newVersion));
        } else {
            version.setText("V " + SystemTool.getAppVersionName(this));
        }
        queryCache();
    }

    /**
     * 弹框设置
     */
    private void initDialog() {
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText(getString(R.string.updateVersion))
                .setCancelText(getString(R.string.cancel))
                .setConfirmText(getString(R.string.update))
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
    }

    /**
     * 监听事件
     *
     * @param v
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_aboutUs:
                Intent intent = new Intent(aty, AboutUsActivity.class);
                intent.putExtra("type", "driver_about");
                showActivity(aty, intent);
                break;
            case R.id.ll_changePassword:
                ((SettingsContract.Presenter) mPresenter).isLogin(2);
                break;
            case R.id.ll_systemVersion:
                if (isUpdateApp) {
                    updateAppUrl = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "updateAppUrl", null);
                    if (StringUtils.isEmpty(updateAppUrl)) {
                        errorMsg("服务器错误,下载地址为空", 0);
                        return;
                    }
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            readAndWriteTask(updateAppUrl);
                        }
                    }).show();
                    break;
                }
                ViewInject.toast(getString(R.string.latestVersion));
                break;
            case R.id.ll_clearCache:
                DataCleanManager.clearAllCache(aty);
                cache.setText("0KB");
                ViewInject.toast("清除成功");
                break;
            case R.id.tv_logOut:
                PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", 0);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", true);
                skipActivity(aty, LoginActivity.class);
                RxBus.getInstance().post(new MsgEvent("RxBusLogOutEvent"));
                break;
        }
    }

    /**
     * 查询缓存
     */
    public void queryCache() {
        try {
            cache.setText(DataCleanManager.getTotalCacheSize(aty));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            BaseResult baseResult = (BaseResult) JsonUtil.getInstance().json2Obj(success, BaseResult.class);
            if ((String) baseResult.getResult() == null) {
                return;
            }
            File path = new File((String) baseResult.getResult());
            FileNewUtil.installApkFile(this, path.getAbsolutePath());
        } else if (flag == 1) {
            tv_logOut.setVisibility(View.VISIBLE);
        } else if (flag == 2) {
            showActivity(aty, ChangePasswordActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0) {
            isUpdateApp = false;
            dismissLoadingDialog();
            ViewInject.toast(msg);
        } else if (flag == 1) {
            tv_logOut.setVisibility(View.GONE);
            dismissLoadingDialog();
        } else if (flag == 2) {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                showActivity(aty, LoginActivity.class);
                return;
            }
            dismissLoadingDialog();
            ViewInject.toast(msg);
        }

    }


    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    public void readAndWriteTask(String updateAppUrl) {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permission, do the thing!
            ((SettingsContract.Presenter) mPresenter).downloadApp(updateAppUrl);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.readAndWrite),
                    NumericConstants.READ_AND_WRITE_CODE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //  Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            ViewInject.toast(getString(R.string.sdPermission));
        }
    }

}
