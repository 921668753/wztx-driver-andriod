package com.ruitukeji.zwbs.mine.helpcenter;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/13.
 */

public class HelpCenterDetailsPresenter implements HelpCenterDetailsContract.Presenter {

    private HelpCenterDetailsContract.View mView;

    public HelpCenterDetailsPresenter(HelpCenterDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getHelpCenterDetails(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.getHelpCenterDetails(httpParams, new ResponseListener<String>() {
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