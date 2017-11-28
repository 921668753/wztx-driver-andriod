package com.ruitukeji.zwbs.order;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/16.
 */

public class EvaluationSharePresenter implements EvaluationShareContract.Presenter {

    private EvaluationShareContract.View mView;

    public EvaluationSharePresenter(EvaluationShareContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getEvaluationShare(int order_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_id", order_id);
        RequestClient.getEvaluationShare(httpParams, new ResponseListener<String>() {
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
