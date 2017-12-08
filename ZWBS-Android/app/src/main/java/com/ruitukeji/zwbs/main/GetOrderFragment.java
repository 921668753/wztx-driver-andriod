package com.ruitukeji.zwbs.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.GetOrderViewAdapter;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.BaseResult;
import com.ruitukeji.zwbs.entity.GetOrderBean;
import com.ruitukeji.zwbs.entity.MessageCenterBean;
import com.ruitukeji.zwbs.entity.WorkingStateBean;
import com.ruitukeji.zwbs.getorder.OrderDetailsActivity;
import com.ruitukeji.zwbs.getorder.dialog.AvailableTypeBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.ConductorBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.GetOrderBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.ModelsBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.SendQuotationBouncedDialog;
import com.ruitukeji.zwbs.getorder.message.MessageCenterActivity;
import com.ruitukeji.zwbs.getorder.selectioncity.SelectionCityActivity;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.loginregister.NewUserInformationActivity;
import com.ruitukeji.zwbs.utils.FileNewUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import java.io.File;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.ruitukeji.zwbs.constant.NumericConstants.STATUS;

/**
 * 接单
 * Created by Administrator on 2017/2/13.
 */

public class GetOrderFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, GetOrderContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    /**
     * 选择城市
     */
    @BindView(id = R.id.ll_city, click = true)
    private LinearLayout ll_city;

    @BindView(id = R.id.tv_city)
    private TextView tv_city;

    /**
     * 车型
     */
    @BindView(id = R.id.ll_models, click = true)
    private LinearLayout ll_models;

    @BindView(id = R.id.tv_models)
    private TextView tv_models;

    @BindView(id = R.id.img_models)
    private ImageView img_models;
    private int modelsId = 0;
    /**
     * 车长
     */
    @BindView(id = R.id.ll_conductor, click = true)
    private LinearLayout ll_conductor;
    @BindView(id = R.id.tv_conductor)
    private TextView tv_conductor;
    @BindView(id = R.id.img_conductor)
    private ImageView img_conductor;

    private int vehicleLengthId = 0;

    /**
     * 可接单类型
     */
    @BindView(id = R.id.ll_availableType, click = true)
    private LinearLayout ll_availableType;
    @BindView(id = R.id.tv_availableType)
    private TextView tv_availableType;
    @BindView(id = R.id.img_availableType)
    private ImageView img_availableType;
    private int availableTypeId = 0;


    private GetOrderContract.Presenter mPresenter;
    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private GetOrderViewAdapter mAdapter;

    @BindView(id = R.id.lv_getorder)
    private ListView lv_getorder;

    /**
     * 消息
     */
    @BindView(id = R.id.rl_message, click = true)
    private RelativeLayout rl_message;
    @BindView(id = R.id.tv_message)
    public TextView tv_message;


    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;


    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;
    private String updateAppUrl = null;
    private SweetAlertDialog sweetAlertDialog = null;

    /**
     * 订单状态
     */
    private int getQuoteOrder = 0;
    private int position = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_getorder, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new GetOrderPresenter(this);
        mAdapter = new GetOrderViewAdapter(getActivity());
        initDialog();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, getActivity(), true);
        lv_getorder.setAdapter(mAdapter);
        lv_getorder.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((GetOrderContract.Presenter) mPresenter).getWorkingState();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
        if (sweetAlertDialog == null) {
            initDialog();
        }
        PreferenceHelper.write(aty, StringConstants.FILENAME, "GetOrderFragmentOnItemChildClick", 0);
        ((GetOrderContract.Presenter) mPresenter).isCertification(sweetAlertDialog, 1);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        String line_id = PreferenceHelper.readString(aty, StringConstants.FILENAME, "line_id", "");
        ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, line_id);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        String line_id = PreferenceHelper.readString(aty, StringConstants.FILENAME, "line_id", "");
        ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, line_id);
        return true;
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position1) {
        position = position1;
        //接单
        if (childView.getId() == R.id.tv_getorder) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "GetOrderFragmentOnItemChildClick", 1);
            if (sweetAlertDialog == null) {
                initDialog();
            }
            ((GetOrderContract.Presenter) mPresenter).isCertification(sweetAlertDialog, 1);
        } else if (childView.getId() == R.id.tv_sendQuotation) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "GetOrderFragmentOnItemChildClick", 2);
            if (sweetAlertDialog == null) {
                initDialog();
            }
            ((GetOrderContract.Presenter) mPresenter).isCertification(sweetAlertDialog, 1);
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        if (flag == 0) {
            if (!(s.equals("true"))) {
                if (sweetAlertDialog == null) {
                    initDialog();
                }
                ((GetOrderContract.Presenter) mPresenter).isCertification(sweetAlertDialog, 0);
                return;
            }
            updateAppUrl = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "updateAppUrl", null);
            if (StringUtils.isEmpty(updateAppUrl)) {
                if (sweetAlertDialog == null) {
                    initDialog();
                }
                ((GetOrderContract.Presenter) mPresenter).isCertification(sweetAlertDialog, 0);
                return;
            }
            sweetAlertDialog.setTitleText(getString(R.string.updateVersion))
                    .setConfirmText(getString(R.string.update))
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            if (sweetAlertDialog == null) {
                                initDialog();
                            }
                            ((GetOrderContract.Presenter) mPresenter).isCertification(sweetAlertDialog, 0);
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            readAndWriteTask(updateAppUrl);
                        }
                    }).show();
        } else if (flag == 1) {
            //创建轨迹返回数据
            dismissLoadingDialog();
        } else if (flag == 2) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods4", false);
            getQuoteOrder = 0;
            GetOrderBean getOrderBean = (GetOrderBean) JsonUtil.getInstance().json2Obj(s, GetOrderBean.class);
            mMorePageNumber = getOrderBean.getResult().getPage();
            totalPageNumber = getOrderBean.getResult().getPageTotal();
            if (getOrderBean.getResult().getList() == null || getOrderBean.getResult().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mAdapter.clear();
                mAdapter.addNewData(getOrderBean.getResult().getList());
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(getOrderBean.getResult().getList());
            }
            dismissLoadingDialog();
        } else if (flag == 3) {
            BaseResult baseResult = (BaseResult) JsonUtil.getInstance().json2Obj(s, BaseResult.class);
            if ((String) baseResult.getResult() == null) {
                dismissLoadingDialog();
                return;
            }
            File path = new File((String) baseResult.getResult());
            FileNewUtil.installApkFile(aty, path.getAbsolutePath());
            dismissLoadingDialog();
            if (sweetAlertDialog == null) {
                initDialog();
            }
            ((GetOrderContract.Presenter) mPresenter).isCertification(sweetAlertDialog, 0);
        } else if (flag == 4) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((GetOrderContract.Presenter) mPresenter).getUnRead();
        } else if (flag == 5) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", false);
            WorkingStateBean workingStateBean = (WorkingStateBean) JsonUtil.getInstance().json2Obj(s, WorkingStateBean.class);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "map_code", workingStateBean.getResult().getMap_code());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isGoWork", workingStateBean.getResult().getOnline());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "auth_status", workingStateBean.getResult().getAuth_status());
            boolean isUpdate = PreferenceHelper.readBoolean(MyApplication.getContext(), StringConstants.FILENAME, "isUpdate", false);
            getSuccess(String.valueOf(isUpdate), 0);
            getQuoteOrder++;
            if (getQuoteOrder == 1) {
                String line_id = PreferenceHelper.readString(aty, StringConstants.FILENAME, "line_id", "");
                ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, line_id);
            }
        } else if (flag == 6) {
            dismissLoadingDialog();
        } else if (flag == 7) {
            //报价
            GetOrderBean.ResultBean.ListBean listBean = mAdapter.getItem(position);
            SendQuotationBouncedDialog sendQuotationBouncedDialog = new SendQuotationBouncedDialog(aty, listBean) {
                @Override
                public void confirm() {
                    this.dismiss();
                    mRefreshLayout.beginRefreshing();
                }
            };
            sendQuotationBouncedDialog.show();
        } else if (flag == 10) {
            // PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "GetOrderFragment");
            Intent intent = new Intent(aty, OrderDetailsActivity.class);
            intent.putExtra("order_id", mAdapter.getItem(position).getId());
            //    intent.putExtra("designation", "getGoodDetail");
            aty.showActivity(aty, intent);
        } else if (flag == 11) {
            //接单
            GetOrderBean.ResultBean.ListBean listBean = mAdapter.getItem(position);
            GetOrderBouncedDialog getOrderBouncedDialog = new GetOrderBouncedDialog(aty, listBean) {
                @Override
                public void confirm() {
                    this.dismiss();
                    mRefreshLayout.beginRefreshing();
                }
            };
            getOrderBouncedDialog.show();
        } else if (flag == 12) {
            //消息
            MessageCenterBean messageCenterBean = (MessageCenterBean) JsonUtil.getInstance().json2Obj(s, MessageCenterBean.class);
            if (messageCenterBean.getResult().getList() == null || messageCenterBean.getResult().getList().size() == 0) {
                // isVisibilityTag = false;
                tv_message.setVisibility(View.GONE);
            }
            if (messageCenterBean.getResult().getList().get(0) == null || messageCenterBean.getResult().getList().get(1) == null) {
                //  isVisibilityTag = false;
                tv_message.setVisibility(View.GONE);
            }
            if (messageCenterBean.getResult().getList().get(0).getUnread() > 0 || messageCenterBean.getResult().getList().get(1).getUnread() > 0) {
                // isVisibilityTag = true;
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    tv_message.setVisibility(View.GONE);
                } else {
                    tv_message.setVisibility(View.VISIBLE);
                }
            } else {
                tv_message.setVisibility(View.GONE);
            }
