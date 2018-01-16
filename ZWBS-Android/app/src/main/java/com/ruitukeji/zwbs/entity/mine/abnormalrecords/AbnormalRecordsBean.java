package com.ruitukeji.zwbs.entity.mine.abnormalrecords;

import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */

public class AbnormalRecordsBean extends BaseResult<AbnormalRecordsBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":1,"dr_id":62,"sp_name":null,"card_number":"苏A123654","car_length":"2.5米","car_type":"货车","g_type":"often","order_code":"13759842654","start_address":"江苏省苏州市昆山市花桥镇海星路5号两岸新天地","end_address":"陕西省商洛市洛南县景兴村","create_time":"2017-12-30 18:42:17","content":"http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png","place":"异常地点","abnormal_time":1514630537,"reason":"异常原因"},{"id":2,"dr_id":62,"sp_name":null,"card_number":"苏A123654","car_length":"2.5米","car_type":"货车","g_type":"often","order_code":"13759842654","start_address":"江苏省苏州市昆山市花桥镇海星路5号两岸新天地","end_address":"陕西省商洛市洛南县景兴村","create_time":"2017-12-30 18:40:56","content":"http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png","place":"异常地点","abnormal_time":1514630537,"reason":"异常原因"},{"id":3,"dr_id":62,"sp_name":null,"card_number":"苏A123654","car_length":"2.5米","car_type":"货车","g_type":"often","order_code":"13759842654","start_address":"江苏省苏州市昆山市花桥镇海星路5号两岸新天地","end_address":"陕西省商洛市洛南县景兴村","create_time":"2017-12-30 18:39:25","content":"http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png","place":"异常地点","abnormal_time":1514630537,"reason":"异常原因"}]
         * page : 1
         * pageSize : 10
         * dataTotal : 3
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
             * id : 1
             * dr_id : 62
             * sp_name : null
             * card_number : 苏A123654
             * car_length : 2.5米
             * car_type : 货车
             * g_type : often
             * goods_name=铁块
             * order_code : 13759842654
             * start_address : 江苏省苏州市昆山市花桥镇海星路5号两岸新天地
             * end_address : 陕西省商洛市洛南县景兴村
             * create_time : 2017-12-30 18:42:17
             * content : http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png
             * place : 异常地点
             * abnormal_time : 1514630537
             * reason : 异常原因
             */

            private int id;
            private int dr_id;
            private String sp_name;
            private String goods_name;
            private String card_number;
            private String car_length;
            private String car_type;
            private String g_type;
            private String order_code;
            private String start_address;
            private String end_address;
            private String create_time;
            private String content;
            private String place;
            private String abnormal_time;
            private String reason;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDr_id() {
                return dr_id;
            }

            public void setDr_id(int dr_id) {
                this.dr_id = dr_id;
            }

            public String getSp_name() {
                return sp_name;
            }

            public void setSp_name(String sp_name) {
                this.sp_name = sp_name;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getCard_number() {
                return card_number;
            }

            public void setCard_number(String card_number) {
                this.card_number = card_number;
            }

            public String getCar_length() {
                return car_length;
            }

            public void setCar_length(String car_length) {
                this.car_length = car_length;
            }

            public String getCar_type() {
                return car_type;
            }

            public void setCar_type(String car_type) {
                this.car_type = car_type;
            }

            public String getG_type() {
                return g_type;
            }

            public void setG_type(String g_type) {
                this.g_type = g_type;
            }

            public String getOrder_code() {
                return order_code;
            }

            public void setOrder_code(String order_code) {
                this.order_code = order_code;
            }

            public String getStart_address() {
                return start_address;
            }

            public void setStart_address(String start_address) {
                this.start_address = start_address;
            }

            public String getEnd_address() {
                return end_address;
            }

            public void setEnd_address(String end_address) {
                this.end_address = end_address;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public String getAbnormal_time() {
                return abnormal_time;
            }

            public void setAbnormal_time(String abnormal_time) {
                this.abnormal_time = abnormal_time;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }
    }
}
