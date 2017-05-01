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
import cn.kkk.usury.adapter.FastLoanAdapter;
import cn.kkk.usury.adapter.SortPicAdapter;
import cn.kkk.usury.model.bean.AppBean;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView mRV_SortPic, mRV_fastRecommend, mRV_fastStrategy;
    StaggeredGridLayoutManager manager;
    SortPicAdapter mSortPicAdapter;
    FastLoanAdapter mFastLoanAdapter;
    ArrayList<AppBean> mArrayListSortPic;



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

        // 8张分类图片
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRV_SortPic.setLayoutManager(manager);
        mRV_SortPic.setHasFixedSize(true);
        mSortPicAdapter = new SortPicAdapter(getContext(), mArrayListSortPic);
        mRV_SortPic.setAdapter(mSortPicAdapter);

        // 速贷推荐
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        mRV_fastRecommend.setLayoutManager(manager);
        mRV_SortPic.setHasFixedSize(true);
        mFastLoanAdapter = new FastLoanAdapter(getContext(), mArrayListSortPic);
        mRV_fastRecommend.setAdapter(mFastLoanAdapter);

        // 速贷攻略
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRV_fastStrategy.setLayoutManager(manager);
        mRV_fastStrategy.setHasFixedSize(true);

    }

    private void initView(View view) {
        mRV_SortPic = (RecyclerView) view.findViewById(R.id.RV_LoanSortPicture);
        mRV_fastRecommend = (RecyclerView) view.findViewById(R.id.RV_fastRecommend);
        mRV_fastStrategy = (RecyclerView) view.findViewById(R.id.RV_fastStrategy);
    }

    private void initData() {
        mArrayListSortPic = new ArrayList<>();
        int[] photoIs = {
                R.drawable.baidu_safe, R.drawable.baidu_safe, R.drawable.baidu_safe,
                R.drawable.baidu_safe, R.drawable.baidu_safe, R.drawable.baidu_safe
        };
        for(int i=0;i<photoIs.length;i++) {
            AppBean app = new AppBean( photoIs[i]);
            app.setName("叮当贷");
            mArrayListSortPic.add(app);
        }
    }
}
