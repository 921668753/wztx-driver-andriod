package com.ruitukeji.zwbs.entity;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class WithdrawalRecordBean extends BaseResult<WithdrawalRecordBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":1,"withdrawal_amount":"0.00","bank_name":"天空","account":"12345******1111","create_at":"2017-07-27 09:38:56","status":"pay_success"},{"id":2,"withdrawal_amount":"0.00","bank_name":"天空","account":"12345******1111","create_at":"2017-07-27 09:38:56","status":"agree"},{"id":3,"withdrawal_amount":"0.00","bank_name":"天空","account":"12345******1111","create_at":"2017-07-27 09:38:56","status":"refuse"},{"id":5,"withdrawal_amount":"0.00","bank_name":"天空","account":"12345******1111","create_at":"2017-07-27 09:38:56","status":"pay_fail"}]
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
             * id : 1
             * withdrawal_amount : 0.00
             * bank_name : 天空
             * account : 12345******1111
             * create_at : 2017-07-27 09:38:56
             * status : pay_success
             */

            private int id;
            private String withdrawal_amount;
            private String bank_name;
            private String account;
            private String create_at;
            private String status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getWithdrawal_amount() {
                return withdrawal_amount;
            }

            public void setWithdrawal_amount(String withdrawal_amount) {
                this.withdrawal_amount = withdrawal_amount;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
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
