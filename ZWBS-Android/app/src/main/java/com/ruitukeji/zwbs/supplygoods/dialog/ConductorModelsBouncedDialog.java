package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.LengthsViewAdapter;
import com.ruitukeji.zwbs.adapter.getorder.TypesViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.ConductorModelsBean;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.LengthBean;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.TypeBean;
import com.ruitukeji.zwbs.getorder.dialog.ConductorModelsContract;
import com.ruitukeji.zwbs.getorder.dialog.ConductorModelsPresenter;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;
import com.ruitukeji.zwbs.utils.myview.NoScrollGridView;

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
    private LengthsViewAdapter lengthsViewAdapter;
    List<LengthBean> lengthBeanlist;

    /**
     * 车型
     */
    private NoScrollGridView gv_vehiclemodel;
    private TypesViewAdapter typesViewAdapter;
    List<TypeBean> typeBeanlist;
    private LengthBean lengthBean;
    private TypeBean typeBean;
    private int vehicleModelId = 0;
    private int vehicleLengthId = 0;

    private ConductorModelsContract.Presenter mPresenter;

    private Context context;
    private TextView tv_transparent;
    private ChildLiistView lv_models;

    public ConductorModelsBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_conductormodelsbounced);
        initView();
    }

    private void initView() {
        gv_vehiclelength = (NoScrollGridView) findViewById(R.id.gv_vehiclelength);
        gv_vehiclemodel = (NoScrollGridView) findViewById(R.id.gv_vehiclemodel);
        tv_transparent = (TextView) findViewById(R.id.tv_transparent);
        tv_transparent.setOnClickListener(this);
        mPresenter = new ConductorModelsPresenter(this);
        lengthsViewAdapter = new LengthsViewAdapter(context);
        typesViewAdapter = new TypesViewAdapter(context);
        mPresenter.getConductorModels();
        gv_vehiclelength.setAdapter(lengthsViewAdapter);
        gv_vehiclelength.setOnItemClickListener(this);
        gv_vehiclemodel.setAdapter(typesViewAdapter);
        gv_vehiclemodel.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_transparent:
                dismiss();
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

    public abstract void confirm();
}
