package com.ruitukeji.zwbs.entity.mission;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class TaskBean extends BaseResult<TaskBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":417,"real_name":"周三","type":"often","appoint_at":1516585881,"order_code":"2018012257995199","goods_name":"五金","final_price":"414.75","org_address_detail":"刚刚","org_address_name":"江苏省苏州市昆山市玉山镇前进西路292号(近中山路口)刚刚好","dest_address_detail":"扣扣麻将屋","dest_address_name":"江苏省苏州市姑苏区西环路与西环路高架出口交叉口西100米扣扣","org_address_maps":"121.062544,31.320609","dest_address_maps":"120.574731,31.30765","phone":"15651720805","is_assigned":0,"status":"distribute","appoint_at_str":"2018-01-22 09:51:21","is_cargo_receipt":0,"arr_time":"","send_time":"2018-01-24 16:01:01","arr_org_time":""},{"id":415,"real_name":"周三","type":"often","appoint_at":1516585859,"order_code":"2018012251565457","goods_name":"五金","final_price":"414.75","org_address_detail":"刚刚","org_address_name":"江苏省苏州市昆山市玉山镇前进西路292号(近中山路口)刚刚好","dest_address_detail":"扣扣麻将屋","dest_address_name":"江苏省苏州市姑苏区西环路与西环路高架出口交叉口西100米扣扣","org_address_maps":"120.955215,31.382213","dest_address_maps":"120.574731,31.30765","phone":"15651720805","is_assigned":1,"status":"quoted","appoint_at_str":"2018-01-22 09:50:59","is_cargo_receipt":0,"arr_time":"","send_time":"","arr_org_time":""}]
         * page : 1
         * pageSize : 5
         * dataTotal : 2
         * pageTotal : 1
         */

        private int page;
        private int pageSize;
        private int dataTotal;
        private int pageTotal;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean {
            /**
             * id : 417
             * real_name : 周三
             * type : often
             * appoint_at : 1516585881
             * order_code : 2018012257995199
             * goods_name : 五金
             * final_price : 414.75
             * org_address_detail : 刚刚
             * org_address_name : 江苏省苏州市昆山市玉山镇前进西路292号(近中山路口)刚刚好
             * dest_address_detail : 扣扣麻将屋
             * dest_address_name : 江苏省苏州市姑苏区西环路与西环路高架出口交叉口西100米扣扣
             * org_address_maps : 121.062544,31.320609
             * dest_address_maps : 120.574731,31.30765
             * phone : 15651720805
             * is_assigned : 0
             * status : distribute
             * appoint_at_str : 2018-01-22 09:51:21
             * is_cargo_receipt : 0
             * arr_time :
             * send_time : 2018-01-24 16:01:01
             * arr_org_time :
             */

            private int id;
            private String real_name;
            private String type;
            private int appoint_at;
            private String order_code;
            private String goods_name;
            private String final_price;
            private String org_address_detail;
            private String org_address_name;
            private String dest_address_detail;
            private String dest_address_name;
            private String org_address_maps;
            private String dest_address_maps;
            private String phone;
            private int is_assigned;
            private int is_cancel;
            private String status;
            private String appoint_at_str;
            private int is_cargo_receipt;
            private String arr_time;
            private String send_time;
            private int arr_org_time;
            private String arr_org_time_str;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getAppoint_at() {
                return appoint_at;
            }

            public void setAppoint_at(int appoint_at) {
                this.appoint_at = appoint_at;
            }

            public String getOrder_code() {
                return order_code;
            }

            public void setOrder_code(String order_code) {
                this.order_code = order_code;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getFinal_price() {
                return final_price;
            }

            public void setFinal_price(String final_price) {
                this.final_price = final_price;
            }

            public String getOrg_address_detail() {
                return org_address_detail;
            }

            public void setOrg_address_detail(String org_address_detail) {
                this.org_address_detail = org_address_detail;
            }

            public String getOrg_address_name() {
                return org_address_name;
            }

            public void setOrg_address_name(String org_address_name) {
                this.org_address_name = org_address_name;
            }

            public String getDest_address_detail() {
                return dest_address_detail;
            }

            public void setDest_address_detail(String dest_address_detail) {
                this.dest_address_detail = dest_address_detail;
            }

            public String getDest_address_name() {
                return dest_address_name;
            }

            public void setDest_address_name(String dest_address_name) {
                this.dest_address_name = dest_address_name;
            }

            public String getOrg_address_maps() {
                return org_address_maps;
            }

            public void setOrg_address_maps(String org_address_maps) {
                this.org_address_maps = org_address_maps;
            }

            public String getDest_address_maps() {
                return dest_address_maps;
            }

            public void setDest_address_maps(String dest_address_maps) {
                this.dest_address_maps = dest_address_maps;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getIs_assigned() {
                return is_assigned;
            }

            public void setIs_assigned(int is_assigned) {
                this.is_assigned = is_assigned;
            }

            public int getIs_cancel() {
                return is_cancel;
            }

            public void setIs_cancel(int is_cancel) {
                this.is_cancel = is_cancel;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAppoint_at_str() {
                return appoint_at_str;
            }

            public void setAppoint_at_str(String appoint_at_str) {
                this.appoint_at_str = appoint_at_str;
            }

            public int getIs_cargo_receipt() {
                return is_cargo_receipt;
            }

            public void setIs_cargo_receipt(int is_cargo_receipt) {
                this.is_cargo_receipt = is_cargo_receipt;
            }

            public String getArr_time() {
                return arr_time;
            }

            public void setArr_time(String arr_time) {
                this.arr_time = arr_time;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public int getArr_org_time() {
                return arr_org_time;
            }

            public void setArr_org_time(int arr_org_time) {
                this.arr_org_time = arr_org_time;
            }

            public String getArr_org_time_str() {
                return arr_org_time_str;
            }

            public void setArr_org_time_str(String arr_org_time_str) {
                this.arr_org_time_str = arr_org_time_str;
            }
        }
    }
}
