package cn.kkk.usury.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.adapter.FastRecommendAdapter;
import cn.kkk.usury.adapter.FastStrategyAdapter;
import cn.kkk.usury.adapter.NewGoodsAdapter;
import cn.kkk.usury.adapter.SortPicAdapter;
import cn.kkk.usury.adapter.SpecialOffersAdapter;
import cn.kkk.usury.model.bean.AppBean;
import cn.kkk.usury.model.dao.AppDao;
import cn.kkk.usury.view.widget.FlowIndicator;
import cn.kkk.usury.view.widget.SlideLoopView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView mRV_SortPic, mRV_fastRecommend, mRV_fastStrategy, mRV_SpecialOffers, mRV_NewGoods;
    StaggeredGridLayoutManager manager;
    LinearLayoutManager linearManager;
    SortPicAdapter mSortPicAdapter;
    FastRecommendAdapter mFastRecommendAdapter;
    FastStrategyAdapter mFastStrategyAdapter;
    SpecialOffersAdapter mSpecialOffersAdapter;
    NewGoodsAdapter mNewGoodsAdapter;
    ArrayList<AppBean> mArrayList;

    SlideLoopView mSlideLoopView;
    FlowIndicator mFlowIndicator;
    ArrayList<String> mSlideList;


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

        // 轮播图
        mSlideLoopView.startPlay(getContext(),mFlowIndicator,mSlideList);

        // 8张分类图片
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRV_SortPic.setLayoutManager(manager);
        mRV_SortPic.setHasFixedSize(true);
        mSortPicAdapter = new SortPicAdapter(getContext(), mArrayList);
        mRV_SortPic.setAdapter(mSortPicAdapter);

        // 速贷推荐
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        mRV_fastRecommend.setLayoutManager(manager);
        mRV_SortPic.setHasFixedSize(true);
        mFastRecommendAdapter = new FastRecommendAdapter(getContext(), mArrayList);
        mRV_fastRecommend.setAdapter(mFastRecommendAdapter);

        // 速贷攻略
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRV_fastStrategy.setLayoutManager(manager);
        mRV_fastStrategy.setHasFixedSize(true);
        mFastStrategyAdapter = new FastStrategyAdapter(getContext(), mArrayList);
        mRV_fastStrategy.setAdapter(mFastStrategyAdapter);

        // 优惠活动
        linearManager = new LinearLayoutManager(getContext());
        mRV_SpecialOffers.setLayoutManager(linearManager);
        mRV_SpecialOffers.setHasFixedSize(true);
        mSpecialOffersAdapter = new SpecialOffersAdapter(getContext(), mArrayList);
        mRV_SpecialOffers.setAdapter(mSpecialOffersAdapter);

        // 新产品上线
        linearManager = new LinearLayoutManager(getContext());
        mRV_NewGoods.setLayoutManager(linearManager);
        mRV_NewGoods.setHasFixedSize(true);
        mNewGoodsAdapter = new NewGoodsAdapter(getContext(), mArrayList);
        mRV_NewGoods.setAdapter(mNewGoodsAdapter);


    }

    private void initView(View view) {
        mRV_SortPic = (RecyclerView) view.findViewById(R.id.RV_LoanSortPicture);
        mRV_fastRecommend = (RecyclerView) view.findViewById(R.id.RV_fastRecommend);
        mRV_fastStrategy = (RecyclerView) view.findViewById(R.id.RV_fastStrategy);
        mRV_SpecialOffers = (RecyclerView) view.findViewById(R.id.RV_specialOffers);
        mRV_NewGoods = (RecyclerView) view.findViewById(R.id.RV_NewGoods);

        mSlideLoopView = (SlideLoopView) view.findViewById(R.id.slv_homeFragment);
        mFlowIndicator = (FlowIndicator) view.findViewById(R.id.flowIndicator_homeFragment);
    }

    private void initData() {
        mArrayList = AppDao.getData(getContext());

        mSlideList = new ArrayList<>();
        mSlideList.add("goods01.png");
        mSlideList.add("goods02.png");
        mSlideList.add("goods03.png");
        mSlideList.add("goods04.png");
        mSlideList.add("goods05.png");

    }
}
