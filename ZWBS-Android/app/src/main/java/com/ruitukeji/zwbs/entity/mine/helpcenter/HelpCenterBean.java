package com.ruitukeji.zwbs.entity.mine.helpcenter;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class HelpCenterBean extends BaseResult<HelpCenterBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":1,"title":"为什么需要实名认证","content":"这是实名认证的内容","create_time":"2018-01-09 16:45:10","update_time":"1970-01-01 08:00:00"},{"id":2,"title":"认证审核通过后还能再次修改吗","content":null,"create_time":"2018-01-09 16:45:10","update_time":"1970-01-01 08:00:00"},{"id":3,"title":"如何申请发票","content":null,"create_time":"2018-01-09 16:45:10","update_time":"1970-01-01 08:00:00"},{"id":4,"title":"认证审核需要多长时间","content":null,"create_time":"2018-01-09 16:45:10","update_time":"1970-01-01 08:00:00"},{"id":5,"title":"有司机接单后如何计算支付订单","content":null,"create_time":"2018-01-09 16:45:10","update_time":"1970-01-01 08:00:00"},{"id":6,"title":"如何发货","content":null,"create_time":"2018-01-09 16:45:10","update_time":"1970-01-01 08:00:00"}]
         * page : 1
         * pageSize : 10
         * dataTotal : 6
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
             * id : 1
             * title : 为什么需要实名认证
             * content : 这是实名认证的内容
             * create_time : 2018-01-09 16:45:10
             * update_time : 1970-01-01 08:00:00
             */

            private int id;
            private String title;
            private String content;
            private String create_time;
            private String update_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
