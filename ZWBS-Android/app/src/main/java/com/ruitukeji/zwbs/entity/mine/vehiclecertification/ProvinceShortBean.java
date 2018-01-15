package com.ruitukeji.zwbs.entity.mine.vehiclecertification;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class ProvinceShortBean extends BaseResult<List<ProvinceShortBean.ResultBean>> {


    /**
     * code : 2000
     * msg : 成功
     * result : [{"id":11348,"name":"苏州市"},{"id":310100,"name":"上海市"},{"id":10960,"name":"无锡市"},{"id":10809,"name":"南京市"},{"id":11245,"name":"常州市"},{"id":11482,"name":"南通市"},{"id":12597,"name":"杭州市"},{"id":11947,"name":"盐城市"},{"id":13280,"name":"嘉兴市"}]
     */

    public class ResultBean implements IPickerViewData {
        /**
         * id : 11348
         * name : 苏州市
         */

        private int id;
        private String name;
        private String provinceName;

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

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
