package com.ruitukeji.zwbs.supplygoods.dialog;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface OriginBouncedContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取省份信息
         */
        void getProvince();

        /**
         * 获取城市列表信息
         */
        void getCity(int provinceId);

        /**
         * 获取地区列表信息
         */
        void getArea(int cityId);

    }

    interface View extends BaseNewView<Presenter, String> {
//        /**
//         * http请求正确
//         * @param s
//         */
//        void getSuccess(String s, int flag);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg, int flag);
    }


}
