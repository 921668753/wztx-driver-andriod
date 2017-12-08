package com.ruitukeji.zwbs.mine.mywallet.mybankcard;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.mine.MyBankCardViewAdapter;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.myview.ChildLiistView;

/**
 * 我的银行卡
 * Created by Administrator on 2017/11/30.
 */

public class MyBankCardActivity extends BaseActivity implements MyBankCardContract.View, AdapterView.OnItemClickListener {

    /**
     * 银行卡列表
     */
    @BindView(id = R.id.lv_bankCard)
    private ChildLiistView lv_bankCard;


    private MyBankCardViewAdapter myBankCardViewAdapter;


    /**
     * 添加银行卡
     */
    @BindView(id = R.id.ll_addBankCard, click = true)
    private LinearLayout ll_addBankCard;

    private String bankCardName = "";
    private String bankCardNun = "";
    private String bankCardId = "";
    private int type = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mybankcard);
    }


    @Override
    public void initData() {
        super.initData();
        myBankCardViewAdapter = new MyBankCardViewAdapter(this);
        mPresenter = new MyBankCardPresenter(this);
        type = getIntent().getIntExtra("type", 0);
        bankCardName = getIntent().getStringExtra("bankCardName");
        bankCardNun = getIntent().getStringExtra("bankCardNun");
        bankCardId = getIntent().getStringExtra("bankCardId");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myBankCard), true, R.id.titlebar);
        lv_bankCard.setOnItemClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_addBankCard:
                showActivity(aty, AddBankCardActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type < 1) {
            return;
        }
        if (type == 1) {
            myBankCardViewAdapter.getItem(position);
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("bankCardName", bankCardName);
            intent.putExtra("bankCardNun", bankCardNun);
            intent.putExtra("bankCardId", bankCardId);
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
            return;
        }
    }

    @Override
    public void setPresenter(MyBankCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {

    }
}
