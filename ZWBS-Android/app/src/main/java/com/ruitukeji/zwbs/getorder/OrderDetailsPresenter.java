package com.ruitukeji.zwbs.getorder;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.getorder.OrderDetailsContract;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/16.
 */

public class OrderDetailsPresenter implements OrderDetailsContract.Presenter {

    private OrderDetailsContract.View mView;

    public OrderDetailsPresenter(OrderDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getOrderDetails(int order_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_id", order_id);
        RequestClient.getOrderDetails(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }

    @Override
    public void getGoodDetails(int good_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", good_id);
        RequestClient.getGoodDetails(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }

    /**
     * 提交司机报价
     */
    @Override
    public void postQuoteAdd(int quote_id, String dr_price, int is_place_order) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goods_id", quote_id);
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


    /**
     * 司机进行发货动作
     */
    @Override
    public void postShipping(int order_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        if (order_id <= 0) {
            mView.error(MyApplication.getContext().getString(R.string.locateFailure));
            return;
        }
        map.put("order_id", order_id);
        String currentLocationLatitudeLongitude = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "currentLocationLatitudeLongitude");
        if (StringUtils.isEmpty(currentLocationLatitudeLongitude)) {
            mView.error(MyApplication.getContext().getString(R.string.locateFailure));
            return;
        }
        map.put("org_address_maps", currentLocationLatitudeLongitude);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postShipping(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });

    }

}