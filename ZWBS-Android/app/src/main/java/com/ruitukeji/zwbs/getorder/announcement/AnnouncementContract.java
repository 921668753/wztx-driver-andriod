package com.ruitukeji.zwbs.getorder.announcement;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface AnnouncementContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取公告通知详情
         */
        void getAnnouncement(int id);

    }

    interface View extends BaseNewView<Presenter, String> {

    }

}
