package com.ruitukeji.zwbs.entity;


import java.util.List;

/**
 * 消息列表
 * Created by Administrator on 2017/5/10.
 */

public class MessageBean extends BaseResult<MessageBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":1000,"type":"all","title":"系统消息","summary":"统消息 全体会员注意...","isRead":0,"pushTime":""}]
         * page : 1
         * pageSize : 10
         * dataTotal : 1
         * pageTotal : 1
         */

        private int page;
        private int pageSize;
        private int dataTotal;
        private int pageTotal;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 1000
             * type : all
             * title : 系统消息
             * summary : 统消息 全体会员注意...
             * isRead : 0
             * pushTime :
             */

            private int id;
            private String type;
            private String title;
            private String summary;
            private int isRead;
            private String pushTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }

            public String getPushTime() {
                return pushTime;
            }

            public void setPushTime(String pushTime) {
                this.pushTime = pushTime;
            }
        }
    }
}
