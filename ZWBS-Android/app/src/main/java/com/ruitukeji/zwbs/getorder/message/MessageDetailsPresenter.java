package com.ruitukeji.zwbs.getorder.message;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/15.
 */

public class MessageDetailsPresenter implements MessageDetailsContract.Presenter {

    private MessageDetailsContract.View mView;

    public MessageDetailsPresenter(MessageDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getMessageDetails(int massageId) {
        mView.showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", massageId);
        RequestClient.getMessageDetails(httpParams, new ResponseListener<String>() {
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
