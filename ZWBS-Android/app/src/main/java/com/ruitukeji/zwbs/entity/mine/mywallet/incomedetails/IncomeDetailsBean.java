package com.ruitukeji.zwbs.entity.mine.mywallet.incomedetails;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */

public class IncomeDetailsBean extends BaseResult<IncomeDetailsBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":257,"name":"丁坤","type":"often","create_at":"2017-09-02 09:27:47","goods_name":"2","order_code":"2017123098545215","car_style_length":"4.2米","car_style_type":"厢式","weight":2,"volume":15,"org_address_detail":"","dest_address_detail":"","kilometres":"5.83","effective_time":0,"is_driver_dock":1,"fact_pay":757},{"id":260,"name":"丁坤","type":"often","create_at":"2017-09-04 11:55:13","goods_name":"测试","order_code":"2017123098545215","car_style_length":"4.2米","car_style_type":"厢式","weight":2,"volume":15,"org_address_detail":"","dest_address_detail":"","kilometres":"2.953","effective_time":20,"is_driver_dock":0,"fact_pay":60},{"id":261,"name":"丁坤","type":"often","create_at":"2017-09-05 09:44:31","goods_name":"测试","order_code":"2017123098539634","car_style_length":"4.2米","car_style_type":"厢式","weight":2,"volume":15,"org_address_detail":"","dest_address_detail":"","kilometres":"22.853","effective_time":0,"is_driver_dock":0,"fact_pay":250},{"id":264,"name":"丁坤","type":"often","create_at":"2017-09-07 10:06:06","goods_name":"测试","order_code":null,"car_style_length":"4.2米","car_style_type":"厢式","weight":2,"volume":15,"org_address_detail":"","dest_address_detail":"","kilometres":"24.261","effective_time":0,"is_driver_dock":null,"fact_pay":null}]
         * page : 1
         * pageSize : 10
         * dataTotal : 4
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
             * id : 257
             * name : 丁坤
             * type : often
             * create_at : 2017-09-02 09:27:47
             * goods_name : 2
             * order_code : 2017123098545215
             * car_style_length : 4.2米
             * car_style_type : 厢式
             * weight : 2
             * volume : 15
             * org_address_detail :
             * dest_address_detail :
             * kilometres : 5.83
             * effective_time : 0
             * is_driver_dock : 1
             * fact_pay : 757
             */

            private int id;
            private String name;
            private String type;
            private String create_at;
            private String goods_name;
            private String order_code;
            private String car_style_length;
            private String car_style_type;
            private String weight;
            private int is_assigned;
            private String volume;
            private String org_address_detail;
            private String dest_address_detail;
            private String kilometres;
            private String effective_time;
            private int is_driver_dock;
            private String fact_pay;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getOrder_code() {
                return order_code;
            }

            public void setOrder_code(String order_code) {
                this.order_code = order_code;
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

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public int getIs_assigned() {
                return is_assigned;
            }

            public void setIs_assigned(int is_assigned) {
                this.is_assigned = is_assigned;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
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

            public String getKilometres() {
                return kilometres;
            }

            public void setKilometres(String kilometres) {
                this.kilometres = kilometres;
            }

            public String getEffective_time() {
                return effective_time;
            }

            public void setEffective_time(String effective_time) {
                this.effective_time = effective_time;
            }

            public int getIs_driver_dock() {
                return is_driver_dock;
            }

            public void setIs_driver_dock(int is_driver_dock) {
                this.is_driver_dock = is_driver_dock;
            }

            public String getFact_pay() {
                return fact_pay;
            }

            public void setFact_pay(String fact_pay) {
                this.fact_pay = fact_pay;
            }
        }
    }
}
