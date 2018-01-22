package com.ruitukeji.zwbs.entity.mine.vehiclecertification;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.ruitukeji.zwbs.entity.BaseResult;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class VehicleBrandBean extends BaseResult<List<VehicleBrandBean.ResultBean>> {

    public class ResultBean implements IPickerViewData {
        /**
         * id : 1
         * name : 北汽
         */
        private int id;
        private String name;

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

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
