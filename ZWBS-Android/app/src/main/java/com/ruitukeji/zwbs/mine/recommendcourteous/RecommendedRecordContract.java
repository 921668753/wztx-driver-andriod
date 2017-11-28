package com.ruitukeji.zwbs.mine.recommendcourteous;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface RecommendedRecordContract {


    interface Presenter extends BasePresenter {
        /**
         * 获取用户信息
         */
        void getRecommendedRecord(int page);
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
