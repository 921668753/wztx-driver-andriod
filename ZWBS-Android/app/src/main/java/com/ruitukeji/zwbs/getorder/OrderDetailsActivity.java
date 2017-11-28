package com.ruitukeji.zwbs.getorder;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.OrderDetailsBean;
import com.ruitukeji.zwbs.order.EvaluationShareActivity;
import com.ruitukeji.zwbs.order.OrderVoucherActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 订单详情
 * Created by Administrator on 2017/2/16.
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsContract.View, EasyPermissions.PermissionCallbacks {

    private final int REQUEST_CODE_PERMISSION_CALL = 1;
    /**
     * 订单状态
     */
    @BindView(id = R.id.tv_orderStatus)
    private TextView tv_orderStatus;
    /**
     * 订单号
     */

    @BindView(id = R.id.ll_orderNumber)
    private LinearLayout ll_orderNumber;
    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;
    /**
     * 货物名称
     */
    @BindView(id = R.id.tv_descriptionGoods)
    private TextView tv_descriptionGoods;

    /**
     * 保险单号
     */
    @BindView(id = R.id.ll_insurancePolicy)
    private LinearLayout ll_insurancePolicy;

    @BindView(id = R.id.tv_insurancePolicy)
    private TextView tv_insurancePolicy;

    @BindView(id = R.id.tv_checkDetails)
    private TextView tv_checkDetails;

    /**
     * 重量
     */
    @BindView(id = R.id.tv_weight)
    private TextView tv_weight;

    /**
     * 货物回单
     */
    @BindView(id = R.id.tv_receiptGoods)
    private TextView tv_receiptGoods;

    /**
     * 货物在途时效
     */
    @BindView(id = R.id.ll_inTransitTime)
    private LinearLayout ll_inTransitTime;
    @BindView(id = R.id.tv_inTransitTime)
    private TextView tv_inTransitTime;

    /**
     * 起始地
     */
    @BindView(id = R.id.tv_origin)
    private TextView tv_origin;
    /**
     * 目的地
     */
    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    @BindView(id = R.id.ll_deliveryInformation)
    private LinearLayout ll_deliveryInformation;

    /**
     * 收货人姓名
     */
    @BindView(id = R.id.tv_consigneeName)
    private TextView tv_consigneeName;
    /**
     * 收货人电话
     */
    @BindView(id = R.id.tv_consigneeTelephone, click = true)
    private TextView tv_consigneeTelephone;
    /**
     * 收货人地址
     */
    @BindView(id = R.id.tv_consigneeAddress)
    private TextView tv_consigneeAddress;
    @BindView(id = R.id.tv_consigneeAddress1)
    private TextView tv_consigneeAddress1;
    /**
     * 寄件人姓名
     */
    @BindView(id = R.id.tv_senderName)
    private TextView tv_senderName;
    /**
     * 寄件人电话
     */
    @BindView(id = R.id.tv_senderPhone, click = true)
    private TextView tv_senderPhone;
    /**
     * 寄件人地址
     */
    @BindView(id = R.id.tv_senderAddress)
    private TextView tv_senderAddress;
    @BindView(id = R.id.tv_senderAddress1)
    private TextView tv_senderAddress1;
    /**
     * 用车时间
     */
    @BindView(id = R.id.tv_transportTime)
    private TextView tv_transportTime;


    @BindView(id = R.id.ll_deliveryTime1)
    private LinearLayout ll_deliveryTime1;

    @BindView(id = R.id.tv_deliveryTime)
    private TextView tv_deliveryTime;

    @BindView(id = R.id.tv_deliveryTime1)
    private TextView tv_deliveryTime1;

    @BindView(id = R.id.ll_arrivalTime)
    private LinearLayout ll_arrivalTime;

    @BindView(id = R.id.tv_arrivalTime)
    private TextView tv_arrivalTime;

    @BindView(id = R.id.tv_arrivalTime1)
    private TextView tv_arrivalTime1;

    /**
     * 车主姓名
     */
    @BindView(id = R.id.ll_ownerName)
    private LinearLayout ll_ownerName;
    @BindView(id = R.id.tv_ownerName)
    private TextView tv_ownerName;
    /**
     * 联系电话
     */
    @BindView(id = R.id.ll_contactPhoneNumber)
    private LinearLayout ll_contactPhoneNumber;
    @BindView(id = R.id.tv_contactPhoneNumber)
    private TextView tv_contactPhoneNumber;
    /**
     * 总运费
     */
    @BindView(id = R.id.ll_totalFreight)
    private LinearLayout ll_totalFreight;
    @BindView(id = R.id.tv_totalFreight)
    private TextView tv_totalFreight;

    /**
     * 备注
     */
    @BindView(id = R.id.tv_remark)
    private TextView tv_remark;

    @BindView(id = R.id.tv_driverQuotation)
    private TextView tv_driverQuotation;


    @BindView(id = R.id.ll_driverQuotation)
    private LinearLayout ll_driverQuotation;

    @BindView(id = R.id.et_driverQuotation)
    private EditText et_driverQuotation;


    /**
     * 查看凭证
     */
    @BindView(id = R.id.ll_commonBottombar)
    private LinearLayout ll_commonBottombar;

    /**
     * 确认报价
     */
    @BindView(id = R.id.tv_bottombar, click = true)
    private TextView tv_bottombar;
    /**
     * 订单状态
     */
    private String status = "";

    private OrderDetailsContract.Presenter mPresenter;
    private int order_id;
    private String designation;

    private SweetAlertDialog sweetAlertDialog = null;
    private String refreshName;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        initDialog();
        mPresenter = new OrderDetailsPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        order_id = getIntent().getIntExtra("order_id", 0);
        if (order_id <= 0) {
            finish();
            return;
        }
        refreshName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "refreshName");
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        if (refreshName != null && refreshName.equals("GetOrderFragment") || refreshName != null && refreshName.equals("SupplyGoodsFragment")) {
            ActivityTitleUtils.initToolbar(aty, getString(R.string.goodsDetails), true, R.id.titlebar);
            ((OrderDetailsContract.Presenter) mPresenter).getGoodDetails(order_id);
        } else {
            ActivityTitleUtils.initToolbar(aty, getString(R.string.orderDetails), true, R.id.titlebar);
            ((OrderDetailsContract.Presenter) mPresenter).getOrderDetails(order_id);
        }
    }

    /**
     * 根据当前状态更改订单详情布局
     * 1.报价中  2.未配送 3.配送中 4.配送完成  5.评价 6.分享
     *
     * @param status
     */
    @SuppressWarnings("deprecation")
    public void getOrderStatus(String status) {
        if (status.equals("quote")) {
            ll_orderNumber.setVisibility(View.GONE);
            tv_orderStatus.setText(getString(R.string.quote));
            ll_deliveryInformation.setVisibility(View.GONE);
            ll_ownerName.setVisibility(View.GONE);
            ll_contactPhoneNumber.setVisibility(View.GONE);
            ll_totalFreight.setVisibility(View.GONE);
            tv_deliveryTime.setText(getString(R.string.systemOffer));
            tv_deliveryTime1.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_arrivalTime.setText(getString(R.string.shipperOffer));
            tv_arrivalTime1.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_bottombar.setText(R.string.confirmQuotation);
        } else if (status.equals("quoted")) {
            ll_orderNumber.setVisibility(View.VISIBLE);
            tv_orderStatus.setText(getString(R.string.noDistribution));
            ll_deliveryInformation.setVisibility(View.VISIBLE);
            ll_ownerName.setVisibility(View.VISIBLE);
            ll_contactPhoneNumber.setVisibility(View.VISIBLE);
            ll_totalFreight.setVisibility(View.VISIBLE);
            ll_deliveryTime1.setVisibility(View.GONE);
            ll_arrivalTime.setVisibility(View.GONE);
            ll_driverQuotation.setVisibility(View.GONE);
            tv_bottombar.setText(R.string.completionLoading);
        } else if (status.equals("distribute")) {
            tv_orderStatus.setText(getString(R.string.inDistribution));
            ll_arrivalTime.setVisibility(View.GONE);
            ll_driverQuotation.setVisibility(View.GONE);
            tv_bottombar.setText(R.string.photoCompleted);
        } else if (status.equals("photo")) {
            tv_orderStatus.setText(getString(R.string.distributionComplete));
            ll_driverQuotation.setVisibility(View.GONE);
            ll_commonBottombar.setVisibility(View.GONE);
        } else if (status.equals("pay_success")) {
            tv_orderStatus.setText(getString(R.string.noEvaluation));
            ll_driverQuotation.setVisibility(View.GONE);
            ll_commonBottombar.setVisibility(View.GONE);
        } else if (status.equals("comment")) {
            tv_orderStatus.setText(getString(R.string.haveEvaluation));
            ll_driverQuotation.setVisibility(View.GONE);
            tv_bottombar.setText(R.string.checkeValuation);
        }else if (status.equals("hang")) {
            tv_orderStatus.setText(getString(R.string.hang));
            ll_deliveryTime1.setVisibility(View.GONE);
            ll_arrivalTime.setVisibility(View.GONE);
            ll_driverQuotation.setVisibility(View.GONE);
            ll_commonBottombar.setVisibility(View.GONE);
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_bottombar:
                int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
                if (isGoWork == 1) {
                    ViewInject.toast(getString(R.string.notOrder1));
                    return;
                }
                if (status.equals("quote")) {
                    sweetAlertDialog.setTitle(getString(R.string.confirmQuotation1));
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                            if (StringUtils.isEmpty(et_driverQuotation.getText().toString().trim())) {
                                if (StringUtils.isEmpty(tv_arrivalTime1.getText().toString().trim()) || tv_arrivalTime1.getText().toString().trim().equals("0.00元")) {
                                    ((OrderDetailsContract.Presenter) mPresenter).postQuoteAdd(order_id, tv_deliveryTime1.getText().toString().trim().substring(0, tv_deliveryTime1.getText().toString().trim().length() - 1), 1);
                                    return;
                                }
                                ((OrderDetailsContract.Presenter) mPresenter).postQuoteAdd(order_id, tv_arrivalTime1.getText().toString().trim().substring(0, tv_arrivalTime1.getText().toString().trim().length() - 1), 1);
                                return;
                            }
                            ((OrderDetailsContract.Presenter) mPresenter).postQuoteAdd(order_id, et_driverQuotation.getText().toString().trim(), 0);
                        }
                    }).show();
                } else if (status.equals("quoted")) {
                    sweetAlertDialog.setTitle(getString(R.string.completionLoading1));
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
                            ((OrderDetailsContract.Presenter) mPresenter).postShipping(order_id);
                        }
                    }).show();
                } else if (status.equals("distribute")) {
                    Intent intent = new Intent(aty, OrderVoucherActivity.class);
                    intent.putExtra("order_id", order_id);
                    showActivity(aty, intent);
                } else if (status.equals("photo")) {
                    //  showActivity(aty, PaymentActivity.class);
                } else if (status.equals("pay_success")) {
                    // showActivity(aty, EvaluationShareActivity.class);
                } else if (status.equals("comment")) {
                    Intent intent = new Intent(aty, EvaluationShareActivity.class);
                    intent.putExtra("order_id", order_id);
                    showActivity(aty, intent);
                }
                break;

            case R.id.tv_senderPhone:
                choiceCallWrapper(getString(R.string.contactPhoneNumber), tv_senderPhone.getText().toString());
                break;

            case R.id.tv_consigneeTelephone:
                choiceCallWrapper(getString(R.string.contactPhoneNumber), tv_consigneeTelephone.getText().toString());
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshOrderDetailsActivity", false);
            OrderDetailsBean orderDetailsBean = (OrderDetailsBean) JsonUtil.getInstance().json2Obj(s, OrderDetailsBean.class);
            OrderDetailsBean.ResultBean result = orderDetailsBean.getResult();
            status = result.getStatus();
            getOrderStatus(status);
            tv_orderNumber.setText(result.getOrder_code());
            tv_descriptionGoods.setText(result.getGoods_name());
            tv_weight.setText(result.getWeight() + "吨");
            if (result.getIs_receipt() == 1) {
                tv_receiptGoods.setText(getString(R.string.is));
            } else {
                tv_receiptGoods.setText(getString(R.string.no));
            }
            if (result.getEffective_time() == 0) {
                ll_inTransitTime.setVisibility(View.GONE);
            } else {
                ll_inTransitTime.setVisibility(View.VISIBLE);
                int hour = result.getEffective_time() / 60;
                int minute = result.getEffective_time() % 60;
                if (hour > 0) {
                    tv_inTransitTime.setText(hour + getString(R.string.hour) + minute + getString(R.string.minute));
                } else {
                    tv_inTransitTime.setText(minute + getString(R.string.minute));
                }
            }
            if (StringUtils.isEmpty(result.getPolicy_code())) {
                ll_insurancePolicy.setVisibility(View.GONE);
            } else {
                ll_insurancePolicy.setVisibility(View.VISIBLE);
                tv_insurancePolicy.setText(result.getPolicy_code());
            }
            tv_origin.setText(result.getOrg_city());
            tv_destination.setText(result.getDest_city());
            tv_consigneeName.setText(result.getDest_receive_name());
            if (StringUtils.isEmpty(result.getDest_telphone())) {
                tv_consigneeTelephone.setText(result.getDest_phone());
            } else {
                tv_consigneeTelephone.setText(result.getDest_phone() + "(" + result.getDest_telphone() + ")");
            }
            if (StringUtils.isEmpty(result.getDest_address_detail())) {
                tv_consigneeAddress.setText(result.getDest_city());
                tv_consigneeAddress1.setText(result.getDest_address_name().substring(result.getDest_city().length()));
            } else {
                tv_consigneeAddress.setText(result.getDest_address_name());
                tv_consigneeAddress1.setText(result.getDest_address_detail());
            }
            tv_senderName.setText(result.getOrg_send_name());

            if (StringUtils.isEmpty(result.getOrg_telphone())) {
                tv_senderPhone.setText(result.getOrg_phone());
            } else {
                tv_senderPhone.setText(result.getOrg_phone() + "(" + result.getDest_telphone() + ")");
            }
            if (StringUtils.isEmpty(result.getOrg_address_detail())) {
                tv_senderAddress.setText(result.getOrg_city());
                tv_senderAddress1.setText(result.getOrg_address_name().substring(result.getOrg_city().length()));
            } else {
                tv_senderAddress.setText(result.getOrg_address_name());
                tv_senderAddress1.setText(result.getOrg_address_detail());
            }
            if (StringUtils.isEmpty(result.getUsecar_time()) || result.getUsecar_time().equals("0")) {
                tv_transportTime.setText("");
            } else {
                tv_transportTime.setText(result.getUsecar_time().substring(0, 16));
            }
            if (StringUtils.isEmpty(result.getSend_time()) || result.getSend_time().equals("0")) {
                tv_deliveryTime1.setText("");
            } else {
                tv_deliveryTime1.setText(result.getSend_time().substring(0, 16));
            }
            if (StringUtils.isEmpty(result.getArr_time()) || result.getArr_time().equals("0")) {
                tv_arrivalTime1.setText("");
            } else {
                tv_arrivalTime1.setText(result.getArr_time().substring(0, 16));
            }
            tv_ownerName.setText(result.getReal_name());
            tv_contactPhoneNumber.setText(result.getPhone());
            tv_totalFreight.setText(result.getFinal_price() + "元");

            if (StringUtils.isEmpty(result.getRemark())) {
                tv_remark.setVisibility(View.GONE);
            } else {
                tv_remark.setVisibility(View.VISIBLE);
                tv_remark.setText(getString(R.string.note1) + result.getRemark());
            }
            if (status.equals("quote")) {
                tv_deliveryTime1.setText(result.getSystem_price() + "元");
                if (StringUtils.isEmpty(result.getMind_price()) || result.getMind_price().equals("0.00")) {
                    tv_arrivalTime1.setTextColor(getResources().getColor(R.color.textColor));
                } else {
                    tv_deliveryTime1.setTextColor(getResources().getColor(R.color.textColor));
                }
                tv_arrivalTime1.setText(result.getMind_price() + "元");

                if (StringUtils.isEmpty(result.getQuote_price()) || result.getQuote_price().equals("0.00")) {
                    et_driverQuotation.setText("");
                } else {
                    order_id = result.getGoods_id();
                    et_driverQuotation.setText(result.getQuote_price());
                }
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            //报价成功

            if (refreshName != null && refreshName.equals("GetOrderFragment")) {
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshGetGoods", true);
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshGoods1", true);
            } else if (refreshName != null && refreshName.equals("SupplyGoodsFragment")) {
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshGoods", true);
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshGetGoods4", true);
            }
            dismissLoadingDialog();
            finish();
        } else if (flag == 2) {
            /**
             * 司机进行发货动作
             */
            String refreshName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "refreshName", "GetOrderFragment");
            if (refreshName != null && refreshName.equals("AllOrderFragment")) {
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshAllOrderFragment", true);
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshNoDistributionOrderFragment1", true);
            } else if (refreshName != null && refreshName.equals("NoDistributionOrderFragment")) {
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshAllOrderFragment1", true);
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshNoDistributionOrderFragment", true);
            }
            dismissLoadingDialog();
            finish();
