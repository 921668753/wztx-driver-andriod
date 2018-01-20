package com.ruitukeji.zwbs.supplygoods.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.getorder.dialog.AvailableTypeBouncedContract;
import com.ruitukeji.zwbs.getorder.dialog.AvailableTypeBouncedPresenter;
import com.ruitukeji.zwbs.utils.myview.ChildListView;

/**
 * 接单---可接单类型弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class AvailableTypeBouncedDialog extends BaseDialog implements View.OnClickListener, AdapterView.OnItemClickListener, AvailableTypeBouncedContract.View {

    private int availableTypeId = 0;
    private Context context;
    private TextView tv_transparent;
    private ChildListView lv_availableType;

    private AvailableTypeBouncedContract.Presenter mPresenter;

    public AvailableTypeBouncedDialog(Context context, int availableTypeId) {
        super(context, R.style.dialog);
        this.context = context;
        this.availableTypeId = availableTypeId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_availabletype);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        lv_availableType = (ChildListView) findViewById(R.id.lv_availableType);
        lv_availableType.setOnItemClickListener(this);
        mPresenter = new AvailableTypeBouncedPresenter(this);
        tv_transparent = (TextView) findViewById(R.id.tv_transparent);
        tv_transparent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_transparent:
                //  dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //confirm();
    }

    @Override
    public void setPresenter(AvailableTypeBouncedContract.Presenter presenter) {

    }

    @Override
    public void getSuccess(String s) {

    }


    /**
     * 选中可接单类型
     *
     * @param position
     */
    private void selectedLength(int position) {
//        for (int i = 0; i < lengthBeanlist.size(); i++) {
//            if (position == lengthBeanlist.get(i).getId() || position == i && position == 0) {
//                lengthBean = lengthBeanlist.get(i);
//                lengthBean.setStatus(1);
//            } else {
//                lengthBeanlist.get(i).setStatus(0);
//            }
//        }
//        lengthsViewAdapter.clear();
//        lengthsViewAdapter.addMoreData(lengthBeanlist);
    }


    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


    public abstract void confirm(String availableTypeName, int availableTypeId);
}
