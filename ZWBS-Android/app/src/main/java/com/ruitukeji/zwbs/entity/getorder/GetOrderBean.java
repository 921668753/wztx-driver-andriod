package com.ruitukeji.zwbs.entity.getorder;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/12.
 */

public class GetOrderBean extends BaseResult<GetOrderBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":380,"org_city":"江苏省苏州市昆山市","volume":"2立方","kilometres":"83.217千米","is_driver_dock":1,"order_code":"2018012097491005","org_send_name":"测试人","mind_price":"0.00","system_price":"1335.10","dest_city":"江苏省苏州市常熟市","weight":"2吨","type":"often","appoint_at":"2018-01-20 12:57:30","goods_name":"测i货物","status":"quote","car_style_length":"12.5米","car_style_length_id":1014,"car_style_type":"箱车","car_style_type_id":1004,"effective_time":242,"usecar_time":"4小时2分钟"},{"id":379,"org_city":"江苏省苏州市昆山市","volume":"10立方","kilometres":"103.435千米","is_driver_dock":1,"order_code":"123456789","org_send_name":"张老板","mind_price":"0.00","system_price":"532.64","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"often","appoint_at":"2018-01-19 09:32:12","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"高栏","car_style_type_id":1002,"effective_time":0,"usecar_time":""},{"id":378,"org_city":"江苏省苏州市昆山市","volume":"10立方","kilometres":"40.642千米","is_driver_dock":1,"order_code":null,"org_send_name":"刚刚","mind_price":"0.00","system_price":"237.52","dest_city":"江苏省苏州市姑苏区","weight":"10吨","type":"often","appoint_at":"2018-01-18 17:09:36","goods_name":"阿胶","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"高栏","car_style_type_id":1002,"effective_time":0,"usecar_time":""},{"id":353,"org_city":"江苏省苏州市昆山市","volume":"30立方","kilometres":"22.063千米","is_driver_dock":1,"order_code":null,"org_send_name":"爱两","mind_price":"0.00","system_price":"167.26","dest_city":"江苏省苏州市昆山市","weight":"60吨","type":"often","appoint_at":"2018-01-18 14:14:56","goods_name":"看见了","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"高栏","car_style_type_id":1002,"effective_time":0,"usecar_time":""},{"id":352,"org_city":"江苏省苏州市昆山市","volume":"10立方","kilometres":"103.435千米","is_driver_dock":1,"order_code":null,"org_send_name":"张老板","mind_price":"0.00","system_price":"719.83","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"often","appoint_at":"2018-01-18 10:06:25","goods_name":"名称","status":"quote","car_style_length":"5.2米","car_style_length_id":1010,"car_style_type":"高栏","car_style_type_id":1002,"effective_time":0,"usecar_time":""}]
         * page : 1
         * pageSize : 5
         * dataTotal : 56
         * pageTotal : 12
         */

        private int page;
        private int pageSize;
        private int dataTotal;
        private int pageTotal;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 380
             * org_city : 江苏省苏州市昆山市
             * volume : 2立方
             * kilometres : 83.217千米
             * is_driver_dock : 1
             * order_code : 2018012097491005
             * org_send_name : 测试人
             * mind_price : 0.00
             * system_price : 1335.10
             * dest_city : 江苏省苏州市常熟市
             * weight : 2吨
             * type : often
             * appoint_at : 2018-01-20 12:57:30
             * goods_name : 测i货物
             * status : quote
             * car_style_length : 12.5米
             * car_style_length_id : 1014
             * car_style_type : 箱车
             * car_style_type_id : 1004
             * effective_time : 242
             * usecar_time : 4小时2分钟
             */

            private int id;
            private String org_city;
            private String volume;
            private String kilometres;
            private int is_assigned;
            private int is_cargo_receipt;
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

            public int getIs_assigned() {
                return is_assigned;
            }

            public void setIs_assigned(int is_assigned) {
                this.is_assigned = is_assigned;
            }

            public int getIs_cargo_receipt() {
                return is_cargo_receipt;
            }

            public void setIs_cargo_receipt(int is_cargo_receipt) {
                this.is_cargo_receipt = is_cargo_receipt;
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
}
