package com.ruitukeji.zwbs.entity.getorder.dialog;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class AvailableTypeBean extends BaseResult<List<AvailableTypeBean.ResultBean>> {


    public class ResultBean {
        /**
         * id : 1
         * name : often
         * value : 实时
         */

        private int id;
        private String name;
        private String value;
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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
