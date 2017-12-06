package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.TypesViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.ConductorModelsBean;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.TypeBean;

import java.util.List;

/**
 * 接单---车型弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class ModelsBouncedDialog extends BaseDialog implements View.OnClickListener, AdapterView.OnItemClickListener, ConductorModelsContract.View {

    private Context context;
    private TextView tv_transparent;
    private ChildLiistView lv_models;
    private ConductorModelsContract.Presenter mPresenter;
    private int modelsId = 0;
    private List<TypeBean> typeBeanlist;
    private TypeBean typeBean;
    private TypesViewAdapter typesViewAdapter;


    public ModelsBouncedDialog(Context context, int modelsId) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.modelsId = modelsId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modelsbounced);
        initView();
    }

    private void initView() {
        lv_models = (ChildLiistView) findViewById(R.id.lv_models);
        typesViewAdapter = new TypesViewAdapter(context);
        lv_models.setOnItemClickListener(this);
        lv_models.setAdapter(typesViewAdapter);
        tv_transparent = (TextView) findViewById(R.id.tv_transparent);
        tv_transparent.setOnClickListener(this);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter = new ConductorModelsPresenter(this);
        mPresenter.getConductorModels();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_transparent:
                confirm();
                break;
        }
    }

    @Override
    public void setPresenter(ConductorModelsContract.Presenter presenter) {
        mPresenter = presenter;
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
        selectedType(typesViewAdapter.getItem(position).getId());
    }

    @Override
    public void getSuccess(String s) {
        ConductorModelsBean conductorModelsBean = (ConductorModelsBean) JsonUtil.getInstance().json2Obj(s, ConductorModelsBean.class);
        typeBeanlist = conductorModelsBean.getResult().getType();
        ConductorModelsBean.ResultBean.TypeBean typeBean = new ConductorModelsBean.ResultBean.TypeBean();
        typeBean.setId(0);
        typeBean.setName(context.getString(R.string.all));
        typeBeanlist.add(0, typeBean);
        if (typeBeanlist != null && typeBeanlist.size() > 0) {
            selectedType(modelsId);
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    public abstract void confirm();
}