package com.ruitukeji.zwbs.getorder.announcement;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/15.
 */

public class AnnouncementPresenter implements AnnouncementContract.Presenter {


    private AnnouncementContract.View mView;

    public AnnouncementPresenter(AnnouncementContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAnnouncement(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAnnouncement(httpParams,id, new ResponseListener<String>() {
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
