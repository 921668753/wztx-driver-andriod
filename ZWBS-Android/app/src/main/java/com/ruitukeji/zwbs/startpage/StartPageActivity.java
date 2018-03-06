package com.ruitukeji.zwbs.startpage;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kymjs.common.FileUtils;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.common.SystemTool;
import com.kymjs.okhttp3.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseInstrumentedActivity;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.startpage.AppConfigBean;
import com.ruitukeji.zwbs.main.MainActivity;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.List;

import okhttp3.OkHttpClient;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 启动页暂定为集成beasactivity
 * 若添加极光推送等需更换极光推送activity   InstrumentedActivity
 * Created by ruitu ck on 2016/9/14.
 */

public class StartPageActivity extends BaseInstrumentedActivity implements StartPageContract.View, EasyPermissions.PermissionCallbacks {

    private StartPageContract.Presenter mPresenter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_startpage);
    }

    /**
     * 设置定位
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new StartPagePresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }

    public void initView() {
        ImageView image = new ImageView(aty);
        image.setBackgroundResource(R.color.white);
        image.setImageResource(R.mipmap.startpage);
        Animation anim = AnimationUtils.loadAnimation(aty, R.anim.splash_start);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                readAndWriteTask();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        image.setAnimation(anim);
        setContentView(image);
    }


    private void jumpTo() {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "isShowingOrderNotic", 0);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "orderId", 0);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "orderCode", "");
        boolean isFirst = PreferenceHelper.readBoolean(this, StringConstants.FILENAME, "isFirstOpen", false);
        Intent jumpIntent = new Intent();
        jumpIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        jumpIntent.setAction("android.intent.action.MAIN");
        jumpIntent.addCategory("android.intent.category.LAUNCHER");
        if (isFirst) {
            PreferenceHelper.write(this, StringConstants.FILENAME, "isFirstOpen", false);
            //   jumpIntent.setClass(this, GuideViewActivity.class);
        } else {
            jumpIntent.setClass(this, MainActivity.class);
        }
        skipActivity(aty, jumpIntent);
        //  overridePendingTransition(0, 0);
    }


    @Override
    public void getSuccess(String s) {
        AppConfigBean appConfigBean = (AppConfigBean) JsonUtil.getInstance().json2Obj(s, AppConfigBean.class);
        PreferenceHelper.write(this, StringConstants.FILENAME, "lastApkUrl", appConfigBean.getResult().getLastApkUrl());
        PreferenceHelper.write(this, StringConstants.FILENAME, "lastApkVersion", appConfigBean.getResult().getLastApkVersion());
        PreferenceHelper.write(this, StringConstants.FILENAME, "lastApkVersionNum", appConfigBean.getResult().getLastApkVersionNum());
        PreferenceHelper.write(this, StringConstants.FILENAME, "defaultAvatar", appConfigBean.getResult().getDefaultAvatar());
        PreferenceHelper.write(this, StringConstants.FILENAME, "share_percent", appConfigBean.getResult().getShare_percent());
        PreferenceHelper.write(this, StringConstants.FILENAME, "grab_range", appConfigBean.getResult().getGrab_range());
        PreferenceHelper.write(this, StringConstants.FILENAME, "premium_rate", appConfigBean.getResult().getPremium_rate());
        PreferenceHelper.write(this, StringConstants.FILENAME, "bond_person_amount", appConfigBean.getResult().getBond_person_amount());
        PreferenceHelper.write(this, StringConstants.FILENAME, "bond_company_amount", appConfigBean.getResult().getBond_company_amount());
        PreferenceHelper.write(this, StringConstants.FILENAME, "withdraw_begintime", appConfigBean.getResult().getWithdraw_begintime());
        PreferenceHelper.write(this, StringConstants.FILENAME, "withdraw_endtime", appConfigBean.getResult().getWithdraw_endtime());
        PreferenceHelper.write(this, StringConstants.FILENAME, "custom_phone", appConfigBean.getResult().getCustom_phone());
        PreferenceHelper.write(this, StringConstants.FILENAME, "custom_email", appConfigBean.getResult().getCustom_email());
        PreferenceHelper.write(this, StringConstants.FILENAME, "complain_phone", appConfigBean.getResult().getComplain_phone());
        PreferenceHelper.write(this, StringConstants.FILENAME, "weixin_limit", appConfigBean.getResult().getWeixin_limit());
        PreferenceHelper.write(this, StringConstants.FILENAME, "alipay_limit", appConfigBean.getResult().getAlipay_limit());
        PreferenceHelper.write(this, StringConstants.FILENAME, "tran_account", appConfigBean.getResult().getTran_account());
        PreferenceHelper.write(this, StringConstants.FILENAME, "share_driver", appConfigBean.getResult().getShare_driver());
        PreferenceHelper.write(this, StringConstants.FILENAME, "share_driver_description", appConfigBean.getResult().getShare_driver_description());
        PreferenceHelper.write(this, StringConstants.FILENAME, "share_driver_title", appConfigBean.getResult().getShare_driver_title());
        // PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", true);
        int lastApkVersionNum = 0;
        if (StringUtils.isEmpty(appConfigBean.getResult().getLastApkVersionNum() + "")) {
            lastApkVersionNum = 0;
        } else {
            lastApkVersionNum = appConfigBean.getResult().getLastApkVersionNum();
        }
        Log.d("tag", "lastApkVersionNum" + "===" + lastApkVersionNum);
        if (lastApkVersionNum > SystemTool.getAppVersionCode(this)) {
            PreferenceHelper.write(this, StringConstants.FILENAME, "isUpdate", true);
            Log.d("tag", "isUpdate" + "===" + true);
        } else {
            PreferenceHelper.write(this, StringConstants.FILENAME, "isUpdate", false);
            Log.d("tag", "isUpdate" + "===" + false);
        }
        jumpTo();
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        ((StartPageContract.Presenter) mPresenter).getAppConfig();
    }

    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    public void readAndWriteTask() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CHANGE_WIFI_STATE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permission, do the thing!
            RxVolley.setRequestQueue(RequestQueue.newRequestQueue(FileUtils.getSaveFolder(StringConstants.CACHEPATH), new OkHttpStack(new OkHttpClient())));
            ((StartPageContract.Presenter) mPresenter).getAppConfig();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.readAndWrite), NumericConstants.READ_AND_WRITE_CODE, perms);
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
        Log.d("tag", "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
////            new AppSettingsDialog.Builder(this).setRationale("内容").setTitle("标题").setNegativeButton("拒绝").setPositiveButton("确定").build().show();
//            new AppSettingsDialog.Builder(this).build().show();
//        }
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            ViewInject.toast(getString(R.string.sdPermission));
            finish();
        }
    }


    @Override
    public void setPresenter(StartPageContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
