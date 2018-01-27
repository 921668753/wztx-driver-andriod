package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.mine.vehiclecertification.LengthsNewViewAdapter;
import com.ruitukeji.zwbs.adapter.mine.vehiclecertification.TypesNewViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean.ResultBean.LengthBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean.ResultBean.TypeBean;
import com.ruitukeji.zwbs.getorder.dialog.ConductorModelsContract;
import com.ruitukeji.zwbs.getorder.dialog.ConductorModelsPresenter;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.myview.NoScrollGridView;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 货源---车型车长弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class ConductorModelsBouncedDialog extends BaseDialog implements View.OnClickListener, ConductorModelsContract.View, AdapterView.OnItemClickListener {

    /**
     * 车长
     */
    private NoScrollGridView gv_vehiclelength;
    private LengthsNewViewAdapter lengthsViewAdapter;
    List<LengthBean> lengthBeanlist;

    /**
     * 车型
     */
    private NoScrollGridView gv_vehiclemodel;
    private TypesNewViewAdapter typesViewAdapter;
    List<TypeBean> typeBeanlist;
    private LengthBean lengthBean;
    private TypeBean typeBean;
    private int vehicleModelId = 0;
    private int vehicleLengthId = 0;

    private ConductorModelsContract.Presenter mPresenter;

    private Context context;


    public ConductorModelsBouncedDialog(Context context, int vehicleLengthId, int vehicleModelId) {
        super(context, R.style.dialog);
        this.context = context;
        this.vehicleLengthId = vehicleLengthId;
        this.vehicleModelId = vehicleModelId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_conductormodelsbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setWindowAnimations(R.style.dialog_supply_animation);  //添加动画
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        gv_vehiclelength = (NoScrollGridView) findViewById(R.id.gv_vehiclelength);
        gv_vehiclemodel = (NoScrollGridView) findViewById(R.id.gv_vehiclemodel);
        TextView tv_emptying = (TextView) findViewById(R.id.tv_emptying);
        tv_emptying.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        LinearLayout ll_dialog = (LinearLayout) findViewById(R.id.ll_dialog);
        ll_dialog.setOnClickListener(this);
        TextView tv_startingPoint = (TextView) findViewById(R.id.tv_startingPoint);
        tv_startingPoint.setOnClickListener(this);
        TextView tv_endPoint = (TextView) findViewById(R.id.tv_endPoint);
        tv_endPoint.setOnClickListener(this);
        TextView tv_availableType = (TextView) findViewById(R.id.tv_availableType);
        tv_availableType.setOnClickListener(this);
        TextView tv_conductorModels = (TextView) findViewById(R.id.tv_conductorModels);
        tv_conductorModels.setOnClickListener(this);
        mPresenter = new ConductorModelsPresenter(this);
        lengthsViewAdapter = new LengthsNewViewAdapter(context);
        typesViewAdapter = new TypesNewViewAdapter(context);
        mPresenter.getConductorModels();
        gv_vehiclelength.setAdapter(lengthsViewAdapter);
        gv_vehiclelength.setOnItemClickListener(this);
        gv_vehiclemodel.setAdapter(typesViewAdapter);
        gv_vehiclemodel.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_dialog:
                cancel();
                break;
            case R.id.tv_startingPoint:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusStartingPointEvent"));
                break;
            case R.id.tv_endPoint:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusEndPointEvent"));
                break;
            case R.id.tv_availableType:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusAvailableTypeBouncedEvent"));
                break;
            case R.id.tv_conductorModels:
                cancel();
                break;
            case R.id.tv_emptying:
                confirm(context.getString(R.string.conductor), 0, context.getString(R.string.models), 0);
                break;
            case R.id.tv_determine:
                if (typeBean == null || lengthBean == null) {
                    ViewInject.toast(context.getString(R.string.pleaseSelect) + context.getString(R.string.conductorModels));
                    return;
                }
                confirm(lengthBean.getName(), lengthBean.getId(), typeBean.getName(), typeBean.getId());
                break;
        }
    }

    @Override
    public void setPresenter(ConductorModelsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s) {
        ConductorModelsBean conductorModelsBean = (ConductorModelsBean) JsonUtil.getInstance().json2Obj(s, ConductorModelsBean.class);
        lengthBeanlist = conductorModelsBean.getResult().getLength();
        LengthBean lengthBean = new LengthBean();
        lengthBean.setId(0);
        lengthBean.setName(context.getString(R.string.all));
        lengthBeanlist.add(0, lengthBean);
        if (lengthBeanlist != null && lengthBeanlist.size() > 0) {
            selectedLength(vehicleLengthId);
        }
        typeBeanlist = conductorModelsBean.getResult().getType();
        TypeBean typeBean = new TypeBean();
        typeBean.setId(0);
        typeBean.setName(context.getString(R.string.all));
        typeBeanlist.add(0, typeBean);
        if (typeBeanlist != null && typeBeanlist.size() > 0) {
            selectedType(vehicleModelId);
        }
        dismissLoadingDialog();

    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


    /**
     * 选中车长
     *
     * @param position
     */
    private void selectedLength(int position) {
        for (int i = 0; i < lengthBeanlist.size(); i++) {
            if (position == lengthBeanlist.get(i).getId() || position == i && position == 0) {
                lengthBean = lengthBeanlist.get(i);
                lengthBean.setStatus(1);
            } else {
                lengthBeanlist.get(i).setStatus(0);
            }
        }
        lengthsViewAdapter.clear();
        lengthsViewAdapter.addMoreData(lengthBeanlist);
    }

    /**
     * 选中车型
     *
     * @param position
     */
    private void selectedType(int position) {
        for (int i = 0; i < typeBeanlist.size(); i++) {
            if (position == typeBeanlist.get(i).getId() || position == i && position == 0) {
                typeBean = typeBeanlist.get(i);
                typeBean.setStatus(1);
            } else {
                typeBeanlist.get(i).setStatus(0);
            }
        }
        typesViewAdapter.clear();
        typesViewAdapter.addMoreData(typeBeanlist);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.gv_vehiclelength) {
            selectedLength(lengthsViewAdapter.getItem(position).getId());
        } else if (parent.getId() == R.id.gv_vehiclemodel) {
            selectedType(typesViewAdapter.getItem(position).getId());
        }
    }

    public abstract void confirm(String conductorName, int conductorId, String models, int modelsId);
}
