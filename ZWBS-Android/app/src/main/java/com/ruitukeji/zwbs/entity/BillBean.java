package com.ruitukeji.zwbs.entity;

import java.util.List;

/**
 * Created by Admin on 2017/7/11.
 */

public class BillBean extends BaseResult<BillBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"order_id":15,"org_city":"江苏省苏州市吴中区","dest_city":"河南省郑州市二七区","real_name":"罢了","company_name":"chhh","customer_type":"person","final_price":"1492968.12","pay_time":null,"usecar_time":"2017-07-21 14:34:06","is_pay":1,"status":"comment"}]
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
             * order_id : 15
             * org_city : 江苏省苏州市吴中区
             * dest_city : 河南省郑州市二七区
             * real_name : 罢了
             * company_name : chhh
             * customer_type : person
             * final_price : 1492968.12
             * pay_time : null
             * usecar_time : 2017-07-21 14:34:06
             * is_pay : 1
             * status : comment
             */

            private int order_id;
            private String org_city;
            private String dest_city;
            private String real_name;
            private String company_name;
            private String customer_type;
            private String final_price;
            private String pay_time;
            private String usecar_time;
            private int is_pay;
            private String status;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
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

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCustomer_type() {
                return customer_type;
            }

            public void setCustomer_type(String customer_type) {
                this.customer_type = customer_type;
            }

            public String getFinal_price() {
                return final_price;
            }

            public void setFinal_price(String final_price) {
                this.final_price = final_price;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getUsecar_time() {
                return usecar_time;
            }

            public void setUsecar_time(String usecar_time) {
                this.usecar_time = usecar_time;
            }

            public int getIs_pay() {
                return is_pay;
            }

            public void setIs_pay(int is_pay) {
                this.is_pay = is_pay;
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
