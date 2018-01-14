package com.ruitukeji.zwbs.mine.setting.aboutus;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by ruitu on 2016/9/24.
 */
public class AboutUsPresenter implements AboutUsContract.Presenter {

    private AboutUsContract.View mView;

    public AboutUsPresenter(AboutUsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getArticle(String type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", type);
        RequestClient.getArticle(httpParams, new ResponseListener<String>() {
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
