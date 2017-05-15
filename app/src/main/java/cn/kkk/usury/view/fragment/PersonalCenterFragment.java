package cn.kkk.usury.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.kkk.usury.Application.SharePreferenceUtils;
import cn.kkk.usury.R;
import cn.kkk.usury.utils.MFGT;

/**
 * Created by apple on 2017/4/29.
 */

public class PersonalCenterFragment extends Fragment {

    RelativeLayout mNameLayout;
    LinearLayout mInfolLayout;
    TextView mTvUserName;
    String name, telephone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_center,null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        SharePreferenceUtils.init(getContext());
        telephone   = SharePreferenceUtils.getInstance().getTelephone();
        name        = SharePreferenceUtils.getInstance().getName();

        showUserInfo();
    }

    private void showUserInfo() {
        mTvUserName.setText((name==null?(telephone==null?getString(R.string.username_default):telephone):name));
    }

    private void initView(View view) {
        mNameLayout = (RelativeLayout) view.findViewById(R.id.center_user_info);
        mInfolLayout = (LinearLayout) view.findViewById(R.id.layout_personal_info);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListener();


    }

//    public void onLoginPersonalInfo(View view) {
//        if (telephone==null) {
//            MFGT.gotoLogin(getActivity());
//            return;
//        }
//        switch (view.getId()) {
//            case R.id.center_user_info:
//                MFGT.gotoPersonalActivity(getActivity());
//                break;
//            case R.id.layout_personal_info:
//                MFGT.gotoPersonalActivity(getActivity());
//                break;
//        }
//
//    }

    private void setListener() {
        setOnLoginListener();
    }

    private void setOnLoginListener() {
        mNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (telephone==null) {
                    MFGT.gotoLogin(getActivity());
                }
                MFGT.gotoPersonalActivity(getActivity());
            }
        });
        mInfolLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (telephone==null) {
                    MFGT.gotoLogin(getActivity());
                }
                MFGT.gotoPersonalActivity(getActivity());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

}
