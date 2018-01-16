package com.ruitukeji.zwbs.entity.mine.mywallet;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Admin on 2017/7/12.
 */

public class MyWalletBean extends BaseResult<MyWalletBean.ResultBean> {


    public class ResultBean {
        /**
         * balance : 0.00
         * withdrawal_money : 0.00
         * bankCard : 5855
         * id : 32
         * bankName : 中国工商银行
         */

        private String balance;
        private String withdrawal_money;
        private String bankCard;
        private int id;
        private String bankName;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getWithdrawal_money() {
            return withdrawal_money;
        }

        public void setWithdrawal_money(String withdrawal_money) {
            this.withdrawal_money = withdrawal_money;
        }

        public String getBankCard() {
            return bankCard;
        }

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
