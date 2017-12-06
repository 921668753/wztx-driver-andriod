package com.ruitukeji.zwbs.supplygoods;

import com.kymjs.common.StringUtils;
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
 * Created by Administrator on 2017/2/21.
 */

public class AddTheLinePresenter implements AddTheLineContract.Presenter {

    private AddTheLineContract.View mView;

    public AddTheLinePresenter(AddTheLineContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postRoute(String startPoint, String endPoint) {
        if (StringUtils.isEmpty(startPoint)) {
            mView.error(MyApplication.getContext().getString(R.string.selectOrigin));
            return;
        }
        if (StringUtils.isEmpty(endPoint)) {
            mView.error(MyApplication.getContext().getString(R.string.selectDestination));
            return;
        }
//        if (startPoint.equals(endPoint)) {
//            mView.error(MyApplication.getContext().getString(R.string.placeCannotSame));
//            return;
//        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("org_city", startPoint);
        map.put("dest_city", endPoint);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postSetLine(httpParams, new ResponseListener<String>() {
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