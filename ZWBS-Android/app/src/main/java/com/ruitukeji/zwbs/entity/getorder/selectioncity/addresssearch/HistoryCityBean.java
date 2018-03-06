package com.ruitukeji.zwbs.entity.getorder.selectioncity.addresssearch;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class HistoryCityBean extends BaseResult<List<HistoryCityBean.ResultBean>> {


    /**
     * status : 1
     * msg : 成功
     * result : [{"id":1,"parent_id":0,"name":"中国","level":null,"area_code":null},{"id":3414,"parent_id":0,"name":"美洲","level":1,"area_code":"/public/upload/ad/2017/09-11/48df0b9e039749c552a07967c78eed37.jpg"}]
     */


    public static class ResultBean {
        /**
         * id : 1
         * parent_id : 0
         * name : 中国
         * level : null
         * area_code : null
         */

        private int id;
        private String name;
        private int country_id;
        private int status;
        private String country;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

}
