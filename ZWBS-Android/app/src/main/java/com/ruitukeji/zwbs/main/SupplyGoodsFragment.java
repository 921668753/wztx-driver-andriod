package com.ruitukeji.zwbs.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.supplygoods.SupplyGoodsViewAdapter;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.supplygoods.SupplyGoodsBean;
import com.ruitukeji.zwbs.getorder.OrderDetailsActivity;
import com.ruitukeji.zwbs.getorder.dialog.GetOrderBouncedDialog;
import com.ruitukeji.zwbs.getorder.dialog.SendQuotationBouncedDialog;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.identityauthentication.IdentityAuthenticationActivity;
import com.ruitukeji.zwbs.mine.vehiclecertification.VehicleCertificationActivity;
import com.ruitukeji.zwbs.supplygoods.SetTheLineActivity;
import com.ruitukeji.zwbs.supplygoods.dialog.AvailableTypeBouncedDialog;
import com.ruitukeji.zwbs.supplygoods.dialog.ConductorModelsBouncedDialog;
import com.ruitukeji.zwbs.supplygoods.dialog.OriginBouncedDialog;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PREVIEW;
import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_SELECT;

/**
 * 货源
 * Created by Administrator on 2017/2/21.
 */

public class SupplyGoodsFragment extends BaseFragment implements SupplyGoodsContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {


    private MainActivity aty;

    /**
     * 设定路线
     */
    @BindView(id = R.id.tv_setLine, click = true)
    private TextView tv_setLine;

    /**
     * 出发地
     */
    @BindView(id = R.id.ll_startingPoint, click = true)
    private LinearLayout ll_startingPoint;
    @BindView(id = R.id.tv_startingPoint)
    private TextView tv_startingPoint;
    @BindView(id = R.id.img_startingPoint)
    private ImageView img_startingPoint;
    private int startProvinceId = 0;
    private int startCityId = 0;
    private int startAreaId = 0;
    private String startProvinceName = "";
    private String startCityName = "";
    private String startAreaName = "";
    private String startingpoint = "";
    /**
     * 目的地
     */
    @BindView(id = R.id.ll_endPoint, click = true)
    private LinearLayout ll_endPoint;
    @BindView(id = R.id.tv_endPoint)
    private TextView tv_endPoint;
    @BindView(id = R.id.img_endPoint)
    private ImageView img_endPoint;
    private int endProvinceId = 0;
    private int endCityId = 0;
    private int endAreaId = 0;
    private String endProvinceName = "";
    private String endCityName = "";
    private String endAreaName = "";
    private String endpoint = "";
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
    /**
     * 车长车型
     */
    @BindView(id = R.id.ll_conductorModels, click = true)
    private LinearLayout ll_conductorModels;
    @BindView(id = R.id.tv_conductorModels)
    private TextView tv_conductorModels;
    @BindView(id = R.id.img_conductorModels)
    private ImageView img_conductorModels;
    private int vehicleModelId = 0;
    private int vehicleLengthId = 0;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private SupplyGoodsViewAdapter mAdapter;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;


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

