package com.ruitukeji.zwbs.mission.fragment;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */

public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View mView;

    public TaskPresenter(TaskContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getTask(int page, long time, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 5);
        httpParams.put("type", type);
        httpParams.put("time", String.valueOf(time));
        RequestClient.getTask(httpParams, new ResponseListener<String>() {
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
    public void postArrOrgTime(int id, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        String address_maps = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "currentLocationLatitudeLongitude");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("type", type);
        map.put("address_maps", address_maps);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postarrOrgTime(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 7);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 7);
            }
        });
    }

    @Override
    public void postShipping(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        String org_address_maps = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "currentLocationLatitudeLongitude");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_id", id);
        map.put("org_address_maps", org_address_maps);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postShipping(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 7);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 7);
            }
        });
    }
}
