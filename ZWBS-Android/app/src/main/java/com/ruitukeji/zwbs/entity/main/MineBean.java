package com.ruitukeji.zwbs.entity.main;

import com.ruitukeji.zwbs.entity.BaseResult;

/**
 * Created by Admin on 2017/7/12.
 */

public class MineBean extends BaseResult<MineBean.ResultBean> {


    public class ResultBean {
        /**
         * id : 62
         * phone : 17051335257
         * online : 0
         * auth_expire_at : null
         * map_code : null
         * sex : 0
         * avatar : http://q.qlogo.cn/qqapp/1106317358/F43E99AD4E8A6613294BD7BD376CBAFD/100
         * real_name : null
         * auth_status : pass
         * car_auth_status : init
         * bond_status : init
         * type : 0
         * recomm_code : 9XiRFd
         * car_id : null
         * is_black : 0
         * card_number : null
         * all_total_order : 0
         * dr_level : 4.9
         * server_level : 4
         * all_complaints_num : 2
         * is_pay_password : 1
         */

        private int id;
        private String phone;
        private int online;
        private String auth_expire_at;
        private String map_code;
        private int sex;
        private String avatar;
        private String real_name;
        private String auth_status;
        private String car_auth_status;
        private String bond_status;
        private int type;
        private String recomm_code;
        private String car_id;
        private int is_black;
        private String card_number;
        private String all_total_order;
        private String dr_level;
        private String server_level;
        private String all_complaints_num;
        private String balance;
        private int is_pay_password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getAuth_expire_at() {
            return auth_expire_at;
        }

        public void setAuth_expire_at(String auth_expire_at) {
            this.auth_expire_at = auth_expire_at;
        }

        public String getMap_code() {
            return map_code;
        }

        public void setMap_code(String map_code) {
            this.map_code = map_code;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getAuth_status() {
            return auth_status;
        }

        public void setAuth_status(String auth_status) {
            this.auth_status = auth_status;
        }

        public String getCar_auth_status() {
            return car_auth_status;
        }

        public void setCar_auth_status(String car_auth_status) {
            this.car_auth_status = car_auth_status;
        }

        public String getBond_status() {
            return bond_status;
        }

        public void setBond_status(String bond_status) {
            this.bond_status = bond_status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRecomm_code() {
            return recomm_code;
        }

        public void setRecomm_code(String recomm_code) {
            this.recomm_code = recomm_code;
        }

        public String getCar_id() {
            return car_id;
        }

        public void setCar_id(String car_id) {
            this.car_id = car_id;
        }

        public int getIs_black() {
            return is_black;
        }

        public void setIs_black(int is_black) {
            this.is_black = is_black;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getAll_total_order() {
            return all_total_order;
        }

        public void setAll_total_order(String all_total_order) {
            this.all_total_order = all_total_order;
        }

        public String getDr_level() {
            return dr_level;
        }

        public void setDr_level(String dr_level) {
            this.dr_level = dr_level;
        }

        public String getServer_level() {
            return server_level;
        }

        public void setServer_level(String server_level) {
            this.server_level = server_level;
        }

        public String getAll_complaints_num() {
            return all_complaints_num;
        }

        public void setAll_complaints_num(String all_complaints_num) {
            this.all_complaints_num = all_complaints_num;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public int getIs_pay_password() {
            return is_pay_password;
        }

        public void setIs_pay_password(int is_pay_password) {
            this.is_pay_password = is_pay_password;
        }
    }
}
