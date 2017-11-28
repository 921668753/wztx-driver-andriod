package com.ruitukeji.zwbs.entity;

import java.util.List;

/**
 * Created by Admin on 2017/7/12.
 */

public class GetOrderBean extends BaseResult<GetOrderBean.ResultBean> {


    public class ResultBean {
        /**
         * list : [{"id":27,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1501035282,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.7335,31.254103","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"太快了","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"太快了","dest_phone":"17051445852","dest_telphone":"","goods_name":"兔兔","volume":"60","weight":"18","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"160466.00","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1501035283,"update_at":1501035283,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-26 10:14:42","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"841.33","org_longitude":"120.7335","org_latitude":"31.254103","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":26,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1501029049,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.733505,31.254093","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"金龙","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"他","dest_phone":"17051335587","dest_telphone":"","goods_name":"金","volume":"50","weight":"60","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"4.2米","car_style_length_id":11,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"964346.58","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1501029050,"update_at":1501029050,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-26 08:30:49","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"846.898","org_longitude":"120.733505","org_latitude":"31.254093","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":25,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500979696,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.733505,31.254093","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"金龙","dest_address_maps":"113.77855,34.759108","dest_city":"河南省郑州市金水区","dest_address_name":"河南省郑州市金水区郑州东站","dest_address_detail":"金龙","dest_phone":"17051384585","dest_telphone":"","goods_name":"木","volume":"60","weight":"90","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"768713.72","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500979696,"update_at":1500979696,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:48:16","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"875.341","org_longitude":"120.733505","org_latitude":"31.254093","dest_longitude":"113.77855","dest_latitude":"34.759108","sp_price":"0.00"},{"id":24,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500978983,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.7335,31.254098","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"金龙","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"途","dest_phone":"17052572653","dest_telphone":"","goods_name":"天","volume":"1000","weight":"90","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"4.2米","car_style_length_id":11,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"1446404.03","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500978983,"update_at":1500978983,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:36:23","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"849.063","org_longitude":"120.7335","org_latitude":"31.254098","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":23,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500978927,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.7335,31.254098","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"天空","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"途","dest_phone":"17055274585","dest_telphone":"","goods_name":"木","volume":"100","weight":"90","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"744537.96","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500978928,"update_at":1500978928,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:35:27","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"849.063","org_longitude":"120.7335","org_latitude":"31.254098","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":22,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500978884,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.7335,31.254098","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"天空","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"途","dest_phone":"17055274585","dest_telphone":"","goods_name":"木","volume":"100","weight":"90","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"744537.96","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500978885,"update_at":1500978885,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:34:44","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"849.063","org_longitude":"120.7335","org_latitude":"31.254098","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":21,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500978871,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.7335,31.254098","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"天空","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"途","dest_phone":"17055274585","dest_telphone":"","goods_name":"木","volume":"100","weight":"90","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"744537.96","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500978872,"update_at":1500978872,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:34:31","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"849.063","org_longitude":"120.7335","org_latitude":"31.254098","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":20,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500978639,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.733506,31.254095","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"啦啦啦","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"金龙","dest_phone":"17051352255","dest_telphone":"","goods_name":"凳子","volume":"100","weight":"60","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"500298.82","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500978640,"update_at":1500978640,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:30:39","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"846.611","org_longitude":"120.733506","org_latitude":"31.254095","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":19,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500978285,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.733506,31.254095","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"啦啦啦","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"金龙","dest_phone":"17051352255","dest_telphone":"","goods_name":"凳子","volume":"100","weight":"60","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"500298.82","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500978521,"update_at":1500978521,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:24:45","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"846.611","org_longitude":"120.733506","org_latitude":"31.254095","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":18,"sp_id":20,"dr_id":null,"type":"often","appoint_at":1500978250,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.733506,31.254095","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"啦啦啦","dest_address_maps":"113.854823,34.526056","dest_city":"河南省郑州市新郑市","dest_address_name":"河南省郑州市新郑市郑州新郑国际机场","dest_address_detail":"金龙","dest_phone":"17051352255","dest_telphone":"","goods_name":"凳子","volume":"100","weight":"60","car_style_type":"厢式","car_style_type_id":3,"car_style_length":"5.2米","car_style_length_id":13,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"500298.82","final_price":"0.00","payway":0,"is_pay":0,"remark":"","tran_type":0,"create_at":1500978252,"update_at":1500978252,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-25 18:24:10","policy_code":null,"per_remark":null,"real_name":"罢了","company_name":"","customer_type":"person","kilometres":"846.611","org_longitude":"120.733506","org_latitude":"31.254095","dest_longitude":"113.854823","dest_latitude":"34.526056","sp_price":"0.00"},{"id":17,"sp_id":34,"dr_id":null,"type":"often","appoint_at":1500082022,"insured_amount":0,"premium_amount":0,"org_address_id":null,"org_send_name":"罢了","org_address_maps":"120.733439,31.254002","org_city":"江苏省苏州市吴中区","org_address_name":"江苏省苏州市吴中区腾飞苏州创新园","org_address_detail":"","org_phone":"17051335257","org_telphone":"","dest_address_id":null,"dest_receive_name":"他","dest_address_maps":"113.658075,34.745793","dest_city":"河南省郑州市二七区","dest_address_name":"河南省郑州市二七区郑州站","dest_address_detail":"啦啦啦","dest_phone":"13462634586","dest_telphone":"","goods_name":"木","volume":"12.222","weight":"90","car_style_type":"平板","car_style_type_id":1,"car_style_length":"4.2米","car_style_length_id":11,"effective_time":0,"is_receipt":1,"mind_price":"0.00","system_price":"1484267.42","final_price":"0.00","payway":0,"is_pay":0,"remark":"记得安排服务好一点的司机","tran_type":0,"create_at":1500978234,"update_at":1500978234,"status":"quote","is_beyond_quota":0,"usecar_time":"2017-07-12 09:25:32","policy_code":null,"per_remark":null,"real_name":"秋大大实业公司","company_name":"","customer_type":"company","kilometres":"869.982","org_longitude":"120.733439","org_latitude":"31.254002","dest_longitude":"113.658075","dest_latitude":"34.745793","sp_price":"0.00"}]
         * page : 1
         * pageSize : 20
         * dataTotal : 11
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
             * id : 27
             * sp_id : 20
             * dr_id : null
             * type : often
             * appoint_at : 1501035282
             * insured_amount : 0
             * premium_amount : 0
             * org_address_id : null
             * org_send_name : 罢了
             * org_address_maps : 120.7335,31.254103
             * org_city : 江苏省苏州市吴中区
             * org_address_name : 江苏省苏州市吴中区腾飞苏州创新园
             * org_address_detail :
             * org_phone : 17051335257
             * org_telphone :
             * dest_address_id : null
             * dest_receive_name : 太快了
             * dest_address_maps : 113.854823,34.526056
             * dest_city : 河南省郑州市新郑市
             * dest_address_name : 河南省郑州市新郑市郑州新郑国际机场
             * dest_address_detail : 太快了
             * dest_phone : 17051445852
             * dest_telphone :
             * goods_name : 兔兔
             * volume : 60
             * weight : 18
             * car_style_type : 厢式
             * car_style_type_id : 3
             * car_style_length : 5.2米
             * car_style_length_id : 13
             * effective_time : 0
             * is_receipt : 1
             * mind_price : 0.00
             * system_price : 160466.00
             * final_price : 0.00
             * payway : 0
             * is_pay : 0
             * remark :
             * tran_type : 0
             * create_at : 1501035283
             * update_at : 1501035283
             * status : quote
             * is_beyond_quota : 0
             * usecar_time : 2017-07-26 10:14:42
             * policy_code : null
             * per_remark : null
             * real_name : 罢了
             * company_name :
             * customer_type : person
             * kilometres : 841.33
             * org_longitude : 120.7335
             * org_latitude : 31.254103
             * dest_longitude : 113.854823
             * dest_latitude : 34.526056
             * sp_price : 0.00
             */

