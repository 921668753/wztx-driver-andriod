package com.ruitukeji.zwbs.mission;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */

public class FillOutExpressPresenter implements FillOutExpressContract.Presenter {

    private FillOutExpressContract.View mView;

    public FillOutExpressPresenter(FillOutExpressContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postFillOutExpress(int g_id, String num, String company) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("g_id", g_id);
        map.put("num", num);
        map.put("company", company);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postfillCourier(httpParams, new ResponseListener<String>() {
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
