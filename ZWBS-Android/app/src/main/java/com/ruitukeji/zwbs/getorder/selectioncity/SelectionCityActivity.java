package com.ruitukeji.zwbs.getorder.selectioncity;

import android.content.Intent;

import com.kymjs.common.Log;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

import static com.ruitukeji.zwbs.constant.NumericConstants.REQUEST_CODE_PHOTO_PREVIEW;

/**
 * 选择城市
 * Created by Administrator on 2017/11/27.
 */

public class SelectionCityActivity extends BaseActivity {

    private BaseFragment contentFragment;
    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_selectioncity);
    }


    @Override
    public void initData() {
        super.initData();
        chageIcon = 0;
        contentFragment = new InlandFragment();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.selectCity), true, R.id.titlebar);
        changeFragment(contentFragment);
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
        int newChageIcon = intent.getIntExtra("newChageIcon", 0);
        Log.d("newChageIcon", newChageIcon + "");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PHOTO_PREVIEW && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            String selectCountry = data.getStringExtra("selectCountry");
            int selectCountryId = data.getIntExtra("selectCountryId", 0);
            android.util.Log.d("tag888", selectCity);
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("selectCity", selectCity);
            intent.putExtra("selectCityId", selectCityId);
            intent.putExtra("selectCountry", selectCountry);
            intent.putExtra("selectCountryId", selectCountryId);
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }
}
