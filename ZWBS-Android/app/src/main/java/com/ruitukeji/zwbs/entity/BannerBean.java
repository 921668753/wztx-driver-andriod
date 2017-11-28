package com.ruitukeji.zwbs.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/7/25.
 */

public class BannerBean extends BaseResult<BannerBean.ResultBean> {


    /**
     * code : 2000
     * result : {"list":[{"position":1,"src":"http://ot090bmn8.bkt.clouddn.com/076e3caed758a1c1/8c91a0e9cae3368f.jpg","url":"http://www.baidu.com"}]}
     */

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
             * position : 1
             * src : http://ot090bmn8.bkt.clouddn.com/076e3caed758a1c1/8c91a0e9cae3368f.jpg
             * url : http://www.baidu.com
             */

            private int position;
            private String src;
            private String url;

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
