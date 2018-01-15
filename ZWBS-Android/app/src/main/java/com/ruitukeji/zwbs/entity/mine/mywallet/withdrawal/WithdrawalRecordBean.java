package com.ruitukeji.zwbs.entity.mine.mywallet.withdrawal;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class WithdrawalRecordBean extends BaseResult<WithdrawalRecordBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"amount":"50.00","status":"agree","real_amount":"50.00","balance":"1970-01-01 08:03:25","result_time":1504502298},{"amount":"50.00","status":"agree","real_amount":"50.00","balance":"1970-01-01 08:04:15","result_time":1504261824},{"amount":"200.00","status":"agree","real_amount":"200.00","balance":"1970-01-01 08:18:00","result_time":1504249893}]
         * page : 1
         * pageSize : 10
         * dataTotal : 3
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
             * amount : 50.00
             * status : agree
             * real_amount : 50.00
             * balance : 1970-01-01 08:03:25
             * result_time : 1504502298
             */

            private String amount;
            private String status;
            private String real_amount;
            private String balance;
            private String result_time;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getResult_time() {
                return result_time;
            }

            public void setResult_time(String result_time) {
                this.result_time = result_time;
            }
        }
    }
}
