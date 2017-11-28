package com.ruitukeji.zwbs.entity;

import java.util.List;

/**
 * Created by Admin on 2017/8/10.
 */

public class MyQuoteBean extends BaseResult<MyQuoteBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":137,"goods_name":"冰","weight":"0.444","goods_id":101,"order_id":68,"sp_id":31,"dr_id":1,"dr_price":"60.00","system_price":"100.00","sp_price":"80.00","create_at":1502418512,"update_at":1502418512,"usecar_time":"2017-08-11 09:38:25","car_style_length":"4.2米","car_style_type":"高栏","org_city":"江苏省苏州市吴中区","dest_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","dest_address_name":"江苏省苏州市吴中区中海双湾花园","org_address_detail":"","dest_address_detail":"","is_receive":0,"status":"init"}]
         * page : 1
         * pageSize : 10
         * dataTotal : 1
         * pageTotal : 1
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
             * id : 137
             * goods_name : 冰
             * weight : 0.444
             * goods_id : 101
             * order_id : 68
             * sp_id : 31
             * dr_id : 1
             * dr_price : 60.00
             * system_price : 100.00
             * sp_price : 80.00
             * create_at : 1502418512
             * update_at : 1502418512
             * usecar_time : 2017-08-11 09:38:25
             * car_style_length : 4.2米
             * car_style_type : 高栏
             * org_city : 江苏省苏州市吴中区
             * dest_city : 江苏省苏州市吴中区
             * org_address_name : 江苏省苏州市吴中区腾飞苏州创新园
             * dest_address_name : 江苏省苏州市吴中区中海双湾花园
             * org_address_detail :
             * dest_address_detail :
             * is_receive : 0
             * status : init
             */

            private int id;
            private String goods_name;
            private String weight;
            private int goods_id;
            private int order_id;
            private int sp_id;
            private int dr_id;
            private String dr_price;
            private String system_price;
            private String sp_price;
            private int create_at;
            private int update_at;
            private String usecar_time;
            private String car_style_length;
            private String car_style_type;
            private String org_city;
            private String dest_city;
            private String org_address_name;
            private String dest_address_name;
            private String org_address_detail;
            private String dest_address_detail;
            private int is_receive;
            private String status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getSp_id() {
                return sp_id;
            }

            public void setSp_id(int sp_id) {
                this.sp_id = sp_id;
            }

            public int getDr_id() {
                return dr_id;
            }

            public void setDr_id(int dr_id) {
                this.dr_id = dr_id;
            }

            public String getDr_price() {
                return dr_price;
            }

            public void setDr_price(String dr_price) {
                this.dr_price = dr_price;
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

            public int getCreate_at() {
                return create_at;
            }

            public void setCreate_at(int create_at) {
                this.create_at = create_at;
            }

            public int getUpdate_at() {
                return update_at;
            }

            public void setUpdate_at(int update_at) {
                this.update_at = update_at;
            }

            public String getUsecar_time() {
                return usecar_time;
            }

            public void setUsecar_time(String usecar_time) {
                this.usecar_time = usecar_time;
            }

            public String getCar_style_length() {
                return car_style_length;
            }

            public void setCar_style_length(String car_style_length) {
                this.car_style_length = car_style_length;
            }

            public String getCar_style_type() {
                return car_style_type;
            }

            public void setCar_style_type(String car_style_type) {
                this.car_style_type = car_style_type;
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

            public String getOrg_address_name() {
                return org_address_name;
            }

            public void setOrg_address_name(String org_address_name) {
                this.org_address_name = org_address_name;
            }

            public String getDest_address_name() {
                return dest_address_name;
            }

            public void setDest_address_name(String dest_address_name) {
                this.dest_address_name = dest_address_name;
            }

            public String getOrg_address_detail() {
                return org_address_detail;
            }

            public void setOrg_address_detail(String org_address_detail) {
                this.org_address_detail = org_address_detail;
            }

            public String getDest_address_detail() {
                return dest_address_detail;
            }

            public void setDest_address_detail(String dest_address_detail) {
                this.dest_address_detail = dest_address_detail;
            }

            public int getIs_receive() {
                return is_receive;
            }

            public void setIs_receive(int is_receive) {
                this.is_receive = is_receive;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
