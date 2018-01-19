package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;

/**
 * 货源---出发地弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class OriginBouncedDialog extends BaseDialog implements View.OnClickListener, AdapterView.OnItemClickListener, OriginBouncedContract.View {

    private Context context;

    private TextView tv_transparent;

    private ChildLiistView lv_province;

    private ChildLiistView lv_city;

    private ChildLiistView lv_area;

    private OriginBouncedContract.Presenter mPresenter;


    public OriginBouncedDialog(Context context, int provinceId, int cityId, int areaId) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_originbounced);
        initView();
    }

    private void initView() {
        lv_province = (ChildLiistView) findViewById(R.id.lv_province);
        lv_province.setOnItemClickListener(this);
        lv_city = (ChildLiistView) findViewById(R.id.lv_city);
        lv_city.setOnItemClickListener(this);
        lv_area = (ChildLiistView) findViewById(R.id.lv_area);
        lv_area.setOnItemClickListener(this);
        tv_transparent = (TextView) findViewById(R.id.tv_transparent);
        tv_transparent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_transparent:
                //   dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    public abstract void confirm(String provinceName, int provinceId, String cityName, int cityId, String areaName, int areaId);

    @Override
    public void setPresenter(OriginBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {






        } else if (flag == 1) {





        } else if (flag == 2) {




        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


}
