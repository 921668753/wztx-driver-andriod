package com.ruitukeji.zwbs.mine.mywallet.incomedetails.incomedetailsfragment;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/15.
 */

public class IncomeDetailsPresenter implements IncomeDetailsContract.Presenter {

    private IncomeDetailsContract.View mView;

    public IncomeDetailsPresenter(IncomeDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getIncomeDetails(int page, int type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", type);
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        RequestClient.getIncomeDetails(httpParams, new ResponseListener<String>() {
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