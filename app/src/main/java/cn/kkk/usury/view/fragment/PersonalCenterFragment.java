package cn.kkk.usury.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.kkk.usury.R;
import cn.kkk.usury.utils.MFGT;

/**
 * Created by apple on 2017/4/29.
 */

public class PersonalCenterFragment extends Fragment {

    RelativeLayout mRelativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_center,null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListener();


    }

    private void setListener() {
        setOnLoginListener();
    }

    private void setOnLoginListener() {
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.gotoLogin(getActivity());
            }
        });
    }

    private void initView(View view) {
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.center_user_info);
    }

}
