package com.ruitukeji.zwbs.mine.abnormalrecords;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.mine.abnormalrecords.AbnormalSituationViewAdapter;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.ImagePreviewNoDelActivity;
import com.ruitukeji.zwbs.entity.mine.abnormalrecords.AbnormalSituationBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.myview.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常情况
 * Created by Administrator on 2017/12/1.
 */

public class AbnormalSituationActivity extends BaseActivity implements AbnormalSituationContract.View, AdapterView.OnItemClickListener {

    private int id = 0;

    /**
     * 姓名
     */
    @BindView(id = R.id.tv_name)
    private TextView tv_name;


    /**
     * 标签
     */
    @BindView(id = R.id.img_label)
    private ImageView img_label;


    /**
     * 时间
     */
    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    /**
     * 货物名称
     */
    @BindView(id = R.id.tv_goodName)
    private TextView tv_goodName;

    /**
     * 快递单号
     */
    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    /**
     * 起运地
     */
    @BindView(id = R.id.tv_thePlace)
    private TextView tv_thePlace;

    /**
     * 目的地
     */
    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    /**
     * 异常图片
     */
    @BindView(id = R.id.gv_abnormalSituation)
    private NoScrollGridView gv_abnormalSituation;

    private AbnormalSituationViewAdapter mAdapter = null;
    /**
     * 异常地点
     */
    @BindView(id = R.id.tv_abnormalLocation)
    private TextView tv_abnormalLocation;

    /**
     * 异常时间
     */
    @BindView(id = R.id.tv_abnormalTime)
    private TextView tv_abnormalTime;

    /**
     * 异常原因
     */
    @BindView(id = R.id.tv_abnormalReason)
    private TextView tv_abnormalReason;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_abnormalsituation);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new AbnormalSituationPresenter(this);
        mAdapter = new AbnormalSituationViewAdapter(this);
        id = getIntent().getIntExtra("id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AbnormalSituationContract.Presenter) mPresenter).getAbnormalSituation(id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.abnormalSituation), true, R.id.titlebar);
        gv_abnormalSituation.setAdapter(mAdapter);
        gv_abnormalSituation.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toImagePreviewNoDelActivity((ArrayList<ImageItem>) mAdapter.getData(), position);
    }

    //打开预览
    @SuppressWarnings("unchecked")
    private void toImagePreviewNoDelActivity(ArrayList<ImageItem> list, int position) {
        Intent intentPreview = new Intent(this, ImagePreviewNoDelActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, list);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        showActivity(aty, intentPreview);
    }

    @Override
    public void setPresenter(AbnormalSituationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s) {
        AbnormalSituationBean abnormalSituationBean = (AbnormalSituationBean) JsonUtil.json2Obj(s, AbnormalSituationBean.class);
        Log.d("tag1111",abnormalSituationBean.getResult().getSp_name());
        tv_name.setText(abnormalSituationBean.getResult().getSp_name());
        if (StringUtils.isEmpty(abnormalSituationBean.getResult().getG_type())) {
            img_label.setImageDrawable(null);
        } else if (abnormalSituationBean.getResult().getG_type().equals("often")) {
            img_label.setImageResource(R.mipmap.label_shishi);
        } else if (abnormalSituationBean.getResult().getG_type().equals("urgent")) {
            img_label.setImageResource(R.mipmap.label_jiaji);
        } else if (abnormalSituationBean.getResult().getG_type().equals("appoint")) {
            img_label.setImageResource(R.mipmap.label_yuyue);
        }
        if (!StringUtils.isEmpty(abnormalSituationBean.getResult().getContent())) {
            mAdapter.clear();
            List<ImageItem> list = new ArrayList<>();
            String[] img1 = abnormalSituationBean.getResult().getContent().split(",");
            if (img1 != null && img1.length > 0) {
                for (int i = 0; i < img1.length; i++) {
                    ImageItem imageItem = new ImageItem();
                    imageItem.path = img1[i];
                    list.add(imageItem);
                }
            }
            mAdapter.addMoreData(list);
        }
        tv_time.setText(abnormalSituationBean.getResult().getAbnormal_time());
        tv_goodName.setText(abnormalSituationBean.getResult().getGoods_name());
        tv_orderNumber.setText(abnormalSituationBean.getResult().getOrder_code());
        tv_thePlace.setText(abnormalSituationBean.getResult().getStart_address());
        tv_destination.setText(abnormalSituationBean.getResult().getEnd_address());
        tv_abnormalLocation.setText(abnormalSituationBean.getResult().getPlace());
        //  tv_abnormalTime.setText(abnormalSituationBean.getResult().getAbnormal_time());
        tv_abnormalReason.setText(abnormalSituationBean.getResult().getReason());
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        dismissLoadingDialog();
        if (!toLigon1(msg)) {
            finish();
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}