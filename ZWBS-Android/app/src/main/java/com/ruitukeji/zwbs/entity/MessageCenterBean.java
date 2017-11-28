package com.ruitukeji.zwbs.entity;

import java.util.List;

/**
 * Created by Admin on 2017/7/28.
 */

public class MessageCenterBean extends BaseResult<MessageCenterBean.ResultBean> {


    public class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * name : 系统消息
             * unread : 0
             * icon_url :
             * push_type : system
             * systemmsg :
             * privatemsg :
             */

            private String name;
            private int unread;
            private String icon_url;
            private String push_type;
            private String systemmsg;
            private String privatemsg;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getUnread() {
                return unread;
            }

            public void setUnread(int unread) {
                this.unread = unread;
            }

            public String getIcon_url() {
                return icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public String getPush_type() {
                return push_type;
            }

            public void setPush_type(String push_type) {
                this.push_type = push_type;
            }

            public String getSystemmsg() {
                return systemmsg;
            }

            public void setSystemmsg(String systemmsg) {
                this.systemmsg = systemmsg;
            }

            public String getPrivatemsg() {
                return privatemsg;
            }

            public void setPrivatemsg(String privatemsg) {
                this.privatemsg = privatemsg;
            }
        }
    }
}
