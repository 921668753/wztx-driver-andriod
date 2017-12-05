package com.ruitukeji.zwbs.getorder.dialog;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 *  可接单类型
 * Created by Administrator on 2017/2/21.
 */

public class AvailableTypeBouncedPresenter implements AvailableTypeBouncedContract.Presenter {


    private AvailableTypeBouncedContract.View mView;

    public AvailableTypeBouncedPresenter(AvailableTypeBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAvailableType() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getConductorModels(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }
}
