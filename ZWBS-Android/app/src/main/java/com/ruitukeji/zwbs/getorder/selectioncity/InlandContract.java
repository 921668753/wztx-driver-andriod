package com.ruitukeji.zwbs.getorder.selectioncity;

import android.content.Context;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.entity.getorder.selectioncity.InlandBean;
import com.ruitukeji.zwbs.entity.getorder.selectioncity.InlandHotCityBean.ResultBean;

import java.util.ArrayList;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface InlandContract {
    interface Presenter extends BasePresenter {

        /**
         * 得到国内全部城市
         */
        //  void getAllCityIn();

        /**
         * 得到国内全部城市（一级全部返回）
         */
        void getAllCity(Context context);

        /**
         * 得到国内热门城市（一级全部返回）
         */
        void getChildHotCity(Context context);


        /**
         * 得到国内全部城市（一级全部返回）
         */
        void getAllCity();

        /**
         * 得到国内热门城市（一级全部返回）
         */
        void getChildHotCity();

        ArrayList<InlandBean> parseData(String result);

        ArrayList<ResultBean> parseDataHot(String result);
    }

    interface View extends BaseNewView<Presenter, String> {
    }

}


