package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.supplygoods.dialog.AddLineProvinceViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 货源---添加路线省弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class AddLineProvinceBouncedDialog extends BaseDialog implements AdapterView.OnItemClickListener, View.OnClickListener, OriginBouncedContract.View {

    private int type = 0;
    private String provinceName = "";
    private int provinceId = 0;
    private Context context;

    private GridView gv_address;

    private AddLineProvinceViewAdapter addLineProvinceViewAdapter = null;

    private OriginBouncedContract.Presenter mPresenter;
    private ResultBean provinceBean;
    private List<ResultBean> provinceBeanlist = null;
    private AddLineCityBouncedDialog addLineCityBouncedDialog = null;

    public AddLineProvinceBouncedDialog(Context context, String provinceName, int provinceId, int type) {
        super(context, R.style.dialog);
        this.context = context;
        this.provinceName = provinceName;
        this.provinceId = provinceId;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_addlineprovince);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        mPresenter = new OriginBouncedPresenter(this);
        addLineProvinceViewAdapter = new AddLineProvinceViewAdapter(context);
        gv_address = (GridView) findViewById(R.id.gv_address);
        gv_address.setOnItemClickListener(this);
        gv_address.setAdapter(addLineProvinceViewAdapter);
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        TextView tv_select = (TextView) findViewById(R.id.tv_select);
        if (type == 0) {
            TextView tv_start = (TextView) findViewById(R.id.tv_start);
            tv_start.setOnClickListener(this);
            tv_select.setText(context.getString(R.string.selectOrigin1));
        } else {
            TextView tv_end = (TextView) findViewById(R.id.tv_end);
            tv_end.setOnClickListener(this);
            tv_select.setText(context.getString(R.string.selectDestination1));
        }
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter.getAddress(0, 0);
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
            case R.id.tv_start:
                cancel();
                break;
            case R.id.tv_end:
                cancel();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        provinceBean = addLineProvinceViewAdapter.getItem(position);
        selectProvince(provinceBean.getId());
        if (provinceBean.getName().startsWith(context.getString(R.string.all1))) {
            cancel();
            confirmProvince(provinceBean.getName(), provinceBean.getId(), 0, 0);
            return;
        }
        if (addLineCityBouncedDialog != null && !addLineCityBouncedDialog.isShowing()) {
            addLineCityBouncedDialog.show();
            addLineCityBouncedDialog.setProvinceId(provinceBean.getName(), provinceBean.getId());
            return;
        }
        addLineCityBouncedDialog = new AddLineCityBouncedDialog(context, provinceBean.getName(), provinceBean.getId()) {
            @Override
            public void confirmCity(String cityName, int cityId, int areaId) {
                this.cancel();
                if (cityName.contains(provinceBean.getName())) {
                    confirmProvince(cityName, provinceBean.getId(), cityId, areaId);
                    return;
                }
                confirmProvince(provinceBean.getName() + cityName, provinceBean.getId(), cityId, areaId);
            }
        };
        addLineCityBouncedDialog.show();
    }

    public abstract void confirmProvince(String provinceName, int provinceId, int cityId, int areaId);

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
        provinceBeanlist = list;
        selectProvince(provinceId);
        dismissLoadingDialog();
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
                gv_address.setSelection(i);
                gv_address.smoothScrollToPosition(i);
            } else {
                provinceBeanlist.get(i).setStatus(0);
            }
        }
        addLineProvinceViewAdapter.clear();
        addLineProvinceViewAdapter.addMoreData(provinceBeanlist);
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        if (addLineCityBouncedDialog != null) {
            addLineCityBouncedDialog.cancel();
        }
        addLineCityBouncedDialog = null;
        super.setOnDismissListener(listener);
    }
}
