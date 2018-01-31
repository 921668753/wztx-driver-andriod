package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.AddLineAreaViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 货源---添加路线区弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class AddLineAreaBouncedDialog extends BaseDialog implements AdapterView.OnItemClickListener, View.OnClickListener, OriginBouncedContract.View {

    private String cityName = "";
    private int cityId = 0;
    private Context context;

    private GridView gv_address;

    private AddLineAreaViewAdapter areaViewAdapter = null;


    ResultBean areaBean;
    private String areaName = "";
    private int areaId = 0;

    private OriginBouncedContract.Presenter mPresenter;

    private List<ResultBean> areaBeanlist = null;


    public AddLineAreaBouncedDialog(Context context, String cityName, int cityId) {
        super(context, R.style.dialog);
        this.context = context;
        this.cityName = cityName;
        this.cityId = cityId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_addlinecity);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        mPresenter = new OriginBouncedPresenter(this);
        areaViewAdapter = new AddLineAreaViewAdapter(context);
        gv_address = (GridView) findViewById(R.id.gv_address);
        gv_address.setOnItemClickListener(this);
        gv_address.setAdapter(areaViewAdapter);
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        TextView tv_select = (TextView) findViewById(R.id.tv_select);
        tv_select.setText(cityName);
        LinearLayout ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getAddress(cityId, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusAddTheLineFinishEvent"));
                break;
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                RxBus.getInstance().post(new MsgEvent<String>("RxBusAddTheLineDetermineEvent"));
                break;
            case R.id.ll_back:
                cancel();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        areaBean = areaViewAdapter.getItem(position);
        selectArea(areaBean.getId());
        cancel();
        if (areaBean.getName().startsWith(context.getString(R.string.all1))) {
            confirmArea("", 0);
        } else {
            confirmArea(areaBean.getName(), areaBean.getId());
        }
    }

    public abstract void confirmArea(String areaName, int areaId);

    @Override
    public void setPresenter(OriginBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        AddressBean addressBean = (AddressBean) JsonUtil.json2Obj(success, AddressBean.class);
        List<ResultBean> list = addressBean.getResult();
        if (list == null || list.size() == 0) {
            errorMsg(context.getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        areaBeanlist = list;
        selectArea(areaId);
        dismissLoadingDialog();
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
                gv_address.setSelection(i);
                gv_address.smoothScrollToPosition(i);
            } else {
                areaBeanlist.get(i).setStatus(0);
            }
        }
        areaViewAdapter.clear();
        areaViewAdapter.addMoreData(areaBeanlist);
    }


    public void setCityId(String cityName, int cityId) {
        this.cityName = cityName;
        this.cityId = cityId;
        TextView tv_select = (TextView) findViewById(R.id.tv_select);
        tv_select.setText(cityName);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getAddress(cityId, 0);
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
