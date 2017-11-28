package com.ruitukeji.zwbs.mine.recommendcourteous;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/13.
 */

public class RecommendedRecordPresenter implements RecommendedRecordContract.Presenter {

    private RecommendedRecordContract.View mView;

    public RecommendedRecordPresenter(RecommendedRecordContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getRecommendedRecord(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        RequestClient.showMyRecommList(httpParams, new ResponseListener<String>() {
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