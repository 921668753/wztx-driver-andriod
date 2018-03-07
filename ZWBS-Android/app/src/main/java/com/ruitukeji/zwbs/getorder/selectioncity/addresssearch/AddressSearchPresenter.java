package com.ruitukeji.zwbs.getorder.selectioncity.addresssearch;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.entity.getorder.selectioncity.InlandBean.ResultBean;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AddressSearchPresenter implements AddressSearchContract.Presenter {
    private AddressSearchContract.View mView;

    public AddressSearchPresenter(AddressSearchContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAllCity() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAllCity(httpParams, new ResponseListener<String>() {
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

    @Override
    public List<ResultBean> getSearchCity(List<ResultBean> cityList, String name) {
        List<ResultBean> cityList1 = new ArrayList<ResultBean>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < cityList.size(); i++) {
                    String py = cityList.get(i).getName();
                    try {
                        if (py.startsWith(name) || PinyinHelper.getShortPinyin(py).startsWith(name.toLowerCase())) {
                            cityList1.add(cityList.get(i));
                        }
                    } catch (PinyinException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        return cityList1;
    }
}
