package com.ruitukeji.zwbs.mission.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.utils.FileNewUtil;
import com.ruitukeji.zwbs.utils.map.GPSUtil;


/**
 * 任务---选择导航弹框
 * Created by Admin on 2017/7/23.
 */

public class NavigationBouncedDialog extends BaseDialog implements View.OnClickListener {
    private String orgLocation = "";
    private String destLocation = "";
    private Context context;
    private TextView tv_baidu;
    private TextView tv_gaode;
    private TextView tv_baidu1;
    private TextView tv_gaode1;

    public NavigationBouncedDialog(Context context, String orgLocation, String destLocation) {
        super(context, R.style.dialog);
        this.context = context;
        this.orgLocation = orgLocation;
        this.destLocation = destLocation;
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
        tv_baidu1 = (TextView) findViewById(R.id.tv_baidu1);
        tv_baidu1.setOnClickListener(this);
        tv_gaode = (TextView) findViewById(R.id.tv_gaode);
        tv_gaode.setOnClickListener(this);
        tv_gaode1 = (TextView) findViewById(R.id.tv_gaode1);
        tv_gaode1.setOnClickListener(this);
        setText();
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_baidu:
                cancel();
                //百度
                initBaiDuMap(orgLocation);
                break;
            case R.id.tv_baidu1:
                cancel();
                //百度
                initBaiDuMap(destLocation);
                break;
            case R.id.tv_gaode:
                cancel();
                //高德
                initGaoDeMap(orgLocation);
                break;
            case R.id.tv_gaode1:
                cancel();
                //高德
                initGaoDeMap(destLocation);
                break;
            case R.id.tv_cancel:
                cancel();
                break;
        }
    }

    /**
     * @param orgLocation
     * @param destLocation
     */
    public void setDestination(String orgLocation, String destLocation) {
        this.orgLocation = orgLocation;
        this.destLocation = destLocation;
        setText();
    }


    private void setText() {
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.baidu.BaiduMap")) {
            tv_baidu.setText(context.getString(R.string.baiDuNavigation));
            tv_baidu1.setText(context.getString(R.string.baiDuNavigation1));
        } else {
            tv_baidu.setText(context.getString(R.string.baiDuNavigation) + "(" + context.getString(R.string.uninstalled) + ")");
            tv_baidu1.setText(context.getString(R.string.baiDuNavigation1) + "(" + context.getString(R.string.uninstalled) + ")");
        }
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.autonavi.minimap")) {
            tv_gaode.setText(context.getString(R.string.gaoDeNavigation));
            tv_gaode1.setText(context.getString(R.string.gaoDeNavigation1));
        } else {
            tv_gaode.setText(context.getString(R.string.gaoDeNavigation) + "(" + context.getString(R.string.uninstalled) + ")");
            tv_gaode1.setText(context.getString(R.string.gaoDeNavigation1) + "(" + context.getString(R.string.uninstalled) + ")");
        }
    }

    /**
     * 调起百度地图 导航
     */
    public void initBaiDuMap(String location) {
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.baidu.BaiduMap")) {
            try {
                // 驾车导航
//                Intent intent = Intent.getIntent("baidumap://map/navi?query=" + mudi);
//                context.startActivity(intent);
                location = GPSUtil.gaoDeToBaiduStr(location);
                Intent intent = new Intent();
                intent.setData(Uri.parse("baidumap://map/navi?location=" + location));
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
     */
    public void initGaoDeMap(String location) {
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
