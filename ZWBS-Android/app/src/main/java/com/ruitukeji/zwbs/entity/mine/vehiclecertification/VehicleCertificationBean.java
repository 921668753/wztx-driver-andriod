package com.ruitukeji.zwbs.entity.mine.vehiclecertification;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Administrator on 2018/1/16.
 */

public class VehicleCertificationBean extends BaseResult<VehicleCertificationBean.ResultBean> {

    public class ResultBean {
        /**
         * car_type : 面包车
         * car_length : 4.2米
         * car_registered_time : null
         * card_number : 苏11111
         * car_front_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * car_bank_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * car_tail_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * license_time : 2018-02-01 13:01:34
         * license_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * transport_pic_time : 2018-03-01 13:01:34
         * transport_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * policy_time : 2018-04-01 13:01:34
         * policy_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * insurance_time : 2018-05-01 13:01:34
         * insurance_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * car_style_type_id : 1000
         * car_style_length_id : 1009
         */
        private int area;
        private int city;
        private String car_type;
        private String car_band;
        private String car_length;
        private String car_registered_time;
        private String card_number;
        private String car_front_pic;
        private String car_bank_pic;
        private String car_tail_pic;
        private String license_time;
        private String license_pic;
        private String transport_pic_time;
        private String transport_pic;
        private String permanent_address;
        private String policy_time;
        private int province;
        private String policy_pic;
        private String insurance_time;
        private String insurance_pic;
        private int car_style_type_id;
        private int car_style_length_id;

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }

        public String getCar_band() {
            return car_band;
        }

        public void setCar_band(String car_band) {
            this.car_band = car_band;
        }

        public String getCar_length() {
            return car_length;
        }

        public void setCar_length(String car_length) {
            this.car_length = car_length;
        }

        public String getCar_registered_time() {
            return car_registered_time;
        }

        public void setCar_registered_time(String car_registered_time) {
            this.car_registered_time = car_registered_time;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCar_front_pic() {
            return car_front_pic;
        }

        public void setCar_front_pic(String car_front_pic) {
            this.car_front_pic = car_front_pic;
        }

        public String getCar_bank_pic() {
            return car_bank_pic;
        }

        public void setCar_bank_pic(String car_bank_pic) {
            this.car_bank_pic = car_bank_pic;
        }

        public String getCar_tail_pic() {
            return car_tail_pic;
        }

        public void setCar_tail_pic(String car_tail_pic) {
            this.car_tail_pic = car_tail_pic;
        }

        public String getLicense_time() {
            return license_time;
        }

        public void setLicense_time(String license_time) {
            this.license_time = license_time;
        }

        public String getLicense_pic() {
            return license_pic;
        }

        public void setLicense_pic(String license_pic) {
            this.license_pic = license_pic;
        }

        public String getTransport_pic_time() {
            return transport_pic_time;
        }

        public void setTransport_pic_time(String transport_pic_time) {
            this.transport_pic_time = transport_pic_time;
        }

        public String getTransport_pic() {
            return transport_pic;
        }

        public void setTransport_pic(String transport_pic) {
            this.transport_pic = transport_pic;
        }

        public String getPermanent_address() {
            return permanent_address;
        }

        public void setPermanent_address(String permanent_address) {
            this.permanent_address = permanent_address;
        }

        public String getPolicy_time() {
            return policy_time;
        }

        public void setPolicy_time(String policy_time) {
            this.policy_time = policy_time;
        }

        public String getPolicy_pic() {
            return policy_pic;
        }

        public void setPolicy_pic(String policy_pic) {
            this.policy_pic = policy_pic;
        }

        public String getInsurance_time() {
            return insurance_time;
        }

        public void setInsurance_time(String insurance_time) {
            this.insurance_time = insurance_time;
        }

        public String getInsurance_pic() {
            return insurance_pic;
        }

        public void setInsurance_pic(String insurance_pic) {
            this.insurance_pic = insurance_pic;
        }

        public int getCar_style_type_id() {
            return car_style_type_id;
        }

        public void setCar_style_type_id(int car_style_type_id) {
            this.car_style_type_id = car_style_type_id;
        }

        public int getCar_style_length_id() {
            return car_style_length_id;
        }

        public void setCar_style_length_id(int car_style_length_id) {
            this.car_style_length_id = car_style_length_id;
        }
    }
}
