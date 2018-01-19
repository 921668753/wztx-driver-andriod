package com.ruitukeji.zwbs.entity.getorder.announcement;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Administrator on 2017/12/29.
 */

public class AnnouncementBean extends BaseResult<AnnouncementBean.ResultBean> {


    public class ResultBean {
        /**
         * id : 1
         * position : 1
         * ad_content : 15秒钟急速响应，随时随地找车配送
         * endtime : 2018-02-28 11:06:04
         * title : 这是标题
         */

        private int id;
        private int position;
        private String ad_content;
        private String endtime;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getAd_content() {
            return ad_content;
        }

        public void setAd_content(String ad_content) {
            this.ad_content = ad_content;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
