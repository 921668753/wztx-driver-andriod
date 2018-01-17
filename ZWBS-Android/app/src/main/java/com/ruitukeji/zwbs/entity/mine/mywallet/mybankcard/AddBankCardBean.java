package com.ruitukeji.zwbs.entity.mine.mywallet.mybankcard;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Administrator on 2018/1/17.
 */

public class AddBankCardBean extends BaseResult<AddBankCardBean.ResultBean> {


    public class ResultBean {
        /**
         * bank_id : 34
         */

        private int bank_id;

        public int getBank_id() {
            return bank_id;
        }

        public void setBank_id(int bank_id) {
            this.bank_id = bank_id;
        }
    }
}