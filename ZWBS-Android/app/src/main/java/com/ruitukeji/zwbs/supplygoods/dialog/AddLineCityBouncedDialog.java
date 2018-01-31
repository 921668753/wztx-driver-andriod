package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.AddLineCityViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 货源---添加路线市弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class AddLineCityBouncedDialog extends BaseDialog implements AdapterView.OnItemClickListener, View.OnClickListener, OriginBouncedContract.View {

    private String provinceName = "";
    private int provinceId = 0;
    private Context context;

    private GridView gv_address;

    ResultBean cityBean;
    private String cityName = "";
    private int cityId = 0;

    private OriginBouncedContract.Presenter mPresenter;

    private List<ResultBean> cityBeanlist = null;
    private AddLineCityViewAdapter cityViewAdapter = null;
    private AddLineAreaBouncedDialog addLineAreaBouncedDialog = null;

    public AddLineCityBouncedDialog(Context context, String provinceName, int provinceId) {
        super(context, R.style.dialog);
        this.context = context;
        this.provinceName = provinceName;
        this.provinceId = provinceId;
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
        cityViewAdapter = new AddLineCityViewAdapter(context);
        gv_address = (GridView) findViewById(R.id.gv_address);
        gv_address.setOnItemClickListener(this);
        gv_address.setAdapter(cityViewAdapter);
        LinearLayout ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        TextView tv_select = (TextView) findViewById(R.id.tv_select);
        tv_select.setText(provinceName);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getAddress(provinceId, 0);
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
        cityBean = cityViewAdapter.getItem(position);
        selectCity(cityBean.getId());
        if (cityBean.getName().startsWith(context.getString(R.string.all1))) {
            cancel();
            confirmCity("", 0, 0);
            return;
        }
        if (addLineAreaBouncedDialog != null && !addLineAreaBouncedDialog.isShowing()) {
            addLineAreaBouncedDialog.show();
            addLineAreaBouncedDialog.setCityId(cityBean.getName(), cityBean.getId());
            return;
        }
        addLineAreaBouncedDialog = new AddLineAreaBouncedDialog(context, cityBean.getName(), cityBean.getId()) {
            @Override
            public void confirmArea(String areaName, int areaId) {
                this.cancel();
                confirmCity(cityBean.getName() + areaName, cityBean.getId(), areaId);
            }
        };
        addLineAreaBouncedDialog.show();
    }


    public abstract void confirmCity(String cityName, int cityId, int areaId);

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
        cityBeanlist = list;
        selectCity(cityId);
        dismissLoadingDialog();
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
                gv_address.setSelection(i);
                gv_address.smoothScrollToPosition(i);
            } else {
                cityBeanlist.get(i).setStatus(0);
            }
        }
        cityViewAdapter.clear();
        cityViewAdapter.addMoreData(cityBeanlist);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    public void setProvinceId(String provinceName, int provinceId) {
        this.provinceName = provinceName;
        this.provinceId = provinceId;
        TextView tv_select = (TextView) findViewById(R.id.tv_select);
        tv_select.setText(provinceName);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getAddress(provinceId, 0);
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        if (addLineAreaBouncedDialog != null) {
            addLineAreaBouncedDialog.cancel();
        }
        addLineAreaBouncedDialog = null;
        super.setOnDismissListener(listener);
    }

}
