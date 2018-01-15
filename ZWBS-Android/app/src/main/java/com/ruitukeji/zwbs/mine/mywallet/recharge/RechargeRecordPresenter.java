package com.ruitukeji.zwbs.mine.mywallet.recharge;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/13.
 */

public class RechargeRecordPresenter implements RechargeRecordContract.Presenter {

    private RechargeRecordContract.View mView;

    public RechargeRecordPresenter(RechargeRecordContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getRechargeRecord(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        RequestClient.getRechargeRecord(httpParams, new ResponseListener<String>() {
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
