package com.ruitukeji.zwbs.supplygoods.dialog;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/21.
 */

public class OriginBouncedPresenter implements OriginBouncedContract.Presenter {

    private OriginBouncedContract.View mView;

    public OriginBouncedPresenter(OriginBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getProvince() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getGoodsList(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getCity(int provinceId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("provinceId", provinceId);
        RequestClient.getGoodsList(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    @Override
    public void getArea(int cityId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("cityId", cityId);
        RequestClient.getGoodsList(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }
}
