package com.ruitukeji.zwbs.mine.mywallet;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/15.
 */

public class MyWalletPresenter implements MyWalletContract.Presenter {


    private MyWalletContract.View mView;

    public MyWalletPresenter(MyWalletContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyWallet() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getMyWallet(httpParams, new ResponseListener<String>() {
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