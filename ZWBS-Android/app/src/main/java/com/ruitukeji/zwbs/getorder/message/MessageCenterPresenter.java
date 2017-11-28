package com.ruitukeji.zwbs.getorder.message;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/15.
 */

public class MessageCenterPresenter implements MessageCenterContract.Presenter {


    private MessageCenterContract.View mView;

    public MessageCenterPresenter(MessageCenterContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getUnRead() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getUnRead(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg, 0);
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
                mView.error(msg, flag);
            }
        });
    }
}
