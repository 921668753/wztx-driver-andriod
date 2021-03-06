package com.ruitukeji.zwbs.mine.vehiclecertification;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.mine.vehiclecertification.LengthsNewViewAdapter;
import com.ruitukeji.zwbs.adapter.mine.vehiclecertification.TypesNewViewAdapter;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean.ResultBean.LengthBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean.ResultBean.TypeBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.myview.NoScrollGridView;

import java.util.List;

/**
 * 车长车型选择
 * Created by Administrator on 2017/2/21.
 */

public class ConductorModelsActivity extends BaseActivity implements ConductorModelsContract.View, AdapterView.OnItemClickListener {

    /**
     * 车长
     */
    @BindView(id = R.id.gv_vehiclelength)
    private NoScrollGridView gv_vehiclelength;
    private LengthsNewViewAdapter lengthsViewAdapter;
    List<LengthBean> lengthBeanlist;

    /**
     * 车型
     */
    @BindView(id = R.id.gv_vehiclemodel)
    private NoScrollGridView gv_vehiclemodel;
    private TypesNewViewAdapter typesViewAdapter;
    List<TypeBean> typeBeanlist;
    private LengthBean lengthBean;
    private TypeBean typeBean;
    private int vehicleModelId = 0;
    private int vehicleLengthId = 0;
    private int isAll = 0;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_conductormodels);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ConductorModelsPresenter(this);
        lengthsViewAdapter = new LengthsNewViewAdapter(this);
        typesViewAdapter = new TypesNewViewAdapter(this);
        vehicleModelId = getIntent().getIntExtra("vehicleModelId", 0);
        vehicleLengthId = getIntent().getIntExtra("vehicleLengthId", 0);
        isAll = getIntent().getIntExtra("isAll", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.selectVehicle), true, R.id.titlebar);
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        ((ConductorModelsContract.Presenter) mPresenter).getConductorModels();
        gv_vehiclelength.setAdapter(lengthsViewAdapter);
        gv_vehiclelength.setOnItemClickListener(this);
        gv_vehiclemodel.setAdapter(typesViewAdapter);
        gv_vehiclemodel.setOnItemClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_submit:
                if (typeBean == null || lengthBean == null) {
                    ViewInject.toast(getString(R.string.pleaseSelect) + getString(R.string.conductorModels));
                    return;
                }
                Intent intent = new Intent();
                // 获取内容
                intent.putExtra("vehicleModelId", typeBean.getId());
                intent.putExtra("vehicleModel", typeBean.getName());
                intent.putExtra("vehicleLengthId", lengthBean.getId());
                intent.putExtra("vehicleLength", lengthBean.getName());
                // 设置结果 结果码，一个数据
                setResult(RESULT_OK, intent);
                // 结束该activity 结束之后，前面的activity才可以处理结果
                aty.finish();
                break;
        }
    }

    @Override
    public void getSuccess(String s) {
        ConductorModelsBean conductorModelsBean = (ConductorModelsBean) JsonUtil.getInstance().json2Obj(s, ConductorModelsBean.class);
        lengthBeanlist = conductorModelsBean.getResult().getLength();
        if (isAll == 1) {
            LengthBean lengthBean = new LengthBean();
            lengthBean.setId(0);
            lengthBean.setName(getString(R.string.all));
            lengthBeanlist.add(0, lengthBean);
        }
        if (lengthBeanlist != null && lengthBeanlist.size() > 0) {
            selectedLength(vehicleLengthId);
        }
        typeBeanlist = conductorModelsBean.getResult().getType();
        if (isAll == 1) {
            TypeBean typeBean = new TypeBean();
            typeBean.setId(0);
            typeBean.setName(getString(R.string.all));
            typeBeanlist.add(0, typeBean);
        }
        if (typeBeanlist != null && typeBeanlist.size() > 0) {
            selectedType(vehicleModelId);
        }
        dismissLoadingDialog();
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
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(ConductorModelsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.gv_vehiclelength) {
            selectedLength(lengthsViewAdapter.getItem(position).getId());
        } else if (parent.getId() == R.id.gv_vehiclemodel) {
            selectedType(typesViewAdapter.getItem(position).getId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lengthsViewAdapter.clear();
        lengthsViewAdapter = null;
        typesViewAdapter.clear();
        typesViewAdapter = null;
    }
}
