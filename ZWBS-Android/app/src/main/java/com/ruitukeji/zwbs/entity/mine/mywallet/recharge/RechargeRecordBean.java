package com.ruitukeji.zwbs.entity.mine.mywallet.recharge;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/25.
 */

public class RechargeRecordBean extends BaseResult<RechargeRecordBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":4,"real_amount":"1.00","balance":"0.00","pay_time":"2018-01-11 18:00:13","pay_way":1,"pay_status":0,"total_amount":"1.00"},{"id":3,"real_amount":"12.00","balance":"0.00","pay_time":"2018-01-11 09:46:17","pay_way":1,"pay_status":0,"total_amount":"12.00"},{"id":2,"real_amount":"12.00","balance":"0.00","pay_time":"2018-01-11 09:46:14","pay_way":2,"pay_status":0,"total_amount":"12.00"},{"id":1,"real_amount":"12.00","balance":"0.00","pay_time":"2018-01-11 09:46:11","pay_way":2,"pay_status":0,"total_amount":"12.00"}]
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
             * id : 4
             * real_amount : 1.00
             * balance : 0.00
             * pay_time : 2018-01-11 18:00:13
             * pay_way : 1
             * pay_status : 0
             * total_amount : 1.00
             */

            private int id;
            private String real_amount;
            private String balance;
            private String pay_time;
            private int pay_way;
            private int pay_status;
            private String total_amount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getReal_amount() {
                return real_amount;
            }

            public void setReal_amount(String real_amount) {
                this.real_amount = real_amount;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public int getPay_way() {
                return pay_way;
            }

            public void setPay_way(int pay_way) {
                this.pay_way = pay_way;
            }

            public int getPay_status() {
                return pay_status;
            }

            public void setPay_status(int pay_status) {
                this.pay_status = pay_status;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }
        }
    }
}

