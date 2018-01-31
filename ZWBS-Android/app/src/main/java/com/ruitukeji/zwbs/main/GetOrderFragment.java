package com.ruitukeji.zwbs.main;

import android.Manifest;
import android.content.DialogInterface;
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
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.BaseResult;
import com.ruitukeji.zwbs.entity.getorder.GetOrderBean;
import com.ruitukeji.zwbs.entity.getorder.HomeBean;
import com.ruitukeji.zwbs.getorder.OrderDetailsActivity;
import com.ruitukeji.zwbs.getorder.announcement.AnnouncementActivity;
import com.ruitukeji.zwbs.getorder.dialog.AvailableTypeBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.ConductorBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.GetOrderBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.ModelsBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.SendQuotationBouncedDialog;
import com.ruitukeji.zwbs.getorder.message.SystemMessageActivity;
import com.ruitukeji.zwbs.getorder.selectioncity.SelectionCityActivity;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.identityauthentication.IdentityAuthenticationActivity;
import com.ruitukeji.zwbs.mine.vehiclecertification.VehicleCertificationActivity;
import com.ruitukeji.zwbs.supplygoods.dialog.AuthenticationBouncedDialog;
import com.ruitukeji.zwbs.utils.FileNewUtil;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.sunfusheng.marqueeview.MarqueeView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_SELECT;
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
     * 通知
     */
    @BindView(id = R.id.ll_ad)
    private LinearLayout ll_ad;
    @BindView(id = R.id.marqueeView)
    private MarqueeView marqueeView;

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

    private String availableTypeName = "all";

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
    private int position = 0;
    private ModelsBouncedDialog modelsBouncedDialog = null;
    private ConductorBouncedDialog conductorBouncedDialog = null;
    private AvailableTypeBouncedDialog availableTypeBouncedDialog = null;
    private List<HomeBean.ResultBean.ListBean> adList = null;

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
        showLoadingDialog(getString(R.string.dataLoad));
        ((GetOrderContract.Presenter) mPresenter).getHome();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, getActivity(), true);
        lv_getorder.setAdapter(mAdapter);
        lv_getorder.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, tv_city.getText().toString(), modelsId, vehicleLengthId, availableTypeName);
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
        ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, tv_city.getText().toString(), modelsId, vehicleLengthId, availableTypeName);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
        ((GetOrderContract.Presenter) mPresenter).isLogin(4);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position1) {
        position = position1;
        //接单
        if (childView.getId() == R.id.tv_getorder) {
            ((GetOrderContract.Presenter) mPresenter).isLogin(5);
        } else if (childView.getId() == R.id.tv_reject) {
            ((GetOrderContract.Presenter) mPresenter).isLogin(6);
        } else if (childView.getId() == R.id.tv_sendQuotation) {
            ((GetOrderContract.Presenter) mPresenter).isLogin(7);
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
                ((GetOrderContract.Presenter) mPresenter).isCertification(0);
                return;
            }
            updateAppUrl = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "updateAppUrl", null);
            if (StringUtils.isEmpty(updateAppUrl)) {
                ((GetOrderContract.Presenter) mPresenter).isCertification(0);
                return;
            }
            sweetAlertDialog.setTitleText(getString(R.string.updateVersion))
                    .setConfirmText(getString(R.string.update))
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            ((GetOrderContract.Presenter) mPresenter).isCertification(0);
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
            //获取首页公告
            HomeBean homeBean = (HomeBean) JsonUtil.getInstance().json2Obj(s, HomeBean.class);
            adList = homeBean.getResult().getList();
            processLogic(homeBean.getResult().getList());
            if (homeBean.getResult().getUnreadMsg() == null || homeBean.getResult().getUnreadMsg().getMsgX() == 0) {
                tv_message.setVisibility(View.GONE);
            } else {
                tv_message.setVisibility(View.VISIBLE);
                String accessToken = PreferenceHelper.readString(aty, StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    tv_message.setVisibility(View.GONE);
                }
                tv_message.setText(String.valueOf(homeBean.getResult().getUnreadMsg().getMsgX()));
            }
            ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, tv_city.getText().toString(), modelsId, vehicleLengthId, availableTypeName);
        } else if (flag == 2) {
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
            ((GetOrderContract.Presenter) mPresenter).isCertification(0);
        } else if (flag == 4) {
            ((GetOrderContract.Presenter) mPresenter).isCertification(0);
        } else if (flag == 5) {
            ((GetOrderContract.Presenter) mPresenter).isCertification(1);
        } else if (flag == 6) {
            ((GetOrderContract.Presenter) mPresenter).isCertification(2);
        } else if (flag == 7) {
            ((GetOrderContract.Presenter) mPresenter).isCertification(3);
        } else if (flag == 8) {
            Intent intent = new Intent(aty, OrderDetailsActivity.class);
            intent.putExtra("order_id", mAdapter.getItem(position).getId());
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else if (flag == 9) {
            int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
            if (isGoWork == 1) {
                ViewInject.toast(getString(R.string.notOrder));
                return;
            }
            GetOrderBean.ResultBean.ListBean listBean = mAdapter.getItem(position);
            String money = "";
            if (listBean.getMind_price().equals("0.00")) {
                money = listBean.getSystem_price();
            } else {
                money = listBean.getMind_price();
            }
            //接单
            GetOrderBouncedDialog getOrderBouncedDialog = new GetOrderBouncedDialog(aty, listBean.getId(), money) {
                @Override
                public void confirm() {
                    this.cancel();
                    mRefreshLayout.beginRefreshing();
                }
            };
            getOrderBouncedDialog.show();
        } else if (flag == 10) {
            int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
            if (isGoWork == 1) {
                ViewInject.toast(getString(R.string.notOrder));
                return;
            }
            //拒绝
            AuthenticationBouncedDialog authenticationBouncedDialog = new AuthenticationBouncedDialog(KJActivityStack.create().topActivity(), getString(R.string.confirmReceiptRejected)) {
                @Override
                public void confirm() {
                    this.cancel();
                    //拒绝
                    GetOrderBean.ResultBean.ListBean listBean = mAdapter.getItem(position);
                    //  showLoadingDialog(getString(R.string.submissionLoad));
                    ((GetOrderContract.Presenter) mPresenter).postRefuseOrder(listBean.getId());
                }
            };
            authenticationBouncedDialog.show();
        } else if (flag == 11) {
            int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
            if (isGoWork == 1) {
                ViewInject.toast(getString(R.string.notOrder));
                return;
            }
            //报价
            GetOrderBean.ResultBean.ListBean listBean = mAdapter.getItem(position);
            SendQuotationBouncedDialog sendQuotationBouncedDialog = new SendQuotationBouncedDialog(aty, listBean.getId(), listBean.getSystem_price()) {
                @Override
                public void confirm() {
                    this.cancel();
                    mRefreshLayout.beginRefreshing();
                }
            };
            sendQuotationBouncedDialog.show();
        } else if (flag == 12) {
            dismissLoadingDialog();
            mRefreshLayout.beginRefreshing();
        } else if (flag == 13) {


        }
    }

    /**
     * 公告
     */
    List<String> tips = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public void processLogic(List<HomeBean.ResultBean.ListBean> list) {
        if (list == null || list.size() == 0) {
            ll_ad.setVisibility(View.GONE);
            return;
        }
        tips.clear();
        for (int i = 0; i < list.size(); i++) {
            tips.add(list.get(i).getAd_content());
        }
        marqueeView.startWithList(tips);
// 在代码里设置自己的动画
        ll_ad.setVisibility(View.VISIBLE);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {

            @Override
            public void onItemClick(int position, TextView textView) {
                Intent intent = new Intent(aty, AnnouncementActivity.class);
                intent.putExtra("id", list.get(position).getId());
                aty.showActivity(aty, intent);
            }
        });
    }


    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0) {
            isShowLoadingMore = false;
            mAdapter.clear();
            mAdapter.addNewData(null);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                tv_hintText.setText(getString(R.string.login1));
            } else {
                tv_hintText.setText(msg + getString(R.string.clickRefresh));
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            dismissLoadingDialog();
        } else if (flag == 1 || flag == 2) {
            isShowLoadingMore = false;
            mAdapter.clear();
            mAdapter.addNewData(null);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                tv_hintText.setText(getString(R.string.login1));
            } else {
                tv_hintText.setText(msg + getString(R.string.clickRefresh));
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            dismissLoadingDialog();
        } else if (flag == 4 || flag == 5 || flag == 6 || flag == 7) {
            dismissLoadingDialog();
            toLigon1(msg);
        } else if (flag == 8) {
            Intent newUserInformation = new Intent(aty, IdentityAuthenticationActivity.class);
            aty.showActivity(aty, newUserInformation);
        } else if (flag == 9) {
            Intent vehicleCertification = new Intent(aty, VehicleCertificationActivity.class);
            aty.showActivity(aty, vehicleCertification);
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
                startActivityForResult(selectionIntent, STATUS);
                break;
            case R.id.ll_models:
                setModels();
                break;
            case R.id.ll_conductor:
                setConductor();
                break;
            case R.id.ll_availableType:
                setAvailableType();
                break;
            case R.id.tv_hintText:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    Intent intent = new Intent(aty, LoginActivity.class);
//                     intent.putExtra("name", "GetOrderFragment");
                    aty.showActivity(aty, intent);
                    break;
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.rl_message:
                aty.showActivity(aty, SystemMessageActivity.class);
                tv_message.setVisibility(View.GONE);
                break;
        }
    }

    public void setModels() {
        img_models.setImageResource(R.mipmap.icon_category_orange_up1);
        if (modelsBouncedDialog != null && !modelsBouncedDialog.isShowing()) {
            modelsBouncedDialog.show();
            return;
        }
        modelsBouncedDialog = new ModelsBouncedDialog(aty, modelsId) {
            @Override
            public void confirm(String typeName, int typeId) {
                this.cancel();
                modelsId = typeId;
                tv_models.setText(typeName);
                mRefreshLayout.beginRefreshing();
            }
        };
        modelsBouncedDialog.show();
        modelsBouncedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                img_models.setImageResource(R.mipmap.ic_category_gray_down);
            }
        });
    }

    public void setConductor() {
        img_conductor.setImageResource(R.mipmap.icon_category_orange_up1);
        if (conductorBouncedDialog != null && !conductorBouncedDialog.isShowing()) {
            conductorBouncedDialog.show();
            return;
        }
        conductorBouncedDialog = new ConductorBouncedDialog(aty, vehicleLengthId) {
            @Override
            public void confirm(String conductorName, int conductorId) {
                this.cancel();
                vehicleLengthId = conductorId;
                tv_conductor.setText(conductorName);
                mRefreshLayout.beginRefreshing();
            }
        };
        conductorBouncedDialog.show();
        conductorBouncedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                img_conductor.setImageResource(R.mipmap.ic_category_gray_down);
            }
        });
    }

    public void setAvailableType() {
        img_availableType.setImageResource(R.mipmap.icon_category_orange_up1);
        if (availableTypeBouncedDialog != null && !availableTypeBouncedDialog.isShowing()) {
            availableTypeBouncedDialog.show();
            return;
        }
        availableTypeBouncedDialog = new AvailableTypeBouncedDialog(aty, availableTypeName) {
            @Override
            public void confirm(String availableTypeName1, String availableTypeValue) {
                this.cancel();
                availableTypeName = availableTypeName1;
                tv_availableType.setText(availableTypeValue);
                mRefreshLayout.beginRefreshing();
            }
        };
        availableTypeBouncedDialog.show();
        availableTypeBouncedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                img_availableType.setImageResource(R.mipmap.ic_category_gray_down);
            }
        });
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
            EasyPermissions.requestPermissions(this, getString(R.string.readAndWrite), NumericConstants.READ_AND_WRITE_CODE, perms);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            marqueeView.stopFlipping();
        } else {
            //可见时执行的操作
            marqueeView.startFlipping();
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        marqueeView.startFlipping();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        marqueeView.stopFlipping();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.cancel();
        }
        if (modelsBouncedDialog != null) {
            modelsBouncedDialog.cancel();
        }
        modelsBouncedDialog = null;
        if (conductorBouncedDialog != null) {
            conductorBouncedDialog.cancel();
        }
        conductorBouncedDialog = null;
        if (availableTypeBouncedDialog != null) {
            availableTypeBouncedDialog.cancel();
        }
        availableTypeBouncedDialog = null;
        sweetAlertDialog = null;
        tips.clear();
        tips = null;
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
        } else if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {// 如果等于1
            mRefreshLayout.beginRefreshing();
        }


    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") || ((String) msgEvent.getData()).equals("RxBusGoWorkEvent")) {
            ((GetOrderContract.Presenter) mPresenter).getQuoteOrder(mMorePageNumber, tv_city.getText().toString(), modelsId, vehicleLengthId, availableTypeName);
        } else if (((String) msgEvent.getData()).equals("RxBusModelsEvent")) {
            setModels();
        } else if (((String) msgEvent.getData()).equals("RxBusConductorEvent")) {
            setConductor();
        } else if (((String) msgEvent.getData()).equals("RxBusAvailableTypeEvent")) {
            setAvailableType();
        } else if (((String) msgEvent.getData()).equals("RxBusCityEvent")) {
            Intent selectionIntent = new Intent(aty, SelectionCityActivity.class);
            startActivityForResult(selectionIntent, STATUS);
        } else if (((String) msgEvent.getData()).equals("RxBusMessageEvent")) {
            aty.showActivity(aty, SystemMessageActivity.class);
            tv_message.setVisibility(View.GONE);
        } else if (((String) msgEvent.getData()).equals("RxBusAdEvent")) {
            if (adList == null || adList.size() == 0) {
                ll_ad.setVisibility(View.GONE);
                return;
            }
            Intent intent = new Intent(aty, AnnouncementActivity.class);
            intent.putExtra("id", adList.get(marqueeView.getPosition()).getId());
            aty.showActivity(aty, intent);
        }
    }

    /**
     * 显示圆点
     */
    public void showTag() {
        tv_message.setVisibility(View.VISIBLE);
    }

}
