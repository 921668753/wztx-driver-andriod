package com.ruitukeji.zwbs.getorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.entity.getorder.OrderDetailsBean;
import com.ruitukeji.zwbs.getorder.dialog.GetOrderBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.SendQuotationBouncedDialog;
import com.ruitukeji.zwbs.main.GetOrderContract;
import com.ruitukeji.zwbs.supplygoods.dialog.AuthenticationBouncedDialog;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

/**
 * 订单详情
 * Created by Administrator on 2017/2/16.
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsContract.View {


    /**
     * 姓名
     */
    @BindView(id = R.id.tv_name)
    private TextView tv_name;


    /**
     * 标签
     */
    @BindView(id = R.id.img_state)
    private ImageView img_state;
    @BindView(id = R.id.img_z)
    private ImageView img_z;


    /**
     * 时间
     */
    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    /**
     * 货物名称
     */
    @BindView(id = R.id.tv_goodName)
    private TextView tv_goodName;

    /**
     * 订单号
     */
    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    /**
     * 重量
     */
    @BindView(id = R.id.tv_weight)
    private TextView tv_weight;

    /**
     * 车长
     */
    @BindView(id = R.id.tv_conductor)
    private TextView tv_conductor;

    /**
     * 车型
     */
    @BindView(id = R.id.tv_models)
    private TextView tv_models;


    /**
     * 体积
     */
    @BindView(id = R.id.tv_volume)
    private TextView tv_volume;


    /**
     * 起运地
     */
    @BindView(id = R.id.tv_thePlace)
    private TextView tv_thePlace;

    /**
     * 目的地
     */
    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    /**
     * 预计公里数
     */
    @BindView(id = R.id.tv_expectedMileage)
    private TextView tv_expectedMileage;

    /**
     * 预计耗时
     */
    @BindView(id = R.id.ll_estimatedTime)
    private LinearLayout ll_estimatedTime;
    @BindView(id = R.id.tv_estimatedTime)
    private TextView tv_estimatedTime;

    /**
     * 实际价格
     */
    @BindView(id = R.id.tv_actualPrice)
    private TextView tv_actualPrice;

    /**
     * 是否有签收单
     */
    @BindView(id = R.id.tv_orderNeeds)
    private TextView tv_orderNeeds;

    /**
     * 发送
     */
    @BindView(id = R.id.tv_getorder, click = true)
    private TextView tv_getorder;

    /**
     * 拒绝
     */
    @BindView(id = R.id.tv_reject, click = true)
    private TextView tv_reject;

    /**
     * 报价
     */
    @BindView(id = R.id.tv_sendQuotation, click = true)
    private TextView tv_sendQuotation;

    private int order_id = 0;
    private String system_price = "0.00";
    private String mind_price = "0.00";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new OrderDetailsPresenter(this);
        order_id = getIntent().getIntExtra("order_id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((OrderDetailsContract.Presenter) mPresenter).getGoodDetails(order_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.orderDetails), true, R.id.titlebar);
//        order_id = getIntent().getIntExtra("order_id", 0);
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((OrderDetailsContract.Presenter) mPresenter).getGoodDetails(order_id);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_getorder:
                String money = "";
                if (StringUtils.isEmpty(mind_price) || mind_price.equals("0.00")) {
                    money = system_price;
                } else {
                    money = mind_price;
                }
                //接单
                GetOrderBouncedDialog getOrderBouncedDialog = new GetOrderBouncedDialog(aty, order_id, money) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        tv_getorder.setVisibility(View.GONE);
                        tv_reject.setVisibility(View.GONE);
                        tv_sendQuotation.setVisibility(View.GONE);
                        finish();
                    }
                };
                getOrderBouncedDialog.show();
                break;
            case R.id.tv_reject:
                //拒绝
                AuthenticationBouncedDialog authenticationBouncedDialog = new AuthenticationBouncedDialog(KJActivityStack.create().topActivity(), getString(R.string.confirmReceiptRejected)) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        //拒绝
                        showLoadingDialog(getString(R.string.submissionLoad));
                        ((GetOrderContract.Presenter) mPresenter).postRefuseOrder(order_id);
                    }
                };
                authenticationBouncedDialog.show();
                break;
            case R.id.tv_sendQuotation:
                //报价
                SendQuotationBouncedDialog sendQuotationBouncedDialog = new SendQuotationBouncedDialog(aty, order_id, system_price) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        tv_getorder.setVisibility(View.GONE);
                        tv_reject.setVisibility(View.GONE);
                        tv_sendQuotation.setVisibility(View.GONE);
                        finish();
                    }
                };
                sendQuotationBouncedDialog.show();
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            OrderDetailsBean orderDetailsBean = (OrderDetailsBean) JsonUtil.json2Obj(s, OrderDetailsBean.class);
            OrderDetailsBean.ResultBean resultBean = orderDetailsBean.getResult();
            if (resultBean == null) {
                errorMsg(getString(R.string.serverReturnsDataError), 0);
                return;
            }
            tv_name.setText(resultBean.getOrg_send_name());
            if (StringUtils.isEmpty(resultBean.getType())) {
                img_state.setBackground(null);
            } else if (resultBean.getType().equals("often")) {
                img_state.setImageResource(R.mipmap.label_shishi);
            } else if (resultBean.getType().equals("urgent")) {
                img_state.setImageResource(R.mipmap.label_jiaji);
            } else if (resultBean.getType().equals("appoint")) {
                img_state.setImageResource(R.mipmap.label_yuyue);
            }
            if (resultBean.getIs_assigned() == 0) {
                img_z.setVisibility(View.GONE);
                tv_reject.setVisibility(View.GONE);
            } else {
                img_z.setVisibility(View.VISIBLE);
                tv_reject.setVisibility(View.VISIBLE);
            }
            tv_time.setText(resultBean.getAppoint_at());
            tv_goodName.setText(resultBean.getGoods_name());
            tv_orderNumber.setText(resultBean.getOrder_code());
            tv_conductor.setText(resultBean.getCar_style_length());
            tv_models.setText(resultBean.getCar_style_type());
            tv_weight.setText(resultBean.getWeight());
            tv_volume.setText(resultBean.getVolume());
            tv_thePlace.setText(resultBean.getOrg_city());
            tv_destination.setText(resultBean.getDest_city());
            tv_expectedMileage.setText(resultBean.getKilometres());
            if (StringUtils.isEmpty(resultBean.getUsecar_time())) {
                ll_estimatedTime.setVisibility(View.GONE);
            } else {
                ll_estimatedTime.setVisibility(View.VISIBLE);
                tv_estimatedTime.setText(resultBean.getUsecar_time());
            }
            system_price = resultBean.getSystem_price();
            if (StringUtils.isEmpty(resultBean.getMind_price()) || resultBean.getMind_price().equals("0.00")) {
                tv_actualPrice.setText(getString(R.string.renminbi) + resultBean.getSystem_price());
            } else {
                mind_price = resultBean.getMind_price();
                tv_actualPrice.setText(getString(R.string.renminbi) + resultBean.getMind_price());
            }
            if (resultBean.getIs_cargo_receipt() == 0) {
                tv_orderNeeds.setText(getString(R.string.orderNotNeeds));
            } else {
                tv_orderNeeds.setText(getString(R.string.orderNeeds));
            }
            if (!StringUtils.isEmpty(resultBean.getStatus()) && resultBean.getStatus().equals("quote")) {
                tv_getorder.setVisibility(View.VISIBLE);
                tv_sendQuotation.setVisibility(View.VISIBLE);
            } else {
                tv_getorder.setVisibility(View.GONE);
                tv_sendQuotation.setVisibility(View.GONE);
            }
        } else if (flag == 1) {
            finish();
        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (!toLigon1(msg)) {
            finish();
        }
    }


    @Override
    public void setPresenter(OrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}