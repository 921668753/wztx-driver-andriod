package com.ruitukeji.zwbs.mine.helpcenter;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/13.
 */

public class HelpCenterPresenter implements HelpCenterContract.Presenter {

    private HelpCenterContract.View mView;

    public HelpCenterPresenter(HelpCenterContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getHelpCenter(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        RequestClient.getHelpCenter(httpParams, new ResponseListener<String>() {
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