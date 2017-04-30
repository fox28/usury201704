package cn.kkk.usury.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.adapter.SortPicAdapter;
import cn.kkk.usury.model.bean.SortPicBean;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView mRV_SortPic;
    StaggeredGridLayoutManager layoutManager;
    SortPicAdapter mSortPicAdapter;
    ArrayList<SortPicBean> mArrayListSortPic;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        initData();
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRV_SortPic.setLayoutManager(layoutManager);
        mRV_SortPic.setHasFixedSize(true);
        mSortPicAdapter = new SortPicAdapter(getContext(), mArrayListSortPic);
        mRV_SortPic.setAdapter(mSortPicAdapter);


    }

    private void initView(View view) {
        mRV_SortPic = (RecyclerView) view.findViewById(R.id.RV_LoanSortPicture);
    }

    private void initData() {
        mArrayListSortPic = new ArrayList<>();
        int[] photoIs = {
                R.drawable.baidu_safe, R.drawable.baidu_safe, R.drawable.baidu_safe,
                R.drawable.baidu_safe, R.drawable.baidu_safe, R.drawable.baidu_safe
        };
        for(int i=0;i<photoIs.length;i++) {
            SortPicBean app = new SortPicBean( photoIs[i]);
            mArrayListSortPic.add(app);
        }
    }
}
