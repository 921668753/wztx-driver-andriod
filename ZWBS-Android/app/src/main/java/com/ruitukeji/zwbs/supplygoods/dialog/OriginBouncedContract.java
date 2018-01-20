package com.ruitukeji.zwbs.supplygoods.dialog;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface OriginBouncedContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取省份列表信息
         */
        void getAddress(int id, int type);

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
