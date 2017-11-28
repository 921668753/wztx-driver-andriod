package com.ruitukeji.zwbs.entity;

/**
 * Created by Admin on 2017/7/11.
 */

public class OrderDetailsBean extends BaseResult<OrderDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * status : quote
         * order_code : 2017080955549754
         * goods_name : 木头
         * weight : 10
         * org_city : 江苏省苏州市吴中区
         * dest_city : 河南省郑州市二七区
         * dest_receive_name : cl
         * dest_phone : 17051335257
         * dest_address_name : 河南省郑州市二七区郑州大学第一附属医院河医院区
         * dest_address_detail : xcg
         * org_send_name : 睿途
         * org_phone : 17051335257
         * org_address_name : 江苏省苏州市吴中区腾飞苏州创新园
         * org_address_detail :
         * usecar_time : 2017-08-08 18:24:54
         * send_time : null
         * arr_time : null
         * real_name : ck
         * phone : 17051335257
         * policy_code : null
         * is_pay : 0
         * is_receipt : 1
         * system_price : 172830.67
         * mind_price : 0.00
         * final_price : 0.00
         * effective_time : 0
         * remark :
         * goods_id : 69
         * quote_price : 20.00
         */

        private String status;
        private String order_code;
        private String goods_name;
        private String weight;
        private String org_city;
        private String dest_city;
        private String dest_receive_name;
        private String dest_phone;
        private String dest_address_name;
        private String dest_address_detail;
        private String dest_telphone;
        private String org_send_name;
        private String org_phone;
        private String org_address_name;
        private String org_telphone;
        private String org_address_detail;
        private String usecar_time;
        private String send_time;
        private String arr_time;
        private String real_name;
        private String phone;
        private String policy_code;
        private int is_pay;
        private int is_receipt;
        private String system_price;
        private String mind_price;
        private String final_price;
        private int effective_time;
        private String remark;
        private int goods_id;
        private String quote_price;
        private String dr_price;

        public String getDr_price() {
            return dr_price;
        }

        public void setDr_price(String dr_price) {
            this.dr_price = dr_price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
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

        public String getDest_receive_name() {
            return dest_receive_name;
        }

        public void setDest_receive_name(String dest_receive_name) {
            this.dest_receive_name = dest_receive_name;
        }

        public String getDest_phone() {
            return dest_phone;
        }

        public void setDest_phone(String dest_phone) {
            this.dest_phone = dest_phone;
        }

        public String getDest_address_name() {
            return dest_address_name;
        }

        public void setDest_address_name(String dest_address_name) {
            this.dest_address_name = dest_address_name;
        }

        public String getDest_address_detail() {
            return dest_address_detail;
        }

        public void setDest_address_detail(String dest_address_detail) {
            this.dest_address_detail = dest_address_detail;
        }

        public String getOrg_send_name() {
            return org_send_name;
        }

        public void setOrg_send_name(String org_send_name) {
            this.org_send_name = org_send_name;
        }

        public String getOrg_phone() {
            return org_phone;
        }

        public void setOrg_phone(String org_phone) {
            this.org_phone = org_phone;
        }

        public String getOrg_address_name() {
            return org_address_name;
        }

        public void setOrg_address_name(String org_address_name) {
            this.org_address_name = org_address_name;
        }

        public String getOrg_address_detail() {
            return org_address_detail;
        }

        public void setOrg_address_detail(String org_address_detail) {
            this.org_address_detail = org_address_detail;
        }

        public String getUsecar_time() {
            return usecar_time;
        }

        public void setUsecar_time(String usecar_time) {
            this.usecar_time = usecar_time;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public String getArr_time() {
            return arr_time;
        }

        public void setArr_time(String arr_time) {
            this.arr_time = arr_time;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPolicy_code() {
            return policy_code;
        }

        public void setPolicy_code(String policy_code) {
            this.policy_code = policy_code;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public int getIs_receipt() {
            return is_receipt;
        }

        public void setIs_receipt(int is_receipt) {
            this.is_receipt = is_receipt;
        }

        public String getSystem_price() {
            return system_price;
        }

        public void setSystem_price(String system_price) {
            this.system_price = system_price;
        }

        public String getMind_price() {
            return mind_price;
        }

        public void setMind_price(String mind_price) {
            this.mind_price = mind_price;
        }

        public String getFinal_price() {
            return final_price;
        }

        public void setFinal_price(String final_price) {
            this.final_price = final_price;
        }

        public int getEffective_time() {
            return effective_time;
        }

        public void setEffective_time(int effective_time) {
            this.effective_time = effective_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getQuote_price() {
            return quote_price;
        }

        public void setQuote_price(String quote_price) {
            this.quote_price = quote_price;
        }

        public String getDest_telphone() {
            return dest_telphone;
        }

        public void setDest_telphone(String dest_telphone) {
            this.dest_telphone = dest_telphone;
        }

        public String getOrg_telphone() {
            return org_telphone;
        }

        public void setOrg_telphone(String org_telphone) {
            this.org_telphone = org_telphone;
        }
    }
}
