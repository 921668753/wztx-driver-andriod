package com.ruitukeji.zwbs.getorder.selectioncity;

/**
 * 国内  id 3426
 * Created by Admin on 2017/9/5.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.app.Activity.RESULT_OK;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.selectioncity.CommonAdapter;
import com.ruitukeji.zwbs.adapter.getorder.selectioncity.HeaderRecyclerAndFooterWrapperAdapter;
import com.ruitukeji.zwbs.adapter.getorder.selectioncity.InlandViewAdapter;
import com.ruitukeji.zwbs.adapter.getorder.selectioncity.OnItemClickListener;
import com.ruitukeji.zwbs.adapter.getorder.selectioncity.ViewHolder;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.BaseResult;
import com.ruitukeji.zwbs.entity.selectioncity.InlandBean;
import com.ruitukeji.zwbs.entity.selectioncity.InlandBean.ResultBean;
import com.ruitukeji.zwbs.entity.selectioncity.InlandHotCityBean;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.decoration.DividerItemDecoration;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.utils.myview.IndexNewBar;

/**
 * 国内  id 3426
 * Created by Admin on 2017/9/5.
 */

public class InlandFragment extends BaseFragment implements InlandContract.View, OnItemClickListener {

    @BindView(id = R.id.rv)
    private RecyclerView mRv;

    @BindView(id = R.id.indexBar)
    private IndexNewBar mIndexBar;
    private InlandViewAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;

    private SuspensionDecoration mDecoration;


    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<InlandHotCityBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<ResultBean> mBodyDatas;

    private DividerItemDecoration dividerItemDecoration;


    private SelectionCityActivity aty;

    private List<InlandHotCityBean.ResultBean> historyCityList;
    private String inlandHistory;