//            showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
//            ((OrderDetailsContract.Presenter) mPresenter).getOrderDetails(order_id);
        }

    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void setPresenter(OrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    protected void onResume() {
        super.onResume();
        boolean isRefreshOrderDetailsActivity = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshOrderDetailsActivity", false);
        if (isRefreshOrderDetailsActivity) {
            showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
            ((OrderDetailsContract.Presenter) mPresenter).getOrderDetails(order_id);
        }
    }


    /**
     * 弹框设置
     */

    private void initDialog() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCancelText(getString(R.string.cancel))
                .setConfirmText(getString(R.string.confirm))
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
    }


    @AfterPermissionGranted(REQUEST_CODE_PERMISSION_CALL)
    private void choiceCallWrapper(String title, String phone) {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            showDialog(title, phone);
        } else {
            EasyPermissions.requestPermissions(this, "拨打电话选择需要以下权限:\n\n访问设备上的电话拨打权限", REQUEST_CODE_PERMISSION_CALL, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == REQUEST_CODE_PERMISSION_CALL) {
            ViewInject.toast("您拒绝了「拨打电话」所需要的相关权限!");
        }
    }


    public void showDialog(String title, String phone) {
        if (sweetAlertDialog == null) {
            initDialog();
        }
        sweetAlertDialog.setTitleText(title)
                .setContentText(phone)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        sDialog = null;
                        Uri uri = Uri.parse("tel:" + phone);
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        //     系统打电话界面：
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sweetAlertDialog = null;
    }
}