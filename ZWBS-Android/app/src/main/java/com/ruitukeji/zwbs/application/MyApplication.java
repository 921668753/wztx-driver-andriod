package com.ruitukeji.zwbs.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.lzy.imagepicker.ImagePicker;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.utils.abnormal.LocalCrashHandler;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


import cn.jpush.android.api.JPushInterface;


//import com.umeng.analytics.MobclickAgent;


/**
 * Created by Administrator on 2016/5/13.
 */
public class MyApplication extends Application {
    public static int screenW;
    public static int screenH;

    private static final String LOGTAG = "tag";
    private static Context mContext;
    public static MyApplication instance;
    public ImagePicker imagePicker;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    /**
     * 解除65k限制,让应用支持多DEX文件
     * http://blog.csdn.net/fjnu_se/article/details/73302290
     *
     * @param base 如果你的Application类已经继承自其它类，你不想修改它，那么可以重写attachBaseContext()方法：
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

//    @Override
//    public void onCreate() {
//       super.onCreate();
//        //    singleton = this;
//    }

    public static MyApplication getInstance() {
        return instance;
    }


    public void init() {
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        JPushInterface.initCrashHandler(this);// JPush开启CrashLog上报
//        JPushInterface.getRegistrationID(getApplicationContext());//获取设备RegistrationID   注：每个设备只有一个
//错误日志保存本地，并强制重启程序
        LocalCrashHandler crashHandler = LocalCrashHandler.getInstance();
        crashHandler.init(this);
        testMemoryInfo();
        mContext = getApplicationContext();
        UMShareAPI.get(this);//友盟分享
        //  Config.REDIRECT_URL = "您新浪后台的回调地址"
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);//友盟新增场景类型设置接口
        MobclickAgent.enableEncrypt(true);//友盟日志加密设置
        MobclickAgent.setDebugMode(true);//友盟使用集成测试服务（推荐）
        //   MobclickAgent.setCatchUncaughtExceptions(false); //关闭    错误统计
        //  initCrashRestart();
        xfyun();
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
    }

    public static Context getContext() {
        return mContext;
    }


    /**
     * 关闭Activity列表中的所有Activity
     */
    public void finishActivity() {
        KJActivityStack.create().finishAllActivity();
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    // 各个平台的配置，建议放在全局Application或者程序入口
    {
        Config.DEBUG = true;
        // 微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin(StringConstants.WEIXINAppKey, StringConstants.WEIXINAppSecret);
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(StringConstants.QQAppID, StringConstants.QQAppKey);
    }

    //查询缓存
    public void testMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int largeMemoryClass = activityManager.getLargeMemoryClass();
        int memoryClass = activityManager.getMemoryClass();
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);

        Log.d(LOGTAG, "largeMemoryClass = " + largeMemoryClass);
        Log.d(LOGTAG, "memoryClass = " + memoryClass);

        Log.d(LOGTAG, "availMem = " + (info.availMem / 1024 / 1024) + "M");
        //   Log.d(LOGTAG, "totalMem = " + (info.totalMem / 1024 / 1024) + "M");
        Log.d(LOGTAG, "lowMemory = " + info.lowMemory);
    }

    /**
     * 讯飞语音初始化
     */
    public void xfyun() {
        //初始化即创建语音配置对象，只有初始化后才可以使用MSC的各项服务。建议将初始化放在程序入口处（如Application、Activity的onCreate方法),初始化代码如下：
        // 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        //SpeechUtility.createUtility(this, SpeechConstant.APPID + "=" + StringConstants.SpeechAPPID);
//        注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请使用参数：SpeechConstant.APPID +"=12345678," + SpeechConstant.FORCE_LOGIN +"=true"。
        SpeechUtility.createUtility(MyApplication.this, SpeechConstant.APPID + "=" + StringConstants.SpeechAPPID + "," + SpeechConstant.FORCE_LOGIN + "=true");
        // SpeechUtility.createUtility(SpeechApp.this, "appid=" + getString(R.string.app_id)+","+SpeechConstant.FORCE_LOGIN+"=true");
        //  Setting.setShowLog(true);
    }
}