    public TextView textView;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (SelectionCityActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_inland, null);
    }


    @Override
    protected void initData() {
        super.initData();
        showLoadingDialog(aty.getString(R.string.dataLoad));
        inlandHistory = PreferenceHelper.readString(aty, StringConstants.FILENAME, "inlandHistory", null);
        historyCityList = new ArrayList<>();
        mSourceDatas = new ArrayList<>();
        mBodyDatas = new ArrayList<ResultBean>();
        mHeaderDatas = new ArrayList<InlandHotCityBean>();
        mPresenter = new InlandPresenter(this);
        mManager = new LinearLayoutManager(aty);
        mRv.setLayoutManager(mManager);
        if (!StringUtils.isEmpty(inlandHistory)) {
            List<InlandHotCityBean.ResultBean> recentCitys = new ArrayList<>();
            InlandHotCityBean recentHistory = new InlandHotCityBean(recentCitys, getString(R.string.recentVisitCity), getString(R.string.recent));
            mHeaderDatas.add(recentHistory);
        }
        List<InlandHotCityBean.ResultBean> hotCitys = new ArrayList<>();
        InlandHotCityBean hotCity = new InlandHotCityBean(hotCitys, getString(R.string.hotCity), getString(R.string.hot));
        mHeaderDatas.add(hotCity);
        mSourceDatas.addAll(mHeaderDatas);
        mAdapter = new InlandViewAdapter(aty, R.layout.item_selectcountry, mBodyDatas);
        mAdapter.setOnItemClickListener(this);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.inland_item_header:
                        final InlandHotCityBean meituanHeaderBean = (InlandHotCityBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<InlandHotCityBean.ResultBean>(aty, R.layout.inland_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final InlandHotCityBean.ResultBean cityName) {
                                        holder.setText(R.id.tvName, cityName.getName());
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //           PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", cityName.getName());
                                                saveHistory(cityName);
                                                Intent intent = new Intent();
                                                // 获取内容
                                                intent.putExtra("selectCity", cityName.getName());
                                                intent.putExtra("selectCityId", cityName.getId());
                                                intent.putExtra("selectCountry", getString(R.string.china));
                                                intent.putExtra("selectCountryId", cityName.getCountry_id());
                                                // 设置结果 结果码，一个数据
                                                aty.setResult(RESULT_OK, intent);
                                                // 结束该activity 结束之后，前面的activity才可以处理结果
                                                aty.finish();
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(aty, 3));
                        break;
                    case R.layout.inland_item_header_top:
                        String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "locationCity", getString(R.string.locateFailure));
                        textView = holder.getView(R.id.tvCurrent);
                        textView.setText(locationCity);
                        LinearLayout ll_localize = holder.getView(R.id.ll_localize);
                        ll_localize.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //      PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", textView.getText().toString());
                                Intent intent = new Intent();
                                // 获取内容
                                intent.putExtra("selectCity", textView.getText().toString());
                                intent.putExtra("selectCityId", 1);
                                intent.putExtra("selectCountry", "");
                                intent.putExtra("selectCountryId", 1);
                                // 设置结果 结果码，一个数据
                                aty.setResult(RESULT_OK, intent);
                                // 结束该activity 结束之后，前面的activity才可以处理结果
                                aty.finish();
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        };
//        readHistory();
        mDecoration = new SuspensionDecoration(aty, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(aty.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
        dividerItemDecoration = new DividerItemDecoration(aty, DividerItemDecoration.VERTICAL_LIST);
        ((InlandContract.Presenter) mPresenter).getAllCity();
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        dividerItemDecoration.setOrientation(0);
        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration);
        mRv.addItemDecoration(dividerItemDecoration);
        mIndexBar.setmPressedShowTextView(null)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
    }


    /**
     * 保存历史
     */
    private void saveHistory(InlandHotCityBean.ResultBean resultBean) {
        if (historyCityList.size() > 0) {
            for (int i = 0; i < historyCityList.size(); i++) {
                if (historyCityList.get(i).getName().equals(resultBean.getName())) {
                    historyCityList.remove(i);
                }
            }
        }
        historyCityList.add(resultBean);
        if (historyCityList.size() > 3) {
            historyCityList.remove(0);
        }
        BaseResult<List<InlandHotCityBean.ResultBean>> baseResult = new BaseResult<>();
        baseResult.setMsg("成功");
        baseResult.setCode(1);
        Collections.reverse(historyCityList);
        baseResult.setResult(historyCityList);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "inlandHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
    }

    @Override
    public void setPresenter(InlandContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            InlandBean inlandBean = (InlandBean) JsonUtil.json2Obj(success, InlandBean.class);
            //   inlandBean.getResult()
            if (!(inlandBean.getResult() != null && inlandBean.getResult().size() > 0)) {
                errorMsg(aty.getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            //模拟线上加载数据
            mBodyDatas.clear();
            mBodyDatas.addAll(inlandBean.getResult());
            ((InlandContract.Presenter) mPresenter).getChildHotCity();
        } else if (flag == 1) {
            InlandHotCityBean inlandHotCityBean = (InlandHotCityBean) JsonUtil.json2Obj(success, InlandHotCityBean.class);
            initDatas(mBodyDatas, inlandHotCityBean.getCityList());
            dismissLoadingDialog();
        }
    }

    /**
     * 组织数据源
     *
     * @return
     */
    private void initDatas(List<ResultBean> list, List<InlandHotCityBean.ResultBean> hotCitys) {
        //先排序
        mIndexBar.getDataHelper().sortSourceDatas(list);
        mAdapter.setDatas(list);
        mHeaderAdapter.notifyDataSetChanged();
        mSourceDatas.addAll(list);
        mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                .invalidate();
        mDecoration.setmDatas(mSourceDatas);
        String inlandHistory = PreferenceHelper.readString(aty, StringConstants.FILENAME, "inlandHistory", null);
        if (StringUtils.isEmpty(inlandHistory)) {
            InlandHotCityBean header3 = mHeaderDatas.get(0);
            header3.setCityList(hotCitys);
            mHeaderAdapter.notifyItemRangeChanged(0, 1);
            return;
        }
        InlandHotCityBean header2 = mHeaderDatas.get(0);
        header2.setCityList(historyCityList);
        InlandHotCityBean header3 = mHeaderDatas.get(1);
        header3.setCityList(hotCitys);
        mHeaderAdapter.notifyItemRangeChanged(0, 2);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter = null;
        mHeaderAdapter.clearFooterView();
        mHeaderAdapter.clearHeaderView();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        ResultBean resultBean = mBodyDatas.get(position);
        InlandHotCityBean.ResultBean resultBean1 = new InlandHotCityBean.ResultBean();
        resultBean1.setId(resultBean.getId());
        resultBean1.setName(resultBean.getName());
        resultBean1.setLevel(resultBean.getLevel());
        resultBean1.setParent_id(resultBean.getParent_id());
        resultBean1.setCountry_id(resultBean.getCountry_id());
        saveHistory(resultBean1);
        //PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", resultBean.getName());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCountry", getString(R.string.china));
        Intent intent = new Intent();
        // 获取内容
        intent.putExtra("selectCity", resultBean.getName());
        intent.putExtra("selectCityId", resultBean.getId());
        intent.putExtra("selectCountry", getString(R.string.china));
        intent.putExtra("selectCountryId", resultBean.getCountry_id());
        // 设置结果 结果码，一个数据
        aty.setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

}
