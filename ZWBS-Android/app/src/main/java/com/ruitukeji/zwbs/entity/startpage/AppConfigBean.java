package com.ruitukeji.zwbs.entity.startpage;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Admin on 2017/7/13.
 */

public class AppConfigBean extends BaseResult<AppConfigBean.ResultBean> {


    public class ResultBean {
        /**
         * lastApkUrl :
         * lastApkVersion :
         * lastApkVersionNum : 0
         * defaultAvatar : http://opmnz562z.bkt.clouddn.com/b22e33502ca2e6e1d93a996e062e8f2d.png
         * share_percent : 50
         * grab_range : 50
         * premium_rate : 75
         * bond_person_amount : 0.01
         * bond_company_amount : 0.02
         * withdraw_begintime : 16
         * withdraw_endtime : 20
         * custom_phone : 15962581692
         * custom_email : 2033517455@qq.com
         * complain_phone : 1801213624448265
         * weixin_limit : 60
         * alipay_limit : 60
         * tran_account : 6215600510025056548
         * share_driver : http://wztx.shp.api.zenmechi.cc/static/share/reg_driver.html
         * share_driver_title : 司机分享描述
         * share_driver_description : 司机分享描述
         * xx : 其他参数
         */

        private String lastApkUrl;
        private String lastApkVersion;
        private int lastApkVersionNum;
        private String defaultAvatar;
        private String share_percent;
        private String grab_range;
        private String premium_rate;
        private String bond_person_amount;
        private String bond_company_amount;
        private String withdraw_begintime;
        private String withdraw_endtime;
        private String custom_phone;
        private String custom_email;
        private String complain_phone;
        private String weixin_limit;
        private String alipay_limit;
        private String tran_account;
        private String share_driver;
        private String share_driver_title;
        private String share_driver_description;
        private String xx;

        public String getLastApkUrl() {
            return lastApkUrl;
        }

        public void setLastApkUrl(String lastApkUrl) {
            this.lastApkUrl = lastApkUrl;
        }

        public String getLastApkVersion() {
            return lastApkVersion;
        }

        public void setLastApkVersion(String lastApkVersion) {
            this.lastApkVersion = lastApkVersion;
        }

        public int getLastApkVersionNum() {
            return lastApkVersionNum;
        }

        public void setLastApkVersionNum(int lastApkVersionNum) {
            this.lastApkVersionNum = lastApkVersionNum;
        }

        public String getDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(String defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public String getShare_percent() {
            return share_percent;
        }

        public void setShare_percent(String share_percent) {
            this.share_percent = share_percent;
        }

        public String getGrab_range() {
            return grab_range;
        }

        public void setGrab_range(String grab_range) {
            this.grab_range = grab_range;
        }

        public String getPremium_rate() {
            return premium_rate;
        }

        public void setPremium_rate(String premium_rate) {
            this.premium_rate = premium_rate;
        }

        public String getBond_person_amount() {
            return bond_person_amount;
        }

        public void setBond_person_amount(String bond_person_amount) {
            this.bond_person_amount = bond_person_amount;
        }

        public String getBond_company_amount() {
            return bond_company_amount;
        }

        public void setBond_company_amount(String bond_company_amount) {
            this.bond_company_amount = bond_company_amount;
        }

        public String getWithdraw_begintime() {
            return withdraw_begintime;
        }

        public void setWithdraw_begintime(String withdraw_begintime) {
            this.withdraw_begintime = withdraw_begintime;
        }

        public String getWithdraw_endtime() {
            return withdraw_endtime;
        }

        public void setWithdraw_endtime(String withdraw_endtime) {
            this.withdraw_endtime = withdraw_endtime;
        }

        public String getCustom_phone() {
            return custom_phone;
        }

        public void setCustom_phone(String custom_phone) {
            this.custom_phone = custom_phone;
        }

        public String getCustom_email() {
            return custom_email;
        }

        public void setCustom_email(String custom_email) {
            this.custom_email = custom_email;
        }

        public String getComplain_phone() {
            return complain_phone;
        }

        public void setComplain_phone(String complain_phone) {
            this.complain_phone = complain_phone;
        }

        public String getWeixin_limit() {
            return weixin_limit;
        }

        public void setWeixin_limit(String weixin_limit) {
            this.weixin_limit = weixin_limit;
        }

        public String getAlipay_limit() {
            return alipay_limit;
        }

        public void setAlipay_limit(String alipay_limit) {
            this.alipay_limit = alipay_limit;
        }

        public String getTran_account() {
            return tran_account;
        }

        public void setTran_account(String tran_account) {
            this.tran_account = tran_account;
        }

        public String getShare_driver() {
            return share_driver;
        }

        public void setShare_driver(String share_driver) {
            this.share_driver = share_driver;
        }

        public String getShare_driver_title() {
            return share_driver_title;
        }

        public void setShare_driver_title(String share_driver_title) {
            this.share_driver_title = share_driver_title;
        }

        public String getShare_driver_description() {
            return share_driver_description;
        }

        public void setShare_driver_description(String share_driver_description) {
            this.share_driver_description = share_driver_description;
        }

        public String getXx() {
            return xx;
        }

        public void setXx(String xx) {
            this.xx = xx;
        }
    }
}
