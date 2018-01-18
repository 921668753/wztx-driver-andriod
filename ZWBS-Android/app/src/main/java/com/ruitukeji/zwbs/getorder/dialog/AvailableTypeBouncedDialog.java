package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.common.ViewInject;

/**
 * 接单---可接单类型弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class AvailableTypeBouncedDialog extends BaseDialog implements AdapterView.OnItemClickListener, AvailableTypeBouncedContract.View {

    private int availableTypeId = 0;
    private Context context;
    private TextView tv_transparent;
    private ListView lv_models;

    private AvailableTypeBouncedContract.Presenter mPresenter;

    public AvailableTypeBouncedDialog(Context context, int availableTypeId) {
        super(context, R.style.dialog);
        this.context = context;
        this.availableTypeId = availableTypeId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modelsbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        lv_models = (ListView) findViewById(R.id.lv_models);
        lv_models.setOnItemClickListener(this);
        mPresenter = new AvailableTypeBouncedPresenter(this);
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
