package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.AreaViewAdapter;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.CityViewAdapter;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.ProvinceViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 货源---出发地弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class OriginBouncedDialog extends BaseDialog implements AdapterView.OnItemClickListener, View.OnClickListener, OriginBouncedContract.View {

    private int type = 0;
    private Context context;

    private ListView lv_province;

    private ProvinceViewAdapter provinceViewAdapter = null;

    private ListView lv_city;

    private CityViewAdapter cityViewAdapter = null;

    private ListView lv_area;

    private AreaViewAdapter areaViewAdapter = null;

    ResultBean provinceBean;
    private String provinceName = "";
    private int provinceId = 0;

    ResultBean cityBean;
    private String cityName = "";
    private int cityId = 0;

    ResultBean areaBean;
    private String areaName = "";
    private int areaId = 0;

    private OriginBouncedContract.Presenter mPresenter;
    private List<ResultBean> provinceBeanlist = null;
    private List<ResultBean> cityBeanlist = null;
    private List<ResultBean> areaBeanlist = null;


    public OriginBouncedDialog(Context context, String provinceName, int provinceId, String cityName, int cityId, String areaName, int areaId, int type) {
        super(context, R.style.dialog);
        this.context = context;
        this.provinceName = provinceName;
        this.provinceId = provinceId;
        this.cityName = cityName;
        this.cityId = cityId;
        this.areaName = areaName;
        this.areaId = areaId;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_originbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setWindowAnimations(R.style.dialog_supply_animation);  //添加动画
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
        LinearLayout ll_dialog = (LinearLayout) findViewById(R.id.ll_dialog);
        ll_dialog.setOnClickListener(this);
        TextView tv_setLine = (TextView) findViewById(R.id.tv_setLine);
        tv_setLine.setOnClickListener(this);
        TextView tv_startingPoint = (TextView) findViewById(R.id.tv_startingPoint);
        tv_startingPoint.setOnClickListener(this);
        TextView tv_endPoint = (TextView) findViewById(R.id.tv_endPoint);
        tv_endPoint.setOnClickListener(this);
        TextView tv_availableType = (TextView) findViewById(R.id.tv_availableType);
        tv_availableType.setOnClickListener(this);
        TextView tv_conductorModels = (TextView) findViewById(R.id.tv_conductorModels);
        tv_conductorModels.setOnClickListener(this);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getAddress(0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_dialog:
                cancel();
                break;
            case R.id.tv_setLine:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusSetLineEvent"));
                break;
            case R.id.tv_startingPoint:
                cancel();
                if (type == 1) {
                    RxBus.getInstance().post(new MsgEvent<String>("RxBusStartingPointEvent"));
                }
                break;
            case R.id.tv_endPoint:
                cancel();
                if (type == 0) {
                    RxBus.getInstance().post(new MsgEvent<String>("RxBusEndPointEvent"));
                }
                break;
            case R.id.tv_availableType:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusAvailableTypeBouncedEvent"));
                break;
            case R.id.tv_conductorModels:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusConductorModelsEvent"));
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lv_province) {
            provinceBean = provinceViewAdapter.getItem(position);
            selectProvince(provinceBean.getId());
            if (provinceBean.getName().startsWith(context.getString(R.string.all1))) {
                cancel();
                confirm(provinceBean.getName(), provinceBean.getId(), 0, 0);
                return;
            }
        } else if (parent.getId() == R.id.lv_city) {
            cityBean = cityViewAdapter.getItem(position);
            selectCity(cityBean.getId());
            if (cityBean.getName().startsWith(context.getString(R.string.all1))) {
                cancel();
                confirm(provinceBean.getName(), provinceBean.getId(), 0, 0);
                return;
            }
        } else if (parent.getId() == R.id.lv_area) {
            areaBean = areaViewAdapter.getItem(position);
            selectArea(areaBean.getId());
            cancel();
            if (areaBean.getName().startsWith(context.getString(R.string.all1)) && cityBean.getName().contains(provinceBean.getName())) {
                confirm(cityBean.getName(), provinceBean.getId(), cityBean.getId(), areaBean.getId());
            } else if (areaBean.getName().startsWith(context.getString(R.string.all1)) && !cityBean.getName().contains(provinceBean.getName())) {
                confirm(cityBean.getName(), provinceBean.getId(), cityBean.getId(), areaBean.getId());
            } else if (cityBean.getName() != null && cityBean.getName().contains(provinceBean.getName())) {
                confirm(areaBean.getName(), provinceBean.getId(), cityBean.getId(), areaBean.getId());
            } else {
                confirm(areaBean.getName(), provinceBean.getId(), cityBean.getId(), areaBean.getId());
            }
        }
    }

    public abstract void confirm(String addressName, int provinceId, int cityId, int areaId);

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
            if (provinceId == provinceBeanlist.get(i).getId() || provinceId == i && provinceId == 0 && StringUtils.isEmpty(provinceName)
                    || provinceId == 0 && !StringUtils.isEmpty(provinceName) && provinceName.contains(provinceBeanlist.get(i).getName())) {
                provinceBean = provinceBeanlist.get(i);
                provinceBean.setStatus(1);
                lv_province.setSelection(i);
                lv_province.smoothScrollToPosition(i);
                cityViewAdapter.clear();
                areaViewAdapter.clear();
                if (provinceBean.getName().startsWith(context.getString(R.string.all1))) {
                    dismissLoadingDialog();
                } else {
                    mPresenter.getAddress(provinceBean.getId(), 1);
                }
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
            if (cityId == cityBeanlist.get(i).getId() || cityId == i && cityId == 0 && StringUtils.isEmpty(cityName)
                    || cityId == 0 && !StringUtils.isEmpty(cityName) && cityName.contains(cityBeanlist.get(i).getName())) {
                cityBean = cityBeanlist.get(i);
                cityBean.setStatus(1);
                lv_city.setSelection(i);
                lv_city.smoothScrollToPosition(i);
                areaViewAdapter.clear();
                if (cityBean.getName().startsWith(context.getString(R.string.all1))) {
                    dismissLoadingDialog();
                } else {
                    mPresenter.getAddress(cityBean.getId(), 2);
                }
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
            if (areaId == areaBeanlist.get(i).getId() || areaId == i && areaId == 0 && StringUtils.isEmpty(areaName)
                    || areaId == 0 && !StringUtils.isEmpty(areaName) && areaId == 0 && areaName.contains(areaBeanlist.get(i).getName())) {
                areaBean = areaBeanlist.get(i);
                areaBean.setStatus(1);
                lv_area.setSelection(i);
                lv_area.smoothScrollToPosition(i);
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
