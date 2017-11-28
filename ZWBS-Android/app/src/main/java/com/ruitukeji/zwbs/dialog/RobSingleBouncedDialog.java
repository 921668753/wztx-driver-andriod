package com.ruitukeji.zwbs.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.entity.GetOrderBean.ResultBean.ListBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.ruitukeji.zwbs.utils.MathUtil.judgeTwoDecimal;

/**
 * 抢单--------抢单弹框
 * Created by Administrator on 2017/2/21.
 */

public abstract class RobSingleBouncedDialog extends Dialog implements RobSingleContract.View {

    private int goods_id;
    private final ListBean listBean;
    private Context context;
    private EditText etMoney;
    private SweetAlertDialog mLoadingDialog = null;
    private RobSingleContract.Presenter mPresenter;
    private TextView tv_origin;
    private TextView tv_destination;
    private TextView tv_descriptionGoods;
    private TextView tv_weight;
    private TextView tv_systemOffer;
    private TextView tv_shipperOffer;
    private TextView tv_transportTime;


    public RobSingleBouncedDialog(Context context, ListBean listBean) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.listBean = listBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_robsinglebounced);
        initView();
    }

    private void initView() {
        tv_origin = (TextView) findViewById(R.id.tv_origin);
        tv_destination = (TextView) findViewById(R.id.tv_destination);
        tv_descriptionGoods = (TextView) findViewById(R.id.tv_descriptionGoods);
        tv_weight = (TextView) findViewById(R.id.tv_weight);
        tv_transportTime = (TextView) findViewById(R.id.tv_transportTime);
        tv_systemOffer = (TextView) findViewById(R.id.tv_systemOffer);
        tv_shipperOffer = (TextView) findViewById(R.id.tv_shipperOffer);
        etMoney = (EditText) findViewById(R.id.et_money);
        findViewById(R.id.btn_confirm);
        findViewById(R.id.img_cancel).setOnClickListener(v -> {
            this.dismiss();
            this.cancel();
        });
        goods_id = listBean.getId();
        mPresenter = new RobSinglePresenter(this);
        // InputFilter[] filters = {new EditInputFilter(context, balance)};
        // etMoney.setFilters(filters);
        findViewById(R.id.btn_confirm).setOnClickListener(v -> {
            String moneys = etMoney.getText().toString().trim();
            if (moneys == null || moneys.length() <= 0) {
                if (listBean.getMind_price() == null || listBean.getMind_price().equals("0.00")) {
                    mPresenter.getQuoteAdd(goods_id, listBean.getSystem_price(), 1);
                    return;
                }
                mPresenter.getQuoteAdd(goods_id, listBean.getMind_price(), 1);
                //ViewInject.toast(context.getString(R.string.hintDriverBid));
                return;
            }
            if (judgeTwoDecimal(moneys)) {
                mPresenter.getQuoteAdd(goods_id, moneys, 0);
//                confirm(moneys);
//                this.dismiss();
            } else {
                ViewInject.toast(context.getString(R.string.hintDriverBid1));
            }
        });
        getSuccess("", 0);
//        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
//        mPresenter.getQuoteInfo(quote_id);
    }

    ;

    public abstract void confirm(String money);

    public void showKeyboard() {
        if (etMoney != null) {
            //设置可获得焦点
            etMoney.setFocusable(true);
            etMoney.setFocusableInTouchMode(true);
            //请求获得焦点
            etMoney.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(etMoney, 0);
        }
    }

    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(KJActivityStack.create().topActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(KJActivityStack.create().topActivity().getResources().getColor(R.color.lonincolors));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
    }

    @Override
    public void setPresenter(RobSingleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
//            RobSingleBean robSingleBean = (RobSingleBean) JsonUtil.getInstance().json2Obj(s, RobSingleBean.class);
            int orgprovince = listBean.getOrg_city().indexOf("省");
            int orgcity = listBean.getOrg_city().indexOf("市");
            if (orgprovince == -1 && orgcity != -1) {
                tv_origin.setText(listBean.getOrg_city().substring(0, orgcity));
            } else if (orgprovince != -1 && orgcity != -1) {
                tv_origin.setText(listBean.getOrg_city().substring(0, orgprovince) + listBean.getOrg_city().substring(orgprovince + 1, orgcity));
            } else {
                tv_origin.setText(listBean.getOrg_city());
            }
            int destprovince = listBean.getDest_city().indexOf("省");
            int destcity = listBean.getDest_city().indexOf("市");
            if (destprovince == -1 && destcity != -1) {
                tv_destination.setText(listBean.getDest_city().substring(0, destcity));
            } else if (destprovince != -1 && destcity != -1) {
                tv_destination.setText(listBean.getDest_city().substring(0, destprovince) + listBean.getDest_city().substring(destprovince + 1, destcity));
            } else {
                tv_destination.setText(listBean.getDest_city());
            }
            tv_descriptionGoods.setText(listBean.getGoods_name());
            tv_weight.setText(listBean.getWeight() + "吨");
            tv_transportTime.setText(listBean.getUsecar_time());
            tv_systemOffer.setText(listBean.getSystem_price() + "元");
            tv_shipperOffer.setText(listBean.getMind_price() + "元");
            if (StringUtils.isEmpty(listBean.getMind_price()) || listBean.getMind_price().equals("0.00")) {
                tv_shipperOffer.setTextColor(context.getResources().getColor(R.color.textColor));
            } else {
                tv_systemOffer.setTextColor(context.getResources().getColor(R.color.textColor));
            }
        } else if (flag == 1) {
            confirm(etMoney.getText().toString().trim());
            this.dismiss();
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
