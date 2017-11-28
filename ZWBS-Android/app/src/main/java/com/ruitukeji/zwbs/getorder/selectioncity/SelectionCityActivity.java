package com.ruitukeji.zwbs.getorder.selectioncity;

import android.content.Intent;
import android.view.View;

import com.kymjs.common.Log;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BaseFragment;

/**
 * 选择城市
 * Created by Administrator on 2017/11/27.
 */

public class SelectionCityActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_selectioncity);
    }


    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public void initWidget() {
        super.initWidget();

        
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }


    /**
     * Activity的启动模式变为singleTask
     * 调用onNewIntent(Intent intent)方法。
     * Fragment调用的时候，一定要在onResume方法中。
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int newChageIcon = intent.getIntExtra("newChageIcon", 2);
        Log.d("newChageIcon", newChageIcon + "");

    }

}
