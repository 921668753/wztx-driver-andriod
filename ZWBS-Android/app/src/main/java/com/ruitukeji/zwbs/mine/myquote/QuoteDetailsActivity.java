package com.ruitukeji.zwbs.mine.myquote;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.OrderDetailsBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 订单详情
 * Created by Administrator on 2017/2/16.
 */

public class QuoteDetailsActivity extends BaseActivity
        // implements QuoteDetailsContract.View
{

//    /**
//     * 订单状态
//     */
//    @BindView(id = R.id.tv_orderStatus)
//    private TextView tv_orderStatus;
//    /**
//     * 订单号
//     */
//
//    @BindView(id = R.id.ll_orderNumber)
//    private LinearLayout ll_orderNumber;
//    @BindView(id = R.id.tv_orderNumber)
//    private TextView tv_orderNumber;
//    /**
//     * 货物名称
//     */
//    @BindView(id = R.id.tv_descriptionGoods)
//    private TextView tv_descriptionGoods;
//
//    /**
//     * 保险单号
//     */
//    @BindView(id = R.id.ll_insurancePolicy)
//    private LinearLayout ll_insurancePolicy;
//
//    @BindView(id = R.id.tv_insurancePolicy)
//    private TextView tv_insurancePolicy;
//
//    @BindView(id = R.id.tv_checkDetails)
//    private TextView tv_checkDetails;
//
//    /**
//     * 重量
//     */
//    @BindView(id = R.id.tv_weight)
//    private TextView tv_weight;
//
//    /**
//     * 货物回单
//     */
//    @BindView(id = R.id.tv_receiptGoods)
//    private TextView tv_receiptGoods;
//
//    /**
//     * 货物在途时效
//     */
//    @BindView(id = R.id.ll_inTransitTime)
//    private LinearLayout ll_inTransitTime;
//    @BindView(id = R.id.tv_inTransitTime)
//    private TextView tv_inTransitTime;
//
//    /**
//     * 起始地
//     */
//    @BindView(id = R.id.tv_origin)
//    private TextView tv_origin;
//    /**
//     * 目的地
//     */
//    @BindView(id = R.id.tv_destination)
//    private TextView tv_destination;
//
//    @BindView(id = R.id.ll_deliveryInformation)
//    private LinearLayout ll_deliveryInformation;
//
//    /**
//     * 收货人姓名
//     */
//    @BindView(id = R.id.tv_consigneeName)
//    private TextView tv_consigneeName;
//    /**
//     * 收货人电话
//     */
//    @BindView(id = R.id.tv_consigneeTelephone, click = true)
//    private TextView tv_consigneeTelephone;
//    /**
//     * 收货人地址
//     */
//    @BindView(id = R.id.tv_consigneeAddress)
//    private TextView tv_consigneeAddress;
//    @BindView(id = R.id.tv_consigneeAddress1)
//    private TextView tv_consigneeAddress1;
//    /**
//     * 寄件人姓名
//     */
//    @BindView(id = R.id.tv_senderName)
//    private TextView tv_senderName;
//    /**
//     * 寄件人电话
//     */
//    @BindView(id = R.id.tv_senderPhone, click = true)
//    private TextView tv_senderPhone;
//    /**
//     * 寄件人地址
//     */
//    @BindView(id = R.id.tv_senderAddress)
//    private TextView tv_senderAddress;
//    @BindView(id = R.id.tv_senderAddress1)
//    private TextView tv_senderAddress1;
//    /**
//     * 用车时间
//     */
//    @BindView(id = R.id.tv_transportTime)
//    private TextView tv_transportTime;
//
//
//    @BindView(id = R.id.ll_deliveryTime1)
//    private LinearLayout ll_deliveryTime1;
//
//    @BindView(id = R.id.tv_deliveryTime)
//    private TextView tv_deliveryTime;
//
//    @BindView(id = R.id.tv_deliveryTime1)
//    private TextView tv_deliveryTime1;
//
//    @BindView(id = R.id.ll_arrivalTime)
//    private LinearLayout ll_arrivalTime;
//
//    @BindView(id = R.id.tv_arrivalTime)
//    private TextView tv_arrivalTime;
//
//    @BindView(id = R.id.tv_arrivalTime1)
//    private TextView tv_arrivalTime1;
//
//    /**
//     * 车主姓名
//     */
//    @BindView(id = R.id.ll_ownerName)
//    private LinearLayout ll_ownerName;
//    @BindView(id = R.id.tv_ownerName)
//    private TextView tv_ownerName;
//    /**
//     * 联系电话
//     */
//    @BindView(id = R.id.ll_contactPhoneNumber)
//    private LinearLayout ll_contactPhoneNumber;
//    @BindView(id = R.id.tv_contactPhoneNumber)
//    private TextView tv_contactPhoneNumber;
//    /**
//     * 总运费
//     */
//    @BindView(id = R.id.ll_totalFreight)
//    private LinearLayout ll_totalFreight;
//    @BindView(id = R.id.tv_totalFreight)
//    private TextView tv_totalFreight;
//
//    /**
//     * 备注
//     */
//    @BindView(id = R.id.tv_remark)
//    private TextView tv_remark;
//
//    @BindView(id = R.id.tv_driverQuotation)
//    private TextView tv_driverQuotation;
//
//
//    @BindView(id = R.id.ll_driverQuotation)
//    private LinearLayout ll_driverQuotation;
//
//    @BindView(id = R.id.et_driverQuotation)
//    private EditText et_driverQuotation;
//
//
//    /**
//     * 查看凭证
//     */
//    @BindView(id = R.id.ll_commonBottombar)
//    private LinearLayout ll_commonBottombar;
//
//    /**
//     * 确认报价
//     */
//    @BindView(id = R.id.tv_bottombar, click = true)
//    private TextView tv_bottombar;
    /**
     * 订单状态
     */
    private String status = "";

    private QuoteDetailsContract.Presenter mPresenter;
    private int goods_id;
    private String designation;

    private SweetAlertDialog sweetAlertDialog = null;
    private String refreshName;
    private int is_receive;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

//    @Override
//    public void initData() {
//        super.initData();
//           initDialog();
//        mPresenter = new QuoteDetailsPresenter(this);
//    }

    @Override
    public void initWidget() {
        super.initWidget();
        goods_id = getIntent().getIntExtra("goods_id", 0);
        status = getIntent().getStringExtra("status");
        is_receive = getIntent().getIntExtra("is_receive", 0);
        refreshName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "refreshName");
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        // if (refreshName != null && refreshName.equals("GetOrderFragment") || refreshName != null && refreshName.equals("SupplyGoodsFragment")) {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.quoteDetails), true, R.id.titlebar);
        ((QuoteDetailsContract.Presenter) mPresenter).getOrderDetails(goods_id);
    }

