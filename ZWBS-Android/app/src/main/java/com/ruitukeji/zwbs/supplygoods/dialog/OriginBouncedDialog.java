package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.AreaViewAdapter;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.CityViewAdapter;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.ProvinceViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.List;

/**
 * 货源---出发地弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class OriginBouncedDialog extends BaseDialog implements View.OnClickListener, AdapterView.OnItemClickListener, OriginBouncedContract.View {

    private Context context;

    private ListView lv_province;

    private ProvinceViewAdapter provinceViewAdapter = null;

    private ListView lv_city;

    private CityViewAdapter cityViewAdapter = null;

    private ListView lv_area;

    private AreaViewAdapter areaViewAdapter = null;

    private int provinceId = 0;

    private int cityId = 0;

    private int areaId = 0;

    private OriginBouncedContract.Presenter mPresenter;
    private List<ResultBean> provinceBeanlist = null;
    private List<ResultBean> cityBeanlist = null;
    private List<ResultBean> areaBeanlist = null;


    public OriginBouncedDialog(Context context, int provinceId, int cityId, int areaId) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_originbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        mPresenter = new OriginBouncedPresenter(this);
        provinceViewAdapter = new ProvinceViewAdapter(context);
        cityViewAdapter = new CityViewAdapter(context);
        areaViewAdapter = new AreaViewAdapter(context);
        lv_province = (ListView) findViewById(R.id.lv_province);
        lv_province.setOnItemClickListener(this);
        lv_province.setAdapter(provinceViewAdapter);
        lv_city = (ListView) findViewById(R.id.lv_city);
        lv_city.setOnItemClickListener(this);
        lv_city.setAdapter(cityViewAdapter);
        lv_area = (ListView) findViewById(R.id.lv_area);
        lv_area.setOnItemClickListener(this);
        lv_area.setAdapter(areaViewAdapter);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getAddress(0, 0);
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
        if (parent.getId() == R.id.lv_province) {


        } else if (parent.getId() == R.id.lv_city) {

        } else if (parent.getId() == R.id.lv_area) {

        }
    }

    public abstract void confirm(String provinceName, int provinceId, String cityName, int cityId, String areaName, int areaId);

    @Override
    public void setPresenter(OriginBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        AddressBean addressBean = (AddressBean) JsonUtil.json2Obj(success, AddressBean.class);
        List<AddressBean.ResultBean> list = addressBean.getResult();
        if (list == null || list.size() == 0) {
            errorMsg(context.getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (flag == 0) {
            provinceBeanlist = list;
            selectProvince(provinceId);
        } else if (flag == 1) {
            cityBeanlist = list;
            selectCity(cityId);
            // cityViewAdapter.addNewData(list);
        } else if (flag == 2) {
            areaBeanlist = list;
            selectArea(areaId);
            dismissLoadingDialog();
        }
    }

    /**
     * 选择省
     */
    private void selectProvince(int provinceId) {
        for (int i = 0; i < provinceBeanlist.size(); i++) {
            if (provinceId == provinceBeanlist.get(i).getId() || provinceId == i && provinceId == 0) {
                ResultBean provinceBean = provinceBeanlist.get(i);
                provinceBean.setStatus(1);
                mPresenter.getAddress(provinceBeanlist.get(i).getId(), 1);
            } else {
                provinceBeanlist.get(i).setStatus(0);
            }
        }
        provinceViewAdapter.clear();
        provinceViewAdapter.addMoreData(provinceBeanlist);
    }

    /**
     * 选择城市
     */
    private void selectCity(int cityId) {
        for (int i = 0; i < cityBeanlist.size(); i++) {
            if (cityId == cityBeanlist.get(i).getId() || cityId == i && cityId == 0) {
                ResultBean provinceBean = cityBeanlist.get(i);
                provinceBean.setStatus(1);
                mPresenter.getAddress(cityBeanlist.get(i).getId(), 2);
            } else {
                cityBeanlist.get(i).setStatus(0);
            }
        }
        cityViewAdapter.clear();
        cityViewAdapter.addMoreData(cityBeanlist);
    }

    /**
     * 选择地区
     */
    private void selectArea(int areaId) {
        for (int i = 0; i < areaBeanlist.size(); i++) {
            if (areaId == areaBeanlist.get(i).getId() || areaId == i && areaId == 0) {
                ResultBean provinceBean = areaBeanlist.get(i);
                provinceBean.setStatus(1);
            } else {
                areaBeanlist.get(i).setStatus(0);
            }
        }
        areaViewAdapter.clear();
        areaViewAdapter.addMoreData(areaBeanlist);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


}
