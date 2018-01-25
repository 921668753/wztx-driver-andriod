package com.ruitukeji.zwbs.mission;

import com.lzy.imagepicker.bean.ImageItem;
import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface ExceptionReportingContract {

    interface Presenter extends BasePresenter {

        /**
         * 上报异常信息
         */
        void postAbnormalInsert(int id, ArrayList<ImageItem> imgList, String place, String content, String abnormal_map);

        /**
         * 上传图片
         */
        void postUpLoadImg(String path, int code);
    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
