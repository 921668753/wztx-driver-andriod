package com.ruitukeji.zwbs.dialog;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.utils.FileNewUtil;
import com.ruitukeji.zwbs.utils.map.GPSUtil;

import java.net.URISyntaxException;

/**
 * Created by Admin on 2017/7/23.
 */

public class NavigationBouncedDialog extends Dialog implements View.OnClickListener {
    private String location = "";
    private String destination;
    private Context context;
    private TextView tv_baidu;
    private TextView tv_gaode;
    private TextView tv_cancel;

    public NavigationBouncedDialog(Context context, String destination, String location) {
        super(context, R.style.dialog);
        this.context = context;
        this.destination = destination;
        this.location = location;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_navigationbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        tv_baidu = (TextView) findViewById(R.id.tv_baidu);
        tv_baidu.setOnClickListener(this);
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.baidu.BaiduMap")) {
            tv_baidu.setText(context.getString(R.string.baiDuNavigation));
        } else {
            tv_baidu.setText(context.getString(R.string.baiDuNavigation) + "(" + context.getString(R.string.uninstalled) + ")");
        }
        tv_gaode = (TextView) findViewById(R.id.tv_gaode);
        tv_gaode.setOnClickListener(this);
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.autonavi.minimap")) {
            tv_gaode.setText(context.getString(R.string.gaoDeNavigation));
        } else {
            tv_gaode.setText(context.getString(R.string.gaoDeNavigation) + "(" + context.getString(R.string.uninstalled) + ")");
        }
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_baidu:
                cancel();
                //百度
                initBaiDuMap(destination, location);
                break;
            case R.id.tv_gaode:
                cancel();
                //高德
                initGaoDeMap(destination, location);
                break;
            case R.id.tv_cancel:
                cancel();
                break;
        }
    }

    public void setDestination(String destination, String location) {
        this.destination = destination;
        this.location = location;
    }


    /**
     * 调起百度地图 导航
     *
     * @param mudi     地址
     * @param location 经纬度
     */
    public void initBaiDuMap(String mudi, String location) {
        Log.d("tag1111", location);
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.baidu.BaiduMap")) {
            try {
                // 驾车导航
//                Intent intent = Intent.getIntent("baidumap://map/navi?query=" + mudi);
//                context.startActivity(intent);
                location = GPSUtil.gaoDeToBaiduStr(location);
                Intent intent = new Intent();
                intent.setData(Uri.parse("baidumap://map/navi?query=" + mudi + "&location=" + location));
                //    intent.setPackage("com.baidu.BaiduMap");
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                ViewInject.toast(context.getString(R.string.parsingError));
            }
            return;
        }
        ViewInject.toast(context.getString(R.string.installedBaiDu));
//        String uristr = "http://api.map.baidu.com/direction?origin=" + "&destination=" + mudi + "&mode=driving&region=" + mudi + "&output=html&src=" + context.getString(R.string.app_name);
//        Uri uri = Uri.parse(uristr);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        context.startActivity(intent);
    }


    /**
     * 高德
     *
     * @param destination 出发地
     * @param location    目的地
     */
    public void initGaoDeMap(String destination, String location) {
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.autonavi.minimap")) {
            try {
                //  Intent intent = Intent.getIntent("androidamap://keywordNavi?sourceApplication=" "&keyword=" + mudi + "&style=2");
                String[] gd_lat_lon = location.split(",");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("androidamap://navi?sourceApplication=" + context.getString(R.string.app_name) + "&lat=" + gd_lat_lon[1] + "&lon=" + gd_lat_lon[0] + "&dev=1&style=2"));
                intent.setPackage("com.autonavi.minimap");
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                ViewInject.toast(context.getString(R.string.parsingError));
            }
            return;
        }
        ViewInject.toast(context.getString(R.string.installedGaoDe));
//        Uri uri = Uri.parse("http://uri.amap.com/navigation?from=&to=" + mudi + "&mode=car&src="+ context.getString(R.string.app_name)+"&callnative=1");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        context.startActivity(intent);
    }
}
