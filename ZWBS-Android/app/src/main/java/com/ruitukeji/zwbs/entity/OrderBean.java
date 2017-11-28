package com.ruitukeji.zwbs.entity;

import java.util.List;

/**
 * Created by Admin on 2017/7/12.
 */

public class OrderBean extends BaseResult<OrderBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"order_id":15,"org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","dest_address_name":"陕西省咸阳市渭城区西安咸阳国际机场","org_city":"江苏省苏州市吴中区","dest_city":"陕西省咸阳市渭城区","weight":"5","goods_name":"电器","status":"distribute","car_style_length":"6米","car_style_type":"厢式","final_price":"20000.00","mind_price":"20000.00","system_price":"22637.06","usecar_time":"2017-08-07 10:50:14","org_address_detail":"","dest_address_detail":""},{"order_id":8,"org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","dest_address_name":"河南省郑州市二七区郑州站","org_city":"江苏省苏州市吴中区","dest_city":"河南省郑州市二七区","weight":"50","goods_name":"金属","status":"quoted","car_style_length":"6米","car_style_type":"平板","final_price":"74808.89","mind_price":"0.00","system_price":"74808.89","usecar_time":"2017-08-05 17:59:20","org_address_detail":"","dest_address_detail":"图库"},{"order_id":7,"org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","org_city":"江苏省苏州市吴中区","dest_city":"河南省郑州市新郑市","weight":"14","goods_name":"木头","status":"quoted","car_style_length":"5.2米","car_style_type":"平板","final_price":"128408.96","mind_price":"0.00","system_price":"128408.96","usecar_time":"2017-08-05 17:57:33","org_address_detail":"","dest_address_detail":"vbb"},{"order_id":4,"org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","dest_address_name":"江苏省苏州市吴中区创意产业园(公交站)","org_city":"江苏省苏州市吴中区","dest_city":"江苏省苏州市吴中区","weight":"30","goods_name":"西瓜","status":"distribute","car_style_length":"4.2米","car_style_type":"自卸车","final_price":"100.00","mind_price":"0.00","system_price":"100.00","usecar_time":"2017-08-05 13:36:11","org_address_detail":"","dest_address_detail":"魔图"},{"order_id":1,"org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","dest_address_name":"江苏省苏州市吴中区苏州吴中SM城市广场购物中心","org_city":"江苏省苏州市吴中区","dest_city":"江苏省苏州市吴中区","weight":"0.5","goods_name":"电子","status":"photo","car_style_length":"4.5米","car_style_type":"面包","final_price":"0.01","mind_price":"0.01","system_price":"424.08","usecar_time":"2017-08-04 14:22:08","org_address_detail":"","dest_address_detail":"A座"}]
         * page : 1
         * pageSize : 10
         * dataTotal : 5
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
             * order_id : 15
             * org_address_name : 江苏省苏州市吴中区腾飞苏州创新园
             * dest_address_name : 陕西省咸阳市渭城区西安咸阳国际机场
             * org_city : 江苏省苏州市吴中区
             * dest_city : 陕西省咸阳市渭城区
             * weight : 5
             * goods_name : 电器
             * status : distribute
             * car_style_length : 6米
             * car_style_type : 厢式
             * final_price : 20000.00
             * mind_price : 20000.00
             * system_price : 22637.06
             * usecar_time : 2017-08-07 10:50:14
             * org_address_detail :
             * dest_address_detail :
             */

            private int order_id;
            private String org_address_name;
            private String dest_address_name;
            private String org_city;
            private String dest_city;
            private String weight;
            private String goods_name;
            private String status;
            private String car_style_length;
            private String car_style_type;
            private String final_price;
            private String mind_price;
            private String system_price;
            private String usecar_time;
            private String org_address_detail;
            private String dest_address_detail;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
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

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
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

            public String getCar_style_type() {
                return car_style_type;
            }

            public void setCar_style_type(String car_style_type) {
                this.car_style_type = car_style_type;
            }

            public String getFinal_price() {
                return final_price;
            }

            public void setFinal_price(String final_price) {
                this.final_price = final_price;
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

            public String getUsecar_time() {
                return usecar_time;
            }

            public void setUsecar_time(String usecar_time) {
                this.usecar_time = usecar_time;
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
        }
    }
}