    private int id = 0;
    private GetOrderBouncedDialog getOrderBouncedDialog = null;
    private SendQuotationBouncedDialog sendQuotationBouncedDialog = null;
    private OriginBouncedDialog originBouncedDialog = null;
    private OriginBouncedDialog originBouncedDialog1 = null;
    private AvailableTypeBouncedDialog availableTypeBouncedDialog = null;
    private ConductorModelsBouncedDialog conductorModelsBouncedDialog = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_supplygoods, null);
    }

    @Override
    protected void initData() {
        super.initData();
        startProvinceName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationProvince", "");
        startCityName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationCity", "");
        startAreaName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationArea", "");
        endProvinceName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationProvince", "");
        endCityName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationCity", "");
        endAreaName = PreferenceHelper.readString(aty, StringConstants.FILENAME, "currentLocationArea", "");
        mPresenter = new SupplyGoodsPresenter(this);
        mAdapter = new SupplyGoodsViewAdapter(getActivity());
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, getActivity(), true);
        lv_order.setAdapter(mAdapter);
        lv_order.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        id = mAdapter.getItem(i).getId();
        ((SupplyGoodsContract.Presenter) mPresenter).isLogin(1);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        SupplyGoodsBean.ResultBean.ListBean listBean = mAdapter.getItem(position);
        if (childView.getId() == R.id.tv_getorder) {
            String money = "";
            if (listBean.getMind_price().equals("0.00")) {
                money = listBean.getSystem_price();
            } else {
                money = listBean.getMind_price();
            }
            getOrderBouncedDialog = new GetOrderBouncedDialog(aty, listBean.getId(), money) {
                @Override
                public void confirm() {
                    this.cancel();
                    mRefreshLayout.beginRefreshing();
                }
            };
            ((SupplyGoodsContract.Presenter) mPresenter).isLogin(2);
        } else if (childView.getId() == R.id.tv_sendQuotation) {
            sendQuotationBouncedDialog = new SendQuotationBouncedDialog(aty, listBean.getId(), listBean.getSystem_price()) {
                @Override
                public void confirm() {
                    this.cancel();
                    mRefreshLayout.beginRefreshing();
                }
            };
            ((SupplyGoodsContract.Presenter) mPresenter).isLogin(3);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((SupplyGoodsContract.Presenter) mPresenter).getSupplyGoods(startingpoint, endpoint, vehicleLengthId, vehicleModelId, availableTypeName, mMorePageNumber);
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
        ((SupplyGoodsContract.Presenter) mPresenter).getSupplyGoods(startingpoint, endpoint, vehicleLengthId, vehicleModelId, availableTypeName, mMorePageNumber);
        return true;
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_setLine:
                Intent intent = new Intent(aty, SetTheLineActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            case R.id.ll_startingPoint:
                setStartingPoint();
                break;
            case R.id.ll_endPoint:
                setEndPoint();
                break;
            case R.id.ll_availableType:
                setAvailableType();
                break;
            case R.id.ll_conductorModels:
                setConductorModels();
                break;
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }


    public void setStartingPoint() {
        img_startingPoint.setImageResource(R.mipmap.icon_category_orange_up1);
        if (originBouncedDialog != null && !originBouncedDialog.isShowing()) {
            originBouncedDialog.show();
            return;
        }
        originBouncedDialog = new OriginBouncedDialog(aty, startProvinceName, startProvinceId, startCityName, startCityId, startAreaName, startAreaId, 0) {
            @Override
            public void confirm(String provinceName, int provinceId, int cityId, int areaId) {
                this.cancel();
                startProvinceId = provinceId;
                startCityId = cityId;
                startAreaId = areaId;
                startingpoint = provinceName;
                if (!StringUtils.isEmpty(provinceName)) {
                    tv_startingPoint.setText(provinceName);
                }
                mRefreshLayout.beginRefreshing();
            }
        };
        originBouncedDialog.show();
        originBouncedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                originBouncedDialog = null;
                img_startingPoint.setImageResource(R.mipmap.ic_category_gray_down);
            }
        });
    }

    public void setEndPoint() {
        img_endPoint.setImageResource(R.mipmap.icon_category_orange_up1);
        if (originBouncedDialog1 != null && !originBouncedDialog1.isShowing()) {
            originBouncedDialog1.show();
            return;
        }
        originBouncedDialog1 = new OriginBouncedDialog(aty, endProvinceName, endProvinceId, endCityName, endCityId, endAreaName, endAreaId, 1) {
            @Override
            public void confirm(String provinceName, int provinceId, int cityId, int areaId) {
                this.cancel();
                endProvinceId = provinceId;
                endCityId = cityId;
                endAreaId = areaId;
                endpoint = provinceName;
                if (!StringUtils.isEmpty(provinceName)) {
                    tv_endPoint.setText(provinceName);
                }

                mRefreshLayout.beginRefreshing();
            }
        };
        originBouncedDialog1.show();
        originBouncedDialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                originBouncedDialog1 = null;
                img_endPoint.setImageResource(R.mipmap.ic_category_gray_down);
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

    public void setConductorModels() {
        img_conductorModels.setImageResource(R.mipmap.icon_category_orange_up1);
        if (conductorModelsBouncedDialog != null && !conductorModelsBouncedDialog.isShowing()) {
            conductorModelsBouncedDialog.show();
            return;
        }
        conductorModelsBouncedDialog = new ConductorModelsBouncedDialog(aty, vehicleLengthId, vehicleModelId) {
            @Override
            public void confirm(String conductorName, int conductorId, String modelsName, int modelsId) {
                this.cancel();
                vehicleLengthId = conductorId;
                vehicleModelId = modelsId;
                tv_conductorModels.setText(conductorName + "/" + modelsName);
                mRefreshLayout.beginRefreshing();
            }
        };
        conductorModelsBouncedDialog.show();
        conductorModelsBouncedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                img_conductorModels.setImageResource(R.mipmap.ic_category_gray_down);
            }
        });
    }


    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            SupplyGoodsBean supplyGoodsBean = (SupplyGoodsBean) JsonUtil.getInstance().json2Obj(s, SupplyGoodsBean.class);
            mMorePageNumber = supplyGoodsBean.getResult().getPage();
            totalPageNumber = supplyGoodsBean.getResult().getPageTotal();
            if (supplyGoodsBean.getResult().getList() == null || supplyGoodsBean.getResult().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mAdapter.clear();
                mAdapter.addNewData(supplyGoodsBean.getResult().getList());
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(supplyGoodsBean.getResult().getList());
            }
        } else if (flag == 1) {
            ((SupplyGoodsContract.Presenter) mPresenter).isCertification(1);
        } else if (flag == 2) {
            ((SupplyGoodsContract.Presenter) mPresenter).isCertification(2);
        } else if (flag == 3) {
            ((SupplyGoodsContract.Presenter) mPresenter).isCertification(3);
        } else if (flag == 4) {
            Intent intent = new Intent(aty, OrderDetailsActivity.class);
            intent.putExtra("order_id", id);
            startActivityForResult(intent, REQUEST_CODE_PREVIEW);
        } else if (flag == 5) {
            // aty.showActivity(aty, SetTheLineActivity.class);
            int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
            if (isGoWork == 1) {
                ViewInject.toast(getString(R.string.notOrder));
                return;
            }
            getOrderBouncedDialog.show();
        } else if (flag == 6) {
            int isGoWork = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", 0);
            if (isGoWork == 1) {
                ViewInject.toast(getString(R.string.notOrder));
                return;
            }
            sendQuotationBouncedDialog.show();
        }
        dismissLoadingDialog();
    }


    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0) {
            isShowLoadingMore = false;
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setText(msg + getString(R.string.clickRefresh));
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
        } else if (flag == 1 || flag == 2 || flag == 3) {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                dismissLoadingDialog();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        } else if (flag == 4) {
            Intent newUserInformation = new Intent(aty, IdentityAuthenticationActivity.class);
            aty.showActivity(aty, newUserInformation);
        } else if (flag == 5) {
            Intent vehicleCertification = new Intent(aty, VehicleCertificationActivity.class);
            aty.showActivity(aty, vehicleCertification);
        }
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(SupplyGoodsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {
            startProvinceId = data.getIntExtra("startProvinceName", 0);
            startCityId = data.getIntExtra("startCityName", 0);
            startAreaId = data.getIntExtra("startAreaName", 0);
            endProvinceId = data.getIntExtra("endProvinceName", 0);
            endCityId = data.getIntExtra("endCityName", 0);
            endAreaId = data.getIntExtra("endAreaName", 0);
            startingpoint = data.getStringExtra("org_address");
            endpoint = data.getStringExtra("dest_address");
            if (!StringUtils.isEmpty(startingpoint)) {
                tv_startingPoint.setText(startingpoint);
            }
            if (!StringUtils.isEmpty(endpoint)) {
                tv_endPoint.setText(endpoint);
            }
            mRefreshLayout.beginRefreshing();
        } else if (requestCode == REQUEST_CODE_PREVIEW && resultCode == RESULT_OK) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getOrderBouncedDialog != null) {
            getOrderBouncedDialog.cancel();
        }
        if (sendQuotationBouncedDialog != null) {
            sendQuotationBouncedDialog.cancel();
        }
        if (originBouncedDialog != null) {
            originBouncedDialog.cancel();
        }
        if (originBouncedDialog1 != null) {
            originBouncedDialog1.cancel();
        }
        if (availableTypeBouncedDialog != null) {
            availableTypeBouncedDialog.cancel();
        }
        originBouncedDialog = null;
        originBouncedDialog1 = null;
        getOrderBouncedDialog = null;
        sendQuotationBouncedDialog = null;
        availableTypeBouncedDialog = null;
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusStartingPointEvent")) {
            setStartingPoint();
        } else if (((String) msgEvent.getData()).equals("RxBusEndPointEvent")) {
            setEndPoint();
        } else if (((String) msgEvent.getData()).equals("RxBusAvailableTypeBouncedEvent")) {
            setAvailableType();
        } else if (((String) msgEvent.getData()).equals("RxBusConductorModelsEvent")) {
            setConductorModels();
        }
    }

}
