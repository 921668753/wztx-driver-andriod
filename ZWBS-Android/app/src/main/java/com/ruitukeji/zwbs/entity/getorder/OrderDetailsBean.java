package com.ruitukeji.zwbs.entity.getorder;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Admin on 2017/7/11.
 */

public class OrderDetailsBean extends BaseResult<OrderDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * id : 418
         * org_city : 江苏省苏州市昆山市
         * volume : 10立方
         * kilometres : 22.063千米
         * is_driver_dock : 1
         * order_code : 2018012248574955
         * org_send_name : 刚刚
         * mind_price : 0.00
         * system_price : 150.20
         * dest_city : 江苏省苏州市昆山市
         * weight : 10吨
         * type : often
         * appoint_at : 2018-01-22 09:55:28
         * goods_name : 五金
         * status : quote
         * car_style_length : 4.2米
         * car_style_length_id : 1009
         * car_style_type : 高栏
         * car_style_type_id : 1002
         * effective_time : 0
         * usecar_time :
         */

        private int id;
        private int is_assigned;
        private String org_city;
        private String volume;
        private String kilometres;
        private int is_driver_dock;
        private String order_code;
        private String org_send_name;
        private String mind_price;
        private String system_price;
        private String dest_city;
        private String weight;
        private String type;
        private String appoint_at;
        private String goods_name;
        private String status;
        private String car_style_length;
        private int car_style_length_id;
        private String car_style_type;
        private int car_style_type_id;
        private int effective_time;
        private String usecar_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_assigned() {
            return is_assigned;
        }

        public void setIs_assigned(int is_assigned) {
            this.is_assigned = is_assigned;
        }

        public String getOrg_city() {
            return org_city;
        }

        public void setOrg_city(String org_city) {
            this.org_city = org_city;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getKilometres() {
            return kilometres;
        }

        public void setKilometres(String kilometres) {
            this.kilometres = kilometres;
        }

        public int getIs_driver_dock() {
            return is_driver_dock;
        }

        public void setIs_driver_dock(int is_driver_dock) {
            this.is_driver_dock = is_driver_dock;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getOrg_send_name() {
            return org_send_name;
        }

        public void setOrg_send_name(String org_send_name) {
            this.org_send_name = org_send_name;
        }

        public String getMind_price() {
            return mind_price;
        }

        public void setMind_price(String mind_price) {
            this.mind_price = mind_price;
        }

        public String getSystem_price() {
            return system_price;
        }

        public void setSystem_price(String system_price) {
            this.system_price = system_price;
        }

        public String getDest_city() {
            return dest_city;
        }

        public void setDest_city(String dest_city) {
            this.dest_city = dest_city;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAppoint_at() {
            return appoint_at;
        }

        public void setAppoint_at(String appoint_at) {
            this.appoint_at = appoint_at;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCar_style_length() {
            return car_style_length;
        }

        public void setCar_style_length(String car_style_length) {
            this.car_style_length = car_style_length;
        }

        public int getCar_style_length_id() {
            return car_style_length_id;
        }

        public void setCar_style_length_id(int car_style_length_id) {
            this.car_style_length_id = car_style_length_id;
        }

        public String getCar_style_type() {
            return car_style_type;
        }

        public void setCar_style_type(String car_style_type) {
            this.car_style_type = car_style_type;
        }

        public int getCar_style_type_id() {
            return car_style_type_id;
        }

        public void setCar_style_type_id(int car_style_type_id) {
            this.car_style_type_id = car_style_type_id;
        }

        public int getEffective_time() {
            return effective_time;
        }

        public void setEffective_time(int effective_time) {
            this.effective_time = effective_time;
        }

        public String getUsecar_time() {
            return usecar_time;
        }

        public void setUsecar_time(String usecar_time) {
            this.usecar_time = usecar_time;
        }
    }
}
