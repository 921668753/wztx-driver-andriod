package com.ruitukeji.zwbs.mine.otherservices;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/3/14.
 */

public class OtherServicesPresenter implements OtherServicesContract.Presenter {

    private OtherServicesContract.View mView;

    public OtherServicesPresenter(OtherServicesContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getOtherServices() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getBanners(httpParams, new ResponseListener<String>() {
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
