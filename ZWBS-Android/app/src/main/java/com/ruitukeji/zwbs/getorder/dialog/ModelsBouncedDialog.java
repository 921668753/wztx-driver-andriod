package com.ruitukeji.zwbs.getorder.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;

/**
 * 接单---车型弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class ModelsBouncedDialog extends BaseDialog implements View.OnClickListener, ConductorModelsContract.View {

    private Context context;
    private TextView tv_transparent;
    private ChildLiistView lv_models;
    private ConductorModelsContract.Presenter mPresenter;

    public ModelsBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modelsbounced);
        initView();
    }

    private void initView() {
        lv_models = (ChildLiistView) findViewById(R.id.lv_models);
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

    @Override
    public void getSuccess(String s) {

    }

    @Override
    public void error(String msg) {

    }

    public abstract void confirm();
}
