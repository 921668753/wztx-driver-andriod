package com.ruitukeji.zwbs.mission.fragment;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

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


}
