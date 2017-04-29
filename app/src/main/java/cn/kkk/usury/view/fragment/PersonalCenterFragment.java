package cn.kkk.usury.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kkk.usury.R;

/**
 * Created by apple on 2017/4/29.
 */

public class PersonalCenterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_center,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
