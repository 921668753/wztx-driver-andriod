package com.ruitukeji.zwbs.mine.mywallet.withdrawal;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.mine.mywallet.withdrawal.WithdrawalRecordContract;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/13.
 */

public class WithdrawalRecordPresenter implements WithdrawalRecordContract.Presenter {

    private WithdrawalRecordContract.View mView;

    public WithdrawalRecordPresenter(WithdrawalRecordContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getWithdrawalRecord(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        RequestClient.showCashRecord(httpParams, new ResponseListener<String>() {
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
