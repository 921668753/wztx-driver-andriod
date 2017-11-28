package com.ruitukeji.zwbs.entity;

/**
 * 添加路线
 * Created by Administrator on 2017/2/21.
 */

public class AddTheLineBean extends BaseResult<AddTheLineBean.ResultBean> {


    public class ResultBean {
        /**
         * drline_id : 6
         */

        private String drline_id;

        public String getDrline_id() {
            return drline_id;
        }

        public void setDrline_id(String drline_id) {
            this.drline_id = drline_id;
        }
    }
}
