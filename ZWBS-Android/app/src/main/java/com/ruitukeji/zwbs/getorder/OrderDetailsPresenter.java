package com.ruitukeji.zwbs.getorder;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/16.
 */

public class OrderDetailsPresenter implements OrderDetailsContract.Presenter {

    private OrderDetailsContract.View mView;

    public OrderDetailsPresenter(OrderDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodDetails(int good_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", good_id);
        RequestClient.getGoodDetails(httpParams, new ResponseListener<String>() {
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
    public void postRefuseOrder(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map map = new HashMap();
        map.put("g_id", id);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postRefuseOrder(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postCancelGoodsComplete(int goods_id, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map map = new HashMap();
        map.put("goods_id", goods_id);
        map.put("type", type);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postConfirmCancelOrder(httpParams, new ResponseListener<String>() {
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

}