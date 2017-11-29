package com.ruitukeji.zwbs.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.GetOrderViewAdapter;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.dialog.RobSingleBouncedDialog;
import com.ruitukeji.zwbs.entity.GetOrderBean;
import com.ruitukeji.zwbs.getorder.OrderDetailsActivity;
import com.ruitukeji.zwbs.loginregister.ConductorModelsActivity;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.loginregister.NewUserInformationActivity;
import com.ruitukeji.zwbs.supplygoods.SetTheLineActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.PickerViewUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;


import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 货源
 * Created by Administrator on 2017/2/21.
 */

public class SupplyGoodsFragment extends BaseFragment
     //   implements SupplyGoodsContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener
{

//    @BindView(id = R.id.ll_startingpoint, click = true)
//    private LinearLayout ll_startingpoint;
//
//    @BindView(id = R.id.tv_startingpoint)
//    private TextView tv_startingpoint;
//
//    @BindView(id = R.id.ll_endpoint, click = true)
//    private LinearLayout ll_endpoint;
//
//    @BindView(id = R.id.tv_endpoint)
//    private TextView tv_endpoint;
//
//    @BindView(id = R.id.ll_conductormodels, click = true)
//    private LinearLayout ll_conductormodels;
//
//    @BindView(id = R.id.tv_conductormodels)
//    private TextView tv_conductormodels;
//
//    @BindView(id = R.id.mRefreshLayout)
//    private BGARefreshLayout mRefreshLayout;
//
//    private GetOrderViewAdapter mAdapter;
//
//    @BindView(id = R.id.lv_order)
//    private ListView lv_order;
//
//    private MainActivity aty;
//
//    public PickerViewUtil pickerViewUtil;
//    public PickerViewUtil pickerViewUtil1;
//
//    private String startingpoint = "";
//    private String endpoint = "";
//    private String vehiclelength;
//    private String vehiclemodel;
//
//
//    /**
//     * 错误提示页
//     */
//    @BindView(id = R.id.ll_commonError)
//    private LinearLayout ll_commonError;
//    @BindView(id = R.id.tv_hintText, click = true)
//    private TextView tv_hintText;
//    /**
//     * 当前页码
//     */
//    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
//    /**
//     * 总页码
//     */
//    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;
//    /**
//     * 是否加载更多
//     */
//    private boolean isShowLoadingMore = false;
//
//    private int vehicleModelId = 0;
//    private int vehicleLengthId = 0;
//    private int id = 0;
//    private RobSingleBouncedDialog robSingleBouncedDialog = null;
//    private SweetAlertDialog supplyGoodsSweetAlertDialog = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
//        aty = (MainActivity) getActivity();
//        return View.inflate(aty, R.layout.fragment_supplygoods, null);
        return null;
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        initDialog();
//        mPresenter = new SupplyGoodsPresenter(this);
//        mAdapter = new GetOrderViewAdapter(getActivity());
//        //选项选择器
//        initPickerViewUtil();
//    }
//
//
//    @Override
//    protected void initWidget(View parentView) {
//        super.initWidget(parentView);
//        SimpleDelegate simpleDelegate = new SimpleDelegate() {
//            @Override
//            public void onClickRightCtv() {
//                super.onClickRightCtv();
//                ((SupplyGoodsContract.Presenter) mPresenter).isLogin(3);
//            }
//        };
//        ActivityTitleUtils.initToolbar(parentView, getString(R.string.supplygoods), getString(R.string.settheline), R.id.titlebar, simpleDelegate);
//        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, getActivity(), true);
//        lv_order.setAdapter(mAdapter);
//        lv_order.setOnItemClickListener(this);
//        mAdapter.setOnItemChildClickListener(this);
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((SupplyGoodsContract.Presenter) mPresenter).getSupplyGoods(startingpoint, endpoint, vehicleLengthId, vehicleModelId, mMorePageNumber);
//    }
//
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        id = mAdapter.getItem(i).getId();
//        ((SupplyGoodsContract.Presenter) mPresenter).isLogin(1);
//    }
//
//    @Override
//    public void onItemChildClick(ViewGroup parent, View childView, int position) {
//        GetOrderBean.ResultBean.ListBean listBean = mAdapter.getItem(position);
////        if (childView.getId() == R.id.rl_getorder) {
////            robSingleBouncedDialog = null;
////            robSingleBouncedDialog = new RobSingleBouncedDialog(aty, listBean) {
////                @Override
////                public void confirm(String money) {
////                    this.dismiss();
////                    mRefreshLayout.beginRefreshing();
////                }
////            };
////            ((SupplyGoodsContract.Presenter) mPresenter).isLogin(2);
////        }
//    }
//
//    @Override
//    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
//        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
//        mRefreshLayout.endRefreshing();
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((SupplyGoodsContract.Presenter) mPresenter).getSupplyGoods(startingpoint, endpoint, vehicleLengthId, vehicleModelId, mMorePageNumber);
//    }
//
//    @Override
//    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
//        mRefreshLayout.endLoadingMore();
//        if (!isShowLoadingMore) {
//            return false;
//        }
//        mMorePageNumber++;
//        if (mMorePageNumber > totalPageNumber) {
//            ViewInject.toast(getString(R.string.noMoreData));
//            return false;
//        }
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((SupplyGoodsContract.Presenter) mPresenter).getSupplyGoods(startingpoint, endpoint, vehicleLengthId, vehicleModelId, mMorePageNumber);
//        return true;
//    }
//
//    @Override
//    protected void widgetClick(View v) {
//        super.widgetClick(v);
//        switch (v.getId()) {
//            case R.id.ll_startingpoint:
//                //点击弹出选项选择器
//                pickerViewUtil.onoptionsSelectListener();
//                break;
//            case R.id.ll_endpoint:
//                pickerViewUtil1.onoptionsSelectListener();
//                break;
//            case R.id.ll_conductormodels:
//                Intent intent = new Intent();
//                intent.putExtra("vehicleModelId", vehicleModelId);
//                intent.putExtra("vehicleLengthId", vehicleLengthId);
//                intent.putExtra("isAll", 1);
//                intent.setClass(aty, ConductorModelsActivity.class);
//                startActivityForResult(intent, 3);
//                break;
//            case R.id.tv_hintText:
//                mRefreshLayout.beginRefreshing();
//                break;
//        }
//    }
//
//
//    @Override
//    public void getSuccess(String s, int flag) {
//        if (flag == 0) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods1", false);
//            isShowLoadingMore = true;
//            ll_commonError.setVisibility(View.GONE);
//            mRefreshLayout.setVisibility(View.VISIBLE);
//            GetOrderBean getOrderBean = (GetOrderBean) JsonUtil.getInstance().json2Obj(s, GetOrderBean.class);
//            mMorePageNumber = getOrderBean.getResult().getPage();
//            totalPageNumber = getOrderBean.getResult().getPageTotal();
//            if (getOrderBean.getResult().getList() == null || getOrderBean.getResult().getList().size() == 0) {
//                error(getString(R.string.serverReturnsDataNull));
//                return;
//            }
//            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//                mAdapter.clear();
//                mAdapter.addNewData(getOrderBean.getResult().getList());
//                mRefreshLayout.endRefreshing();
//            } else {
//                mRefreshLayout.endLoadingMore();
//                mAdapter.addMoreData(getOrderBean.getResult().getList());
//            }
//        } else if (flag == 1) {
//            if (supplyGoodsSweetAlertDialog == null) {
//                initDialog();
//            }
//            ((SupplyGoodsContract.Presenter) mPresenter).isCertification(supplyGoodsSweetAlertDialog, 1);
//        } else if (flag == 2) {
//            if (supplyGoodsSweetAlertDialog == null) {
//                initDialog();
//            }
//            ((SupplyGoodsContract.Presenter) mPresenter).isCertification(supplyGoodsSweetAlertDialog, 2);
//        } else if (flag == 3) {
//            if (supplyGoodsSweetAlertDialog == null) {
//                initDialog();
//            }
//            ((SupplyGoodsContract.Presenter) mPresenter).isCertification(supplyGoodsSweetAlertDialog, 0);
//        } else if (flag == 4) {
//            aty.showActivity(aty, SetTheLineActivity.class);
//        } else if (flag == 5) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "SupplyGoodsFragment");
//            Intent intent = new Intent(aty, OrderDetailsActivity.class);
//            intent.putExtra("order_id", id);
//        //    intent.putExtra("designation", "goodDetail");
//            aty.showActivity(aty, intent);
//        } else if (flag == 6) {
//            int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
//            if (isGoWork == 1) {
//                ViewInject.toast(getString(R.string.notOrder));
//                return;
//            }
//            robSingleBouncedDialog.show();
//        }
//        dismissLoadingDialog();
//    }
//
//
//    @Override
//    public void error(String msg, int flag) {
//        if (flag == 0) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGoods1", false);
//            isShowLoadingMore = false;
//            mRefreshLayout.setVisibility(View.GONE);
//            ll_commonError.setVisibility(View.VISIBLE);
//            tv_hintText.setText(msg + getString(R.string.clickRefresh));
//            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//                mRefreshLayout.endRefreshing();
//            } else {
//                mRefreshLayout.endLoadingMore();
//            }
//        } else if (flag == 1 || flag == 2 || flag == 3) {
//            if (msg.equals("" + NumericConstants.TOLINGIN)) {
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "SupplyGoodsFragment");
//                dismissLoadingDialog();
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//            }
//        } else if (flag == 4) {
//            String auth_status = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "auth_status");
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "SupplyGoodsFragment");
//            Intent newUserInformation = new Intent(aty, NewUserInformationActivity.class);
//            newUserInformation.putExtra("auth_status", auth_status);
//            aty.showActivity(aty, newUserInformation);
//        } else if (flag == 5) {
//            ViewInject.toast(getString(R.string.inAuthentication) + "," + getString(R.string.pleaseWait));
//        }
//        dismissLoadingDialog();
//    }
//
//    @Override
//    public void setPresenter(SupplyGoodsContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    @Override
//    public void error(String msg) {
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 3 && resultCode == aty.RESULT_OK) {// 如果等于1
//            vehiclemodel = data.getStringExtra("vehicleModel");
//            vehiclelength = data.getStringExtra("vehicleLength");
//            vehicleModelId = data.getIntExtra("vehicleModelId", 0);
//            vehicleLengthId = data.getIntExtra("vehicleLengthId", 0);
//            if (vehiclemodel.equals(getString(R.string.all)) && vehiclelength.equals(getString(R.string.all))) {
//                tv_conductormodels.setText(getString(R.string.conductorModels1));
//            } else if (vehiclemodel.equals(getString(R.string.all))) {
//                tv_conductormodels.setText(vehiclelength);
//            } else if (vehiclelength.equals(getString(R.string.all))) {
//                tv_conductormodels.setText(vehiclemodel);
//            } else {
//                tv_conductormodels.setText(vehiclelength + "/" + vehiclemodel);
//            }
//            mRefreshLayout.beginRefreshing();
//        }
//
//    }
//
//    @Override
//    public void onChange() {
//        super.onChange();
//        boolean isRefreshGoods1 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshGoods1", false);
//        if (isRefreshGoods1) {
//            mRefreshLayout.beginRefreshing();
//        }
//        //选项选择器
////        if (pickerViewUtil == null && pickerViewUtil1 == null) {
////            initPickerViewUtil();
////        } else if (pickerViewUtil.isShowing()) {
////            pickerViewUtil.onDismiss();
//////            initPickerViewUtil();
////        } else if (pickerViewUtil1.isShowing()) {
////            pickerViewUtil1.onDismiss();
//////            initPickerViewUtil();
////        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        boolean isRefreshGoods = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshGoods", false);
//        if (isRefreshGoods) {
//            mRefreshLayout.beginRefreshing();
//        }
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        robSingleBouncedDialog = null;
//        if (supplyGoodsSweetAlertDialog != null && supplyGoodsSweetAlertDialog.isShowing()) {
//            supplyGoodsSweetAlertDialog.dismiss();
//        }
//        supplyGoodsSweetAlertDialog = null;
//        pickerViewUtil.onDestroy();
//        pickerViewUtil1.onDestroy();
//    }
//
//
//    /**
//     * 弹框设置
//     */
//    private void initDialog() {
//        supplyGoodsSweetAlertDialog = null;
//        supplyGoodsSweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE);
//        supplyGoodsSweetAlertDialog.setCancelable(false);
//        supplyGoodsSweetAlertDialog.setCancelText(getString(R.string.cancel))
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
//    private void initPickerViewUtil() {
//        if (pickerViewUtil == null) {
//            pickerViewUtil = new PickerViewUtil(getActivity(), 1) {
//                @Override
//                public void getAddress(String address) {
//                    startingpoint = address;
//                    if (StringUtils.isEmpty(startingpoint)) {
//                        tv_startingpoint.setText("出发地");
//                        return;
//                    }
//                    tv_startingpoint.setText(address);
//                    mRefreshLayout.beginRefreshing();
//                }
//            };
//        }
//        if (pickerViewUtil1 == null) {
//            pickerViewUtil1 = new PickerViewUtil(getActivity(), 1) {
//                @Override
//                public void getAddress(String address) {
//                    endpoint = address;
//                    if (StringUtils.isEmpty(endpoint)) {
//                        tv_endpoint.setText("目的地");
//                        return;
//                    }
//                    tv_endpoint.setText(address);
//                    mRefreshLayout.beginRefreshing();
//                }
//            };
//        }
//    }

}
