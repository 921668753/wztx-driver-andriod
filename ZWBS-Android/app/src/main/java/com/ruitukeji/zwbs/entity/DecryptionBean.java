package com.ruitukeji.zwbs.entity;

/**
 * Created by ruitu on 2016/11/17.
 */

public class DecryptionBean extends BaseResult<DecryptionBean.ResultBean> {

    /**
     * result : {"name":"8a4Kde14xn/D5NsxZ5mvQQ=="}
     * err_code : 2000
     * err_msg : ok
     */

    public class ResultBean {
        /**
         * name : 8a4Kde14xn/D5NsxZ5mvQQ==
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
