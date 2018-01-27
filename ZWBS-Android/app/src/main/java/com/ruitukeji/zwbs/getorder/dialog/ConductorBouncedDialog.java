package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.dialog.LengthsViewAdapter;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean.ResultBean.LengthBean;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 接单---车长弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class ConductorBouncedDialog extends BaseDialog implements AdapterView.OnItemClickListener, View.OnClickListener, ConductorModelsContract.View {

    private Context context;
    private ListView lv_models;
    private ConductorModelsContract.Presenter mPresenter;
    private int vehicleLengthId = 0;
    List<LengthBean> lengthBeanlist;
    private LengthBean lengthBean;
    private LengthsViewAdapter lengthsViewAdapter;

    public ConductorBouncedDialog(Context context, int vehicleLengthId) {
        super(context, R.style.dialog);
        this.context = context;
        this.vehicleLengthId = vehicleLengthId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modelsbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setWindowAnimations(R.style.dialog_getorder_animation);  //添加动画
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        lv_models = (ListView) findViewById(R.id.lv_models);
        lengthsViewAdapter = new LengthsViewAdapter(context);
        lv_models.setOnItemClickListener(this);
        lv_models.setAdapter(lengthsViewAdapter);
        TextView tv_city = (TextView) findViewById(R.id.tv_city);
        tv_city.setOnClickListener(this);
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setOnClickListener(this);
        TextView tv_ad = (TextView) findViewById(R.id.tv_ad);
        tv_ad.setOnClickListener(this);
        TextView tv_models = (TextView) findViewById(R.id.tv_models);
        tv_models.setOnClickListener(this);
        TextView tv_conductor = (TextView) findViewById(R.id.tv_conductor);
        tv_conductor.setOnClickListener(this);
        TextView tv_availableType = (TextView) findViewById(R.id.tv_availableType);
        tv_availableType.setOnClickListener(this);
        LinearLayout ll_models = (LinearLayout) findViewById(R.id.ll_models);
        ll_models.setOnClickListener(this);
        showLoadingDialog(context.getString(R.string.dataLoad));
        mPresenter = new ConductorModelsPresenter(this);
        mPresenter.getConductorModels();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_models:
                cancel();
                break;
            case R.id.tv_city:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusCityEvent"));
                break;
            case R.id.tv_back:
                cancel();
                break;
            case R.id.tv_message:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusMessageEvent"));
                break;
            case R.id.tv_ad:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusAdEvent"));
                break;
            case R.id.tv_models:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusModelsEvent"));
                break;
            case R.id.tv_conductor:
                cancel();
                break;
            case R.id.tv_availableType:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusAvailableTypeEvent"));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        selectedLength(lengthsViewAdapter.getItem(position).getId());
        confirm(lengthsViewAdapter.getItem(position).getName(), lengthsViewAdapter.getItem(position).getId());
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

    public abstract void confirm(String conductorName, int conductorId);
}
