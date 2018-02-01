package com.ruitukeji.zwbs.entity;

import java.util.List;

/**
 * Created by Admin on 2017/7/22.
 */

public class SetTheLineBean extends BaseResult<SetTheLineBean.ResultBean> {


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
             * drline_id : 31
             * org_city : 江西省景德镇市浮梁县
             * dest_city : 辽宁省辽阳市辽阳县
             * origin_province : 360000
             * origin_city : 360200
             * origin_area : 360222
             * destination_province : 210000
             * destination_city : 211000
             * destination_area : 211021
             */

            private int drline_id;
            private String org_city;
            private String dest_city;
            private int origin_province;
            private int origin_city;
            private int origin_area;
            private int destination_province;
            private int destination_city;
            private int destination_area;

            public int getDrline_id() {
                return drline_id;
            }

            public void setDrline_id(int drline_id) {
                this.drline_id = drline_id;
            }

            public String getOrg_city() {
                return org_city;
            }

            public void setOrg_city(String org_city) {
                this.org_city = org_city;
            }

            public String getDest_city() {
                return dest_city;
            }

            public void setDest_city(String dest_city) {
                this.dest_city = dest_city;
            }

            public int getOrigin_province() {
                return origin_province;
            }

            public void setOrigin_province(int origin_province) {
                this.origin_province = origin_province;
            }

            public int getOrigin_city() {
                return origin_city;
            }

            public void setOrigin_city(int origin_city) {
                this.origin_city = origin_city;
            }

            public int getOrigin_area() {
                return origin_area;
            }

            public void setOrigin_area(int origin_area) {
                this.origin_area = origin_area;
            }

            public int getDestination_province() {
                return destination_province;
            }

            public void setDestination_province(int destination_province) {
                this.destination_province = destination_province;
            }

            public int getDestination_city() {
                return destination_city;
            }

            public void setDestination_city(int destination_city) {
                this.destination_city = destination_city;
            }

            public int getDestination_area() {
                return destination_area;
            }

            public void setDestination_area(int destination_area) {
                this.destination_area = destination_area;
            }
        }
    }
}
