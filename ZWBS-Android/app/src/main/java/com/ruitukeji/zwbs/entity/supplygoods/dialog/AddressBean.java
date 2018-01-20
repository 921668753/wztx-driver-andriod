package com.ruitukeji.zwbs.entity.supplygoods.dialog;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public class AddressBean extends BaseResult<List<AddressBean.ResultBean>> {


    public class ResultBean {
        /**
         * id : 1
         * name : 北京市
         */

        private int id;
        private String name;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
