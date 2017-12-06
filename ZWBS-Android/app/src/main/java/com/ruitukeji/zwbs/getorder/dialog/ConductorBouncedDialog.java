package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.LengthsViewAdapter;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.ConductorModelsBean;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.LengthBean;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;

import java.util.List;

/**
 * 接单---车长弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class ConductorBouncedDialog extends BaseDialog implements View.OnClickListener, AdapterView.OnItemClickListener, ConductorModelsContract.View {

    private Context context;
    private TextView tv_transparent;
    private ChildLiistView lv_models;
    private ConductorModelsContract.Presenter mPresenter;
    private int vehicleLengthId = 0;
    List<LengthBean> lengthBeanlist;
    private LengthBean lengthBean;
    private LengthsViewAdapter lengthsViewAdapter;


    public ConductorBouncedDialog(Context context, int vehicleLengthId) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.vehicleLengthId = vehicleLengthId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modelsbounced);
        initView();
    }

    private void initView() {
        lv_models = (ChildLiistView) findViewById(R.id.lv_models);
        lengthsViewAdapter = new LengthsViewAdapter(context);
        lv_models.setOnItemClickListener(this);
        lv_models.setAdapter(lengthsViewAdapter);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        selectedLength(lengthsViewAdapter.getItem(position).getId());
    }

    @Override
    public void setPresenter(ConductorModelsContract.Presenter presenter) {
        mPresenter = presenter;
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
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    public abstract void confirm();
}