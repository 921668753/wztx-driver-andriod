package com.ruitukeji.zwbs.entity.getorder;

import com.google.gson.annotations.SerializedName;
import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class HomeBean extends BaseResult<HomeBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":4,"position":1,"endtime":"2017-12-29 11:06:14","ad_content":"货物随时随地拉，有货请联系货某"},{"id":6,"position":1,"endtime":"2018-02-28 11:06:04","ad_content":"15秒钟急速响应，随时随地找车配送"},{"id":5,"position":2,"endtime":"2015-05-09 16:00:00","ad_content":"专业拉货，有货联系电话"}]
         * unreadMsg : {"msg":0}
         */

        private UnreadMsgBean unreadMsg;
        private List<ListBean> list;

        public UnreadMsgBean getUnreadMsg() {
            return unreadMsg;
        }

        public void setUnreadMsg(UnreadMsgBean unreadMsg) {
            this.unreadMsg = unreadMsg;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class UnreadMsgBean {
            /**
             * msg : 0
             */

            @SerializedName("msg")
            private int msgX;

            public int getMsgX() {
                return msgX;
            }

            public void setMsgX(int msgX) {
                this.msgX = msgX;
            }
        }

        public class ListBean {
            /**
             * id : 4
             * position : 1
             * endtime : 2017-12-29 11:06:14
             * ad_content : 货物随时随地拉，有货请联系货某
             */

            private int id;
            private int position;
            private String endtime;
            private String ad_content;

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

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getAd_content() {
                return ad_content;
            }

            public void setAd_content(String ad_content) {
                this.ad_content = ad_content;
            }
        }
    }
}