            private int id;
            private String type;
            private String org_send_name;
            private String org_city;
            private String org_address_detail;
            private String org_phone;
            private String org_telphone;
            private String dest_receive_name;
            private String dest_address_maps;
            private String dest_city;
            private String dest_address_detail;
            private String dest_phone;
            private String goods_name;
            private String volume;
            private String weight;
            private String car_style_type;
            private int car_style_type_id;
            private String car_style_length;
            private int car_style_length_id;
            private int is_receipt;
            private String mind_price;
            private String system_price;
            private String final_price;
            private String status;
            private String usecar_time;
            private String real_name;
            private String org_latitude;
            private String sp_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getOrg_send_name() {
                return org_send_name;
            }

            public void setOrg_send_name(String org_send_name) {
                this.org_send_name = org_send_name;
            }

            public String getOrg_city() {
                return org_city;
            }

            public void setOrg_city(String org_city) {
                this.org_city = org_city;
            }

            public String getOrg_address_detail() {
                return org_address_detail;
            }

            public void setOrg_address_detail(String org_address_detail) {
                this.org_address_detail = org_address_detail;
            }

            public String getOrg_phone() {
                return org_phone;
            }

            public void setOrg_phone(String org_phone) {
                this.org_phone = org_phone;
            }

            public String getOrg_telphone() {
                return org_telphone;
            }