//            String line_id = PreferenceHelper.readString(aty, StringConstants.FILENAME, "line_id", "");
//            ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, line_id);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0) {
            getQuoteOrder = 0;
            isShowLoadingMore = false;
            mAdapter.clear();
            mAdapter.addNewData(null);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            //  tv_tag.setVisibility(View.VISIBLE);
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods4", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "GetOrderFragment");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isGoWork", 1);
//                tv_goToWork.setTextColor(getResources().getColor(R.color.lonincolors));
//                tv_goToWork.setBackgroundResource(R.drawable.shape_gotowork);
//                tv_afterWork.setTextColor(getResources().getColor(R.color.mainColor));
//                tv_afterWork.setBackgroundResource(R.drawable.shape_afterwork1);
                tv_hintText.setText(getString(R.string.login1));
            } else {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods1", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods4", false);
                tv_hintText.setText(msg + getString(R.string.clickRefresh));
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            dismissLoadingDialog();
        } else if (flag == 2) {
            getQuoteOrder = 0;
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods4", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "GetOrderFragment");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isGoWork", 1);
//                tv_goToWork.setTextColor(getResources().getColor(R.color.lonincolors));
//                tv_goToWork.setBackgroundResource(R.drawable.shape_gotowork);
//                tv_afterWork.setTextColor(getResources().getColor(R.color.mainColor));
//                tv_afterWork.setBackgroundResource(R.drawable.shape_afterwork1);
                tv_hintText.setText(getString(R.string.login1));
            }
            ((GetOrderContract.Presenter) mPresenter).getUnRead();
            //  dismissLoadingDialog();
        } else if (flag == 7 || flag == 8) {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods4", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshGetGoods2", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "GetOrderFragment");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                Intent intent = new Intent(aty, LoginActivity.class);
                // intent.putExtra("name", "GetOrderFragment");
                aty.showActivity(aty, intent);
            }
        } else if (flag == 9) {
            String auth_status = PreferenceHelper.readString(aty, StringConstants.FILENAME, "auth_status");
            PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "GetOrderFragment");
            Intent newUserInformation = new Intent(aty, NewUserInformationActivity.class);
            newUserInformation.putExtra("auth_status", auth_status);
            aty.showActivity(aty, newUserInformation);
        } else if (flag == 12) {
            ((GetOrderContract.Presenter) mPresenter).getUnRead();
        }
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_city:
                Intent selectionIntent = new Intent(aty, SelectionCityActivity.class);
                // intent.putExtra("name", "GetOrderFragment");
                startActivityForResult(selectionIntent, STATUS);
                break;

            case R.id.ll_models:
                ModelsBouncedDialog modelsBouncedDialog = new ModelsBouncedDialog(aty, modelsId) {
                    @Override
                    public void confirm(String typeName, int typeId) {
                        this.dismiss();
                        modelsId = typeId;
                        tv_models.setText(typeName);
                        img_models.setImageResource(R.mipmap.ic_category_gray_down);
                        mRefreshLayout.beginRefreshing();
                    }
                };
                img_models.setImageResource(R.mipmap.icon_category_orange_up1);
                modelsBouncedDialog.show();
                break;
            case R.id.ll_conductor:
                ConductorBouncedDialog conductorBouncedDialog = new ConductorBouncedDialog(aty, vehicleLengthId) {
                    @Override
                    public void confirm(String conductorName, int conductorId) {
                        this.dismiss();
                        vehicleLengthId = conductorId;
                        tv_conductor.setText(conductorName);
                        img_conductor.setImageResource(R.mipmap.ic_category_gray_down);
                        mRefreshLayout.beginRefreshing();
                    }
                };
                img_conductor.setImageResource(R.mipmap.icon_category_orange_up1);
                conductorBouncedDialog.show();
                break;

            case R.id.ll_availableType:
                AvailableTypeBouncedDialog availableTypeBouncedDialog = new AvailableTypeBouncedDialog(aty, availableTypeId) {


                    @Override
                    public void confirm(String availableTypeName, int availableTypeId1) {
                        this.dismiss();
                        availableTypeId = availableTypeId1;
                        tv_availableType.setText(availableTypeName);
                        img_availableType.setImageResource(R.mipmap.ic_category_gray_down);
                        mRefreshLayout.beginRefreshing();
                    }
                };
                img_availableType.setImageResource(R.mipmap.icon_category_orange_up1);
                availableTypeBouncedDialog.show();
                break;

            case R.id.tv_hintText:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "GetOrderFragment");
                    Intent intent = new Intent(aty, LoginActivity.class);
                    // intent.putExtra("name", "GetOrderFragment");
                    aty.showActivity(aty, intent);
                    break;
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.rl_message:
                aty.showActivity(aty, MessageCenterActivity.class);
                tv_message.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public void setPresenter(GetOrderContract.Presenter presenter) {
        mPresenter = presenter;
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
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            ViewInject.toast(getString(R.string.sdPermission));
        }
    }

    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    public void readAndWriteTask(String updateAppUrl) {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(aty, perms)) {
            // Have permission, do the thing!
            ((GetOrderContract.Presenter) mPresenter).downloadApp(updateAppUrl);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.readAndWrite),
                    NumericConstants.READ_AND_WRITE_CODE, perms);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isRefreshGetGoods = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshGetGoods", false);
        if (isRefreshGetGoods) {
            mRefreshLayout.beginRefreshing();
        }
        boolean isRefreshGetGoods1 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshGetGoods1", false);
        if (isRefreshGetGoods1) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((GetOrderContract.Presenter) mPresenter).getWorkingState();
        }
    }

    @Override
    public void onChange() {
        super.onChange();
        boolean isRefreshGetGoods2 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshGetGoods2", false);
        if (isRefreshGetGoods2) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((GetOrderContract.Presenter) mPresenter).getWorkingState();
        }
        boolean isRefreshGetGoods4 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshGetGoods4", false);
        if (isRefreshGetGoods4) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.dismiss();
        }
        sweetAlertDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }


    /**
     * 弹框设置
     */
    private void initDialog() {
        sweetAlertDialog = null;
        sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCancelText(getString(R.string.cancel))
                .setConfirmText(getString(R.string.confirm))
                .showCancelButton(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", selectCity);
            tv_city.setText(selectCity);
            //   showLoadingDialog(aty.getString(R.string.dataLoad));
            //  ((HomePageContract.Presenter) mPresenter).getHomePage(selectCity);
            Log.d("tag888", selectCity);
        }
    }

    /**
     * 显示圆点
     */
    public void showTag() {
        tv_message.setVisibility(View.VISIBLE);
    }

}
