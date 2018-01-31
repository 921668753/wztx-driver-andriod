package com.ruitukeji.zwbs.entity.mine.abnormalrecords;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Administrator on 2018/1/8.
 */

public class AbnormalSituationBean extends BaseResult<AbnormalSituationBean.ResultBean> {


    public class ResultBean {
        /**
         * id : 15
         * dr_id : 62
         * sp_name : 看见了
         * dr_name : 看见了
         * card_number : 苏11111
         * car_length : 6.8
         * car_style_length_id : 7
         * car_type : 飞翼箱式车
         * car_style_type_id : 1015
         * g_type : often
         * g_time : 1516585881
         * order_code : 2018012257995199
         * start_address : 江苏省苏州市昆山市玉山镇前进西路292号(近中山路口)刚刚好刚刚
         * end_address : 江苏省苏州市姑苏区西环路与西环路高架出口交叉口西100米扣扣扣扣麻将屋
         * create_time : 2018-01-25 11:07:53
         * content : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * place : 江苏省苏州市昆山市海星路5号靠近昆山市仁之源劳务服务有限公司
         * abnormal_time : 2018-01-25 11:07
         * reason : 看见了
         * goods_name : 五金
         * abnormal_map : 121.062544,31.320191
         */

        private int id;
        private int dr_id;
        private String sp_name;
        private String dr_name;
        private String card_number;
        private String car_length;
        private int car_style_length_id;
        private String car_type;
        private int car_style_type_id;
        private String g_type;
        private int g_time;
        private String order_code;
        private String start_address;
        private String end_address;
        private String create_time;
        private String content;
        private String place;
        private String abnormal_time;
        private String reason;
        private String goods_name;
        private String abnormal_map;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDr_id() {
            return dr_id;
        }

        public void setDr_id(int dr_id) {
            this.dr_id = dr_id;
        }

        public String getSp_name() {
            return sp_name;
        }

        public void setSp_name(String sp_name) {
            this.sp_name = sp_name;
        }

        public String getDr_name() {
            return dr_name;
        }

        public void setDr_name(String dr_name) {
            this.dr_name = dr_name;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCar_length() {
            return car_length;
        }

        public void setCar_length(String car_length) {
            this.car_length = car_length;
        }

        public int getCar_style_length_id() {
            return car_style_length_id;
        }

        public void setCar_style_length_id(int car_style_length_id) {
            this.car_style_length_id = car_style_length_id;
        }

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }

        public int getCar_style_type_id() {
            return car_style_type_id;
        }

        public void setCar_style_type_id(int car_style_type_id) {
            this.car_style_type_id = car_style_type_id;
        }

        public String getG_type() {
            return g_type;
        }

        public void setG_type(String g_type) {
            this.g_type = g_type;
        }

        public int getG_time() {
            return g_time;
        }

        public void setG_time(int g_time) {
            this.g_time = g_time;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getStart_address() {
            return start_address;
        }

        public void setStart_address(String start_address) {
            this.start_address = start_address;
        }

        public String getEnd_address() {
            return end_address;
        }

        public void setEnd_address(String end_address) {
            this.end_address = end_address;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getAbnormal_time() {
            return abnormal_time;
        }

        public void setAbnormal_time(String abnormal_time) {
            this.abnormal_time = abnormal_time;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getAbnormal_map() {
            return abnormal_map;
        }

        public void setAbnormal_map(String abnormal_map) {
            this.abnormal_map = abnormal_map;
        }
    }
}
