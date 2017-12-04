package com.ruitukeji.zwbs.getorder.dialog;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2017/7/12.
 */

public class GetOrderBouncedPresenter implements GetOrderBouncedContract.Presenter {


    private GetOrderBouncedContract.View mView;

    public GetOrderBouncedPresenter(GetOrderBouncedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getQuoteAdd(int goods_id, String dr_price,int is_place_order) {
        mView.showLoadingDialog(MyApplication.getContext().getString(R.string.submissionLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goods_id", goods_id);
        map.put("dr_price", dr_price);
        map.put("is_receive", is_place_order);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.getQuoteAdd(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });


    }
}
