package com.ruitukeji.zwbs.getorder.selectioncity.addresssearch;


import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.entity.getorder.selectioncity.InlandBean.ResultBean;

import java.util.List;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface AddressSearchContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取全国城市
         */
        void getAllCity();

        /**
         * 获取搜索城市
         */
        List<ResultBean> getSearchCity(List<ResultBean> cityList, String name);

    }

    interface View extends BaseNewView<Presenter, String> {
    }

}


