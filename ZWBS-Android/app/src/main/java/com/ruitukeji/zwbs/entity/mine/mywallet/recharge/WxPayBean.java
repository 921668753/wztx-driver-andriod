package com.ruitukeji.zwbs.entity.mine.mywallet.recharge;

import com.google.gson.annotations.SerializedName;
import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Admin on 2017/7/27.
 */

public class WxPayBean extends BaseResult<WxPayBean.ResultBean> {


    public class ResultBean {
        /**
         * appid : wxc50f5bf05f014dee
         * partnerid : 1483170282
         * prepayid : wx20170727075849cd9d9d96600245089155
         * package : Sign=WXPay
         * noncestr : 59792cb9aa9da
         * timestamp : 1501113529
         * sign : DC10C9AA59403E6918D574AC980FCFDC
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
