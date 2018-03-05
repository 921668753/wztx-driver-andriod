package com.ruitukeji.zwbs.getorder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.getorder.OrderDetailsBean;
import com.ruitukeji.zwbs.getorder.dialog.GetOrderBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.SendQuotationBouncedDialog;
import com.ruitukeji.zwbs.supplygoods.dialog.AuthenticationBouncedDialog;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

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
     * 司机装卸货
     */
    @BindView(id = R.id.tv_driverCargo)
    private TextView tv_driverCargo;

    /**
     * 配送点
     */
    @BindView(id = R.id.ll_peiSongDian)
    private LinearLayout ll_peiSongDian;
    @BindView(id = R.id.tv_peiSongDian)
    private TextView tv_peiSongDian;

    /**
     * 配送点费用
     */
    @BindView(id = R.id.ll_costDistribution)
    private LinearLayout ll_costDistribution;
    @BindView(id = R.id.tv_costDistribution)
    private TextView tv_costDistribution;

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

    @BindView(id = R.id.ll_button)
    private LinearLayout ll_button;

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

    @BindView(id = R.id.ll_button1)
    private LinearLayout ll_button1;

    /**
     * 分割线
     */
    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    /**
     * 不同意
     */
    @BindView(id = R.id.tv_donotAgree, click = true)
    private TextView tv_donotAgree;

    /**
     * 同意
     */
    @BindView(id = R.id.tv_consent, click = true)
    private TextView tv_consent;


    private int order_id = 0;
    private String system_price = "0.00";
    private String mind_price = "0.00";
    private GetOrderBouncedDialog getOrderBouncedDialog = null;
    private AuthenticationBouncedDialog authenticationBouncedDialog = null;
    private SendQuotationBouncedDialog sendQuotationBouncedDialog = null;
    private AuthenticationBouncedDialog authenticationBouncedDialog1 = null;
    private AuthenticationBouncedDialog authenticationBouncedDialog2 = null;

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
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
        if (isGoWork == 1) {
            ViewInject.toast(getString(R.string.notOrder));
            return;
        }
        switch (v.getId()) {
            case R.id.tv_getorder:
                String money = "";
                if (StringUtils.isEmpty(mind_price) || mind_price.equals("0.00")) {
                    money = system_price;
                } else {
                    money = mind_price;
                }
                if (getOrderBouncedDialog != null && !getOrderBouncedDialog.isShowing()) {
                    getOrderBouncedDialog.show();
                    return;
                }
                //接单
                getOrderBouncedDialog = new GetOrderBouncedDialog(aty, order_id, money) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        tv_getorder.setVisibility(View.GONE);
                        tv_reject.setVisibility(View.GONE);
                        tv_sendQuotation.setVisibility(View.GONE);
                        Intent intent = new Intent();
                        // 设置结果 结果码，一个数据
                        setResult(RESULT_OK, intent);
                        // 结束该activity 结束之后，前面的activity才可以处理结果
                        aty.finish();
                    }
                };
                getOrderBouncedDialog.show();
                break;
            case R.id.tv_reject:

                if (authenticationBouncedDialog != null && !authenticationBouncedDialog.isShowing()) {
                    authenticationBouncedDialog.show();
                    return;
                }
                //拒绝
                authenticationBouncedDialog = new AuthenticationBouncedDialog(KJActivityStack.create().topActivity(), getString(R.string.confirmReceiptRejected)) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        //拒绝
                        showLoadingDialog(getString(R.string.submissionLoad));
                        ((OrderDetailsContract.Presenter) mPresenter).postRefuseOrder(order_id);
                    }
                };
                authenticationBouncedDialog.show();
                break;
            case R.id.tv_sendQuotation:
                if (sendQuotationBouncedDialog != null && !sendQuotationBouncedDialog.isShowing()) {
                    sendQuotationBouncedDialog.show();
                    return;
                }
                //报价
                sendQuotationBouncedDialog = new SendQuotationBouncedDialog(aty, order_id, system_price) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        tv_getorder.setVisibility(View.GONE);
                        tv_reject.setVisibility(View.GONE);
                        tv_sendQuotation.setVisibility(View.GONE);
                        Intent intent = new Intent();
                        // 设置结果 结果码，一个数据
                        setResult(RESULT_OK, intent);
                        // 结束该activity 结束之后，前面的activity才可以处理结果
                        aty.finish();
                    }
                };
                sendQuotationBouncedDialog.show();
                break;
            case R.id.tv_donotAgree:
                if (authenticationBouncedDialog1 != null && !authenticationBouncedDialog1.isShowing()) {
                    authenticationBouncedDialog1.show();
                    return;
                }
                //不同意
                authenticationBouncedDialog1 = new AuthenticationBouncedDialog(KJActivityStack.create().topActivity(), getString(R.string.confirmAgreeCancelOrder1)) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        //不同意
                        showLoadingDialog(getString(R.string.submissionLoad));
                        ((OrderDetailsContract.Presenter) mPresenter).postCancelGoodsComplete(order_id, 0);
                    }
                };
                authenticationBouncedDialog1.show();
                break;
            case R.id.tv_consent:
                if (authenticationBouncedDialog2 != null && !authenticationBouncedDialog2.isShowing()) {
                    authenticationBouncedDialog2.show();
                    return;
                }
                //同意
                authenticationBouncedDialog2 = new AuthenticationBouncedDialog(KJActivityStack.create().topActivity(), getString(R.string.confirmAgreeCancelOrder)) {
                    @Override
                    public void confirm() {
                        this.cancel();
                        //同意
                        showLoadingDialog(getString(R.string.submissionLoad));
                        ((OrderDetailsContract.Presenter) mPresenter).postCancelGoodsComplete(order_id, 1);
                    }
                };
                authenticationBouncedDialog2.show();
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
            tv_expectedMileage.setText(resultBean.getKilometres());
            if (StringUtils.isEmpty(resultBean.getUsecar_time()) || !StringUtils.isEmpty(resultBean.getUsecar_time()) && resultBean.getUsecar_time().equals("0")) {
                ll_estimatedTime.setVisibility(View.GONE);
            } else {
                ll_estimatedTime.setVisibility(View.VISIBLE);
                tv_estimatedTime.setText(resultBean.getUsecar_time());
            }
            system_price = resultBean.getSystem_price();
            if (!StringUtils.isEmpty(resultBean.getStatus()) && resultBean.getStatus().equals("init") || !StringUtils.isEmpty(resultBean.getStatus()) && resultBean.getStatus().equals("quote")) {
                if (StringUtils.isEmpty(resultBean.getMind_price()) || StringUtils.toDouble(resultBean.getMind_price()) == 0) {
                    tv_actualPrice.setText(getString(R.string.renminbi) + resultBean.getSystem_price());
                } else {
                    mind_price = resultBean.getMind_price();
                    tv_actualPrice.setText(getString(R.string.renminbi) + resultBean.getMind_price());
                }
                tv_thePlace.setText(resultBean.getOrg_city());
                tv_destination.setText(resultBean.getDest_city());
            } else {
                tv_thePlace.setText(resultBean.getOrg_detail());
                tv_destination.setText(resultBean.getDest_detail());
                tv_actualPrice.setText(getString(R.string.renminbi) + resultBean.getFinal_price());
            }
