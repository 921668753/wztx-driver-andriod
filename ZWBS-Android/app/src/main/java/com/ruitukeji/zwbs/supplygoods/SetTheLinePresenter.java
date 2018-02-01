package com.ruitukeji.zwbs.supplygoods;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21.
 */

public class SetTheLinePresenter implements SetTheLineContract.Presenter {

    private SetTheLineContract.View mView;

    public SetTheLinePresenter(SetTheLineContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getRouteList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getShowLine(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }

    @Override
    public void postDeleteRoute(int routeId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("drline_id", routeId);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postDelLine(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }
}
