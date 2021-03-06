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

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.dialog.AvailableTypeViewAdapter;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.entity.getorder.dialog.AvailableTypeBean;
import com.ruitukeji.zwbs.entity.getorder.dialog.AvailableTypeBean.ResultBean;
import com.ruitukeji.zwbs.getorder.dialog.AvailableTypeBouncedContract;
import com.ruitukeji.zwbs.getorder.dialog.AvailableTypeBouncedPresenter;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

import java.util.List;

/**
 * 接单---可接单类型弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class AvailableTypeBouncedDialog extends BaseDialog implements AdapterView.OnItemClickListener, View.OnClickListener, AvailableTypeBouncedContract.View {

    private String availableTypeName = "all";

    private Context context;

    private ListView lv_availableType;

    private AvailableTypeViewAdapter availableTypeViewAdapter;

    private AvailableTypeBouncedContract.Presenter mPresenter;

    private List<ResultBean> availableTypeList = null;

    public AvailableTypeBouncedDialog(Context context, String availableTypeName) {
        super(context, R.style.dialog);
        this.context = context;
        this.availableTypeName = availableTypeName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_availabletype);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setWindowAnimations(R.style.dialog_supply_animation);  //添加动画
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        availableTypeViewAdapter = new AvailableTypeViewAdapter(context);
        mPresenter = new AvailableTypeBouncedPresenter(this);
        lv_availableType = (ListView) findViewById(R.id.lv_availableType);
        lv_availableType.setOnItemClickListener(this);
        lv_availableType.setAdapter(availableTypeViewAdapter);
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
        mPresenter.getAvailableType();
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
                RxBus.getInstance().post(new MsgEvent<String>("RxBusStartingPointEvent"));
                break;
            case R.id.tv_endPoint:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusEndPointEvent"));
                break;
            case R.id.tv_availableType:
                cancel();
                break;
            case R.id.tv_conductorModels:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusConductorModelsEvent"));
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedAvailableType(availableTypeViewAdapter.getItem(position).getName());
        confirm(availableTypeViewAdapter.getItem(position).getName(), availableTypeViewAdapter.getItem(position).getValue());
    }

    @Override
    public void setPresenter(AvailableTypeBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s) {
        dismissLoadingDialog();
        AvailableTypeBean availableTypeBean = (AvailableTypeBean) JsonUtil.json2Obj(s, AvailableTypeBean.class);
        availableTypeViewAdapter.clear();
        availableTypeList = availableTypeBean.getResult();
        if (availableTypeList != null && availableTypeList.size() > 0) {
            selectedAvailableType(availableTypeName);
        }
    }


    /**
     * 选中可接单类型
     *
     * @param availableTypeValue
     */

    private void selectedAvailableType(String availableTypeValue) {
        for (int i = 0; i < availableTypeList.size(); i++) {
            if (availableTypeValue.equals(availableTypeList.get(i).getName())) {
                ResultBean availableTypeBean = availableTypeList.get(i);
                availableTypeBean.setStatus(1);
            } else {
                availableTypeList.get(i).setStatus(0);
            }
        }
        availableTypeViewAdapter.clear();
        availableTypeViewAdapter.addMoreData(availableTypeList);
    }


    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


    public abstract void confirm(String availableTypeName, String availableTypeValue);
}
