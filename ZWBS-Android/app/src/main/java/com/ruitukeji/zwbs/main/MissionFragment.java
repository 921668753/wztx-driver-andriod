package com.ruitukeji.zwbs.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseFragment;

/**
 * 任务
 * Created by Administrator on 2017/11/29.
 */

public class MissionFragment extends BaseFragment {

    private MainActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mission, null);
    }

}
