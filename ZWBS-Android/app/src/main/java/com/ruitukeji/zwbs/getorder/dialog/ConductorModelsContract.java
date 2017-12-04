package com.ruitukeji.zwbs.getorder.dialog;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * 车长车型
 * Created by Administrator on 2017/2/21.
 */

public interface ConductorModelsContract {


    interface Presenter extends BasePresenter {
        /**
         * 获取车长车型信息
         */
        void getConductorModels();
    }

    interface View extends BaseView<Presenter> {
        /**
         * http请求正确
         *
         * @param s
         */
        void getSuccess(String s);

        /**
         * http请求错误
         */
        void error(String msg);
    }

}
