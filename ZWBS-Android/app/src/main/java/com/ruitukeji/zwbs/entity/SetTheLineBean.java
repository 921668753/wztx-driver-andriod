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
             * drline_id : 1
             * org_city : 广东佛山禅城
             * dest_city : 广西壮族字字去区玉林北流
             */

            private String drline_id;
            private String org_city;
            private String dest_city;

            public String getDrline_id() {
                return drline_id;
            }

            public void setDrline_id(String drline_id) {
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
        }
    }
}
