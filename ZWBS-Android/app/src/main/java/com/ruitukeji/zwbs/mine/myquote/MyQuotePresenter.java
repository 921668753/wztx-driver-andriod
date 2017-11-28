package com.ruitukeji.zwbs.mine.myquote;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/13.
 */

public class MyQuotePresenter implements MyQuoteContract.Presenter {

    private MyQuoteContract.View mView;

    public MyQuotePresenter(MyQuoteContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyQuote(int page) {
        mView.showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
//        httpParams.put("is_pay", is_pay);
        RequestClient.getMyQuote(httpParams, new ResponseListener<String>() {
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
