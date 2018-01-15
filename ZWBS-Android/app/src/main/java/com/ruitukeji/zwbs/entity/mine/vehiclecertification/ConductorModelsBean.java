package com.ruitukeji.zwbs.entity.mine.vehiclecertification;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Admin on 2017/7/13.
 */

public class ConductorModelsBean extends BaseResult<ConductorModelsBean.ResultBean> {

    public static class ResultBean {
        private List<TypeBean> type;
        private List<LengthBean> length;

        public List<TypeBean> getType() {
            return type;
        }

        public void setType(List<TypeBean> type) {
            this.type = type;
        }

        public List<LengthBean> getLength() {
            return length;
        }

        public void setLength(List<LengthBean> length) {
            this.length = length;
        }

        public static class TypeBean {
            /**
             * id : 1
             * name : 平板
             * remark : null
             * type : 1
             * status : 0
             * over_metres_price : null
             * weight_price : null
             * init_kilometres : null
             * init_price : null
             * create_at : 0
             * update_at : 0
             */

            private int id;
            private String name;

            private int type;
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


            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

        }

        public static class LengthBean {
            /**
             * id : 11
             * name : 4.2米
             * remark : null
             * type : 2
             * status : 0
             * over_metres_price : 10
             * weight_price : 20
             * init_kilometres : 50
             * init_price : 100
             * create_at : 0
             * update_at : 0
             */

            private int id;
            private String name;
            private int type;
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


            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }


        }
    }
}
