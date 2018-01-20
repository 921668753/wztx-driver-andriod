package com.ruitukeji.zwbs.supplygoods.dialog;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/21.
 */

public class OriginBouncedPresenter implements OriginBouncedContract.Presenter {

    private OriginBouncedContract.View mView;

    public OriginBouncedPresenter(OriginBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAddress(int id, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (id != 0) {
            httpParams.put("id", id);
        }
        RequestClient.getAddress(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, type);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, type);
            }
        });
    }

}
