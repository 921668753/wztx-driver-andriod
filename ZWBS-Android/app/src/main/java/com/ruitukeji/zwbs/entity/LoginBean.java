package com.ruitukeji.zwbs.entity;

/**
 * Created by ruitu on 2016/8/27.
 */

public class LoginBean extends BaseResult<LoginBean.ResultBean> {


    /**
     * result : {"accessToken":"61581728f56628bfd565a6bb8f42d0b5","refreshToken":"a9de48465b52f16bb976343a75665ce9","expireTime":7200}
     */

    public class ResultBean {
        /**
         * accessToken : 61581728f56628bfd565a6bb8f42d0b5
         * refreshToken : a9de48465b52f16bb976343a75665ce9
         * expireTime : 7200
         */

        private String accessToken;
        private String refreshToken;
        private String userId;
        private int expireTime;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public int getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(int expireTime) {
            this.expireTime = expireTime;
        }
    }
}
