package com.ruitukeji.zwbs.mission;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface ExceptionReportingContract {

    interface Presenter extends BasePresenter {

        /**
         * 上报异常信息
         */
        void postAbnormalInsert(int id, String img, String place, String content);

        /**
         * 上传图片
         */
        void postUpLoadImg(String path, int code);
    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
