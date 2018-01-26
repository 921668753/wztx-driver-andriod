package com.ruitukeji.zwbs.main;

import android.Manifest;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.iflytek.cloud.SpeechSynthesizer;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.main.WorkingStateBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements MainContract.View, EasyPermissions.PermissionCallbacks, AMapLocationListener {
    @BindView(id = R.id.bottombar_getorder, click = true)
    private LinearLayout bottombar_getorder;

    @BindView(id = R.id.img_getorder)
    private ImageView img_getorder;

    @BindView(id = R.id.tv_getorder)
    private TextView tv_getorder;

    @BindView(id = R.id.bottombar_mission, click = true)
    private LinearLayout bottombar_order;

    @BindView(id = R.id.img_mission)
    private ImageView img_mission;

    @BindView(id = R.id.tv_mission)
    private TextView tv_mission;

    @BindView(id = R.id.img_chuche, click = true)
    private ImageView img_chuche;

    @BindView(id = R.id.bottombar_supplygoods, click = true)
    private LinearLayout bottombar_supplygoods;

    @BindView(id = R.id.img_supplygoods)
    private ImageView img_supplygoods;

    @BindView(id = R.id.tv_supplygoods)
    private TextView tv_supplygoods;

    @BindView(id = R.id.bottombar_mine, click = true)
    private LinearLayout bottombar_mine;

    @BindView(id = R.id.img_mine)
    private ImageView img_mine;

    @BindView(id = R.id.tv_mine)
    private TextView tv_mine;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;
    private BaseFragment contentFragment3;
    private long firstTime = 0;

    private boolean isTost = true;


    // 语音合成对象
    private SpeechSynthesizer mTts;
    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;
    public static boolean isForeground = true;

    /**
     * 是否出车 0为收车（下班）   1为出车中(上班)
     */
    private int isGoWork = 0;

    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    AMapLocationClient mlocationClient = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MainPresenter(this);
        contentFragment = new GetOrderFragment();
        contentFragment1 = new MissionFragment();
        contentFragment2 = new SupplyGoodsFragment();
        contentFragment3 = new MineFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
        ((MainContract.Presenter) mPresenter).getWorkingState();
        registerMessageReceiver();  //   极光推送 used for receive msg
        //初始化语音合成对象
        mTts = SpeechSynthesizer.createSynthesizer(MainActivity.this, null);
    }

    /**
     * Activity的启动模式变为singleTask
     * 调用onNewIntent(Intent intent)方法。
     * Fragment调用的时候，一定要在onResume方法中。
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int newChageIcon = intent.getIntExtra("newChageIcon", 4);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            ((MainContract.Presenter) mPresenter).setSimulateClick(bottombar_getorder, 160, 100);
        } else if (newChageIcon == 1) {
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        //极光推送 定制声音、震动、闪灯等 Notification 样式。
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
        builder.statusBarDrawable = R.mipmap.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);
        cleanColors(0);
        changeFragment(contentFragment);
        chageIcon = 0;
    }


    @Override
    public void initDataFromThread() {
        super.initDataFromThread();
        choiceLocationWrapper();
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.bottombar_getorder:
                chageIcon = 0;
                cleanColors(chageIcon);
                changeFragment(contentFragment);
                break;
            case R.id.bottombar_mission:
                ((MainContract.Presenter) mPresenter).isLogin(0);
                break;
            case R.id.img_chuche:
                if (isGoWork == 0) {
                    isGoWork = 1;
                    ((MainContract.Presenter) mPresenter).isLogin(2);
                } else {
                    isGoWork = 0;
                    ((MainContract.Presenter) mPresenter).isLogin(3);
                }
                break;
            case R.id.bottombar_supplygoods:
                chageIcon = 2;
                cleanColors(chageIcon);
                changeFragment(contentFragment2);
                break;
            case R.id.bottombar_mine:
                chageIcon = 3;
                cleanColors(chageIcon);
                changeFragment(contentFragment3);
                break;
            default:
                break;
        }
    }

    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    //如果两次按键时间间隔大于2秒，则不退出
                    ViewInject.toast(this, "再按一次退出程序");
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {
                    //  int i = 1 / 0;
                    //   KjBitmapUtil.getInstance().getKjBitmap().cleanCache();
                    MobclickAgent.onProfileSignOff();//关闭账号统计     退出登录也加
                    JPushInterface.stopCrashHandler(getApplication());//JPush关闭CrashLog上报
                    MobclickAgent.onKillProcess(aty);
                    KJActivityStack.create().appExit(aty);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        isForeground = false;
        mlocationClient.stopLocation();
        mlocationClient.onDestroy();
        mlocationClient = null;
        unregisterReceiver(mMessageReceiver);
        mTts.stopSpeaking();
        // 退出时释放连接
        mTts.destroy();
        mTts = null;
        super.onDestroy();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;


    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MessageReceiver.MESSAGE_RECEIVED_ACTION);
        Intent intent = registerReceiver(mMessageReceiver, filter);
    }

    public String msg;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            chageIcon = 1;
            cleanColors(chageIcon);
            changeFragment(contentFragment1);
        } else if (flag == 1) {
            WorkingStateBean workingStateBean = (WorkingStateBean) JsonUtil.getInstance().json2Obj(s, WorkingStateBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "map_code", workingStateBean.getResult().getMap_code());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isGoWork", workingStateBean.getResult().getOnline());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "auth_status", workingStateBean.getResult().getAuth_status());
            if (workingStateBean.getResult().getOnline() == 0) {
                img_chuche.setImageResource(R.mipmap.shouche);
                isGoWork = 0;
            } else if (workingStateBean.getResult().getOnline() == 1) {
                img_chuche.setImageResource(R.mipmap.chuche);
                isGoWork = 1;
            }
        } else if (flag == 2 || flag == 3) {
            showLoadingDialog(MyApplication.getContext().getString(R.string.sendingLoad));
            ((MainContract.Presenter) mPresenter).postWorkingState(isGoWork);
        } else if (flag == 4) {
            isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 1);
            if (isGoWork == 0) {

                img_chuche.setImageResource(R.mipmap.shouche);
            } else {
                img_chuche.setImageResource(R.mipmap.chuche);
            }
            dismissLoadingDialog();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0 || flag == 2 || flag == 3) {
            toLigon1(msg);
        }
    }

    public class MessageReceiver extends BroadcastReceiver {
        public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
        public static final String KEY_TITLE = "title";
        public static final String KEY_MESSAGE = "message";
        public static final String KEY_EXTRAS = "extras";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                //       String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                Log.d("messge", messge);
                if (!messge.startsWith("您有一条新的订单")) {
                    return;
                }
                ((MainContract.Presenter) mPresenter).speech(mTts, messge);
            }
        }
    }

    /**
     * 清除颜色，并添加颜色
     */
    public void cleanColors(int chageIcon) {
        img_getorder.setImageResource(R.mipmap.orders_personal);
        tv_getorder.setTextColor(getResources().getColor(R.color.hintcolors));
        img_mission.setImageResource(R.mipmap.task_personal);
        tv_mission.setTextColor(getResources().getColor(R.color.hintcolors));
        img_supplygoods.setImageResource(R.mipmap.goods_personal);
        tv_supplygoods.setTextColor(getResources().getColor(R.color.hintcolors));
        img_mine.setImageResource(R.mipmap.tab_personal);
        tv_mine.setTextColor(getResources().getColor(R.color.hintcolors));
        if (chageIcon == 0) {
            img_getorder.setImageResource(R.mipmap.orders_personal_selected);
            tv_getorder.setTextColor(getResources().getColor(R.color.announcementCloseColors));
        } else if (chageIcon == 1) {
            img_mission.setImageResource(R.mipmap.task_personal_selected);
            tv_mission.setTextColor(getResources().getColor(R.color.announcementCloseColors));
        } else if (chageIcon == 2) {
            img_supplygoods.setImageResource(R.mipmap.goods_personal_selected);
            tv_supplygoods.setTextColor(getResources().getColor(R.color.announcementCloseColors));
        } else if (chageIcon == 3) {
            img_mine.setImageResource(R.mipmap.tab_personal_selected);
            tv_mine.setTextColor(getResources().getColor(R.color.announcementCloseColors));
        } else {
            img_getorder.setImageResource(R.mipmap.orders_personal_selected);
            tv_getorder.setTextColor(getResources().getColor(R.color.announcementCloseColors));
        }
    }

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choiceLocationWrapper() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CHANGE_WIFI_STATE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
//            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), StringConstants.PHOTOPATH);
//            startActivityForResult(BGAPhotoPickerActivity.newIntent(this, true ? takePhotoDir : null, 1, null, false), NumericConstants.REQUEST_CODE_CHOOSE_PHOTO);
            locationOption();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.locationPermissions), NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
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
            ViewInject.toast(getString(R.string.locationPermissions1));
        }
    }


    /**
     * 启动定位
     */
    public void locationOption() {
        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(60000);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(30000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                isTost = true;
                Log.d("latLonPoint", amapLocation.getAddress());
                Log.d("latLonPoint1", amapLocation.getProvider());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "currentLocationAddress", amapLocation.getAddress());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "currentLocationProvince", amapLocation.getProvince());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "currentLocationCity", amapLocation.getCity());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "currentLocationArea", amapLocation.getDistrict());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "currentLocationLatitudeLongitude", amapLocation.getLongitude() + "," + amapLocation.getLatitude());
                int isGoWork = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "isGoWork", 1);
                if (isGoWork == 0) {
                    nearbySearch(amapLocation);
                }
            } else {
                if (amapLocation.getErrorCode() == 12) {
                    if (isTost) {
                        ViewInject.toast("请打开GPS定位");
                        isTost = false;
                    }
                }
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                android.util.Log.e("AmapError", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
            }
        }
    }

    /**
     * 初始化附近派单功能
     */
    public void nearbySearch(AMapLocation amapLocation) {
//        Log.d("latLonPoint", latLonPoint);
        String map_code = PreferenceHelper.readString(aty, StringConstants.FILENAME, "map_code");
        ((MainContract.Presenter) mPresenter).postDriverLocation(map_code, amapLocation.getLongitude() + "," + amapLocation.getLatitude(), amapLocation.getAddress());
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") || ((String) msgEvent.getData()).equals("RxBusLogOutEvent")) {
            ((MainContract.Presenter) mPresenter).getWorkingState();
        }
    }


}
