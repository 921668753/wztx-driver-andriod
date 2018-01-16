package com.ruitukeji.zwbs.mine.abnormalrecords;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/13.
 */

public class AbnormalSituationPresenter implements AbnormalSituationContract.Presenter {

    private AbnormalSituationContract.View mView;

    public AbnormalSituationPresenter(AbnormalSituationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAbnormalSituation(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.getAbnormalSituation(httpParams, new ResponseListener<String>() {
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