            public void setOrg_telphone(String org_telphone) {
                this.org_telphone = org_telphone;
            }

            public String getDest_receive_name() {
                return dest_receive_name;
            }

            public void setDest_receive_name(String dest_receive_name) {
                this.dest_receive_name = dest_receive_name;
            }

            public String getDest_address_maps() {
                return dest_address_maps;
            }

            public void setDest_address_maps(String dest_address_maps) {
                this.dest_address_maps = dest_address_maps;
            }

            public String getDest_city() {
                return dest_city;
            }

            public void setDest_city(String dest_city) {
                this.dest_city = dest_city;
            }

            public String getDest_address_detail() {
                return dest_address_detail;
            }

            public void setDest_address_detail(String dest_address_detail) {
                this.dest_address_detail = dest_address_detail;
            }

            public String getDest_phone() {
                return dest_phone;
            }

            public void setDest_phone(String dest_phone) {
                this.dest_phone = dest_phone;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getCar_style_type() {
                return car_style_type;
            }

            public void setCar_style_type(String car_style_type) {
                this.car_style_type = car_style_type;
            }

            public int getCar_style_type_id() {
                return car_style_type_id;
            }

            public void setCar_style_type_id(int car_style_type_id) {
                this.car_style_type_id = car_style_type_id;
            }

            public String getCar_style_length() {
                return car_style_length;
            }

            public void setCar_style_length(String car_style_length) {
                this.car_style_length = car_style_length;
            }

            public int getCar_style_length_id() {
                return car_style_length_id;
            }

            public void setCar_style_length_id(int car_style_length_id) {
                this.car_style_length_id = car_style_length_id;
            }

            public int getIs_receipt() {
                return is_receipt;
            }

            public void setIs_receipt(int is_receipt) {
                this.is_receipt = is_receipt;
            }

            public String getMind_price() {
                return mind_price;
            }

            public void setMind_price(String mind_price) {
                this.mind_price = mind_price;
            }

            public String getSystem_price() {
                return system_price;
            }

            public void setSystem_price(String system_price) {
                this.system_price = system_price;
            }

            public String getFinal_price() {
                return final_price;
            }

            public void setFinal_price(String final_price) {
                this.final_price = final_price;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUsecar_time() {
                return usecar_time;
            }

            public void setUsecar_time(String usecar_time) {
                this.usecar_time = usecar_time;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getOrg_latitude() {
                return org_latitude;
            }

            public void setOrg_latitude(String org_latitude) {
                this.org_latitude = org_latitude;
            }

            public String getSp_price() {
                return sp_price;
            }

            public void setSp_price(String sp_price) {
                this.sp_price = sp_price;
            }
        }
    }
}
