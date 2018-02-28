package com.ruitukeji.zwbs.mission;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.entity.mission.UploadReceiptVoucherBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

/**
 * 填写快递单
 * Created by Administrator on 2017/11/29.
 */

public class FillOutExpressActivity extends BaseActivity implements FillOutExpressContract.View {

    /**
     * 签收单邮寄地址
     */
    @BindView(id = R.id.tv_billReceipt)
    private TextView tv_billReceipt;

    /**
     * 签收单邮寄地址
     */
    @BindView(id = R.id.tv_mailingAddress)
    private TextView tv_mailingAddress;

    /**
     * 签收单收件人
     */
    @BindView(id = R.id.tv_recipient)
    private TextView tv_recipient;

    /**
     * 签收单联系方式
     */
    @BindView(id = R.id.tv_contactInformation)
    private TextView tv_contactInformation;

    /**
     * 签收单快递单号
     */
    @BindView(id = R.id.et_expressNumber)
    private EditText et_expressNumber;

    /**
     * 签收单快递公司
     */
    @BindView(id = R.id.et_expressCompany)
    private EditText et_expressCompany;

    /**
     * 完成订单
     */
    @BindView(id = R.id.tv_completeOrder, click = true)
    private TextView tv_completeOrder;

    private int g_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_filloutexpress);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new FillOutExpressPresenter(this);
        g_id = getIntent().getIntExtra("order_id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((FillOutExpressContract.Presenter) mPresenter).getReceiptInformation(g_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.fillOutExpress), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_completeOrder:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((FillOutExpressContract.Presenter) mPresenter).postFillOutExpress(g_id, et_expressNumber.getText().toString().trim(), et_expressCompany.getText().toString().trim());
                break;
        }
    }

    @Override
    public void setPresenter(FillOutExpressContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            UploadReceiptVoucherBean uploadReceiptVoucherBean = (UploadReceiptVoucherBean) JsonUtil.json2Obj(success, UploadReceiptVoucherBean.class);
            UploadReceiptVoucherBean.ResultBean resultBean = uploadReceiptVoucherBean.getResult();
            int cargo_is_express = resultBean.getCargo_is_express();
            if (cargo_is_express == 0) {
                tv_billReceipt.setText(getString(R.string.billReceipt1));
            } else {
                tv_billReceipt.setText(getString(R.string.billReceipt));
            }
            String cargo_address = resultBean.getCargo_address();
            String cargo_address_detail = resultBean.getCargo_address_detail();
            tv_mailingAddress.setText(cargo_address + cargo_address_detail);
            tv_recipient.setText(resultBean.getCargo_man());
            tv_contactInformation.setText(resultBean.getCargo_tel());
        } else if (flag == 1) {
            Intent intent = new Intent();
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (!toLigon1(msg)) {
            return;
        }
    }
}
