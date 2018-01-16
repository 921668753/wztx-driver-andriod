package com.ruitukeji.zwbs.mine.personalcertificate;

import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/14.
 */

public class PersonalCertificatePresenter implements PersonalCertificateContract.Presenter {

    private PersonalCertificateContract.View mView;

    public PersonalCertificatePresenter(PersonalCertificateContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getPersonalCertificate() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getAuthInfo(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.error(msg);
//            }
//        });
    }
}