//    /**
//     * 根据当前状态更改订单详情布局
//     * 1.报价中  2.未配送 3.配送中 4.配送完成  5.评价 6.分享
//     *
//     * @param status
//     */
//    @SuppressWarnings("deprecation")
//    public void getOrderStatus(String status, int is_receive) {
//        if (status != null && status.equals("init") && is_receive == 0) {
//            ll_orderNumber.setVisibility(View.GONE);
//            tv_orderStatus.setText(getString(R.string.quote));
//            ll_deliveryInformation.setVisibility(View.GONE);
//            ll_ownerName.setVisibility(View.GONE);
//            ll_contactPhoneNumber.setVisibility(View.GONE);
//            ll_totalFreight.setVisibility(View.GONE);
//            tv_deliveryTime.setText(getString(R.string.systemOffer));
//            tv_deliveryTime1.setTextColor(getResources().getColor(R.color.lonincolors));
//            tv_arrivalTime.setText(getString(R.string.shipperOffer));
//            tv_arrivalTime1.setTextColor(getResources().getColor(R.color.lonincolors));
//            tv_bottombar.setText(R.string.confirmQuotation);
//        } else if (status != null && status.equals("complete") && is_receive == 1) {
//            ll_orderNumber.setVisibility(View.GONE);
//            tv_orderStatus.setText(getString(R.string.theBid));
//            ll_deliveryInformation.setVisibility(View.GONE);
//            ll_ownerName.setVisibility(View.GONE);
//            ll_contactPhoneNumber.setVisibility(View.GONE);
//            ll_totalFreight.setVisibility(View.GONE);
//            tv_deliveryTime.setText(getString(R.string.systemOffer));
//            tv_deliveryTime1.setTextColor(getResources().getColor(R.color.lonincolors));
//            tv_arrivalTime.setText(getString(R.string.shipperOffer));
//            tv_arrivalTime1.setTextColor(getResources().getColor(R.color.lonincolors));
//            tv_driverQuotation.setVisibility(View.GONE);
//            ll_driverQuotation.setVisibility(View.GONE);
//            ll_commonBottombar.setVisibility(View.GONE);
//        } else {
//            ll_orderNumber.setVisibility(View.GONE);
//            tv_orderStatus.setText(getString(R.string.offerFailure));
//            ll_deliveryInformation.setVisibility(View.GONE);
//            ll_ownerName.setVisibility(View.GONE);
//            ll_contactPhoneNumber.setVisibility(View.GONE);
//            ll_totalFreight.setVisibility(View.GONE);
//            tv_deliveryTime.setText(getString(R.string.systemOffer));
//            tv_deliveryTime1.setTextColor(getResources().getColor(R.color.lonincolors));
//            tv_arrivalTime.setText(getString(R.string.shipperOffer));
//            tv_arrivalTime1.setTextColor(getResources().getColor(R.color.lonincolors));
//            tv_driverQuotation.setVisibility(View.GONE);
//            ll_driverQuotation.setVisibility(View.GONE);
//            ll_commonBottombar.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void widgetClick(View v) {
//        super.widgetClick(v);
//        switch (v.getId()) {
//            case R.id.tv_bottombar:
//                int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
//                if (isGoWork == 1) {
//                    ViewInject.toast(getString(R.string.notOrder1));
//                    return;
//                }
//                if (status != null && status.equals("init")) {
//                    sweetAlertDialog.setTitle(getString(R.string.confirmQuotation1));
//                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                            sweetAlertDialog.dismiss();
//                            showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
//                            if (StringUtils.isEmpty(et_driverQuotation.getText().toString().trim())) {
//                                if (StringUtils.isEmpty(tv_arrivalTime1.getText().toString().trim()) || tv_arrivalTime1.getText().toString().trim().equals("0.00元")) {
//                                    ((QuoteDetailsContract.Presenter) mPresenter).postQuoteAdd(goods_id, tv_deliveryTime1.getText().toString().trim().substring(0, tv_deliveryTime1.getText().toString().trim().length() - 1), 1);
//                                    return;
//                                }
//                                ((QuoteDetailsContract.Presenter) mPresenter).postQuoteAdd(goods_id, tv_arrivalTime1.getText().toString().trim().substring(0, tv_arrivalTime1.getText().toString().trim().length() - 1), 1);
//                                return;
//                            }
//                            ((QuoteDetailsContract.Presenter) mPresenter).postQuoteAdd(goods_id, et_driverQuotation.getText().toString().trim(), 0);
//                        }
//                    }).show();
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void getSuccess(String s, int flag) {
//        if (flag == 0) {
//            OrderDetailsBean orderDetailsBean = (OrderDetailsBean) JsonUtil.getInstance().json2Obj(s, OrderDetailsBean.class);
//            OrderDetailsBean.ResultBean result = orderDetailsBean.getResult();
//            //  status = result.getStatus();
//            getOrderStatus(status, is_receive);
//            tv_orderNumber.setText(result.getOrder_code());
//            tv_descriptionGoods.setText(result.getGoods_name());
//            tv_weight.setText(result.getWeight() + "吨");
//            if (result.getIs_receipt() == 1) {
//                tv_receiptGoods.setText(getString(R.string.is));
//            } else {
//                tv_receiptGoods.setText(getString(R.string.no));
//            }
//            if (result.getEffective_time() == 0) {
//                ll_inTransitTime.setVisibility(View.GONE);
//            } else {
//                ll_inTransitTime.setVisibility(View.VISIBLE);
//                int hour = result.getEffective_time() / 60;
//                int minute = result.getEffective_time() % 60;
//                if (hour > 0) {
//                    tv_inTransitTime.setText(hour + getString(R.string.hour) + minute + getString(R.string.minute));
//                } else {
//                    tv_inTransitTime.setText(minute + getString(R.string.minute));
//                }
//            }
//            if (StringUtils.isEmpty(result.getPolicy_code())) {
//                ll_insurancePolicy.setVisibility(View.GONE);
//            } else {
//                ll_insurancePolicy.setVisibility(View.VISIBLE);
//                tv_insurancePolicy.setText(result.getPolicy_code());
//            }
//            tv_origin.setText(result.getOrg_city());
//            tv_destination.setText(result.getDest_city());
//            tv_consigneeName.setText(result.getDest_receive_name());
//            tv_consigneeTelephone.setText(result.getDest_phone());
//            if (StringUtils.isEmpty(result.getDest_address_detail())) {
//                tv_consigneeAddress.setText(result.getDest_city());
//                tv_consigneeAddress1.setText(result.getDest_address_name().substring(result.getDest_city().length()));
//            } else {
//                tv_consigneeAddress.setText(result.getDest_address_name());
//                tv_consigneeAddress1.setText(result.getDest_address_detail());
//            }
//            tv_senderName.setText(result.getOrg_send_name());
//            tv_senderPhone.setText(result.getOrg_phone());
//            if (StringUtils.isEmpty(result.getOrg_address_detail())) {
//                tv_senderAddress.setText(result.getOrg_city());
//                tv_senderAddress1.setText(result.getOrg_address_name().substring(result.getOrg_city().length()));
//            } else {
//                tv_senderAddress.setText(result.getOrg_address_name());
//                tv_senderAddress1.setText(result.getOrg_address_detail());
//            }
//            if (StringUtils.isEmpty(result.getUsecar_time()) || result.getUsecar_time().equals("0")) {
//                tv_transportTime.setText("");
//            } else {
//                tv_transportTime.setText(result.getUsecar_time().substring(0, 16));
//            }
//            if (StringUtils.isEmpty(result.getSend_time()) || result.getSend_time().equals("0")) {
//                tv_deliveryTime1.setText("");
//            } else {
//                tv_deliveryTime1.setText(result.getSend_time().substring(0, 16));
//            }
//            if (StringUtils.isEmpty(result.getArr_time()) || result.getArr_time().equals("0")) {
//                tv_arrivalTime1.setText("");
//            } else {
//                tv_arrivalTime1.setText(result.getArr_time().substring(0, 16));
//            }
//            tv_ownerName.setText(result.getReal_name());
//            tv_contactPhoneNumber.setText(result.getPhone());
//            tv_totalFreight.setText(result.getFinal_price() + "元");
//
//            if (StringUtils.isEmpty(result.getRemark())) {
//                tv_remark.setVisibility(View.GONE);
//            } else {
//                tv_remark.setVisibility(View.VISIBLE);
//                tv_remark.setText(getString(R.string.note1) + result.getRemark());
//            }
//            tv_deliveryTime1.setText(result.getSystem_price() + "元");
//            if (StringUtils.isEmpty(result.getMind_price()) || result.getMind_price().equals("0.00")) {
//                tv_arrivalTime1.setTextColor(getResources().getColor(R.color.textColor));
//            } else {
//                tv_deliveryTime1.setTextColor(getResources().getColor(R.color.textColor));
//            }
//            tv_arrivalTime1.setText(result.getMind_price() + "元");
//
//            if (StringUtils.isEmpty(result.getDr_price()) || result.getDr_price().equals("0.00")) {
//                et_driverQuotation.setText("");
//            } else {
//                goods_id = result.getGoods_id();
//                et_driverQuotation.setText(result.getDr_price());
//            }
//            dismissLoadingDialog();
//        } else if (flag == 1) {
//            //报价成功
//            dismissLoadingDialog();
//            finish();
//        }
//
//    }
//
//    @Override
//    public void error(String msg) {
//        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
//            dismissLoadingDialog();
//            skipActivity(aty, LoginActivity.class);
//            return;
//        }
//        dismissLoadingDialog();
//        ViewInject.toast(msg);
//    }
//
//    @Override
//    public void setPresenter(QuoteDetailsContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//
//    /**
//     * 弹框设置
//     */
//
//    private void initDialog() {
//        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
//        sweetAlertDialog.setCancelable(false);
//        sweetAlertDialog.setCancelText(getString(R.string.cancel))
//                .setConfirmText(getString(R.string.confirm))
//                .showCancelButton(true)
//                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        sweetAlertDialog.dismiss();
//                    }
//                });
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        sweetAlertDialog = null;
//    }
}