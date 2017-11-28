package com.ruitukeji.zwbs.entity;

/**
 * Created by Admin on 2017/7/12.
 */

public class RobSingleBean extends BaseResult<RobSingleBean.ResultBean> {

    public class ResultBean {

        private String org_short_name;
        private String dest_short_name;
        private String goods_name;
        private String weight;
        private String system_price;
        private String sp_price;
        private String usecar_time;

        public String getOrg_short_name() {
            return org_short_name;
        }

        public void setOrg_short_name(String org_short_name) {
            this.org_short_name = org_short_name;
        }

        public String getDest_short_name() {
            return dest_short_name;
        }

        public void setDest_short_name(String dest_short_name) {
            this.dest_short_name = dest_short_name;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getSystem_price() {
            return system_price;
        }

        public void setSystem_price(String system_price) {
            this.system_price = system_price;
        }

        public String getSp_price() {
            return sp_price;
        }

        public void setSp_price(String sp_price) {
            this.sp_price = sp_price;
        }

        public String getUsecar_time() {
            return usecar_time;
        }

        public void setUsecar_time(String usecar_time) {
            this.usecar_time = usecar_time;
        }
    }


}
