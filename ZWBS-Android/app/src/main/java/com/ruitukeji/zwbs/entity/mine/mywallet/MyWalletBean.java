package com.ruitukeji.zwbs.entity.mine.mywallet;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Admin on 2017/7/12.
 */

public class MyWalletBean extends BaseResult<MyWalletBean.ResultBean> {


    public class ResultBean {
        /**
         * balance : 344.99
         * pre_month_total_order : 0
         * pre_month_total_money : 0.00
         * cur_month_total_order : 0
         * month_total_money : 0.00
         * year_total_money : 0.00
         * uninvoicing_singular_total_order : 7
         * uninvoicing_singular_total_money : 434828.00
         * withdrawal_money : 344.99
         * bonus : 0.00
         */

        private String balance;
        private int pre_month_total_order;
        private String pre_month_total_money;
        private int cur_month_total_order;
        private String month_total_money;
        private String year_total_money;
        private int uninvoicing_singular_total_order;
        private String uninvoicing_singular_total_money;
        private String withdrawal_money;
        private String bonus;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public int getPre_month_total_order() {
            return pre_month_total_order;
        }

        public void setPre_month_total_order(int pre_month_total_order) {
            this.pre_month_total_order = pre_month_total_order;
        }

        public String getPre_month_total_money() {
            return pre_month_total_money;
        }

        public void setPre_month_total_money(String pre_month_total_money) {
            this.pre_month_total_money = pre_month_total_money;
        }

        public int getCur_month_total_order() {
            return cur_month_total_order;
        }

        public void setCur_month_total_order(int cur_month_total_order) {
            this.cur_month_total_order = cur_month_total_order;
        }

        public String getMonth_total_money() {
            return month_total_money;
        }

        public void setMonth_total_money(String month_total_money) {
            this.month_total_money = month_total_money;
        }

        public String getYear_total_money() {
            return year_total_money;
        }

        public void setYear_total_money(String year_total_money) {
            this.year_total_money = year_total_money;
        }

        public int getUninvoicing_singular_total_order() {
            return uninvoicing_singular_total_order;
        }

        public void setUninvoicing_singular_total_order(int uninvoicing_singular_total_order) {
            this.uninvoicing_singular_total_order = uninvoicing_singular_total_order;
        }

        public String getUninvoicing_singular_total_money() {
            return uninvoicing_singular_total_money;
        }

        public void setUninvoicing_singular_total_money(String uninvoicing_singular_total_money) {
            this.uninvoicing_singular_total_money = uninvoicing_singular_total_money;
        }

        public String getWithdrawal_money() {
            return withdrawal_money;
        }

        public void setWithdrawal_money(String withdrawal_money) {
            this.withdrawal_money = withdrawal_money;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }
    }
}
