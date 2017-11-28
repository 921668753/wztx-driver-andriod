package com.ruitukeji.zwbs.dialog;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.utils.FileNewUtil;

import java.net.URISyntaxException;

/**
 * Created by Admin on 2017/7/23.
 */

public class NavigationBouncedDialog extends Dialog implements View.OnClickListener {
    private String destination;
    private Context context;
    private TextView tv_baidu;
    private TextView tv_gaode;
    private TextView tv_cancel;

    public NavigationBouncedDialog(Context context, String destination) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.destination = destination;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_navigationbounced);
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
                dismiss();
                //百度
                initBaiDuMap("", destination);
                break;
            case R.id.tv_gaode:
                dismiss();
                //高德
                initGaoDeMap("", destination);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }


    /**
     * 调起百度地图
     *
     * @param chufa 出发地
     * @param mudi  目的地
     */
    public void initBaiDuMap(String chufa, String mudi) {

        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.baidu.BaiduMap")) {
            try {
                // 驾车导航
                Intent intent = Intent.getIntent("baidumap://map/navi?query=" + mudi);
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                ViewInject.toast("路径解析错误");
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                //showDialog();
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
     * @param chufa 出发地
     * @param mudi  目的地
     */
    public void initGaoDeMap(String chufa, String mudi) {
        if (FileNewUtil.isAvilible(KJActivityStack.create().topActivity(), "com.autonavi.minimap")) {
            try {
                Intent intent = Intent.getIntent("androidamap://keywordNavi?sourceApplication=" + context.getString(R.string.app_name) + "&keyword=" + mudi + "&style=2");
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                ViewInject.toast("路径解析错误");
            }
            return;
        }
        ViewInject.toast(context.getString(R.string.installedGaoDe));
//        Uri uri = Uri.parse("http://uri.amap.com/navigation?from=&to=" + mudi + "&mode=car&src="+ context.getString(R.string.app_name)+"&callnative=1");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        context.startActivity(intent);
    }
}
