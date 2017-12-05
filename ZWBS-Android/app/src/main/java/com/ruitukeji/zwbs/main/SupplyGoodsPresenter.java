package com.ruitukeji.zwbs.main;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.NationalCity;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.supplygoods.SetTheLineActivity;
import com.ruitukeji.zwbs.utils.GetJsonDataUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import org.json.JSONArray;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Administrator on 2017/2/21.
 */

public class SupplyGoodsPresenter implements SupplyGoodsContract.Presenter {


    private SupplyGoodsContract.View mView;

    public SupplyGoodsPresenter(SupplyGoodsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getSupplyGoods(String startPoint, String endPoint, int vehicleLength, int vehicleModel, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("pageSize", 10);
        if (!StringUtils.isEmpty(startPoint)) {
            httpParams.put("org_city", startPoint);
        }
        if (!StringUtils.isEmpty(endPoint)) {
            httpParams.put("dest_city", endPoint);
        }
        if (vehicleLength != 0) {
            httpParams.put("car_style_length_id", vehicleLength);
        }
        if (vehicleModel != 0) {
            httpParams.put("car_style_type_id", vehicleModel);
        }
        RequestClient.getGoodsList(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void isLogin(int flag) {
        RequestClient.isLogin(new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

    @Override
    public void isCertification(SweetAlertDialog sweetAlertDialog, int flag) {
        String auth_status = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "auth_status");
        if (auth_status != null && auth_status.equals("init") || auth_status != null && auth_status.equals("refuse") || auth_status != null && auth_status.equals("delete")) {
            sweetAlertDialog.setTitleText(KJActivityStack.create().topActivity().getString(R.string.notPass))
                    .setConfirmText(KJActivityStack.create().topActivity().getString(R.string.confirm))
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            mView.errorMsg("", 4);
                        }
                    }).show();
            return;
        } else if (auth_status != null && auth_status.equals("check")) {
            mView.errorMsg("", 5);
            return;
        } else {
            if (flag == 0) {
                mView.getSuccess("", 4);
            } else if (flag == 1) {
                mView.getSuccess("", 5);
            } else if (flag == 2) {
                mView.getSuccess("", 6);
            }

        }
    }

    @Override
    public void initJsonData(Handler mHandler, ArrayList<NationalCity> options1Items, ArrayList<ArrayList<String>> options2Items, ArrayList<ArrayList<ArrayList<String>>> options3Items) {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(KJActivityStack.create().topActivity(), "province1.json");//获取assets目录下的json文件数据

        ArrayList<NationalCity> jsonBean = parseData(mHandler, JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        Log.d("tag1", options3Items.size() + "=province");
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getChildren().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getChildren().get(c).getText();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getChildren().get(c).getChildren() == null
                        || jsonBean.get(i).getChildren().get(c).getChildren().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getChildren().get(c).getChildren().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getChildren().get(c).getChildren().get(d).getText();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            Log.d("tag1", options3Items.size() + "=CityList");
            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
            Log.d("tag1", options3Items.size() + "=Province_AreaList");
        }

        // mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    //弹出选择器
    @Override
    public void ShowPickerView(ArrayList<NationalCity> options1Items, ArrayList<ArrayList<String>> options2Items, ArrayList<ArrayList<ArrayList<String>>> options3Items) {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(KJActivityStack.create().topActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //返回的分别是三个级别的选中位置
                String tx = null;
                String options1Items1 = options1Items.get(options1).getPickerViewText();
                String options1Items2 = options2Items.get(options1).get(options2);
                String options1Items3 = options3Items.get(options1).get(options2).get(options3);
//                if (type == 0) {
//                    if (options1Items1.equals(options1Items2.substring(0, options1Items2.length() - 2))) {
//                        tx = options1Items2 + options1Items3;
//                    } else if (options1Items2.equals("直辖县级")) {
//                        tx = options1Items1 + options1Items3;
//                    } else {
//                        tx = options1Items1 + options1Items2 + options1Items3;
//                    }
//                } else {
                if (options1Items2.equals("中国")) {
                    tx = "";
                } else if (options1Items2.equals(options1Items3)) {
                    tx = options1Items1;
                } else if (options1Items2.equals(options1Items3.substring(1, options1Items2.length() - 1))) {
                    tx = options1Items1 + options1Items2;
                } else if (options1Items2.equals("直辖县级")) {
                    tx = options1Items1 + options1Items3;
                } else {
                    tx = options1Items1 + options1Items2 + options1Items3;
                }
            }
        })
                .setTitleText("城市选择")
//                .setDividerColor(Color.BLACK)
//                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setSubCalSize(18)//确定取消按钮大小
                .setLineSpacingMultiplier(1.5f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1, 2)  //设置默认选中项
                //       .isDialog(true)//设置为对话框模式
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        Log.d("tag", options1Items.size() + "");
        Log.d("tag", options2Items.size() + "");
        Log.d("tag", options3Items.size() + "");
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    public ArrayList<NationalCity> parseData(Handler mHandler, String result) {//Gson 解析
        ArrayList<NationalCity> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                NationalCity entity = gson.fromJson(data.optJSONObject(i).toString(), NationalCity.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //  mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


}
