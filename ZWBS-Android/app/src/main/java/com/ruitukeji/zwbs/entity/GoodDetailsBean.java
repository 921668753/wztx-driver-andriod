package com.ruitukeji.zwbs.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 2017/8/10.
 */

public class GoodDetailsBean extends BaseResult<GoodDetailsBean.ResultBean> {


    /**
     * code : 2000
     * result : {"status":"quoted","goods_name":"木","weight":"90","org_city":"江苏省苏州市吴中区","dest_city":"河南省郑州市二七区","dest_receive_name":"他","dest_phone":"13462634586","dest_address_name":"河南省郑州市二七区郑州站","dest_address_detail":"啦啦啦","org_send_name":"罢了","org_phone":"17051335257","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","usecar_time":"2017-07-12 09:25:32","real_name":"ck","phone":"17051335257","policy_code":null,"is_pay":null,"is_receipt":1,"system_price":"1484267.42","mind_price":"0.00","final_price":"1484267.42","effective_time":0,"remark":"记得安排服务好一点的司机"}
     */

    public class ResultBean {
        /**
         * status : quoted
         * goods_name : 木
         * weight : 90
         * org_city : 江苏省苏州市吴中区
         * dest_city : 河南省郑州市二七区
         * dest_receive_name : 他
         * dest_phone : 13462634586
         * dest_address_name : 河南省郑州市二七区郑州站
         * dest_address_detail : 啦啦啦
         * org_send_name : 罢了
         * org_phone : 17051335257
         * org_address_name : 江苏省苏州市吴中区腾飞苏州创新园
         * org_address_detail :
         * usecar_time : 2017-07-12 09:25:32
         * real_name : ck
         * phone : 17051335257
         * policy_code : null
         * is_pay : null
         * is_receipt : 1
         * system_price : 1484267.42
         * mind_price : 0.00
         * final_price : 1484267.42
         * effective_time : 0
         * remark : 记得安排服务好一点的司机
         */

        private String status;
        private String goods_name;
        private String weight;
        private String org_city;
        private String dest_city;
        private String dest_receive_name;
        private String dest_phone;
        private String dest_address_name;
        private String dest_address_detail;
        private String org_send_name;
        private String org_phone;
        private String org_address_name;
        private String org_address_detail;
        private String usecar_time;
        private String real_name;
        private String phone;
        private Object policy_code;
        private Object is_pay;
        private int is_receipt;
        private String system_price;
        private String mind_price;
        private String final_price;
        private int effective_time;
        private String remark;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public Object getPolicy_code() {
            return policy_code;
        }

        public void setPolicy_code(Object policy_code) {
            this.policy_code = policy_code;
        }

        public Object getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(Object is_pay) {
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
    }
}
