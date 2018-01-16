package com.ruitukeji.zwbs.entity.mine.identityauthentication;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Administrator on 2018/1/16.
 */

public class IdentityAuthenticationBean extends BaseResult<IdentityAuthenticationBean.ResultBean> {


    public class ResultBean {
        /**
         * real_name : 看见了
         * identity : 411111111111111111
         * identity_pic_time : 0000-00-00 00:00:00
         * hold_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * front_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * back_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * license_pic_time : 0000-00-00 00:00:00
         * license_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * transport_pic_time : 0000-00-00 00:00:00
         * transport_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         * faith_pic : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
         */

        private String real_name;
        private String identity;
        private String identity_pic_time;
        private String hold_pic;
        private String front_pic;
        private String back_pic;
        private String license_pic_time;
        private String license_pic;
        private String transport_pic_time;
        private String transport_pic;
        private String faith_pic;

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getIdentity_pic_time() {
            return identity_pic_time;
        }

        public void setIdentity_pic_time(String identity_pic_time) {
            this.identity_pic_time = identity_pic_time;
        }

        public String getHold_pic() {
            return hold_pic;
        }

        public void setHold_pic(String hold_pic) {
            this.hold_pic = hold_pic;
        }

        public String getFront_pic() {
            return front_pic;
        }

        public void setFront_pic(String front_pic) {
            this.front_pic = front_pic;
        }

        public String getBack_pic() {
            return back_pic;
        }

        public void setBack_pic(String back_pic) {
            this.back_pic = back_pic;
        }

        public String getLicense_pic_time() {
            return license_pic_time;
        }

        public void setLicense_pic_time(String license_pic_time) {
            this.license_pic_time = license_pic_time;
        }

        public String getLicense_pic() {
            return license_pic;
        }

        public void setLicense_pic(String license_pic) {
            this.license_pic = license_pic;
        }

        public String getTransport_pic_time() {
            return transport_pic_time;
        }

        public void setTransport_pic_time(String transport_pic_time) {
            this.transport_pic_time = transport_pic_time;
        }

        public String getTransport_pic() {
            return transport_pic;
        }

        public void setTransport_pic(String transport_pic) {
            this.transport_pic = transport_pic;
        }

        public String getFaith_pic() {
            return faith_pic;
        }

        public void setFaith_pic(String faith_pic) {
            this.faith_pic = faith_pic;
        }
    }
}
