package com.ruitukeji.zwbs.entity.loginregister;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by ruitu on 2016/8/27.
 */

public class LoginBean extends BaseResult<LoginBean.ResultBean> {


    public class ResultBean {
        /**
         * userId : 79
         * real_name : 睿图
         * accessToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjpbNzksInp0YkhGNW9YSmUiXSwiZXhwIjoxNTQ1ODc2MzMwfQ.JzhFNeqFh-L1_dkKnnbc2gV0jsg0lCdttFxOY457UD0
         * refreshToken : 没有哟~
         * expireTime : 1545876330
         */

        private int userId;
        private String real_name;
        private String accessToken;
        private String refreshToken;
        private String expireTime;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }
    }
}