//            if (resultBean.getIs_cargo_receipt() == 0) {
//                tv_orderNeeds.setText(getString(R.string.orderNotNeeds));
//            } else {
//                tv_orderNeeds.setText(getString(R.string.orderNeeds));
//            }
            if (resultBean.getIs_cargo_receipt() == 1 && resultBean.getIs_express() == 1) {
                tv_orderNeeds.setVisibility(View.VISIBLE);
                tv_orderNeeds.setText(getString(R.string.orderNeeds));
            } else if (resultBean.getIs_cargo_receipt() == 1 && resultBean.getIs_express() == 0) {
                tv_orderNeeds.setVisibility(View.VISIBLE);
                tv_orderNeeds.setText(getString(R.string.orderNeeds1));
            } else if (resultBean.getIs_cargo_receipt() == 0) {
                tv_orderNeeds.setVisibility(View.VISIBLE);
                tv_orderNeeds.setText(getString(R.string.orderNotNeeds));
            } else {
                tv_orderNeeds.setVisibility(View.GONE);
            }


            if (resultBean.getIs_driver_dock() == 0) {
                tv_driverCargo.setText(getString(R.string.noNeed));
            } else {
                tv_driverCargo.setText(getString(R.string.need));
            }
            if (resultBean.getSpot() == 0) {
                ll_peiSongDian.setVisibility(View.GONE);
                ll_costDistribution.setVisibility(View.GONE);
            } else {
                ll_peiSongDian.setVisibility(View.VISIBLE);
                ll_costDistribution.setVisibility(View.VISIBLE);
                tv_peiSongDian.setText(resultBean.getSpot() + getString(R.string.ge));
                tv_costDistribution.setText(resultBean.getSpot_cost() + getString(R.string.ge1));
            }

            if (!StringUtils.isEmpty(resultBean.getStatus()) && resultBean.getStatus().equals("quote")) {
                tv_getorder.setVisibility(View.VISIBLE);
                tv_sendQuotation.setVisibility(View.VISIBLE);
                tv_divider.setVisibility(View.GONE);
                ll_button1.setVisibility(View.GONE);
            } else if (!StringUtils.isEmpty(resultBean.getStatus()) && resultBean.getStatus().equals("quoted") && resultBean.getIs_cancel() == 2) {
                ll_button.setVisibility(View.GONE);
                tv_divider.setVisibility(View.VISIBLE);
                ll_button1.setVisibility(View.VISIBLE);
            } else {
                tv_divider.setVisibility(View.GONE);
                ll_button.setVisibility(View.GONE);
                ll_button1.setVisibility(View.GONE);
            }
            RxBus.getInstance().post(new MsgEvent<String>("RxBusOrderMessageDetailsEvent"));
        } else if (flag == 1) {
            Intent intent = new Intent();
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        } else if (flag == 2) {
            Intent intent = new Intent();
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getOrderBouncedDialog != null) {
            getOrderBouncedDialog.cancel();
        }
        if (authenticationBouncedDialog != null) {
            authenticationBouncedDialog.cancel();
        }
        if (sendQuotationBouncedDialog != null) {
            sendQuotationBouncedDialog.cancel();
        }
        if (authenticationBouncedDialog1 != null) {
            authenticationBouncedDialog1.cancel();
        }
        if (authenticationBouncedDialog2 != null) {
            authenticationBouncedDialog2.cancel();
        }
        sendQuotationBouncedDialog = null;
        authenticationBouncedDialog = null;
        authenticationBouncedDialog1 = null;
        authenticationBouncedDialog2 = null;
        getOrderBouncedDialog = null;
    }
}