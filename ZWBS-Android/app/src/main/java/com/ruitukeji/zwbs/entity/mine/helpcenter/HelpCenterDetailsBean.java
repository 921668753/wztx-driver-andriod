package com.ruitukeji.zwbs.entity.mine.helpcenter;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Administrator on 2018/1/10.
 */

public class HelpCenterDetailsBean extends BaseResult<HelpCenterDetailsBean.ResultBean> {

    public class ResultBean {
        /**
         * id : 1
         * title : 为什么需要实名认证
         * content : 这是实名认证的内容
         * create_time : 2018-01-09 16:45:10
         * update_time : 1970-01-01 08:00:00
         * type : 0
         */

        private int id;
        private String title;
        private String content;
        private String create_time;
        private String update_time;
        private String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
