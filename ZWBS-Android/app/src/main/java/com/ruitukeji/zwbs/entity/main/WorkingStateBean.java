package com.ruitukeji.zwbs.entity.main;


import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Admin on 2017/7/25.
 */

public class WorkingStateBean extends BaseResult<WorkingStateBean.ResultBean> {


    /**
     * result : {"online":0,"map_code":"31","auth_status":"pass"}
     */

    public class ResultBean {
        /**
         * online : 0
         * map_code : 31
         * auth_status : pass
         */

        private int online;
        private String map_code;
        private String auth_status;

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getMap_code() {
            return map_code;
        }

        public void setMap_code(String map_code) {
            this.map_code = map_code;
        }

        public String getAuth_status() {
            return auth_status;
        }

        public void setAuth_status(String auth_status) {
            this.auth_status = auth_status;
        }
    }
}
