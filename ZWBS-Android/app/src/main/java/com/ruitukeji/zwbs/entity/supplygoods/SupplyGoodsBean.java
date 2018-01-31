package com.ruitukeji.zwbs.entity.supplygoods;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/26.
 */

public class SupplyGoodsBean extends BaseResult<SupplyGoodsBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":379,"org_city":"江苏省苏州市昆山市","volume":"10立方","kilometres":"103.435千米","is_driver_dock":1,"mind_price":"0.00","system_price":"532.64","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"often","appoint_at":"2018-01-19 09:32:12","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"高栏","car_style_type_id":1002,"usecar_time":"25272092小时12分钟"},{"id":378,"org_city":"江苏省苏州市昆山市","volume":"10立方","kilometres":"40.642千米","is_driver_dock":1,"mind_price":"0.00","system_price":"237.52","dest_city":"江苏省苏州市姑苏区","weight":"10吨","type":"often","appoint_at":"2018-01-18 17:09:36","goods_name":"阿胶","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"高栏","car_style_type_id":1002,"usecar_time":""},{"id":377,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:30","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""},{"id":376,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:10","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""},{"id":375,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:09","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""},{"id":374,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:08","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""},{"id":373,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:06","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""},{"id":372,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:05","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""},{"id":371,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:04","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""},{"id":370,"org_city":"江苏省苏州市姑苏区","volume":"10立方","kilometres":"73.32千米","is_driver_dock":1,"mind_price":"0.00","system_price":"391.10","dest_city":"江苏省苏州市张家港市","weight":"10吨","type":"urgent","appoint_at":"2018-01-18 15:53:02","goods_name":"五金","status":"quote","car_style_length":"4.2米","car_style_length_id":1009,"car_style_type":"保温车","car_style_type_id":1020,"usecar_time":""}]
         * page : 1
         * pageSize : 10
         * dataTotal : 117
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
             * id : 379
             * org_city : 江苏省苏州市昆山市
             * volume : 10立方
             * kilometres : 103.435千米
             * is_driver_dock : 1
             * mind_price : 0.00
             * system_price : 532.64
             * dest_city : 江苏省苏州市张家港市
             * weight : 10吨
             * type : often
             * appoint_at : 2018-01-19 09:32:12
             * goods_name : 五金
             * status : quote
             * car_style_length : 4.2米
             * car_style_length_id : 1009
             * car_style_type : 高栏
             * car_style_type_id : 1002
             * usecar_time : 25272092小时12分钟
             */

            private int id;
            private String org_send_name;
            private String org_city;
            private String volume;
            private String kilometres;
            private int is_cargo_receipt;
            private String mind_price;
            private String system_price;
            private String dest_city;
            private int effective_time;
            private String weight;
            private String type;
            private String order_code;
            private String appoint_at;
            private String goods_name;
            private String status;
            private String car_style_length;
            private int car_style_length_id;
            private String car_style_type;
            private int car_style_type_id;
            private String usecar_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrg_send_name() {
                return org_send_name;
            }

            public void setOrg_send_name(String org_send_name) {
                this.org_send_name = org_send_name;
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

            public int getIs_cargo_receipt() {
                return is_cargo_receipt;
            }

            public void setIs_cargo_receipt(int is_cargo_receipt) {
                this.is_cargo_receipt = is_cargo_receipt;
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

            public int getEffective_time() {
                return effective_time;
            }

            public void setEffective_time(int effective_time) {
                this.effective_time = effective_time;
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

            public String getOrder_code() {
                return order_code;
            }

            public void setOrder_code(String order_code) {
                this.order_code = order_code;
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
