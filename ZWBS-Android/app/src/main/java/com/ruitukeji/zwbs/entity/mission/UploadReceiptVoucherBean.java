package com.ruitukeji.zwbs.entity.mission;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Administrator on 2018/1/24.
 */

public class UploadReceiptVoucherBean extends BaseResult<UploadReceiptVoucherBean.ResultBean> {


    public class ResultBean {
        /**
         * cargo_man : 你看啥
         * cargo_tel : 13725252522
         * cargo_address : 江苏省苏州市昆山市
         * cargo_address_detail : 看你咋滴
         * cargo_is_express : 1
         * is_cargo_receipt : 1
         */

        private String cargo_man;
        private String cargo_tel;
        private String cargo_address;
        private String cargo_address_detail;
        private int cargo_is_express;
        private int is_cargo_receipt;

        public String getCargo_man() {
            return cargo_man;
        }

        public void setCargo_man(String cargo_man) {
            this.cargo_man = cargo_man;
        }

        public String getCargo_tel() {
            return cargo_tel;
        }

        public void setCargo_tel(String cargo_tel) {
            this.cargo_tel = cargo_tel;
        }

        public String getCargo_address() {
            return cargo_address;
        }

        public void setCargo_address(String cargo_address) {
            this.cargo_address = cargo_address;
        }

        public String getCargo_address_detail() {
            return cargo_address_detail;
        }

        public void setCargo_address_detail(String cargo_address_detail) {
            this.cargo_address_detail = cargo_address_detail;
        }

        public int getCargo_is_express() {
            return cargo_is_express;
        }

        public void setCargo_is_express(int cargo_is_express) {
            this.cargo_is_express = cargo_is_express;
        }

        public int getIs_cargo_receipt() {
            return is_cargo_receipt;
        }

        public void setIs_cargo_receipt(int is_cargo_receipt) {
            this.is_cargo_receipt = is_cargo_receipt;
        }
    }
}
