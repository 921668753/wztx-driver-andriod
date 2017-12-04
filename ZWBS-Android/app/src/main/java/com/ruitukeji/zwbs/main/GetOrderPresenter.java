package com.ruitukeji.zwbs.main;

import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.ProgressListener;
import com.ruitukeji.zwbs.BuildConfig;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.GaoDeCreateBean;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.MathUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Administrator on 2017/2/19.
 */

public class GetOrderPresenter implements GetOrderContract.Presenter {

    private GetOrderContract.View mView;

    public GetOrderPresenter(GetOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getUnRead() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getUnRead(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 12);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 12);
            }
        });
    }

    /**
     * 创建订单轨迹信息
     */
    public void postTrackOrders(String driverId, String location, String address) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //http://lbs.amap.com/yuntu/reference/cloudsearch#t5
        httpParams.putHeaders("Content-Type", "application/x-www-form-urlencoded");
        httpParams.put("tableid", StringConstants.LogisticsPositioningTableid);
        httpParams.put("loctype", 1);
        Map map = new HashMap();
        map.put("_name", driverId);
        map.put("_location", location);
        map.put("_address", address);
//        map.put("createtime", System.currentTimeMillis());
        httpParams.put("data", JsonUtil.getInstance().obj2JsonString(map).toString());
        httpParams.put("key", BuildConfig.GAODE_APPKEY);

        RequestClient.postTrackOrders(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                GaoDeCreateBean gaoDeCreateBean = (GaoDeCreateBean) JsonUtil.getInstance().json2Obj(response, GaoDeCreateBean.class);
                if (gaoDeCreateBean.getStatus() == NumericConstants.STATUS) {
                    mView.getSuccess(response, 1);
                } else {
                    Log.d("nearbySearch", gaoDeCreateBean.getStatus() + "");
                    mView.errorMsg("创建轨迹出现异常,云端返回数据错误",0);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getQuoteOrder(int page, String line_id) {
        //  mView.showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        if (!StringUtils.isEmpty(line_id)) {
            httpParams.put("line_id", line_id);
        }
        RequestClient.getQuoteList(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getWorkingState() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getWorkingState(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 5);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }

    @Override
    public void downloadApp(String updateAppUrl) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download));
        ProgressListener progressListener = new ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                String size = MathUtil.keepZero(((double) transferredBytes) * 100 / totalSize) + "%";
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download) + size);
            }
        };
        RequestClient.downloadApp(updateAppUrl, progressListener, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void isLogin(int flag) {
        RequestClient.isLogin(new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

    @Override
    public void isCertification(SweetAlertDialog sweetAlertDialog, int flag) {
        String auth_status = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "auth_status");
        if (flag == 0) {
            if (auth_status != null && auth_status.equals("refuse") || auth_status != null && auth_status.equals("delete")) {
                sweetAlertDialog.setTitleText(KJActivityStack.create().topActivity().getString(R.string.notPass))
                        .setConfirmText(KJActivityStack.create().topActivity().getString(R.string.confirm))
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                mView.errorMsg("", 9);
                            }
                        }).show();
            }
        } else if (flag == 1) {
            if (auth_status != null && auth_status.equals("init") || auth_status != null && auth_status.equals("refuse") || auth_status != null && auth_status.equals("delete")) {
                sweetAlertDialog.setTitleText(KJActivityStack.create().topActivity().getString(R.string.notPass))
                        .setConfirmText(KJActivityStack.create().topActivity().getString(R.string.confirm))
                        .showCancelButton(true)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                mView.errorMsg("", 9);
                            }
                        })
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        }).show();
                return;
            } else if (auth_status != null && auth_status.equals("check")) {
                ViewInject.toast(KJActivityStack.create().topActivity().getString(R.string.inAuthentication) + "," + KJActivityStack.create().topActivity().getString(R.string.pleaseWait));
                return;
            } else {
                int GetOrderFragmentOnItemChildClick = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "GetOrderFragmentOnItemChildClick", 0);
                if (GetOrderFragmentOnItemChildClick == 0) {
                    mView.getSuccess("", 10);
                } else if (GetOrderFragmentOnItemChildClick == 1) {
                    mView.getSuccess("", 11);
                } else if (GetOrderFragmentOnItemChildClick == 2) {
                    mView.getSuccess("", 7);
                }
            }
        }
    }


}
